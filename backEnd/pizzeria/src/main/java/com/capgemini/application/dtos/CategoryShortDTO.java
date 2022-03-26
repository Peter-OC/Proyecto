package com.capgemini.application.dtos;

import com.capgemini.domains.entities.Category;
import com.capgemini.domains.entities.Category.Type;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CategoryShortDTO {

	@JsonProperty("id")
	private int idCategory;

	@JsonProperty("type")
	private String type;
	
	@JsonProperty("description")
	private String description;
	
	public static CategoryShortDTO from(Category source) {
		return new CategoryShortDTO(
				source.getIdCategory(),
				source.getType() == null ? null : source.getType().getValue(),
				source.getDescription()
				);
	}
}
