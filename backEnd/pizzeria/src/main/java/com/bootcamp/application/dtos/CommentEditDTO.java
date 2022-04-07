package com.bootcamp.application.dtos;

import java.util.Date;

import javax.persistence.JoinColumn;

import com.bootcamp.domains.entities.Comment;
import com.bootcamp.domains.entities.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CommentEditDTO {

	@JsonProperty("id")
	private int idComment;
	
	@JsonProperty("fecha")
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date date;

	@JsonProperty("puntuacion")
	private Integer score;

	@JsonProperty("comentario")
	private String text;

	@JsonProperty("producto")
	private int idProduct;

	@JsonProperty("usuario")
	private String user;


	
	public static CommentEditDTO from(Comment source) {
		return new CommentEditDTO(
				source.getIdComment(),
				source.getDate(),
				source.getScore(),
				source.getText(),
				source.getProduct().getIdProduct(),
				source.getUser()
				);
	}
	
	public static Comment from(CommentEditDTO source) {
		return new Comment(
				source.getScore(),
				source.getText(),
				new Product(source.getIdProduct())
				);
	}
	
	public Comment update(Comment target) {
		target.setScore(score);
		target.setText(text);
		return target;
	}
}
