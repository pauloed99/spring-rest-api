package com.example.demo.repository;

import java.util.List;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Person;
import com.example.demo.utils.PersonCreator;


@DataJpaTest
@DisplayName("Tests of Person Repository")
class PersonRepositoryTest {
	@Autowired
	private PersonRepository repository;

	@Test
	@DisplayName("Should be return all persons from database")
	void findAllPersons() {
		repository.save(PersonCreator.personToBeSaved());
		List<Person> persons = repository.findAll();
		Assertions.assertThat(persons).isNotEmpty();
		Assertions.assertThat(persons.get(0)).isInstanceOf(Person.class);
		Assertions.assertThat(persons.size()).isEqualTo(1);
	}
	
	@Test
	@DisplayName("Should be save a person in a database")
	void savePerson() {
		Person person = repository.save(PersonCreator.personToBeSaved());
		Assertions.assertThat(person).isNotNull();
		Assertions.assertThat(person.getEmail()).isEqualTo("paulo@email.com");
	}
	
	@Test
	@DisplayName("Should be update a person in database")
	void updatePerson() {
		Person person = repository.save(PersonCreator.personToBeSaved());
		person.setLastname("Eduardo");
		Person personUpdated = repository.save(PersonCreator.personToBeUpdated(person.getId(), person.getAdress().getId()));
		Assertions.assertThat(personUpdated.getLastname()).isEqualTo("Eduardo");
		Assertions.assertThat(personUpdated.getAdress().getDistrict()).isEqualTo("Aldeota");
	}
	
	@Test
	@DisplayName("Should be delete a person in database")
	void deletePersonById() {
		Person person = repository.save(PersonCreator.personToBeSaved());
		repository.deleteById(person.getId());
		Optional<Person> personOptional = repository.findById(person.getId());
		Assertions.assertThat(personOptional.isEmpty()).isTrue();
	}
	
	@Test
	@DisplayName("Should be find a person in database")
	void findPersonById() {
		Person person = repository.save(PersonCreator.personToBeSaved());
		Optional<Person> personOptional = repository.findById(person.getId());
		Assertions.assertThat(personOptional).isNotNull();
		Assertions.assertThat(personOptional).contains(person);
	}
	
	@Test
	@DisplayName("Should be not find a person in database")
	void notFindPersonById() {
		Optional<Person> personOptional = repository.findById(7L);
		Assertions.assertThat(personOptional.isEmpty()).isTrue();
	}

}
