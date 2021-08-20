package com.example.demo.DTO;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AdressPutDTO {
	
	@NotEmpty(message = "Informe o id")
	private Long id;
	
	@NotEmpty(message = "O estado não pode ser vazio")
	private String state;

	@NotEmpty(message = "A cidade não pode ser vazia")
	private String city;

	@NotEmpty(message = "O bairro não pode ser vazio")
	private String district;

	@NotEmpty(message = "A rua não pode ser vazia")
	private String street;

	@NotEmpty(message = "O número da rua não pode ser vazio")
	private String streetNumber;

	private String apartmentNumber;
}
