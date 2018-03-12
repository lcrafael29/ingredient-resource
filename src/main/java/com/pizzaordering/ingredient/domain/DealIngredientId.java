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

/**
 * Deal ingredient composite id.
 * 
 * @author Rafael Lima Costa
 *
 */
public class DealIngredientId implements Serializable {
	
	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Id of ingredient.
	 */
	private Long ingredientId;
	
	/**
	 * Deal.
	 */
	private Deal deal;
	
	/**
	 * Constructor.
	 */
	public DealIngredientId() {
	}

	/**
	 * Get id of ingredient.
	 * 
	 * @return Id of ingredient.
	 */
	public Long getIngredientId() {
		return ingredientId;
	}

	/**
	 * Set id of ingredient.
	 * 
	 * @param ingredientId Id of ingredient.
	 */
	public void setIngredientId(Long ingredientId) {
		this.ingredientId = ingredientId;
	}

	/**
	 * Get deal.
	 * 
	 * @return Deal.
	 */
	public Deal getDeal() {
		return deal;
	}

	/**
	 * Set deal.
	 * 
	 * @param deal Closed recipe.
	 */
	public void setDeal(Deal deal) {
		this.deal = deal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deal == null) ? 0 : deal.hashCode());
		result = prime * result + ((ingredientId == null) ? 0 : ingredientId.hashCode());
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
		DealIngredientId other = (DealIngredientId) obj;
		if (deal == null) {
			if (other.deal != null)
				return false;
		} else if (!deal.equals(other.deal))
			return false;
		if (ingredientId == null) {
			if (other.ingredientId != null)
				return false;
		} else if (!ingredientId.equals(other.ingredientId))
			return false;
		return true;
	}
}