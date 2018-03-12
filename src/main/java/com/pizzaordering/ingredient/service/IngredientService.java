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

package com.pizzaordering.ingredient.service;

import java.math.BigDecimal;

import com.pizzaordering.ingredient.domain.Deal;
import com.pizzaordering.ingredient.domain.Order;
import com.pizzaordering.ingredient.entity.Ingredient;

/**
 * Interface of ingredient service layer.
 * 
 * @author Rafael Lima Costa
 *
 */
public interface IngredientService {
	
	/**
	 * Operation for adding an ingredient.
	 * 
	 * @param ingredient Ingredient to be added on database.
	 * @return Ingredient added on database.
	 */
	public Ingredient addIngredient(Ingredient ingredient);
	
	/**
	 * Operation for getting an ingredient.
	 * 
	 * @param id Id of ingredient to be gotten from database.
	 * @return Ingredient gotten from database.
	 */
	public Ingredient getIngredient(Long id);
	
	/**
	 * Operation for updating an ingredient.
	 * 
	 * @param ingredient Ingredient to be updated on database.
	 * @return Ingredient updated on database.
	 */
	public Ingredient updateIngredient(Ingredient ingredient);
	
	/**
	 * Operation for deleting an ingredient.
	 * 
	 * @param id Id of ingredient to be deleted from database.
	 */
	public void deleteIngredient(Long id);
	
	/**
	 * Operation for calculating the price of an order with customized ingredients.
	 * 
	 * @param order Order with customized ingredients to be calculated.
	 * @return Price of the order with customized ingredients.
	 */
	public BigDecimal calculateOrderPrice(Order order);
	
	/**
	 * Operation for reversing ingredients from inventory of a canceled order with customized ingredients.
	 * 
	 * @param order Order with customized ingredients to be reversed.
	 */
	public void reverseOrderIngredients(Order order);
	
	/**
	 * Operation for adding ingredients discount of a deal.
	 * 
	 * @param deal Deal with discount ingredients to be added.
	 */
	public void addIngredientDiscount(Deal deal);
	
	/**
	 * Operation for deleting ingredients discount of a deal.
	 * 
	 * @param deal Deal with discount ingredients to be deleted.
	 */
	public void deleteIngredientDiscount(Deal deal);
}