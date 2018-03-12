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

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pizzaordering.ingredient.domain.ClosedRecipe;
import com.pizzaordering.ingredient.service.ClosedRecipeClientService;

/**
 * Implementation of closed recipe client service layer interface.
 * 
 * @author Rafael Lima Costa
 *
 */
@Service
public class ClosedRecipeClientServiceImpl implements ClosedRecipeClientService {
	
	/**
	 * URI of closed recipe resource.
	 */
	private static final String URI_CLOSED_RECIPE_RESOURCE = "http://localhost:8082/closedrecipes";
	
	/**
	 * Spring implementation for HTTP RESTful resources consummation.
	 */
	private RestTemplate restTemplate = new RestTemplate();
	
	/**
	 * Consume closed recipe resource sending id of closed recipe to be gotten.
	 * 
	 * @param closedRecipeId Id of closed recipe to be gotten from service.
	 * @return Closed recipe gotten from service.
	 */
	@Override
	public ClosedRecipe getClosedRecipe(Integer closedRecipeId) {
		return restTemplate.getForObject(URI_CLOSED_RECIPE_RESOURCE + "/" + closedRecipeId, ClosedRecipe.class);
	}
}