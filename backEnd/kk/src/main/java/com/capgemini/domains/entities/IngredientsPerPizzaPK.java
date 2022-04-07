package com.bootcamp.domains.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ingredients_per_pizza database table.
 * 
 */
@Embeddable
public class IngredientsPerPizzaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_pizza", insertable=false, updatable=false)
	private int idPizza;

	@Column(name="id_ingredient", insertable=false, updatable=false)
	private int idIngredient;

	public IngredientsPerPizzaPK() {
	}
	public int getIdPizza() {
		return this.idPizza;
	}
	public void setIdPizza(int idPizza) {
		this.idPizza = idPizza;
	}
	public int getIdIngredient() {
		return this.idIngredient;
	}
	public void setIdIngredient(int idIngredient) {
		this.idIngredient = idIngredient;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof IngredientsPerPizzaPK)) {
			return false;
		}
		IngredientsPerPizzaPK castOther = (IngredientsPerPizzaPK)other;
		return 
			(this.idPizza == castOther.idPizza)
			&& (this.idIngredient == castOther.idIngredient);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPizza;
		hash = hash * prime + this.idIngredient;
		
		return hash;
	}
}