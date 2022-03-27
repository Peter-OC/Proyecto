package com.capgemini.application.dtos;

import org.springframework.beans.factory.parsing.SourceExtractor;

import com.capgemini.domains.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UserEditDTO {

	@JsonProperty("username")
	private String username;
	
	@JsonProperty("nombre")
	private String first_name;

	@JsonProperty("apellido")
	private String last_name;
	
	@JsonProperty("direcion")
	private String address;

	@JsonProperty("contraseña")
	private String password;
	
	@JsonProperty("rol")
	private String function;
	
	public static UserEditDTO from(User source) {
		return new UserEditDTO(
				source.getUsername(),
				source.getFirstName(),
				source.getLastName(),
				source.getAddress(),
				source.getPassword(),
				source.getFunction()
				);
	}
	
	public static User from(UserEditDTO source) {
		return new User(
				source.getAddress(),
				source.getFirst_name(),
				source.getFunction(),
				source.getLast_name(),				
				source.getPassword()				
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