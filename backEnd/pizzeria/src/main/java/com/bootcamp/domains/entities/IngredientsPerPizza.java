package com.bootcamp.domains.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ingredients_per_pizza database table.
 * 
 */
@Entity
@Table(name="ingredients_per_pizza")
@NamedQuery(name="IngredientsPerPizza.findAll", query="SELECT i FROM IngredientsPerPizza i")
public class IngredientsPerPizza implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IngredientsPerPizzaPK id;

	private int amount;

	//bi-directional many-to-one association to Ingredient
	@ManyToOne
	@JoinColumn(name="id_ingredient", insertable = false, updatable = false)
	private Ingredient ingredient;

	//bi-directional many-to-one association to Pizza
	@ManyToOne
	@JoinColumn(name="id_pizza", insertable = false, updatable = false)
	private Pizza pizza;

	public IngredientsPerPizza() {
	}
	
	
	public IngredientsPerPizza(int amount, Ingredient ingredient, Pizza pizza) {
		super();
		this.amount = amount;
		this.ingredient = ingredient;
		this.pizza = pizza;
		this.id = new IngredientsPerPizzaPK(pizza.getPizzaId(), ingredient.getIngredientId());
	}

	public IngredientsPerPizzaPK getId() {
		return this.id;
	}

	public void setId(IngredientsPerPizzaPK id) {
		this.id = id;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Ingredient getIngredient() {
		return this.ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Pizza getPizza() {
		return this.pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

}