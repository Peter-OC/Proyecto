package com.bootcamp.domains.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.bootcamp.domains.core.entities.EntityBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the pizza database table.
 * 
 */
@Entity
@Table(name="pizzas")
@NamedQuery(name="Pizza.findAll", query="SELECT p FROM Pizza p")
public class Pizza extends EntityBase<Pizza> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pizza")
	private int idPizza;
	
	//bi-directional many-to-one association to Ingredient
	@ManyToOne
	@JoinColumn(name="base")
	private Ingredient ingredientBase;
	
	//bi-directional many-to-one association to Ingredient
	@ManyToOne
	@JoinColumn(name="sauce")
	private Ingredient ingredientSauce;

	//bi-directional many-to-one association to IngredientsPerPizza
	@OneToMany(mappedBy="pizza", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<IngredientsPerPizza> ingredientsPerPizzas;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="pizza", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> products;

	
	public Pizza() {
		super();
		products = new ArrayList<Product>();
		ingredientsPerPizzas = new ArrayList<IngredientsPerPizza>();
	}

	public Pizza(int idPizza) {
		this();
		this.idPizza = idPizza;
	}
	

	public Pizza(int idPizza, Ingredient ingredientBase, Ingredient ingredientSauce) {
		this();
		this.idPizza = idPizza;
		this.ingredientBase = ingredientBase;
		this.ingredientSauce = ingredientSauce;
	}

	public Pizza(int idPizza, List<IngredientsPerPizza> ingredientsPerPizzas, Ingredient ingredientBase,
			Ingredient ingredientSauce, List<Product> products) {
		this();
		this.idPizza = idPizza;
		this.ingredientsPerPizzas = ingredientsPerPizzas;
		this.ingredientBase = ingredientBase;
		this.ingredientSauce = ingredientSauce;
		this.products = products;
	}

	public int getPizzaId() {
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

	@Override
	public int hashCode() {
		return Objects.hash(idPizza);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		return idPizza == other.idPizza;
	}
	
}