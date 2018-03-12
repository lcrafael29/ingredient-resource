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

package com.pizzaordering.ingredient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Starter class of application.
 * 
 * @author Rafael Lima Costa
 *
 */
@SpringBootApplication
public class IngredientResourceApplication {

	/**
	 * Starter method of application.
	 * 
	 * @param args Input arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(IngredientResourceApplication.class, args);
	}
}