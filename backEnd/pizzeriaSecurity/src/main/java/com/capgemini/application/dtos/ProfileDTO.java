package com.capgemini.application.dtos;

import com.capgemini.domains.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ProfileDTO {
	
	@JsonProperty("nombre")
	private String first_name;

	@JsonProperty("apellido")
	private String last_name;
	
	@JsonProperty("direccion")
	private String address;

	
	public static ProfileDTO from(User source) {
		return new ProfileDTO(
				source.getFirstName(),
				source.getLastName(),
				source.getAddress()
				);
	}
	
	public static User from(ProfileDTO source) {
		return new User(
				source.getFirst_name(),
				source.getLast_name(),
				source.getAddress()
				);
	}
}