package com.capgemini.application.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class PasswordDTO {
	@NotBlank
	private String oldPassword;
	@NotNull
	//@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{8,}$")
	private String newPassword;
}
