package com.capgemini.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.capgemini.domains.core.entities.EntityBase;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@Table(name="comments")
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment extends EntityBase<Comment> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_comment")
	private int idComment;

	@Temporal(TemporalType.DATE)
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name="date")
	private Date date;

	private Integer score;

	@Lob
	private String text;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="id_product")
	private Product product;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="comment")
	private List<Product> products;

	public Comment() {
	}

	public Comment(int idComment, @NotNull @PastOrPresent Date date, String text, Product product, User user) {
		super();
		this.idComment = idComment;
		this.date = date;
		this.text = text;
		this.product = product;
		this.user = user;
	}
	
	

	public Comment(int idComment, Integer score, String text) {
		super();
		this.idComment = idComment;
		this.score = score;
		this.text = text;
	}

	public int getIdComment() {
		return this.idComment;
	}

	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setComment(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setComment(null);

		return product;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idComment);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return idComment == other.idComment;
	}

	@Override
	public String toString() {
		return "Comment [idComment=" + idComment + ", date=" + date + ", score=" + score + ", text=" + text
				+ ", product=" + product + ", user=" + user + ", products=" + products + "]";
	}
	
	

}