package com.capgemini.application.dtos;

import java.util.List;

import com.capgemini.domains.entities.Category;
import com.capgemini.domains.entities.Category.Type;
import com.capgemini.domains.entities.Comment;
import com.capgemini.domains.entities.Pizza;
import com.capgemini.domains.entities.Product;
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
				source.getCategory().getType().getValue(),
				source.getComments().stream().map(item -> CommentShortDTO.from(item)).toList(),
				source.getCategory().getType() == Type.PIZZA ? PizzaDetailsDTO.from(source.getPizza()) : null
				);
	}
}
