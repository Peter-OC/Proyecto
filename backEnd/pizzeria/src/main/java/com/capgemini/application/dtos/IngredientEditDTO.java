package com.capgemini.application.dtos;

import org.springframework.beans.factory.parsing.SourceExtractor;

import com.capgemini.domains.entities.Ingredient;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class IngredientEditDTO {

	@JsonProperty("id")
	private int ingredientId;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("price")
	private float price;
	
	@JsonProperty("photoURL")
	private String photo;
	
	public static IngredientEditDTO from(Ingredient source) {
		return new IngredientEditDTO(
				source.getIdIngredient(),
				source.getName(),
				source.getType().getValue(),
				source.getPrice(),
				source.getPhoto()
				);
	}
	
	public static Ingredient from(IngredientEditDTO source) {
		return new Ingredient(
				source.getIngredientId(),
				source.getName(),
				Ingredient.Type.getEnum(source.getType()),
				source.getPrice(),
				source.getPhoto()
				);
	}
	
//	public Ingredient update(Ingredient target) {
//		actualizaPropiedadesEntidad(target);
//		borrarPizzasSobrantes(target);
//		actualizarPagosCambiados(target);
//		incorporarNuevosPagos(target);
//		return target;
//	}
//
//	private void actualizaPropiedadesEntidad(Ingredient target) {
//		target.setIdIngredient(ingredientId);
//		target.setName(name);
//		target.setType(Ingredient.Type.getEnum(type));
//		target.setPrice(price);
//		target.setPhoto(photo);
//	}
//
//	private void borrarPizzasSobrantes(Ingredient sauce) {
//		// Borramos los que no estan en el DTO
//		
//		
//		sauce.getpizzasSauce().stream()
//		.filter(entity -> pagos.stream().noneMatch(dto -> entity.getPaymentId() == dto.getIdPago())).toList()
//		.forEach(item -> target.removePayment(item));
//	}
//
//	private void actualizarPagosCambiados(Ingredient target) {
//		// Actualizamos con el DTO la entidad
//		target.getPayments().forEach(entity -> {
//			var dto = pagos.stream().filter(item -> item.getIdPago() == entity.getPaymentId()).findFirst().get();
//			if (entity.getPaymentDate() != dto.getFecha())
//				entity.setPaymentDate(dto.getFecha());
//			if (entity.getAmount() != dto.getImporte())
//				entity.setAmount(dto.getImporte());
//			if (entity.getStaff().getStaffId() != dto.getIdEmpleado())
//				entity.setStaff(new Staff(dto.getIdEmpleado()));
//		});
//	}
//
//	private void incorporarNuevosPagos(Ingredient target) {
//		// Añadimos los nuevos del DTO a la entidad
//		pagos.stream().filter(
//				dto -> target.getPayments().stream().noneMatch(entity -> entity.getPaymentId() == dto.getIdPago()))
//				.forEach(dto -> target.addPayment(PagoEditDTO.from(dto)));
//	}
}
