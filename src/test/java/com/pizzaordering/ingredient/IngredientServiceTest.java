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

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.pizzaordering.ingredient.data.IngredientRepository;
import com.pizzaordering.ingredient.domain.ClosedRecipe;
import com.pizzaordering.ingredient.domain.ClosedRecipeIngredient;
import com.pizzaordering.ingredient.domain.Deal;
import com.pizzaordering.ingredient.domain.DealIngredient;
import com.pizzaordering.ingredient.domain.Order;
import com.pizzaordering.ingredient.domain.OrderCustomization;
import com.pizzaordering.ingredient.entity.Ingredient;
import com.pizzaordering.ingredient.service.ClosedRecipeClientService;
import com.pizzaordering.ingredient.service.IngredientService;

/**
 * Unit test of ingredient service layer.
 * 
 * @author Rafael Lima Costa
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientServiceTest {
	
	/**
	 * Interface of ingredient service layer.
	 */
	@Autowired
	IngredientService ingredientService;
	
	/**
	 * Interface of closed recipe client service layer mocked.
	 */
	@MockBean
	ClosedRecipeClientService closedRecipeClientService;
	
	/**
	 * Interface of ingredient repository layer mocked.
	 */
	@MockBean
	IngredientRepository ingredientRepository;
	
	/**
	 * Test calculate order price method when a closed recipe is used on request without customized ingredients:
	 * 
	 * > Mock database and external calls of this flow.
	 * > Test method sending input and comparing returned output with expected output.
	 */
	@Test
	public void calculateOrderPriceClosedRecipeTest() {
		ClosedRecipe closedRecipe = null;
		ClosedRecipeIngredient closedRecipeIngredient = null;
		Map<Long, ClosedRecipeIngredient> closedRecipeIngredientMap = null;
		Ingredient ingredient = null;
		Order order = null;
		BigDecimal price = null;
		
		// Mock closedRecipeClientService.getClosedRecipe(order.getClosedRecipeId()) call.
		closedRecipe = new ClosedRecipe();
		closedRecipe.setId(1);
		closedRecipe.setDescription("Pepperoni");
		
		closedRecipeIngredient = new ClosedRecipeIngredient();
		closedRecipeIngredient.setPortionQuantity(3);
		
		closedRecipeIngredientMap = new HashMap<Long, ClosedRecipeIngredient>();
		closedRecipeIngredientMap.put(1L, closedRecipeIngredient);
		
		closedRecipeIngredient = new ClosedRecipeIngredient();
		closedRecipeIngredient.setPortionQuantity(5);
		
		closedRecipeIngredientMap.put(2L, closedRecipeIngredient);
		
		closedRecipe.setClosedRecipeIngredientMap(closedRecipeIngredientMap);
		
		Mockito.when(closedRecipeClientService.getClosedRecipe(1)).thenReturn(closedRecipe);
		
		// Mock first ingredientRepository.findById(closedRecipeIngredientMapId) call.
		ingredient = new Ingredient();
		ingredient.setId(1L);
		ingredient.setDescription("Pepperoni");
		ingredient.setType('P');
		ingredient.setPortionQuantity(500);
		ingredient.setPortionPrice(BigDecimal.valueOf(3));
		
		Mockito.when(ingredientRepository.findById(1L)).thenReturn(Optional.of(ingredient));
		
		// Mock first ingredientRepository.save(ingredient) call.
		ingredient = new Ingredient();
		ingredient.setId(1L);
		ingredient.setDescription("Pepperoni");
		ingredient.setType('P');
		ingredient.setPortionQuantity(500 - (3 * 2));
		ingredient.setPortionPrice(BigDecimal.valueOf(3));
		
		Mockito.when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
		
		// Mock second ingredientRepository.findById(closedRecipeIngredientMapId) call.
		ingredient = new Ingredient();
		ingredient.setId(2L);
		ingredient.setDescription("Provolone");
		ingredient.setType('C');
		ingredient.setPortionQuantity(500);
		ingredient.setPortionPrice(BigDecimal.valueOf(1.5));
		
		Mockito.when(ingredientRepository.findById(2L)).thenReturn(Optional.of(ingredient));
		
		// Mock second ingredientRepository.save(ingredient) call.
		ingredient = new Ingredient();
		ingredient.setId(2L);
		ingredient.setDescription("Provolone");
		ingredient.setType('C');
		ingredient.setPortionQuantity(500 - (5 * 2));
		ingredient.setPortionPrice(BigDecimal.valueOf(1.5));
		
		Mockito.when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
		
		// Input.
		order = new Order();
		order.setClosedRecipeId(1);
		order.setSize('M');
		order.setBreadThickness('S');
		
		// Output.
		price = BigDecimal.valueOf(33).setScale(2);
		
		// Test.
		assertThat(ingredientService.calculateOrderPrice(order)).isEqualTo(price);
	}
	
	/**
	 * Test calculate order price method when customized ingredients are used on request without a closed recipe:
	 * 
	 * > Mock database calls of this flow.
	 * > Test method sending input and comparing returned output with expected output.
	 */
	@Test
	public void calculateOrderPriceCustomizedIngredientsTest() {
		Ingredient ingredient = null;
		Order order = null;
		OrderCustomization orderCustomization = null;
		Map<Long, OrderCustomization> orderCustomizationMap = null;
		BigDecimal price = null;
		
		// Mock first ingredientRepository.findById(orderCustomizationMapId) call.
		ingredient = new Ingredient();
		ingredient.setId(1L);
		ingredient.setDescription("Pepperoni");
		ingredient.setType('P');
		ingredient.setPortionQuantity(500);
		ingredient.setPortionPrice(BigDecimal.valueOf(3));
		
		Mockito.when(ingredientRepository.findById(1L)).thenReturn(Optional.of(ingredient));
		
		// Mock first ingredientRepository.save(ingredient) call.
		ingredient = new Ingredient();
		ingredient.setId(1L);
		ingredient.setDescription("Pepperoni");
		ingredient.setType('P');
		ingredient.setPortionQuantity(500 - (6 * 2));
		ingredient.setPortionPrice(BigDecimal.valueOf(3));
		
		Mockito.when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
		
		// Mock second ingredientRepository.findById(orderCustomizationMapId) call.
		ingredient = new Ingredient();
		ingredient.setId(2L);
		ingredient.setDescription("Provolone");
		ingredient.setType('C');
		ingredient.setPortionQuantity(500);
		ingredient.setPortionPrice(BigDecimal.valueOf(1.5));
		
		Mockito.when(ingredientRepository.findById(2L)).thenReturn(Optional.of(ingredient));
		
		// Mock second ingredientRepository.save(ingredient) call.
		ingredient = new Ingredient();
		ingredient.setId(2L);
		ingredient.setDescription("Provolone");
		ingredient.setType('C');
		ingredient.setPortionQuantity(500 - (2 * 2));
		ingredient.setPortionPrice(BigDecimal.valueOf(1.5));
		
		Mockito.when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
		
		// Input.
		order = new Order();
		order.setSize('M');
		order.setBreadThickness('S');
		
		orderCustomization = new OrderCustomization();
		orderCustomization.setType('A');
		orderCustomization.setPortionQuantity(6);
		orderCustomization.setObservation("Pepperoni tastes good.");
		
		orderCustomizationMap = new HashMap<Long, OrderCustomization>();
		orderCustomizationMap.put(1L, orderCustomization);
		
		orderCustomization = new OrderCustomization();
		orderCustomization.setType('A');
		orderCustomization.setPortionQuantity(2);
		orderCustomization.setObservation("Just a little bit of cheese.");
		
		orderCustomizationMap.put(2L, orderCustomization);
		
		order.setOrderCustomizationMap(orderCustomizationMap);
		
		// Output.
		price = BigDecimal.valueOf(42).setScale(2);
		
		// Test.
		assertThat(ingredientService.calculateOrderPrice(order)).isEqualTo(price);
	}
	
	/**
	 * Test calculate order price method when a closed recipe is used on request with customized ingredients:
	 * 
	 * > Mock database and external calls of this flow.
	 * > Test method sending input and comparing returned output with expected output.
	 */
	@Test
	public void calculateOrderPriceClosedRecipeCustomizedIngredientsTest() {
		ClosedRecipe closedRecipe = null;
		ClosedRecipeIngredient closedRecipeIngredient = null;
		Map<Long, ClosedRecipeIngredient> closedRecipeIngredientMap = null;
		Ingredient ingredient = null;
		Order order = null;
		OrderCustomization orderCustomization = null;
		Map<Long, OrderCustomization> orderCustomizationMap = null;
		BigDecimal price = null;
		
		// Mock closedRecipeClientService.getClosedRecipe(order.getClosedRecipeId()) call.
		closedRecipe = new ClosedRecipe();
		closedRecipe.setId(1);
		closedRecipe.setDescription("Pepperoni");
		
		closedRecipeIngredient = new ClosedRecipeIngredient();
		closedRecipeIngredient.setPortionQuantity(3);
		
		closedRecipeIngredientMap = new HashMap<Long, ClosedRecipeIngredient>();
		closedRecipeIngredientMap.put(1L, closedRecipeIngredient);
		
		closedRecipeIngredient = new ClosedRecipeIngredient();
		closedRecipeIngredient.setPortionQuantity(5);
		
		closedRecipeIngredientMap.put(2L, closedRecipeIngredient);
		
		closedRecipe.setClosedRecipeIngredientMap(closedRecipeIngredientMap);
		
		Mockito.when(closedRecipeClientService.getClosedRecipe(1)).thenReturn(closedRecipe);
		
		// Mock first ingredientRepository.findById(closedRecipeIngredientMapId) call.
		ingredient = new Ingredient();
		ingredient.setId(1L);
		ingredient.setDescription("Pepperoni");
		ingredient.setType('P');
		ingredient.setPortionQuantity(500);
		ingredient.setPortionPrice(BigDecimal.valueOf(3));
		
		Mockito.when(ingredientRepository.findById(1L)).thenReturn(Optional.of(ingredient));
		
		// Mock first ingredientRepository.save(ingredient) call.
		ingredient = new Ingredient();
		ingredient.setId(1L);
		ingredient.setDescription("Pepperoni");
		ingredient.setType('P');
		ingredient.setPortionQuantity(500 - (3 * 2));
		ingredient.setPortionPrice(BigDecimal.valueOf(3));
		
		Mockito.when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
		
		// Mock second ingredientRepository.findById(closedRecipeIngredientMapId) call.
		ingredient = new Ingredient();
		ingredient.setId(2L);
		ingredient.setDescription("Provolone");
		ingredient.setType('C');
		ingredient.setPortionQuantity(500);
		ingredient.setPortionPrice(BigDecimal.valueOf(1.5));
		
		Mockito.when(ingredientRepository.findById(2L)).thenReturn(Optional.of(ingredient));
		
		// Mock second ingredientRepository.save(ingredient) call.
		ingredient = new Ingredient();
		ingredient.setId(2L);
		ingredient.setDescription("Provolone");
		ingredient.setType('C');
		ingredient.setPortionQuantity(500 - ((5 * 2) - (2 * 2)));
		ingredient.setPortionPrice(BigDecimal.valueOf(1.5));
		
		Mockito.when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
		
		// Mock third ingredientRepository.findById(orderCustomizationMapId) call.
		ingredient = new Ingredient();
		ingredient.setId(3L);
		ingredient.setDescription("Tomato");
		ingredient.setType('V');
		ingredient.setPortionQuantity(500);
		ingredient.setPortionPrice(BigDecimal.valueOf(1));
		
		Mockito.when(ingredientRepository.findById(3L)).thenReturn(Optional.of(ingredient));
		
		// Mock third ingredientRepository.save(ingredient) call.
		ingredient = new Ingredient();
		ingredient.setId(3L);
		ingredient.setDescription("Tomato");
		ingredient.setType('V');
		ingredient.setPortionQuantity(500 - (6 * 2));
		ingredient.setPortionPrice(BigDecimal.valueOf(1));
		
		Mockito.when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
		
		// Input.
		order = new Order();
		order.setClosedRecipeId(1);
		order.setSize('M');
		order.setBreadThickness('S');
		
		orderCustomization = new OrderCustomization();
		orderCustomization.setType('A');
		orderCustomization.setPortionQuantity(6);
		orderCustomization.setObservation("Tomato is great.");
		
		orderCustomizationMap = new HashMap<Long, OrderCustomization>();
		orderCustomizationMap.put(3L, orderCustomization);
		
		orderCustomization = new OrderCustomization();
		orderCustomization.setType('R');
		orderCustomization.setPortionQuantity(2);
		orderCustomization.setObservation("Less cheese.");
		
		orderCustomizationMap.put(2L, orderCustomization);
		
		order.setOrderCustomizationMap(orderCustomizationMap);
		
		// Output.
		price = BigDecimal.valueOf(39).setScale(2);
		
		// Test.
		assertThat(ingredientService.calculateOrderPrice(order)).isEqualTo(price);
	}
	
	/**
	 * Test reverse order ingredients method when a closed recipe is used on request without customized ingredients:
	 * 
	 * > Mock database and external calls of this flow.
	 * > Test method by processing it without error.
	 */
	@Test
	public void reverseOrderIngredientsClosedRecipeTest() {
		ClosedRecipe closedRecipe = null;
		ClosedRecipeIngredient closedRecipeIngredient = null;
		Map<Long, ClosedRecipeIngredient> closedRecipeIngredientMap = null;
		Ingredient ingredient = null;
		Order order = null;
		
		// Mock closedRecipeClientService.getClosedRecipe(order.getClosedRecipeId()) call.
		closedRecipe = new ClosedRecipe();
		closedRecipe.setId(1);
		closedRecipe.setDescription("Pepperoni");
		
		closedRecipeIngredient = new ClosedRecipeIngredient();
		closedRecipeIngredient.setPortionQuantity(3);
		
		closedRecipeIngredientMap = new HashMap<Long, ClosedRecipeIngredient>();
		closedRecipeIngredientMap.put(1L, closedRecipeIngredient);
		
		closedRecipeIngredient = new ClosedRecipeIngredient();
		closedRecipeIngredient.setPortionQuantity(5);
		
		closedRecipeIngredientMap.put(2L, closedRecipeIngredient);
		
		closedRecipe.setClosedRecipeIngredientMap(closedRecipeIngredientMap);
		
		Mockito.when(closedRecipeClientService.getClosedRecipe(1)).thenReturn(closedRecipe);
		
		// Mock first ingredientRepository.findById(closedRecipeIngredientMapId) call.
		ingredient = new Ingredient();
		ingredient.setId(1L);
		ingredient.setDescription("Pepperoni");
		ingredient.setType('P');
		ingredient.setPortionQuantity(500);
		ingredient.setPortionPrice(BigDecimal.valueOf(3));
		
		Mockito.when(ingredientRepository.findById(1L)).thenReturn(Optional.of(ingredient));
		
		// Mock first ingredientRepository.save(ingredient) call.
		ingredient = new Ingredient();
		ingredient.setId(1L);
		ingredient.setDescription("Pepperoni");
		ingredient.setType('P');
		ingredient.setPortionQuantity(500 + (3 * 2));
		ingredient.setPortionPrice(BigDecimal.valueOf(3));
		
		Mockito.when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
		
		// Mock second ingredientRepository.findById(closedRecipeIngredientMapId) call.
		ingredient = new Ingredient();
		ingredient.setId(2L);
		ingredient.setDescription("Provolone");
		ingredient.setType('C');
		ingredient.setPortionQuantity(500);
		ingredient.setPortionPrice(BigDecimal.valueOf(1.5));
		
		Mockito.when(ingredientRepository.findById(2L)).thenReturn(Optional.of(ingredient));
		
		// Mock second ingredientRepository.save(ingredient) call.
		ingredient = new Ingredient();
		ingredient.setId(2L);
		ingredient.setDescription("Provolone");
		ingredient.setType('C');
		ingredient.setPortionQuantity(500 + (5 * 2));
		ingredient.setPortionPrice(BigDecimal.valueOf(1.5));
		
		Mockito.when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
		
		// Input.
		order = new Order();
		order.setClosedRecipeId(1);
		order.setSize('M');
		order.setBreadThickness('S');
		
		// Test.
		ingredientService.reverseOrderIngredients(order);
	}
	
	/**
	 * Test reverse order ingredients method when customized ingredients are used on request without a closed recipe:
	 * 
	 * > Mock database calls of this flow.
	 * > Test method by processing it without error.
	 */
	@Test
	public void reverseOrderIngredientsCustomizedIngredientsTest() {
		Ingredient ingredient = null;
		Order order = null;
		OrderCustomization orderCustomization = null;
		Map<Long, OrderCustomization> orderCustomizationMap = null;
		
		// Mock first ingredientRepository.findById(orderCustomizationMapId) call.
		ingredient = new Ingredient();
		ingredient.setId(1L);
		ingredient.setDescription("Pepperoni");
		ingredient.setType('P');
		ingredient.setPortionQuantity(500);
		ingredient.setPortionPrice(BigDecimal.valueOf(3));
		
		Mockito.when(ingredientRepository.findById(1L)).thenReturn(Optional.of(ingredient));
		
		// Mock first ingredientRepository.save(ingredient) call.
		ingredient = new Ingredient();
		ingredient.setId(1L);
		ingredient.setDescription("Pepperoni");
		ingredient.setType('P');
		ingredient.setPortionQuantity(500 + (6 * 2));
		ingredient.setPortionPrice(BigDecimal.valueOf(3));
		
		Mockito.when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
		
		// Mock second ingredientRepository.findById(orderCustomizationMapId) call.
		ingredient = new Ingredient();
		ingredient.setId(2L);
		ingredient.setDescription("Provolone");
		ingredient.setType('C');
		ingredient.setPortionQuantity(500);
		ingredient.setPortionPrice(BigDecimal.valueOf(1.5));
		
		Mockito.when(ingredientRepository.findById(2L)).thenReturn(Optional.of(ingredient));
		
		// Mock second ingredientRepository.save(ingredient) call.
		ingredient = new Ingredient();
		ingredient.setId(2L);
		ingredient.setDescription("Provolone");
		ingredient.setType('C');
		ingredient.setPortionQuantity(500 + (2 * 2));
		ingredient.setPortionPrice(BigDecimal.valueOf(1.5));
		
		Mockito.when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
		
		// Input.
		order = new Order();
		order.setSize('M');
		order.setBreadThickness('S');
		
		orderCustomization = new OrderCustomization();
		orderCustomization.setType('A');
		orderCustomization.setPortionQuantity(6);
		orderCustomization.setObservation("Pepperoni tastes good.");
		
		orderCustomizationMap = new HashMap<Long, OrderCustomization>();
		orderCustomizationMap.put(1L, orderCustomization);
		
		orderCustomization = new OrderCustomization();
		orderCustomization.setType('A');
		orderCustomization.setPortionQuantity(2);
		orderCustomization.setObservation("Just a little bit of cheese.");
		
		orderCustomizationMap.put(2L, orderCustomization);
		
		order.setOrderCustomizationMap(orderCustomizationMap);
		
		// Test.
		ingredientService.reverseOrderIngredients(order);
	}
	
	/**
	 * Test reverse order ingredients method when a closed recipe is used on request with customized ingredients:
	 * 
	 * > Mock database and external calls of this flow.
	 * > Test method by processing it without error.
	 */
	@Test
	public void reverseOrderIngredientsClosedRecipeCustomizedIngredientsTest() {
		ClosedRecipe closedRecipe = null;
		ClosedRecipeIngredient closedRecipeIngredient = null;
		Map<Long, ClosedRecipeIngredient> closedRecipeIngredientMap = null;
		Ingredient ingredient = null;
		Order order = null;
		OrderCustomization orderCustomization = null;
		Map<Long, OrderCustomization> orderCustomizationMap = null;
		
		// Mock closedRecipeClientService.getClosedRecipe(order.getClosedRecipeId()) call.
		closedRecipe = new ClosedRecipe();
		closedRecipe.setId(1);
		closedRecipe.setDescription("Pepperoni");
		
		closedRecipeIngredient = new ClosedRecipeIngredient();
		closedRecipeIngredient.setPortionQuantity(3);
		
		closedRecipeIngredientMap = new HashMap<Long, ClosedRecipeIngredient>();
		closedRecipeIngredientMap.put(1L, closedRecipeIngredient);
		
		closedRecipeIngredient = new ClosedRecipeIngredient();
		closedRecipeIngredient.setPortionQuantity(5);
		
		closedRecipeIngredientMap.put(2L, closedRecipeIngredient);
		
		closedRecipe.setClosedRecipeIngredientMap(closedRecipeIngredientMap);
		
		Mockito.when(closedRecipeClientService.getClosedRecipe(1)).thenReturn(closedRecipe);
		
		// Mock first ingredientRepository.findById(orderCustomizationMapId) call.
		ingredient = new Ingredient();
		ingredient.setId(1L);
		ingredient.setDescription("Pepperoni");
		ingredient.setType('P');
		ingredient.setPortionQuantity(500);
		ingredient.setPortionPrice(BigDecimal.valueOf(3));
		
		Mockito.when(ingredientRepository.findById(1L)).thenReturn(Optional.of(ingredient));
		
		// Mock first ingredientRepository.save(ingredient) call.
		ingredient = new Ingredient();
		ingredient.setId(1L);
		ingredient.setDescription("Pepperoni");
		ingredient.setType('P');
		ingredient.setPortionQuantity(500 + (3 * 2));
		ingredient.setPortionPrice(BigDecimal.valueOf(3));
		
		Mockito.when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
		
		// Mock second ingredientRepository.findById(orderCustomizationMapId) call.
		ingredient = new Ingredient();
		ingredient.setId(2L);
		ingredient.setDescription("Provolone");
		ingredient.setType('C');
		ingredient.setPortionQuantity(500);
		ingredient.setPortionPrice(BigDecimal.valueOf(1.5));
		
		Mockito.when(ingredientRepository.findById(2L)).thenReturn(Optional.of(ingredient));
		
		// Mock second ingredientRepository.save(ingredient) call.
		ingredient = new Ingredient();
		ingredient.setId(2L);
		ingredient.setDescription("Provolone");
		ingredient.setType('C');
		ingredient.setPortionQuantity(500 + ((5 * 2) - (2 * 2)));
		ingredient.setPortionPrice(BigDecimal.valueOf(1.5));
		
		Mockito.when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
		
		// Mock third ingredientRepository.findById(orderCustomizationMapId) call.
		ingredient = new Ingredient();
		ingredient.setId(3L);
		ingredient.setDescription("Tomato");
		ingredient.setType('V');
		ingredient.setPortionQuantity(500);
		ingredient.setPortionPrice(BigDecimal.valueOf(1));
		
		Mockito.when(ingredientRepository.findById(3L)).thenReturn(Optional.of(ingredient));
		
		// Mock third ingredientRepository.save(ingredient) call.
		ingredient = new Ingredient();
		ingredient.setId(3L);
		ingredient.setDescription("Tomato");
		ingredient.setType('V');
		ingredient.setPortionQuantity(500 + (6 * 2));
		ingredient.setPortionPrice(BigDecimal.valueOf(1));
		
		Mockito.when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
		
		// Input.
		order = new Order();
		order.setClosedRecipeId(1);
		order.setSize('M');
		order.setBreadThickness('S');
		
		orderCustomization = new OrderCustomization();
		orderCustomization.setType('A');
		orderCustomization.setPortionQuantity(6);
		orderCustomization.setObservation("Tomato is great.");
		
		orderCustomizationMap = new HashMap<Long, OrderCustomization>();
		orderCustomizationMap.put(3L, orderCustomization);
		
		orderCustomization = new OrderCustomization();
		orderCustomization.setType('R');
		orderCustomization.setPortionQuantity(2);
		orderCustomization.setObservation("Less cheese.");
		
		orderCustomizationMap.put(2L, orderCustomization);
		
		order.setOrderCustomizationMap(orderCustomizationMap);
		
		// Test.
		ingredientService.calculateOrderPrice(order);
	}
	
	/**
	 * Test add ingredient discount method:
	 * 
	 * > Mock database calls of this flow.
	 * > Test method by processing it without error.
	 */
	@Test
	public void addIngredientDiscountTest() {
		Ingredient ingredient = null;
		Deal deal = null;
		DealIngredient dealIngredient = null;
		Map<Long, DealIngredient> dealIngredientMap = null;
		
		// Mock first ingredientRepository.findById(orderCustomizationMapId) call.
		ingredient = new Ingredient();
		ingredient.setId(1L);
		ingredient.setDescription("Pepperoni");
		ingredient.setType('P');
		ingredient.setPortionQuantity(500);
		ingredient.setPortionPrice(BigDecimal.valueOf(3));
		
		Mockito.when(ingredientRepository.findById(1L)).thenReturn(Optional.of(ingredient));
		
		// Mock first ingredientRepository.save(ingredient) call.
		ingredient = new Ingredient();
		ingredient.setId(1L);
		ingredient.setDescription("Pepperoni");
		ingredient.setType('P');
		ingredient.setPortionQuantity(0 + 10);
		ingredient.setPortionPrice(BigDecimal.valueOf(3));
		
		Mockito.when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
		
		// Mock second ingredientRepository.findById(orderCustomizationMapId) call.
		ingredient = new Ingredient();
		ingredient.setId(2L);
		ingredient.setDescription("Provolone");
		ingredient.setType('C');
		ingredient.setPortionQuantity(500);
		ingredient.setPortionPrice(BigDecimal.valueOf(1.5));
		
		Mockito.when(ingredientRepository.findById(2L)).thenReturn(Optional.of(ingredient));
		
		// Mock second ingredientRepository.save(ingredient) call.
		ingredient = new Ingredient();
		ingredient.setId(2L);
		ingredient.setDescription("Provolone");
		ingredient.setType('C');
		ingredient.setPortionQuantity(0 + 10);
		ingredient.setPortionPrice(BigDecimal.valueOf(1.5));
		
		Mockito.when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
		
		// Input.
		deal = new Deal();
		deal.setDescription("Cheese Promotion");
		
		dealIngredient = new DealIngredient();
		dealIngredient.setDiscountPercentage(BigDecimal.TEN);
		
		dealIngredientMap = new HashMap<Long, DealIngredient>();
		dealIngredientMap.put(1L, dealIngredient);
		
		dealIngredient = new DealIngredient();
		dealIngredient.setDiscountPercentage(BigDecimal.TEN);
		
		dealIngredientMap.put(2L, dealIngredient);
		
		deal.setDealIngredientMap(dealIngredientMap);
		
		// Test.
		ingredientService.addIngredientDiscount(deal);
	}
	
	/**
	 * Test delete ingredient discount method:
	 * 
	 * > Mock database calls of this flow.
	 * > Test method by processing it without error.
	 */
	@Test
	public void deleteIngredientDiscountTest() {
		Ingredient ingredient = null;
		Deal deal = null;
		DealIngredient dealIngredient = null;
		Map<Long, DealIngredient> dealIngredientMap = null;
		
		// Mock first ingredientRepository.findById(orderCustomizationMapId) call.
		ingredient = new Ingredient();
		ingredient.setId(1L);
		ingredient.setDescription("Pepperoni");
		ingredient.setType('P');
		ingredient.setPortionQuantity(500);
		ingredient.setPortionPrice(BigDecimal.valueOf(3));
		ingredient.setPercentageDiscount(BigDecimal.valueOf(10));
		
		Mockito.when(ingredientRepository.findById(1L)).thenReturn(Optional.of(ingredient));
		
		// Mock first ingredientRepository.save(ingredient) call.
		ingredient = new Ingredient();
		ingredient.setId(1L);
		ingredient.setDescription("Pepperoni");
		ingredient.setType('P');
		ingredient.setPortionQuantity(500);
		ingredient.setPortionPrice(BigDecimal.valueOf(3));
		ingredient.setPercentageDiscount(BigDecimal.valueOf(10).subtract(BigDecimal.valueOf(10)));
		
		Mockito.when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
		
		// Mock second ingredientRepository.findById(orderCustomizationMapId) call.
		ingredient = new Ingredient();
		ingredient.setId(2L);
		ingredient.setDescription("Provolone");
		ingredient.setType('C');
		ingredient.setPortionQuantity(500);
		ingredient.setPortionPrice(BigDecimal.valueOf(1.5));
		ingredient.setPercentageDiscount(BigDecimal.valueOf(10));
		
		Mockito.when(ingredientRepository.findById(2L)).thenReturn(Optional.of(ingredient));
		
		// Mock second ingredientRepository.save(ingredient) call.
		ingredient = new Ingredient();
		ingredient.setId(2L);
		ingredient.setDescription("Provolone");
		ingredient.setType('C');
		ingredient.setPortionQuantity(0 + 10);
		ingredient.setPortionPrice(BigDecimal.valueOf(1.5));
		ingredient.setPercentageDiscount(BigDecimal.valueOf(10).subtract(BigDecimal.valueOf(10)));
		
		Mockito.when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
		
		// Input.
		deal = new Deal();
		deal.setDescription("Cheese Promotion");
		
		dealIngredient = new DealIngredient();
		dealIngredient.setDiscountPercentage(BigDecimal.TEN);
		
		dealIngredientMap = new HashMap<Long, DealIngredient>();
		dealIngredientMap.put(1L, dealIngredient);
		
		dealIngredient = new DealIngredient();
		dealIngredient.setDiscountPercentage(BigDecimal.TEN);
		
		dealIngredientMap.put(2L, dealIngredient);
		
		deal.setDealIngredientMap(dealIngredientMap);
		
		// Test.
		ingredientService.deleteIngredientDiscount(deal);
	}
}