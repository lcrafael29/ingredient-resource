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

import java.util.stream.Stream;

/**
 * Enumeration of pizza size multiply factor.
 * 
 * @author Rafael Lima Costa
 *
 */
public enum PizzaSizeMultiplyFactorEnum {
	
	/**
	 * Small size.
	 */
	SMALL('S', 1),
	
	/**
	 * Medium size.
	 */
	MEDIUM('M', 2),
	
	/**
	 * Big size.
	 */
	BIG('B', 3);
	
	/**
	 * Short description of pizza size.
	 */
	private final Character shortDescription;
	
	/**
	 * Multiply factor of pizza size.
	 */
	private final Integer multiplyFactor;
	
	/**
	 * Constructor.
	 */
	PizzaSizeMultiplyFactorEnum(Character shortDescription, Integer multiplyFactor) {
		this.shortDescription = shortDescription;
		this.multiplyFactor = multiplyFactor;
	}
	
	/**
	 * Get short description of pizza size.
	 * 
	 * @return Short description of pizza size.
	 */
	public Character getShortDescription() {
		return this.shortDescription;
	}
	
	/**
	 * Get multiply factor of pizza size.
	 * 
	 * @return Multiply factor of pizza size.
	 */
	public Integer getMultiplyFactor() {
		return this.multiplyFactor;
	}
	
	/**
	 * Get multiply factor proportional to pizza size.
	 * 
	 * @param pizzaSizeShortDescription Short description of pizza size.
	 * @return Multiply factor proportional to pizza size.
	 */
	public static Integer getMultiplyFactor(Character pizzaSizeShortDescription) {
		return Stream.of(PizzaSizeMultiplyFactorEnum.values()).filter(
				pizzaSizeEnum -> pizzaSizeEnum.getShortDescription().equals(pizzaSizeShortDescription)).map(
						pizzaSizeEnum -> pizzaSizeEnum.getMultiplyFactor()).findAny().get();
	}
}