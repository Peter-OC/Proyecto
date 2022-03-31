package com.capgemini.application.dtos;

import java.util.Date;

import com.capgemini.domains.entities.Comment;
import com.capgemini.domains.entities.Product;
import com.capgemini.domains.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CommentShortDTO {

	@JsonProperty("id")
	private int idComment;

	@JsonProperty("fecha")
	private Date date;

	@JsonProperty("comentario")
	private String text;

	@JsonProperty("usuario")
	private int user;


	
	public static CommentShortDTO from(Comment source) {
		return new CommentShortDTO(
				source.getIdComment(),
				source.getDate(),
				source.getText(),
				source.getUser().getIdUser()
				);
	}
}
