package com.capgemini.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProfileDTO {
	
	@JsonProperty("nombre")
	private String first_name;

	@JsonProperty("apellido")
	private String last_name;
	
	@JsonProperty("direcion")
	private String address;
}