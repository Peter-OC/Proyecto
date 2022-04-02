package com.capgemini.application.dtos;

import org.springframework.beans.factory.parsing.SourceExtractor;

import com.capgemini.domains.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UserCreateDTO {

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
	
	public static UserCreateDTO from(User source) {
		return new UserCreateDTO(
				source.getUsername(),
				source.getFirstName(),
				source.getLastName(),
				source.getAddress(),
				source.getPassword()
				);
	}
	
	public static User from(UserCreateDTO source) {
		return new User(
				source.getUsername(),
				source.getAddress(),
				source.getFirst_name(),
				"ROLE_USER",
				source.getLast_name(),				
				source.getPassword()				
				);
	}
	
	
	public User update(User target) {
		actualizaUser(target);

		return target;
	}

	private void actualizaUser(User target) {
		target.setUsername(username);
		target.setFirstName(first_name);
		target.setLastName(last_name);
		target.setAddress(address);
//		target.setPassword(password);
//		target.setFunction(function);
	}
	

}