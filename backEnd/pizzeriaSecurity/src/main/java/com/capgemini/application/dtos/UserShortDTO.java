package com.capgemini.application.dtos;

import com.capgemini.domains.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserShortDTO {
	
	@JsonProperty("username")
	private String username;

	@JsonProperty("contraseña")
	private String password;
	
	public static UserShortDTO from(User source) {
		return new UserShortDTO(
				source.getUsername(),
				source.getPassword()
				);
	}

}
