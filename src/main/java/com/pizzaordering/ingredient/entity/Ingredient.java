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

package com.pizzaordering.ingredient.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Ingredient entity.
 * 
 * @author Rafael Lima Costa
 *
 */
@Entity
@Table(name = "igd_ingredient")
public class Ingredient implements Serializable {
	
	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Description.
	 */
	private String description;
	
	/**
	 * Type.
	 */
	private Character type;
	
	/**
	 * Quantity of portion.
	 */
	private Integer portionQuantity;
	
	/**
	 * Price of portion.
	 */
	private BigDecimal portionPrice;
	
	/**
	 * Percentage of discount.
	 */
	private BigDecimal percentageDiscount;
	
	/**
	 * Constructor.
	 */
	public Ingredient() {
	}

	/**
	 * Get id of ingredient.
	 * 
	 * @return Id of ingredient.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set id of ingredient.
	 * 
	 * @param id Id of ingredient.
	 */
	public void setId(Long id) {
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
	 * Get type.
	 * 
	 * @return Type.
	 */
	public Character getType() {
		return type;
	}

	/**
	 * Set type.
	 * 
	 * @param type Type.
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
	 * Set quantity of portion.
	 * 
	 * @param portionQuantity Quantity of portion.
	 */
	public void setPortionQuantity(Integer portionQuantity) {
		this.portionQuantity = portionQuantity;
	}

	/**
	 * Get price of portion.
	 * 
	 * @return Price of portion.
	 */
	public BigDecimal getPortionPrice() {
		return portionPrice;
	}
	
	/**
	 * Set price of portion.
	 * 
	 * @param portionPrice Price of portion.
	 */
	public void setPortionPrice(BigDecimal portionPrice) {
		this.portionPrice = portionPrice;
	}

	/**
	 * Get percentage of discount.
	 * 
	 * @return Percentage of discount.
	 */
	public BigDecimal getPercentageDiscount() {
		return percentageDiscount;
	}

	/**
	 * Set percentage of discount.
	 * 
	 * @param percentageDiscount Percentage of discount.
	 */
	public void setPercentageDiscount(BigDecimal percentageDiscount) {
		this.percentageDiscount = percentageDiscount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((percentageDiscount == null) ? 0 : percentageDiscount.hashCode());
		result = prime * result + ((portionPrice == null) ? 0 : portionPrice.hashCode());
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
		Ingredient other = (Ingredient) obj;
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
		if (percentageDiscount == null) {
			if (other.percentageDiscount != null)
				return false;
		} else if (!percentageDiscount.equals(other.percentageDiscount))
			return false;
		if (portionPrice == null) {
			if (other.portionPrice != null)
				return false;
		} else if (!portionPrice.equals(other.portionPrice))
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