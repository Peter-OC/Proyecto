package com.capgemini.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.capgemini.domains.core.entities.EntityBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The persistent class for the ingredient database table.
 * 
 */
@Entity
@Table(name = "ingredients")
@NamedQuery(name = "Ingredient.findAll", query = "SELECT i FROM Ingredient i")
public class Ingredient extends EntityBase<Ingredient> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ingredient")
	private int ingredientId;

	@Length(min = 2, max = 25)
	@NotBlank
	private String name;

	public static enum Type {
		BASE("base"), SAUCE("sauce"), OTHER("other");

		String value;

		Type(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public static Type getEnum(String value) {
			switch (value) {
			case "base":
				return Type.BASE;
			case "sauce":
				return Type.SAUCE;
			case "other":
				return Type.OTHER;
			default:
				throw new IllegalArgumentException("Enum incorrecto: " + value);
			}
		}
	}

	@Converter
	private static class TypeConverter implements AttributeConverter<Type, String> {
		@Override
		public String convertToDatabaseColumn(Type type) {
			if (type == null) {
				return null;
			}
			return type.getValue();
		}

		@Override
		public Type convertToEntityAttribute(String value) {
			if (value == null) {
				return null;
			}
			return Type.getEnum(value);
		}
	}

	@Convert(converter = TypeConverter.class)
	@NotNull
	private Type type;

	private float price;

	private String photo;

	// bi-directional many-to-one association to IngredientsPerPizza
	@OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<IngredientsPerPizza> ingredientsPerPizzas;

	// bi-directional many-to-one association to Pizza
	@OneToMany(mappedBy = "ingredientBase", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pizza> pizzasBase;

	// bi-directional many-to-one association to Pizza
	@OneToMany(mappedBy = "ingredientSauce", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pizza> pizzasSauce;

	public Ingredient() {
		ingredientsPerPizzas = new ArrayList<IngredientsPerPizza>();
		pizzasBase = new ArrayList<Pizza>();
		pizzasSauce = new ArrayList<Pizza>();
	}

	
	
	public Ingredient(int ingredientId) {
		this();
		this.ingredientId = ingredientId;
	}



	public Ingredient(int idIngredient, @Length(min = 2, max = 25) @NotBlank String name) {
		this();
		this.ingredientId = idIngredient;
		this.name = name;
	}

	public Ingredient(int ingredientId, @Length(min = 2, max = 25) @NotBlank String name, @NotNull Type type,
			float price, String photo) {
		this();
		this.ingredientId = ingredientId;
		this.name = name;
		this.type = type;
		this.price = price;
		this.photo = photo;
	}

	public Ingredient(int ingredientId, @Length(min = 2, max = 25) @NotBlank String name, @NotNull Type type,
			float price, String photo, List<IngredientsPerPizza> ingredientsPerPizzas, List<Pizza> pizzasIngredient,
			List<Pizza> pizzasSauce) {
		super();
		this.ingredientId = ingredientId;
		this.name = name;
		this.type = type;
		this.price = price;
		this.photo = photo;
		this.ingredientsPerPizzas = ingredientsPerPizzas;
		this.pizzasBase = pizzasIngredient;
		this.pizzasSauce = pizzasSauce;
	}

	public int getIngredientId() {
		return this.ingredientId;
	}

	public void setIdIngredient(int idIngredient) {
		this.ingredientId = idIngredient;
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

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
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

	public List<Pizza> getPizzasBase() {
		return this.pizzasBase;
	}

	public void setpizzasBase(List<Pizza> pizzasBase) {
		this.pizzasBase = pizzasBase;
	}

	public Pizza addpizzasBase(Pizza pizzasBase) {
		getPizzasBase().add(pizzasBase);
		pizzasBase.setIngredientBase(this);

		return pizzasBase;
	}

	public Pizza removepizzasBase(Pizza pizzasBase) {
		getPizzasBase().remove(pizzasBase);
		pizzasBase.setIngredientBase(null);

		return pizzasBase;
	}

	public List<Pizza> getpizzasSauce() {
		return this.pizzasSauce;
	}

	public void setpizzasSauce(List<Pizza> pizzasSauce) {
		this.pizzasSauce = pizzasSauce;
	}

	public Pizza addpizzasSauce(Pizza pizzasSauce) {
		getpizzasSauce().add(pizzasSauce);
		pizzasSauce.setIngredientSauce(this);

		return pizzasSauce;
	}

	public Pizza removepizzasSauce(Pizza pizzasSauce) {
		getpizzasSauce().remove(pizzasSauce);
		pizzasSauce.setIngredientSauce(null);

		return pizzasSauce;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ingredientId);
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
		return ingredientId == other.ingredientId;
	}

	@Override
	public String toString() {
		return "Ingredient [type=" + type + ", name=" + name + "]";
	}

}