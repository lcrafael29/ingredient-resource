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
import java.math.BigDecimal;

/**
 * Deal ingredient domain.
 * 
 * @author Rafael Lima Costa
 *
 */
public class DealIngredient implements Serializable {
	
	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Composite id of deal ingredient.
	 */
	private DealIngredientId dealIngredientId;
	
	/**
	 * Percentage of discount.
	 */
	private BigDecimal discountPercentage;
	
	/**
	 * Constructor.
	 */
	public DealIngredient() {
	}

	/**
	 * Get composite id of deal ingredient.
	 * 
	 * @return Composite id of deal ingredient.
	 */
	public DealIngredientId getDealIngredientId() {
		return dealIngredientId;
	}

	/**
	 * Set composite id of deal ingredient.
	 * 
	 * @param dealIngredientId Composite id of deal ingredient.
	 */
	public void setDealIngredientId(DealIngredientId dealIngredientId) {
		this.dealIngredientId = dealIngredientId;
	}

	/**
	 * Get percentage of discount.
	 * 
	 * @return Percentage of discount.
	 */
	public BigDecimal getDiscountPercentage() {
		return discountPercentage;
	}

	/**
	 * Set percentage of discount.
	 * 
	 * @param discountPercentage Percentage of discount.
	 */
	public void setDiscountPercentage(BigDecimal discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dealIngredientId == null) ? 0 : dealIngredientId.hashCode());
		result = prime * result + ((discountPercentage == null) ? 0 : discountPercentage.hashCode());
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
		DealIngredient other = (DealIngredient) obj;
		if (dealIngredientId == null) {
			if (other.dealIngredientId != null)
				return false;
		} else if (!dealIngredientId.equals(other.dealIngredientId))
			return false;
		if (discountPercentage == null) {
			if (other.discountPercentage != null)
				return false;
		} else if (!discountPercentage.equals(other.discountPercentage))
			return false;
		return true;
	}
}