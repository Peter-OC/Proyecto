package com.bootcamp.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pizza database table.
 * 
 */
@Entity
@Table(name="pizza")
@NamedQuery(name="Pizza.findAll", query="SELECT p FROM Pizza p")
public class Pizza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pizza")
	private int idPizza;

	//bi-directional many-to-one association to IngredientsPerPizza
	@OneToMany(mappedBy="pizza")
	private List<IngredientsPerPizza> ingredientsPerPizzas;

	//bi-directional many-to-one association to Ingredient
	@ManyToOne
	@JoinColumn(name="base")
	private Ingredient ingredientBase;

	//bi-directional many-to-one association to Ingredient
	@ManyToOne
	@JoinColumn(name="sauce")
	private Ingredient ingredientSauce;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="pizza")
	private List<Product> products;

	public Pizza() {
	}

	public int getIdPizza() {
		return this.idPizza;
	}

	public void setIdPizza(int idPizza) {
		this.idPizza = idPizza;
	}

	public List<IngredientsPerPizza> getIngredientsPerPizzas() {
		return this.ingredientsPerPizzas;
	}

	public void setIngredientsPerPizzas(List<IngredientsPerPizza> ingredientsPerPizzas) {
		this.ingredientsPerPizzas = ingredientsPerPizzas;
	}

	public IngredientsPerPizza addIngredientsPerPizza(IngredientsPerPizza ingredientsPerPizza) {
		getIngredientsPerPizzas().add(ingredientsPerPizza);
		ingredientsPerPizza.setPizza(this);

		return ingredientsPerPizza;
	}

	public IngredientsPerPizza removeIngredientsPerPizza(IngredientsPerPizza ingredientsPerPizza) {
		getIngredientsPerPizzas().remove(ingredientsPerPizza);
		ingredientsPerPizza.setPizza(null);

		return ingredientsPerPizza;
	}

	public Ingredient getIngredientBase() {
		return this.ingredientBase;
	}

	public void setIngredientBase(Ingredient ingredientBase) {
		this.ingredientBase = ingredientBase;
	}

	public Ingredient getIngredientSauce() {
		return this.ingredientSauce;
	}

	public void setIngredientSauce(Ingredient ingredientSauce) {
		this.ingredientSauce = ingredientSauce;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setPizza(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setPizza(null);

		return product;
	}

}