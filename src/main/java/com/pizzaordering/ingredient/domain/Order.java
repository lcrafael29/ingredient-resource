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
import java.util.Map;

/**
 * Order domain.
 * 
 * @author Rafael Lima Costa
 *
 */
public class Order implements Serializable {
	
	/**
	 * serialVersionUID - long
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Id.
	 */
	private Long id;
	
	/**
	 * Id of closed recipe for common pizza recipes.
	 */
	private Integer closedRecipeId;
	
	/**
	 * Size of pizza.
	 */
	private Character size;
	
	/**
	 * Thickness of bread.
	 */
	private Character breadThickness;
	
	/**
	 * Price of pizza.
	 */
	private BigDecimal price;
	
	/**
	 * Map of order customization ingredients, using ingredient id as key and orderCustomization as value.
	 */
	private Map<Long, OrderCustomization> orderCustomizationMap;
	
	/**
	 * Constructor.
	 */
	public Order() {
	}

	/**
	 * Get id of order.
	 * 
	 * @return Id of order
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set id of order.
	 * 
	 * @param id Id of order
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get id of closed recipe.
	 * 
	 * @return Id of closed recipe.
	 */
	public Integer getClosedRecipeId() {
		return closedRecipeId;
	}

	/**
	 * Set id of closed recipe.
	 * 
	 * @param closedRecipeId Id of closed recipe.
	 */
	public void setClosedRecipeId(Integer closedRecipeId) {
		this.closedRecipeId = closedRecipeId;
	}

	/**
	 * Get size of pizza.
	 * 
	 * @return Size of pizza.
	 */
	public Character getSize() {
		return size;
	}

	/**
	 * Set size of pizza.
	 * 
	 * @param size Size of pizza.
	 */
	public void setSize(Character size) {
		this.size = size;
	}

	/**
	 * Get thickness of bread.
	 * 
	 * @return Thickness of bread.
	 */
	public Character getBreadThickness() {
		return breadThickness;
	}

	/**
	 * Set thickness of bread.
	 * 
	 * @param breadThickness Thickness of bread.
	 */
	public void setBreadThickness(Character breadThickness) {
		this.breadThickness = breadThickness;
	}

	/**
	 * Get price of pizza.
	 * 
	 * @return Price of Pizza.
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Set price of pizza.
	 * 
	 * @param price Price of pizza.
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	/**
	 * Get map of order customization.
	 * 
	 * @return Map of order customization.
	 */
	public Map<Long, OrderCustomization> getOrderCustomizationMap() {
		return orderCustomizationMap;
	}

	/**
	 * Set map of order customization.
	 * 
	 * @param orderCustomizationMap Map of order customization.
	 */
	public void setOrderCustomizationMap(Map<Long, OrderCustomization> orderCustomizationMap) {
		this.orderCustomizationMap = orderCustomizationMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((breadThickness == null) ? 0 : breadThickness.hashCode());
		result = prime * result + ((closedRecipeId == null) ? 0 : closedRecipeId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
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
		Order other = (Order) obj;
		if (breadThickness == null) {
			if (other.breadThickness != null)
				return false;
		} else if (!breadThickness.equals(other.breadThickness))
			return false;
		if (closedRecipeId == null) {
			if (other.closedRecipeId != null)
				return false;
		} else if (!closedRecipeId.equals(other.closedRecipeId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}
}