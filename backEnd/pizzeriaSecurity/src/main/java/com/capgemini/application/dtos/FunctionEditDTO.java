package com.capgemini.application.dtos;

import com.capgemini.domains.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class FunctionEditDTO {

	@JsonProperty("rol")
	private String function;
	
	public static FunctionEditDTO from(User source) {
		return new FunctionEditDTO(			
				source.getFunction()
				);
	}
	
	public static User from(FunctionEditDTO source) {
		return new User(			
				source.getFunction()							
				);
	}
	

}