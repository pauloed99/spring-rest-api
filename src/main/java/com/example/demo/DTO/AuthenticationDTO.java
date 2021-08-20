package com.example.demo.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationDTO {
	
	@NotEmpty(message = "O email não pode ser vazio")
	private String email;
	
	@NotEmpty(message = "A senha não pode ser vazia")
	@Size(min = 8)
	private String password;
}
