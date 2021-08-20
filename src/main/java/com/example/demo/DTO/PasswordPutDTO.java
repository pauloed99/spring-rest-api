package com.example.demo.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PasswordPutDTO {
	
	@NotNull(message = "O id do usuário não pode ser vazio")
	private Long id;
	
	@Size(min = 8, message = "A senha nova deve ter no mínimo 8 caracteres")
	@NotEmpty(message = "A senha não pode ser vazia")
	private String password;
	
	@Size(min = 8, message = "A senha de confirmação deve ter no mínimo 8 caracteres")
	@NotEmpty(message = "A senha de confirmação não pode ser vazia")
	private String passwordConfirm;
	
	@Size(min = 8, message = "A senha antiga deve ter no mínimo 8 caracteres")
	@NotEmpty(message = "A senha antiga não pode ser vazia")
	private String oldPassword;
}
