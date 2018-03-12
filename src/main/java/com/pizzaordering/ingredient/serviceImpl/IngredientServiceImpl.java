/**
 * Pizza Ordering Application
 * 
 * HTTP REST Microservices that handle ordering, deals and inventory
 * 
 * FastSpring Coding Challenge
 * 
 * Rafael Lima Costa
 * March of 2018
 * Santa Barbara, CA, USA
 */

package com.pizzaordering.ingredient.serviceImpl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzaordering.ingredient.data.IngredientRepository;
import com.pizzaordering.ingredient.domain.ClosedRecipe;
import com.pizzaordering.ingredient.domain.ClosedRecipeIngredient;
import com.pizzaordering.ingredient.domain.Deal;
import com.pizzaordering.ingredient.domain.DealIngredient;
import com.pizzaordering.ingredient.domain.Order;
import com.pizzaordering.ingredient.domain.OrderCustomization;
import com.pizzaordering.ingredient.entity.Ingredient;
import com.pizzaordering.ingredient.service.ClosedRecipeClientService;
import com.pizzaordering.ingredient.service.IngredientService;
import com.pizzaordering.ingredient.util.OrderCustomizationTypeEnum;

/**
 * Implementation of ingredient service layer interface.
 * 
 * @author Rafael Lima Costa
 *
 */
@Service
public class IngredientServiceImpl implements IngredientService {
	
	/**
	 * Interface of closed recipe client service layer.
	 */
	@Autowired
	ClosedRecipeClientService closedRecipeClientService;
	
	/**
	 * Interface of ingredient repository layer.
	 */
	@Autowired
	IngredientRepository ingredientRepository;
	
	/*
	 * Save ingredient on database.
	 */
	public Ingredient addIngredient(Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}
	
	/*
	 * Get ingredient from database.
	 */
	public Ingredient getIngredient(Long id) {
		return ingredientRepository.findById(id).get();
	}
	
	/*
	 * Update ingredient on database.
	 */
	public Ingredient updateIngredient(Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}
	
	/*
	 * Delete ingredient from database.
	 */
	public void deleteIngredient(Long id) {
		ingredientRepository.deleteById(id);
	}
	
