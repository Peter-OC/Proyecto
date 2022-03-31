package com.capgemini.application.dtos;

import java.util.Date;

import javax.persistence.JoinColumn;

import com.capgemini.domains.entities.Comment;
import com.capgemini.domains.entities.Product;
import com.capgemini.domains.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CommentDetailsDTO {

	@JsonProperty("id")
	private int idComment;
	
	@JsonProperty("fecha")
	private Date date;

	@JsonProperty("puntuacion")
	private Integer score;

	@JsonProperty("comentario")
	private String text;

	@JsonProperty("nombreProducto")
	private String product;

	@JsonProperty("idUsuario")
	private int user;


	
	public static CommentDetailsDTO from(Comment source) {
		return new CommentDetailsDTO(
				source.getIdComment(),
				source.getDate(),
				source.getScore(),
				source.getText(),
				source.getProduct().getName(),
				source.getUser().getIdUser()				
				);
	}
}
