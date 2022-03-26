package com.capgemini.domains.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the products_per_order database table.
 * 
 */
@Embeddable
public class ProductsPerOrderPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_product", insertable=false, updatable=false)
	private int idProduct;

	@Column(name="id_order", insertable=false, updatable=false)
	private int idOrder;

	public ProductsPerOrderPK() {
	}
	
	public ProductsPerOrderPK(int idProduct, int idOrder) {
		super();
		this.idProduct = idProduct;
		this.idOrder = idOrder;
	}

	public int getIdProduct() {
		return this.idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public int getIdOrder() {
		return this.idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProductsPerOrderPK)) {
			return false;
		}
		ProductsPerOrderPK castOther = (ProductsPerOrderPK)other;
		return 
			(this.idProduct == castOther.idProduct)
			&& (this.idOrder == castOther.idOrder);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idProduct;
		hash = hash * prime + this.idOrder;
		
		return hash;
	}
}