	/*
	 * Flow 1) Calculate order price from closed recipe ingredients without customized ingredients:
	 * > Load closed recipe ingredients from HTTP REST service if closed recipe identifier is on request.
	 * > Calculate total price of closed recipe ingredients always considering the pizza size multiply factor and discount deals.
	 * > Decrement ingredients inventory quantity always considering the pizza size multiply factor.
	 * 
	 * Flow 2) Calculate order price from customized ingredients without closed recipe:
	 * > Calculate total price of customized ingredients always considering the pizza size multiply factor and discount deals.
	 * > Decrement ingredients inventory quantity always considering the pizza size multiply factor.
	 * 
	 * Flow 3) Flow 1 + Flow 2
	 */
	@Override
	public BigDecimal calculateOrderPrice(Order order) {
		ClosedRecipe closedRecipe = null;
		ClosedRecipeIngredient closedRecipeIngredient = null;
		Boolean ingredientRemoved = false;
		OrderCustomization orderCustomization = null;
		Ingredient ingredient = null;
		BigDecimal ingredientPortionPrice = null;
		Integer portionQuantityCalculated = null;
		BigDecimal totalPrice = BigDecimal.ZERO;
		
		// Flow 1.
		if (order.getClosedRecipeId() != null) {
			closedRecipe = closedRecipeClientService.getClosedRecipe(order.getClosedRecipeId());
			
			for (Long closedRecipeIngredientMapId : closedRecipe.getClosedRecipeIngredientMap().keySet()) {
				closedRecipeIngredient = closedRecipe.getClosedRecipeIngredientMap().get(closedRecipeIngredientMapId);
				
				ingredientRemoved = false;
				
				if (order.getOrderCustomizationMap() != null) {
					orderCustomization = order.getOrderCustomizationMap().get(closedRecipeIngredientMapId);
				}
				
				// Consider removed ingredients (partial or total) on price calculation and inventory update.
				if (orderCustomization != null && OrderCustomizationTypeEnum.REMOVE.getType().equals(
						orderCustomization.getType().toString().toUpperCase())) {
					if (orderCustomization.getPortionQuantity(
							order.getSize()) < closedRecipeIngredient.getPortionQuantity(order.getSize())) {
						ingredient = ingredientRepository.findById(closedRecipeIngredientMapId).get();
						
						// Calculate discount.
						if (ingredient.getPercentageDiscount() != null
								&& ingredient.getPercentageDiscount().compareTo(BigDecimal.ZERO) > 0) {
							ingredientPortionPrice = ingredient.getPortionPrice().multiply(
									BigDecimal.valueOf(100).subtract(ingredient.getPercentageDiscount()).divide(BigDecimal.valueOf(100)));
						} else {
							ingredientPortionPrice = ingredient.getPortionPrice();
						}
						
						// Calculate portion quantity considering pizza size.
						portionQuantityCalculated = closedRecipeIngredient.getPortionQuantity(
								order.getSize()) - orderCustomization.getPortionQuantity(order.getSize());
						
						// Calculate price.
						totalPrice = totalPrice.add(ingredientPortionPrice.multiply(BigDecimal.valueOf(portionQuantityCalculated)));
						
						// Update inventory.
						ingredient.setPortionQuantity(ingredient.getPortionQuantity() - portionQuantityCalculated);
						
						ingredientRepository.save(ingredient);
					}
					
					ingredientRemoved = true;
				}
				
				if (!ingredientRemoved) {
					ingredient = ingredientRepository.findById(closedRecipeIngredientMapId).get();
					
					// Calculate discount.
					if (ingredient.getPercentageDiscount() != null
							&& ingredient.getPercentageDiscount().compareTo(BigDecimal.ZERO) > 0) {
						ingredientPortionPrice = ingredient.getPortionPrice().multiply(
								BigDecimal.valueOf(100).subtract(ingredient.getPercentageDiscount()).divide(BigDecimal.valueOf(100)));
					} else {
						ingredientPortionPrice = ingredient.getPortionPrice();
					}
					
					// Calculate portion quantity considering pizza size.
					portionQuantityCalculated = closedRecipeIngredient.getPortionQuantity(order.getSize());
					
					// Calculate price.
					totalPrice = totalPrice.add(ingredientPortionPrice.multiply(BigDecimal.valueOf(portionQuantityCalculated)));
					
					// Update inventory.
					ingredient.setPortionQuantity(ingredient.getPortionQuantity() - portionQuantityCalculated);
					
					ingredientRepository.save(ingredient);
				}
			}
		}
		
		// Flow 2.
		if (order.getOrderCustomizationMap() != null) {
			for (Long orderCustomizationMapId : order.getOrderCustomizationMap().keySet()) {
				orderCustomization = order.getOrderCustomizationMap().get(orderCustomizationMapId);
				
				if (OrderCustomizationTypeEnum.ADD.getType().equals(
						orderCustomization.getType().toString().toUpperCase())) {
					ingredient = ingredientRepository.findById(orderCustomizationMapId).get();
					
					// Calculate discount.
					if (ingredient.getPercentageDiscount() != null
							&& ingredient.getPercentageDiscount().compareTo(BigDecimal.ZERO) > 0) {
						ingredientPortionPrice = ingredient.getPortionPrice().multiply(
								BigDecimal.valueOf(100).subtract(ingredient.getPercentageDiscount()).divide(BigDecimal.valueOf(100)));
					} else {
						ingredientPortionPrice = ingredient.getPortionPrice();
					}
					
					// Calculate portion quantity considering pizza size.
					portionQuantityCalculated = orderCustomization.getPortionQuantity(order.getSize());
					
					// Calculate price.
					totalPrice = totalPrice.add(ingredientPortionPrice.multiply(BigDecimal.valueOf(portionQuantityCalculated)));
					
					// Update inventory.
					ingredient.setPortionQuantity(ingredient.getPortionQuantity() - portionQuantityCalculated);
					
					ingredientRepository.save(ingredient);
				}
			}
		}
		
		return totalPrice.setScale(2);
	}
	
