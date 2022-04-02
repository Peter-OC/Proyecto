package com.capgemini.application.dtos;


import com.capgemini.domains.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserDetailsDTO {
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("nombre")
	private String first_name;

	@JsonProperty("apellido")
	private String last_name;
	
	@JsonProperty("direccion")
	private String address;

	@JsonProperty("contraseña")
	private String password;
	
	@JsonProperty("rol")
	private String function;
	
	public static UserDetailsDTO from(User source) {
		return new UserDetailsDTO(
				source.getUsername(),
				source.getFirstName(),
				source.getLastName(),
				source.getAddress(),
				source.getPassword(),
				source.getFunction()
				);
	}

}
