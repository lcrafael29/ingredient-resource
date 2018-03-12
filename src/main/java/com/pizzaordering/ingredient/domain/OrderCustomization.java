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
 * Order customization domain.
 * 
 * @author Rafael Lima Costa
 *
 */
public class OrderCustomization implements Serializable {
	
	/**
	 * serialVersionUID - long
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Composite id of order customization.
	 */
	private OrderCustomizationId orderCustomizationId;
	
	/**
	 * Type of customization.
	 */
	private Character type;
	
	/**
	 * Quantity of portion.
	 */
	private Integer portionQuantity;
	
	/**
	 * Observation.
	 */
	private String observation;
	
	/**
	 * Constructor.
	 */
	public OrderCustomization() {
	}

	/**
	 * Get id of order customization.
	 * 
	 * @return Id of order customization.
	 */
	public OrderCustomizationId getOrderCustomizationId() {
		return orderCustomizationId;
	}

	/**
	 * Set id of order customization.
	 * 
	 * @param orderCustomizationId Id of order customization.
	 */
	public void setOrderCustomizationId(OrderCustomizationId orderCustomizationId) {
		this.orderCustomizationId = orderCustomizationId;
	}

	/**
	 * Get type of customization.
	 * 
	 * @return Type of customization.
	 */
	public Character getType() {
		return type;
	}

	/**
	 * Set type of customization.
	 * 
	 * @param type Type of customization.
	 */
	public void setType(Character type) {
		this.type = type;
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

	/**
	 * Get observation.
	 * 
	 * @return Observation.
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * Set observation.
	 * 
	 * @param observation Observation.
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((observation == null) ? 0 : observation.hashCode());
		result = prime * result + ((orderCustomizationId == null) ? 0 : orderCustomizationId.hashCode());
		result = prime * result + ((portionQuantity == null) ? 0 : portionQuantity.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		OrderCustomization other = (OrderCustomization) obj;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		if (orderCustomizationId == null) {
			if (other.orderCustomizationId != null)
				return false;
		} else if (!orderCustomizationId.equals(other.orderCustomizationId))
			return false;
		if (portionQuantity == null) {
			if (other.portionQuantity != null)
				return false;
		} else if (!portionQuantity.equals(other.portionQuantity))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}