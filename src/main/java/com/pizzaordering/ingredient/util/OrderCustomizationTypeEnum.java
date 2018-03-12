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

package com.pizzaordering.ingredient.util;

/**
 * Enumeration of order customization type. 
 * 
 * @author Rafael Lima Costa
 *
 */
public enum OrderCustomizationTypeEnum {
	
	/**
	 * Customization of adding an ingredient.
	 */
	ADD("A"),
	
	/**
	 * Customization of removing an ingredient.
	 */
	REMOVE("R");
	
	/**
	 * Type of customization.
	 */
	private final String type;
	
	/**
	 * Constructor.
	 */
	OrderCustomizationTypeEnum(String type) {
		this.type = type;
	}
	
	/**
	 * Get type of customization.
	 * 
	 * @return Type of customization.
	 */
	public String getType() {
		return this.type;
	}
}