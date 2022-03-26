package com.capgemini.domains.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;

import com.capgemini.domains.core.entities.EntityBase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order extends EntityBase<Order> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_order")
	private int idOrder;

	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;
	
	@Temporal(TemporalType.DATE)
	@Column(name="order_date")
	@PastOrPresent
	@NotNull
	private Date orderDate;

	@NotBlank
	@Length(max = 100)
	private String address;

	@Temporal(TemporalType.DATE)
	@Column(name="delivery_date")
	@PastOrPresent
	private Date deliveryDate;

	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 5, fraction = 2)
	private float price;

	public static enum Status {
		ORDERED("ordered"),
		IN_PROCESS("in_process"),
		READY("ready"),
		SENT("sent"),
		RECEIVED("received"),
		CANCELED("canceled");
		
		String value;
		
		Status (String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
		public static Status getEnum(String value) {
			switch(value) {
			case "ordered": return Status.ORDERED;
			case "in_process": return Status.IN_PROCESS;
			case "ready": return Status.READY;
			case "sent": return Status.SENT;
			case "received": return Status.RECEIVED;
			case "canceled": return Status.CANCELED;
			default:
				throw new IllegalArgumentException("Unexpected value: " + value);
			}
		}
	}
	
	@Converter
	private static class StatusConverter implements AttributeConverter<Status, String> {
		@Override
		public String convertToDatabaseColumn(Status status) {
			if (status == null) {
				return null;
		    }
		    return status.getValue();
		}
		
		@Override
		public Status convertToEntityAttribute(String value) {
			if (value == null) {
				return null;
		    }
		    return Status.getEnum(value);
		}
	}
	
	@Convert(converter = StatusConverter.class)
	@Column(name="status_order")
	@NotNull
	private Status status;
	
	//bi-directional many-to-one association to ProductsPerOrder
	@OneToMany(mappedBy="order", cascade = CascadeType.ALL, orphanRemoval = true)
	@Valid
	private List<ProductsPerOrder> productsPerOrders;

	public Order() {
		super();
		productsPerOrders = new ArrayList<ProductsPerOrder>();
	}
	

	public Order(User user, @PastOrPresent @NotNull Date orderDate, @NotBlank @Length(max = 100) String address,
			@PastOrPresent Date deliveryDate,
			@NotNull @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 5, fraction = 2) float price,
			@NotNull Status status) {
		this();
		this.user = user;
		this.orderDate = orderDate;
		this.address = address;
		this.deliveryDate = deliveryDate;
		this.price = price;
		this.status = status;
	}

	public Order(int idOrder, User user, @PastOrPresent @NotNull Date orderDate,
			@NotBlank @Length(max = 100) String address, @PastOrPresent @NotNull Date deliveryDate,
			@NotNull @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 5, fraction = 2) float price,
			@NotNull Status status) {
		this();
		this.idOrder = idOrder;
		this.user = user;
		this.orderDate = orderDate;
		this.address = address;
		this.deliveryDate = deliveryDate;
		this.price = price;
		this.status = status;
	}

	public int getIdOrder() {
		return this.idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ProductsPerOrder> getProductsPerOrders() {
		return this.productsPerOrders;
	}

	public void setProductsPerOrders(List<ProductsPerOrder> productsPerOrders) {
		this.productsPerOrders = productsPerOrders;
	}

	public ProductsPerOrder addProductsPerOrder(ProductsPerOrder productsPerOrder) {
		getProductsPerOrders().add(productsPerOrder);
		productsPerOrder.setOrder(this);

		return productsPerOrder;
	}
	
	public ProductsPerOrder addProductsPerOrder(int amount, Product product) {
		var productsPerOrder = new ProductsPerOrder(amount, product, this);
		getProductsPerOrders().add(productsPerOrder);
		return productsPerOrder;
	}

	public ProductsPerOrder removeProductsPerOrder(ProductsPerOrder productsPerOrder) {
		getProductsPerOrders().remove(productsPerOrder);
		productsPerOrder.setOrder(null);

		return productsPerOrder;
	}
	
	public ProductsPerOrder removeProductsPerOrder(int amount, Product product) {
		var productsPerOrder = new ProductsPerOrder(amount, product, this);
		getProductsPerOrders().remove(productsPerOrder);
		return productsPerOrder;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idOrder);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return idOrder == other.idOrder;
	}

	@Override
	public String toString() {
		return "Order [idOrder=" + idOrder + ", user=" + user + ", orderDate=" + orderDate + ", price=" + price
				+ ", statusOrder=" + status + "]";
	}
	

}