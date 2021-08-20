package com.example.demo.DTO;

import javax.validation.Valid;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PersonPostDTO {
	
	@NotEmpty(message = "O nome não pode ser vazio")
	private String firstname;
	
	@NotEmpty(message = "O sobrenome não pode ser vazio")
	private String lastname;
	
	@NotEmpty
	@Email(message = "O email deve ser válido")
	private String email;
	
	@Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
	@NotEmpty(message = "A senha não pode ser vazia")
	private String password;
	
	@Size(min = 8, message = "A senha de confirmação deve ter no mínimo 8 caracteres")
	@NotEmpty(message = "A senha de confirmação não pode ser vazia")
	private String passwordConfirm;
	
	@Size(max = 14, message = "O Cpf deve ter no máximo 14 caracteres")
	@NotEmpty(message = "O CPF não pode ser vazio")
	private String cpf;
	
	@NotEmpty(message = "A data de aniversário não pode ser vazia")
	private String birthDate;
	
	@NotNull(message = "O Endereço não pode ser vazio")
	@Valid
	private AdressPostDTO adress;
	
	@NotEmpty(message = "O Gênero não pode ser vazio")
	private String gender;
	
	@NotEmpty(message = "O telefone não pode ser vazio")
	private String phone;
}
