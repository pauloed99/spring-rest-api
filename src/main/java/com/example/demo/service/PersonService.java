package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PersonService {

	private PersonRepository repository;

	public Page<Person> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public Page<Person> findByGender(String gender, Pageable pageable) {
		return repository.findByGender(gender, pageable);
	}
	
	public Page<Person> findByAdressState(String state, Pageable pageable){
		return repository.findByAdressState(state, pageable);
	}
	
	public Page<Person> findByAdressCity(String city, Pageable pageable){
		return repository.findByAdressCity(city, pageable);
	}

	public Person findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"A pessoa com id " + id + " não foi encontrada"));
	}
	
	public Person findByEmail(String email) {
		return repository.findByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"A pessoa com o email " + email + " não foi encontrada"));
	}

	public Person save(Person person) {
		return repository.save(person);
	}

	public void deleteById(Long id) {
		repository.deleteById(findById(id).getId());
	}

	public void update(Person person) {
		Person personModel = findById(person.getId());
		person.setPassword(personModel.getPassword());
		repository.save(person);
	}
	
	public void updatePassword(Person person) {
		Person personModel = findById(person.getId());
		personModel.setPassword(person.getPassword());
		repository.save(personModel);
	}

}
