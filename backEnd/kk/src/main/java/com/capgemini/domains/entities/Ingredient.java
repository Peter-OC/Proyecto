package com.capgemini.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ingredient database table.
 * 
 */
@Entity
@Table(name="ingredient")
@NamedQuery(name="Ingredient.findAll", query="SELECT i FROM Ingredient i")
public class Ingredient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ingredient")
	private int idIngredient;

	private String name;

	private String photo;

	private float price;

	private String type;

	//bi-directional many-to-one association to IngredientsPerPizza
	@OneToMany(mappedBy="ingredient")
	private List<IngredientsPerPizza> ingredientsPerPizzas;

	//bi-directional many-to-one association to Pizza
	@OneToMany(mappedBy="ingredientBase")
	private List<Pizza> pizzas1;

	//bi-directional many-to-one association to Pizza
	@OneToMany(mappedBy="ingredientSauce")
	private List<Pizza> pizzas2;

	public Ingredient() {
	}

	public int getIdIngredient() {
		return this.idIngredient;
	}

	public void setIdIngredient(int idIngredient) {
		this.idIngredient = idIngredient;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<IngredientsPerPizza> getIngredientsPerPizzas() {
		return this.ingredientsPerPizzas;
	}

	public void setIngredientsPerPizzas(List<IngredientsPerPizza> ingredientsPerPizzas) {
		this.ingredientsPerPizzas = ingredientsPerPizzas;
	}

	public IngredientsPerPizza addIngredientsPerPizza(IngredientsPerPizza ingredientsPerPizza) {
		getIngredientsPerPizzas().add(ingredientsPerPizza);
		ingredientsPerPizza.setIngredient(this);

		return ingredientsPerPizza;
	}

	public IngredientsPerPizza removeIngredientsPerPizza(IngredientsPerPizza ingredientsPerPizza) {
		getIngredientsPerPizzas().remove(ingredientsPerPizza);
		ingredientsPerPizza.setIngredient(null);

		return ingredientsPerPizza;
	}

	public List<Pizza> getPizzas1() {
		return this.pizzas1;
	}

	public void setPizzas1(List<Pizza> pizzas1) {
		this.pizzas1 = pizzas1;
	}

	public Pizza addPizzas1(Pizza pizzas1) {
		getPizzas1().add(pizzas1);
		pizzas1.setIngredientBase(this);

		return pizzas1;
	}

	public Pizza removePizzas1(Pizza pizzas1) {
		getPizzas1().remove(pizzas1);
		pizzas1.setIngredientBase(null);

		return pizzas1;
	}

	public List<Pizza> getPizzas2() {
		return this.pizzas2;
	}

	public void setPizzas2(List<Pizza> pizzas2) {
		this.pizzas2 = pizzas2;
	}

	public Pizza addPizzas2(Pizza pizzas2) {
		getPizzas2().add(pizzas2);
		pizzas2.setIngredientSauce(this);

		return pizzas2;
	}

	public Pizza removePizzas2(Pizza pizzas2) {
		getPizzas2().remove(pizzas2);
		pizzas2.setIngredientSauce(null);

		return pizzas2;
	}

}