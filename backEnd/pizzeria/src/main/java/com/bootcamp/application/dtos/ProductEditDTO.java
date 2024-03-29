package com.bootcamp.application.dtos;

import java.util.List;

import com.bootcamp.domains.entities.Product;
import com.bootcamp.domains.entities.Product.Type;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductEditDTO {
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("descripcion")
	private String description;

	@JsonProperty("nombre")
	private String name;
	
	@JsonProperty("foto")
	private String photo;

	@JsonProperty("precio")
	private float price;
	
	
	@JsonProperty("pizza")
	private PizzaEditDTO pizza;
	
	@JsonProperty("tipo")
	private int tipo;
	

	
	public static ProductEditDTO from(Product source) {
		return new ProductEditDTO(
				source.getIdProduct(),
				source.getDescription(),
				source.getName(),
				source.getPhoto(),				
				source.getPrice(),
				source.getType() == Type.PIZZA ? PizzaEditDTO.from(source.getPizza()) : null,
				source.getType().getValue()		
				);
	}
	
	public static  Product from(ProductEditDTO source) {
		return new Product(
				source.getDescription(),
				source.getName(),
				source.getPhoto(),				
				source.getPrice(), 
				Product.Type.getEnum(source.getTipo())
				);
	}
	
	public Product update(Product target) {
		actualizaPropiedadesEntidad(target);
		return target;
	}
	
    private void actualizaPropiedadesEntidad(Product target) {
		target.setDescription(description);
		target.setName(name);
		target.setPhoto(photo);
		target.setPrice(price);
    }
}
