package com.capgemini.application.dtos;

import com.capgemini.domains.entities.Category;
import com.capgemini.domains.entities.Order;
import com.capgemini.domains.entities.Category.Type;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CategoryEditDTO {

	@JsonProperty("id")
	private int idCategory;

	@JsonProperty("type")
	private String type;
	
	@JsonProperty("description")
	private String description;
	
	public static CategoryEditDTO from(Category source) {
		return new CategoryEditDTO(
				source.getIdCategory(),
				source.getType() == null ? null : source.getType().getValue(),
				source.getDescription()
				);
	}
	
	public static Category from(CategoryEditDTO source) {
		return new Category(
				source.getIdCategory(),
				source.getDescription(),
				source.getType() == null ? null : Category.Type.getEnum(source.getType())
				);
	}
	
	public Category update(Category target) {
		target.setIdCategory(idCategory);
		target.setType(type == null ? null : Category.Type.getEnum(type));
		target.setDescription(description);
		return target;
	}
}
