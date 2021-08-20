package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService{

	private PersonRepository personRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Person person = personRepository.findByEmail(email)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED,
						"Não foi possível encontrar o usuário por esse email"));
		return new User(person.getEmail(), person.getPassword(), new ArrayList<>());
	}

}
