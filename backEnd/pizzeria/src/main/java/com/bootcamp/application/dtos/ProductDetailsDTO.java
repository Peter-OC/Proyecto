package com.bootcamp.application.dtos;

import java.util.List;

import com.bootcamp.domains.entities.Product;
import com.bootcamp.domains.entities.Product.Type;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductDetailsDTO {

	@JsonProperty("id")
	private int idProduct;
	
	@JsonProperty("descripcion")
	private String description;
	
	@JsonProperty("no_me_gusta")
	private Integer dislike;

	@JsonProperty("me_gusta")
	private Integer like;

	@JsonProperty("nombre")
	private String name;
	
	@JsonProperty("foto")
	private String photo;

	@JsonProperty("precio")
	private float price;
	
	@JsonProperty("categoria")
	private String category;
	
	@JsonProperty("lista_comentarios")
	private List<CommentShortDTO> comments;
	
	private PizzaDetailsDTO pizza;
	
	public static ProductDetailsDTO from(Product source) {
		return new ProductDetailsDTO(
				source.getIdProduct(),
				source.getDescription(),
				source.getDislike(),
				source.getLike(),
				source.getName(),
				source.getPhoto(),
				source.getPrice(),
				source.getType().toString(),
				source.getComments().stream().map(item -> CommentShortDTO.from(item)).toList(),
				source.getType() == Type.PIZZA ? PizzaDetailsDTO.from(source.getPizza()) : null
				);
	}
}
