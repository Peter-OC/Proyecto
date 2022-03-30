package com.capgemini.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.capgemini.domains.core.entities.EntityBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@Table(name="categories")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category extends EntityBase<Category> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_category")
	private int idCategory;

	@Lob
	private String description;

	
	public static enum Type {
        DRINK("drink"),
        STARTER("starter"),
        PIZZA("pizza");
        String value;
        Type (String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
        public static Type getEnum(String value) {
            switch(value) {
            case "drink": return Type.DRINK;
            case "starter": return Type.STARTER;
            case "pizza": return Type.PIZZA;
            default:
                throw new IllegalArgumentException("Unexpected value: " + value);
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
    @Column(name="type")
    @NotNull
    private Type type;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="category", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> products;

	public Category() {
		products = new ArrayList<>();
	}
	
	

	public Category(int idCategory, String description, @NotNull Type type) {
		super();
		this.idCategory = idCategory;
		this.description = description;
		this.type = type;
	}
	

	public Category(int idCategory) {
		this();
		this.idCategory = idCategory;
	}


	public int getIdCategory() {
		return this.idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setCategory(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setCategory(null);

		return product;
	}


	@Override
	public int hashCode() {
		return Objects.hash(idCategory);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return idCategory == other.idCategory;
	}


	@Override
	public String toString() {
		return "Category [idCategory=" + idCategory + ", description=" + description + ", type=" + type + ", products="
				+ products + "]";
	}

	
}