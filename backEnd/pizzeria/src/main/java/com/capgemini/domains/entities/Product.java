package com.capgemini.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.capgemini.domains.core.entities.EntityBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name = "products")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product extends EntityBase<Product> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product")
	private int idProduct;

	@Lob
	private String description;

	private Integer dislike;

	private Integer like;

	@Length(min = 2, max = 50)
	@NotBlank
	private String name;

	private String photo;

	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 4, fraction = 2)
	private float price;

	// bi-directional many-to-one association to Comment
	@OneToMany(mappedBy = "product")
	private List<Comment> comments;

	// bi-directional many-to-one association to Pizza
	@ManyToOne
	@JoinColumn(name = "id_pizza")
	private Pizza pizza;

	// bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name = "id_category")
	private Category category;

	// bi-directional many-to-one association to Comment
	@ManyToOne
	@JoinColumn(name = "id_comment")
	private Comment comment;

	// bi-directional many-to-one association to ProductsPerOrder
	@OneToMany(mappedBy = "product")
	private List<ProductsPerOrder> productsPerOrders;

	public Product() {
		super();
		productsPerOrders = new ArrayList<>();
	}

	public Product(int idProduct) {
		this();
		this.idProduct = idProduct;
	}
	
	public int getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDislike() {
		return this.dislike;
	}

	public void setDislike(Integer dislike) {
		this.dislike = dislike;
	}

	public Integer getLike() {
		return this.like;
	}

	public void setLike(Integer like) {
		this.like = like;
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

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setProduct(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setProduct(null);

		return comment;
	}

	public Pizza getPizza() {
		return this.pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public List<ProductsPerOrder> getProductsPerOrders() {
		return this.productsPerOrders;
	}

	public void setProductsPerOrders(List<ProductsPerOrder> productsPerOrders) {
		this.productsPerOrders = productsPerOrders;
	}

	public ProductsPerOrder addProductsPerOrder(ProductsPerOrder productsPerOrder) {
		getProductsPerOrders().add(productsPerOrder);
		productsPerOrder.setProduct(this);

		return productsPerOrder;
	}

	public ProductsPerOrder removeProductsPerOrder(ProductsPerOrder productsPerOrder) {
		getProductsPerOrders().remove(productsPerOrder);
		productsPerOrder.setProduct(null);

		return productsPerOrder;
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", description=" + description + ", dislike=" + dislike + ", like="
				+ like + ", name=" + name + ", photo=" + photo + ", price=" + price + ", comments=" + comments
				+ ", pizza=" + pizza + ", category=" + category + ", comment=" + comment + ", productsPerOrders="
				+ productsPerOrders + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProduct);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return idProduct == other.idProduct;
	}

}