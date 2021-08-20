package com.example.demo.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.DTO.AuthenticationDTO;
import com.example.demo.DTO.PasswordPutDTO;
import com.example.demo.DTO.PersonPostDTO;
import com.example.demo.DTO.PersonPutDTO;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.model.Person;
import com.example.demo.security.JWTConfig;
import com.example.demo.service.PersonService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/persons")
public class PersonController {
	private PersonService service;
	private AuthenticationManager authenticationManager;
	private JWTConfig jwtConfig;
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public ResponseEntity<Page<Person>> get(Pageable pageable, @RequestParam Map<String, String> allParam) {
		String gender = allParam.get("gender");
		String adressState = allParam.get("adressState");
		String adressCity = allParam.get("adressCity");
		
		if(gender != null)
			return ResponseEntity.ok(service.findByGender(gender, pageable));
		if(adressState != null)
			return ResponseEntity.ok(service.findByAdressState(adressState, pageable));
		if(adressCity != null)
			return ResponseEntity.ok(service.findByAdressCity(adressCity, pageable));
			
		return ResponseEntity.ok(service.findAll(pageable));
	}

	@GetMapping("{id}")
	public ResponseEntity<Person> getById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@PostMapping
	public ResponseEntity<Person> post(@RequestBody @Valid PersonPostDTO person) {
		if(!person.getPassword().equals(person.getPasswordConfirm())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
					"As senhas precisam ser iguais");
		} 
		person.setPassword(passwordEncoder.encode(person.getPassword()));
		return new ResponseEntity<>(service.save(PersonMapper.INSTANCE.toPerson(person)), HttpStatus.CREATED);		
	}
	
	@PostMapping("/authenticate")
    public ResponseEntity<Map<String, String>> generateToken(@RequestBody @Valid AuthenticationDTO authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Você não está autorizado !");
        }
        return ResponseEntity.ok(Map.of("token", jwtConfig.generateToken(authRequest.getEmail())));
    }

	@PutMapping
	public ResponseEntity<Void> put(@RequestBody @Valid PersonPutDTO person, @RequestHeader("Authorization") String authorization) {
		String token = authorization.substring(7);
        String email = jwtConfig.extractUsername(token);
        Person personModel = service.findByEmail(email);
        if(person.getId() == personModel.getId() && person.getAdress().getId() == personModel.getId()) {
        	service.update(PersonMapper.INSTANCE.toPerson(person));
    		return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, 
				"Você não tem autorização para fazer esta ação");
	}
	
	@PutMapping("/updatePassword")
	public ResponseEntity<Void> putPassword(@RequestBody @Valid PasswordPutDTO person, @RequestHeader("Authorization") String authorization){
		String token = authorization.substring(7);
        String email = jwtConfig.extractUsername(token);
        Long personId = service.findByEmail(email).getId();
        if(person.getId() != personId) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, 
				"Você não tem autorização para fazer esta ação");
		if(person.getPassword().equals(person.getPasswordConfirm())) {
			Person personModel = service.findById(person.getId());
			boolean flag = passwordEncoder.matches(person.getOldPassword(), personModel.getPassword());
			if(flag) {
				person.setPassword(passwordEncoder.encode(person.getPassword()));
				service.updatePassword(PersonMapper.INSTANCE.toPerson(person));
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			} 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
					"A senha antiga está errada");
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
				"A senha nova e a sua senha de confirmação não são iguais");
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}
