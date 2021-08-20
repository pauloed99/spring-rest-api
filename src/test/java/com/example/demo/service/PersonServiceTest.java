package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.utils.PersonCreator;

@ExtendWith(SpringExtension.class)
class PersonServiceTest {
	
	@InjectMocks
	PersonService personService;
	
	@Mock
	PersonRepository personRepositoryMock;

	@BeforeEach
	void setUp() {
		PageImpl<Person> persons = new PageImpl<>(List.of(PersonCreator.personToBeSavedMock()));
		PageImpl<Person> personsByGender = new PageImpl<>(List.of(PersonCreator.personToBeSavedByGenderMock()));
		PageImpl<Person> personsByAdressState = new PageImpl<>(List.of(PersonCreator.personToBeSavedByAdressStateMock()));
		PageImpl<Person> personsByAdressCity = new PageImpl<>(List.of(PersonCreator.personToBeSavedByAdressCityMock()));
		Optional<Person> personOptional = Optional.of(PersonCreator.personToBeSavedMock());
		Person person = PersonCreator.personToBeSavedMock();
		BDDMockito.when(personRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(persons);
		BDDMockito.when(personRepositoryMock.findByGender(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(personsByGender);
		BDDMockito.when(personRepositoryMock.findByAdressCity(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(personsByAdressCity);
		BDDMockito.when(personRepositoryMock.findByAdressState(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(personsByAdressState);
		BDDMockito.when(personRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(personOptional);
		BDDMockito.when(personRepositoryMock.save(ArgumentMatchers.any())).thenReturn(person);
		BDDMockito.doNothing().when(personRepositoryMock).deleteById(ArgumentMatchers.anyLong());
		BDDMockito.when(personRepositoryMock.findByEmail(ArgumentMatchers.anyString())).thenReturn(personOptional);
	}

	@Test
	@DisplayName("Should be return a page of all persons")
	void testFindAll() {
		Page<Person> personsPage = personService.findAll(PageRequest.of(1, 1));
		
		Long id = PersonCreator.personToBeSavedMock().getId();
		String lastname = PersonCreator.personToBeSavedMock().getLastname();
		String adressStreet = PersonCreator.personToBeSavedMock().getAdress().getStreet();

		Assertions.assertThat(personsPage.getSize()).isEqualTo(1);
		Assertions.assertThat(personsPage.getContent().get(0).getId()).isEqualTo(id);
		Assertions.assertThat(personsPage.getContent().get(0).getAdress().getStreet()).isEqualTo(adressStreet);
		Assertions.assertThat(personsPage.getContent().get(0).getLastname()).isEqualTo(lastname);
	}

	@Test
	@DisplayName("Should be return a list of persons by a gender")
	void testFindByGender() {
		Page<Person> personsByGender = personService.findByGender("Feminino", null);
		
		Assertions.assertThat(personsByGender.getContent().get(0).getGender()).isEqualTo("Feminino");
		Assertions.assertThat(personsByGender.getContent().get(0).getFirstname()).isEqualTo("Thais");
	}

	@Test
	@DisplayName("Should be return a list of persons by adress state")
	void testFindByAdressState() {
		Page<Person> personsByAdressState = personService.findByAdressState("São Paulo", null);
		
		Assertions.assertThat(personsByAdressState.getContent().get(0).getAdress().getState()).isEqualTo("São Paulo");
		Assertions.assertThat(personsByAdressState.getContent().get(0).getFirstname()).isEqualTo("Renata");
	}

	@Test
	@DisplayName("Should be return a list of persons by adress city")
	void testFindByAdressCity() {
		Page<Person> personsByAdressState = personService.findByAdressCity("Guaramiranga", null);
		
		Assertions.assertThat(personsByAdressState.getContent().get(0).getAdress().getCity()).isEqualTo("Guaramiranga");
		Assertions.assertThat(personsByAdressState.getContent().get(0).getFirstname()).isEqualTo("Carlos");
	}

	@Test
	@DisplayName("Should be return a person by id")
	void testFindById() {
		Person person = personService.findById(1L);
		
		Assertions.assertThat(person).isNotNull();
		Assertions.assertThat(person.getLastname()).isEqualTo("Dutra");
	}
	
	@Test
	@DisplayName("Should be return a exception when not find the person's id")
	void testFindByIdAndReturnException() {
		BDDMockito.when(personRepositoryMock.findById(ArgumentMatchers.anyLong()))
			.thenReturn(Optional.empty());
		
		Assertions.assertThatExceptionOfType(ResponseStatusException.class)
		.isThrownBy(()-> personService.findById(1L));
		
	}

	@Test
	@DisplayName("Should be save a person and return her saved")
	void testSave() {
		Person person = personService.save(null);
		System.out.println(person);
		
		Assertions.assertThat(person).isNotNull();
		Assertions.assertThat(person.getLastname()).isEqualTo("Dutra");
	}

	@Test
	@DisplayName("Should be delete a person")
	void testDeleteById() {
		Assertions.assertThatCode(()-> personService.deleteById(1L))
		.doesNotThrowAnyException();
	}
	
	@Test
	@DisplayName("should be put a the password of a person")
	void testUpdatePassword() {
		Assertions.assertThatCode(()-> personService.save(null))
		.doesNotThrowAnyException();
	}

	@Test
	@DisplayName("Should be put a person")
	void testUpdate() {
		Assertions.assertThatCode(()-> personService.save(null))
		.doesNotThrowAnyException();
	}

}
