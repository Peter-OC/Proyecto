package com.bootcamp.application.dtos;

import org.springframework.beans.factory.parsing.SourceExtractor;

import com.bootcamp.domains.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UserEditDTO {

	@JsonProperty("nombre")
	private String first_name;

	@JsonProperty("apellido")
	private String last_name;
	
	@JsonProperty("direccion")
	private String address;
	
	@JsonProperty("rol")
	private String[] function;
	
	public static UserEditDTO from(User source) {
		return new UserEditDTO(
				source.getFirstName(),
				source.getLastName(),
				source.getAddress(),
				source.getFunction().split(",")
				);
	}
}