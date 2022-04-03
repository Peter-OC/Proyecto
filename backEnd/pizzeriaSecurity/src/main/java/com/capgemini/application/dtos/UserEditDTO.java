package com.capgemini.application.dtos;

import org.springframework.beans.factory.parsing.SourceExtractor;

import com.capgemini.domains.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UserEditDTO {

	@JsonProperty("nombre")
	private String first_name;

	@JsonProperty("apellido")
	private String last_name;
	
	
	@JsonProperty("rol")
	private String[] function;
	
	public static UserEditDTO from(User source) {
		return new UserEditDTO(
				source.getFirstName(),
				source.getLastName(),
				source.getFunction().split(",")
				);
	}
}