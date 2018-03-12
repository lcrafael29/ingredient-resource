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

package com.pizzaordering.ingredient.data;

import org.springframework.data.repository.CrudRepository;

import com.pizzaordering.ingredient.entity.Ingredient;

/**
 * Interface of ingredient repository layer.
 * 
 * @author Rafael Lima Costa
 *
 */
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}