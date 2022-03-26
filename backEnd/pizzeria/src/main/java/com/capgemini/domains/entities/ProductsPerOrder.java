package com.capgemini.domains.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.capgemini.domains.core.entities.EntityBase;


/**
 * The persistent class for the products_per_order database table.
 * 
 */
@Entity
@Table(name="products_per_order")
@NamedQuery(name="ProductsPerOrder.findAll", query="SELECT p FROM ProductsPerOrder p")
public class ProductsPerOrder extends EntityBase<ProductsPerOrder> implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductsPerOrderPK id;

	@Positive
	private int amount;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="id_product", insertable = false, updatable = false)
	@NotNull
	private Product product;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="id_order", insertable = false, updatable = false)
	@NotNull
	private Order order;

	public ProductsPerOrder() {
	}
	
	public ProductsPerOrder(ProductsPerOrderPK id) {
		super();
		this.id = id;
	}
	
	

	public ProductsPerOrder(@Positive int amount) {
		this();
		this.id =  new ProductsPerOrderPK(product.getIdProduct(), order.getIdOrder());
		this.amount = amount;
	}

	public ProductsPerOrder(int amount, Product product, Order order) {
		this();		
		this.amount = amount;
		this.product = product;	
		this.order = order;
		this.id = new ProductsPerOrderPK(product.getIdProduct(), order.getIdOrder());
		
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductsPerOrder other = (ProductsPerOrder) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ProductsPerOrder [id=" + id + ", amount=" + amount + ", product=" + product + ", order=" + order + "]";
	}
	
	

}