package com.capgemini.domains.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the products_per_order database table.
 * 
 */
@Entity
@Table(name="products_per_order")
@NamedQuery(name="ProductsPerOrder.findAll", query="SELECT p FROM ProductsPerOrder p")
public class ProductsPerOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductsPerOrderPK id;

	private int amount;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="id_product", insertable = false, updatable = false)
	private Product product;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="id_order", insertable = false, updatable = false)
	private Order order;

	public ProductsPerOrder() {
	}

	public ProductsPerOrderPK getId() {
		return this.id;
	}

	public void setId(ProductsPerOrderPK id) {
		this.id = id;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}