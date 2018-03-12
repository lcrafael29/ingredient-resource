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

import com.pizzaordering.ingredient.util.PizzaSizeMultiplyFactorEnum;

/**
 * Closed recipe ingredient domain.
 * 
 * @author Rafael Lima Costa
 *
 */
public class ClosedRecipeIngredient implements Serializable {
	
	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Composite id of closed recipe ingredient.
	 */
	private ClosedRecipeIngredientId closedRecipeIngredientId;
	
	/**
	 * Quantity of portion.
	 */
	private Integer portionQuantity;
	
	/**
	 * Constructor.
	 */
	public ClosedRecipeIngredient() {
	}

	/**
	 * Get composite id of closed recipe ingredient.
	 * 
	 * @return Composite id of closed recipe ingredient.
	 */
	public ClosedRecipeIngredientId getClosedRecipeIngredientId() {
		return closedRecipeIngredientId;
	}

	/**
	 * Set composite id of closed recipe ingredient.
	 * 
	 * @param closedRecipeIngredientId Composite id of closed recipe ingredient.
	 */
	public void setClosedRecipeIngredientId(ClosedRecipeIngredientId closedRecipeIngredientId) {
		this.closedRecipeIngredientId = closedRecipeIngredientId;
	}

	/**
	 * Get quantity of portion.
	 * 
	 * @return Quantity of portion.
	 */
	public Integer getPortionQuantity() {
		return portionQuantity;
	}
	
	/**
	 * Get quantity of portion proportional to pizza size.
	 * 
	 * @param pizzaSize Size of pizza.
	 * @return Quantity of portion proportional to pizza size.
	 */
	public Integer getPortionQuantity(Character pizzaSize) {
		return portionQuantity * PizzaSizeMultiplyFactorEnum.getMultiplyFactor(pizzaSize);
	}

	/**
	 * Set quantity of portion.
	 * 
	 * @param portionQuantity Quantity of portion.
	 */
	public void setPortionQuantity(Integer portionQuantity) {
		this.portionQuantity = portionQuantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((closedRecipeIngredientId == null) ? 0 : closedRecipeIngredientId.hashCode());
		result = prime * result + ((portionQuantity == null) ? 0 : portionQuantity.hashCode());
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
		ClosedRecipeIngredient other = (ClosedRecipeIngredient) obj;
		if (closedRecipeIngredientId == null) {
			if (other.closedRecipeIngredientId != null)
				return false;
		} else if (!closedRecipeIngredientId.equals(other.closedRecipeIngredientId))
			return false;
		if (portionQuantity == null) {
			if (other.portionQuantity != null)
				return false;
		} else if (!portionQuantity.equals(other.portionQuantity))
			return false;
		return true;
	}
}