	/*
	 * Flow 1) Update ingredients inventory from closed recipe ingredients without customized ingredients:
	 * > Load closed recipe ingredients from HTTP REST service if closed recipe identifier is on request.
	 * > Increment ingredients inventory quantity always considering the pizza size multiply factor.
	 * 
	 * Flow 2) Update ingredients inventory from customized ingredients without closed recipe:
	 * > Increment ingredients inventory quantity always considering the pizza size multiply factor.
	 * 
	 * Flow 3) Flow 1 + Flow 2
	 */
	@Override
	public void reverseOrderIngredients(Order order) {
		ClosedRecipe closedRecipe = null;
		ClosedRecipeIngredient closedRecipeIngredient = null;
		Boolean ingredientRemoved = false;
		OrderCustomization orderCustomization = null;
		Ingredient ingredient = null;
		Integer portionQuantityCalculated = null;
		
		// Flow 1.
		if (order.getClosedRecipeId() != null) {
			closedRecipe = closedRecipeClientService.getClosedRecipe(order.getClosedRecipeId());
			
			for (Long closedRecipeIngredientMapId : closedRecipe.getClosedRecipeIngredientMap().keySet()) {
				closedRecipeIngredient = closedRecipe.getClosedRecipeIngredientMap().get(closedRecipeIngredientMapId);
				
				ingredientRemoved = false;
				
				if (order.getOrderCustomizationMap() != null) {
					orderCustomization = order.getOrderCustomizationMap().get(closedRecipeIngredientMapId);
				}
				
				// Consider removed ingredients (partial or total) on inventory update.
				if (orderCustomization != null && OrderCustomizationTypeEnum.REMOVE.getType().equals(
						orderCustomization.getType().toString().toUpperCase())) {
					if (orderCustomization.getPortionQuantity(
							order.getSize()) < closedRecipeIngredient.getPortionQuantity(order.getSize())) {
						ingredient = ingredientRepository.findById(closedRecipeIngredientMapId).get();
						
						// Calculate portion quantity considering pizza size.
						portionQuantityCalculated = closedRecipeIngredient.getPortionQuantity(
								order.getSize()) - orderCustomization.getPortionQuantity(order.getSize());
						
						// Update inventory.
						ingredient.setPortionQuantity(ingredient.getPortionQuantity() + portionQuantityCalculated);
						
						ingredientRepository.save(ingredient);
					}
					
					ingredientRemoved = true;
				}
				
				if (!ingredientRemoved) {
					ingredient = ingredientRepository.findById(closedRecipeIngredientMapId).get();
					
					// Calculate portion quantity considering pizza size.
					portionQuantityCalculated = closedRecipeIngredient.getPortionQuantity(order.getSize());
					
					// Update inventory.
					ingredient.setPortionQuantity(ingredient.getPortionQuantity() + portionQuantityCalculated);
					
					ingredientRepository.save(ingredient);
				}
			}
		}
		
		// Flow 2.
		if (order.getOrderCustomizationMap() != null) {
			for (Long orderCustomizationMapId : order.getOrderCustomizationMap().keySet()) {
				orderCustomization = order.getOrderCustomizationMap().get(orderCustomizationMapId);
				
				if (OrderCustomizationTypeEnum.ADD.getType().equals(
						orderCustomization.getType().toString().toUpperCase())) {
					ingredient = ingredientRepository.findById(orderCustomizationMapId).get();
					
					// Calculate portion quantity considering pizza size.
					portionQuantityCalculated = orderCustomization.getPortionQuantity(order.getSize());
					
					// Update inventory.
					ingredient.setPortionQuantity(ingredient.getPortionQuantity() + portionQuantityCalculated);
					
					ingredientRepository.save(ingredient);
				}
			}
		}
	}
	
	/*
	 * Add ingredients discount.
	 */
	@Override
	public void addIngredientDiscount(Deal deal) {
		DealIngredient dealIngredient = null;
		Ingredient ingredient = null;
		BigDecimal percentageDiscount = null;
		
		if (deal.getDealIngredientMap() != null) {
			for (Long dealIngredientMapId : deal.getDealIngredientMap().keySet()) {
				dealIngredient = deal.getDealIngredientMap().get(dealIngredientMapId);
				
				ingredient = ingredientRepository.findById(dealIngredientMapId).get();
				
				if (ingredient.getPercentageDiscount() != null) {
					percentageDiscount = ingredient.getPercentageDiscount();
				} else {
					percentageDiscount = BigDecimal.ZERO;
				}
				
				ingredient.setPercentageDiscount(percentageDiscount.add(
						dealIngredient.getDiscountPercentage()));
				
				ingredientRepository.save(ingredient);
			}
		}
	}
	
	/*
	 * Delete ingredients discount.
	 */
	@Override
	public void deleteIngredientDiscount(Deal deal) {
		DealIngredient dealIngredient = null;
		Ingredient ingredient = null;
		
		if (deal.getDealIngredientMap() != null) {
			for (Long dealIngredientMapId : deal.getDealIngredientMap().keySet()) {
				dealIngredient = deal.getDealIngredientMap().get(dealIngredientMapId);
				
				ingredient = ingredientRepository.findById(dealIngredientMapId).get();
				
				if (ingredient.getPercentageDiscount() != null && ingredient.getPercentageDiscount().compareTo(BigDecimal.ZERO) > 0) {
					ingredient.setPercentageDiscount(ingredient.getPercentageDiscount().subtract(
							dealIngredient.getDiscountPercentage()));
					
					ingredientRepository.save(ingredient);
				}
			}
		}
	}
}