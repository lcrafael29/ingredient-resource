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

import com.pizzaordering.ingredient.domain.ClosedRecipe;

/**
 * Interface of closed recipe client service layer.
 * 
 * @author Rafael Lima Costa
 *
 */
public interface ClosedRecipeClientService {
	
	/**
	 * Operation for getting closed recipe consuming closed recipe resource via HTTP REST.
	 * 
	 * @param closedRecipeId Id of closed recipe to be gotten from service.
	 * @return Closed recipe gotten from service.
	 */
	public ClosedRecipe getClosedRecipe(Integer closedRecipeId);
}