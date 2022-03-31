package com.capgemini.application.dtos;

import java.util.List;

import com.capgemini.domains.entities.Product;
import com.capgemini.domains.entities.Product.Type;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductEditDTO {

	@JsonProperty("id")
	private int idProduct;
	
	@JsonProperty("descripcion")
	private String description;
	
	@JsonProperty("no_me_gusta")
	private Integer dislike;

	@JsonProperty("me_gusta")
	private Integer thelike;

	@JsonProperty("nombre")
	private String name;
	
	@JsonProperty("foto")
	private String photo;

	@JsonProperty("precio")
	private float price;
	
	
	@JsonProperty("pizza")
	private PizzaEditDTO pizza;
	
	@JsonProperty("categoria")
	private int category;
	
	@JsonProperty("lista_comentarios")
	private List<CommentShortDTO> comments;

	
	public static ProductEditDTO from(Product source) {
		return new ProductEditDTO(
				source.getIdProduct(),
				source.getDescription(),
				source.getDislike(),
				source.getLike(),
				source.getName(),
				source.getPhoto(),				
				source.getPrice(),
				source.getType() == Type.PIZZA ? PizzaEditDTO.from(source.getPizza()) : null,
				source.getType().getValue(),
				source.getComments().stream().map(item -> CommentShortDTO.from(item)).toList()
				);
	}
	
	public static  Product from(ProductEditDTO source) {
		return new Product(
				source.getIdProduct(),
				source.getDescription(),
				source.getDislike(),
				source.getThelike(),
				source.getName(),
				source.getPhoto(),				
				source.getPrice(),
				null,
				Product.Type.getEnum(source.getCategory())
				);
	}
	
	public Product update(Product target) {
		actualizaPropiedadesEntidad(target);
		return target;
	}
	
    private void actualizaPropiedadesEntidad(Product target) {
		target.setDescription(description);
		target.setDislike(dislike);
		target.setLike(thelike);
		target.setName(name);
		target.setPhoto(photo);
		target.setPrice(price);
		target.setType(Product.Type.getEnum(category));
    }
}
