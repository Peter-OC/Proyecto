package com.bootcamp.application.dtos;

import java.util.Date;

import com.bootcamp.domains.entities.Comment;
import com.bootcamp.domains.entities.Product;
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
	private String user;


	
	public static CommentShortDTO from(Comment source) {
		return new CommentShortDTO(
				source.getIdComment(),
				source.getDate(),
				source.getText(),
				source.getUser()
				);
	}
}
