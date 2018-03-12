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

package com.pizzaordering.ingredient.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pizzaordering.ingredient.domain.Deal;
import com.pizzaordering.ingredient.domain.Order;
import com.pizzaordering.ingredient.entity.Ingredient;
import com.pizzaordering.ingredient.service.IngredientService;

/**
 * Resource to expose ingredient operations and handle ingredient requests.
 * 
 * @author Rafael Lima Costa
 *
 */
@RestController
@RequestMapping("/ingredients")
public class IngredientController {
	
	/**
	 * Interface of ingredient service layer.
	 */
	@Autowired
	IngredientService ingredientService;
	
	/**
	 * Operation for adding an ingredient.
	 * 
	 * @param ingredient Ingredient to be added on database.
	 * @return Ingredient added on database.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
		return ingredientService.addIngredient(ingredient);
	}
	
	/**
	 * Operation for getting an ingredient.
	 * 
	 * @param id Id of ingredient to be gotten from database.
	 * @return Ingredient gotten from database.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Ingredient getIngredient(@PathVariable Long id) {
		return ingredientService.getIngredient(id);
	}
	
	/**
	 * Operation for updating an ingredient.
	 * 
	 * @param ingredient Ingredient to be updated on database.
	 * @return Ingredient updated on database.
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public Ingredient updateIngredient(@RequestBody Ingredient ingredient) {
		return ingredientService.updateIngredient(ingredient);
	}
	
	/**
	 * Operation for deleting an ingredient.
	 * 
	 * @param id Id of ingredient to be deleted from database.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteIngredient(@PathVariable Long id) {
		ingredientService.deleteIngredient(id);
	}
	
	/**
	 * Operation for calculating the price of an order with customized ingredients.
	 * 
	 * @param order Order with customized ingredients to be calculated.
	 * @return Price of the order with customized ingredients.
	 */
	@RequestMapping(value = "/calculateOrderPrice", method = RequestMethod.POST)
	public BigDecimal calculateOrderPrice(@RequestBody Order order) {
		return ingredientService.calculateOrderPrice(order);
	}
	
	/**
	 * Operation for reversing ingredients from inventory of a canceled order with customized ingredients.
	 * 
	 * @param order Order with customized ingredients to be reversed.
	 */
	@RequestMapping(value = "/reverseOrderIngredients", method = RequestMethod.PUT)
	public void reverseOrderIngredients(@RequestBody Order order) {
		ingredientService.reverseOrderIngredients(order);
	}
	
	/**
	 * Operation for adding ingredients discount of a deal.
	 * 
	 * @param deal Deal with discount ingredients to be added.
	 */
	@RequestMapping(value = "/addIngredientDiscount", method = RequestMethod.PUT)
	public void addIngredientDiscount(@RequestBody Deal deal) {
		ingredientService.addIngredientDiscount(deal);
	}
	
	/**
	 * Operation for deleting ingredients discount of a deal.
	 * 
	 * @param deal Deal with discount ingredients to be deleted.
	 */
	@RequestMapping(value = "/deleteIngredientDiscount", method = RequestMethod.PUT)
	public void deleteIngredientDiscount(@RequestBody Deal deal) {
		ingredientService.deleteIngredientDiscount(deal);
	}
}