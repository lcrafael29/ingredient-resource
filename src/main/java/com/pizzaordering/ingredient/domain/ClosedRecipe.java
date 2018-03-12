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

package com.pizzaordering.ingredient.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * Closed recipe domain.
 * 
 * @author Rafael Lima Costa
 *
 */
public class ClosedRecipe implements Serializable {
	
	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Id.
	 */
	private Integer id;
	
	/**
	 * Description.
	 */
	private String description;
	
	/**
	 * Map of closed recipe ingredients, using ingredient id as key and closed recipe ingredient as value.
	 */
	private Map<Long, ClosedRecipeIngredient> closedRecipeIngredientMap;
	
	/**
	 * Constructor.
	 */
	public ClosedRecipe() {
	}

	/**
	 * Get id of closed recipe.
	 * 
	 * @return Id of closed recipe.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set id of closed recipe.
	 * 
	 * @param id Id of closed recipe.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Get description.
	 * 
	 * @return Description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set description.
	 * 
	 * @param description Description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get map of closed recipe ingredient.
	 * 
	 * @return Map of closed recipe ingredient.
	 */
	public Map<Long, ClosedRecipeIngredient> getClosedRecipeIngredientMap() {
		return closedRecipeIngredientMap;
	}

	/**
	 * Set map of closed recipe ingredient.
	 * 
	 * @param closedRecipeIngredientMap Map of closed recipe ingredient.
	 */
	public void setClosedRecipeIngredientMap(Map<Long, ClosedRecipeIngredient> closedRecipeIngredientMap) {
		this.closedRecipeIngredientMap = closedRecipeIngredientMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClosedRecipe other = (ClosedRecipe) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}