package com.example.demo.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PersonPutDTO {
	
	@NotNull(message = "Informe o id")
	private Long id;
	
	@NotEmpty(message = "O nome não pode ser vazio")
	private String firstname;
	
	@NotEmpty(message = "O sobrenome não pode ser vazio")
	private String lastname;
	
	@NotEmpty(message = "O email não pode ser vazio")
	private String email;
	
	@NotEmpty(message = "O cpf não pode ser vazio")
	private String cpf;
	
	@NotEmpty(message = "A data de aniversário não pode ser vazia")
	private String birthDate;

	@NotNull(message = "O endereço não pode ser vazio")
	private AdressPutDTO adress;
	
	@NotEmpty(message = "O gênero não pode ser vazio")
	private String gender;

	@NotEmpty(message = "O telefone não pode ser vazio")	
	private String phone;
}
