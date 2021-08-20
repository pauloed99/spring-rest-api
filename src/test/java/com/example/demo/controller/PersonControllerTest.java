package com.example.demo.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.model.Person;
import com.example.demo.security.JWTConfig;
import com.example.demo.service.PersonService;
import com.example.demo.utils.PersonCreator;

@ExtendWith(SpringExtension.class)
class PersonControllerTest {

	@InjectMocks
	PersonController personController;

	@Mock
	PersonService personServiceMock;
	
	@Mock
	JWTConfig jwtConfigMock;
	
	@Mock
	PasswordEncoder passwordEncoder;

	@BeforeEach
	void setUp() {
		PageImpl<Person> persons = new PageImpl<>(List.of(PersonCreator.personToBeSavedMock()));
		PageImpl<Person> personsByGender = new PageImpl<>(List.of(PersonCreator.personToBeSavedByGenderMock()));
		PageImpl<Person> personsByAdressState = new PageImpl<>(List.of(PersonCreator.personToBeSavedByAdressStateMock()));
		PageImpl<Person> personsByAdressCity = new PageImpl<>(List.of(PersonCreator.personToBeSavedByAdressCityMock()));
		Person person = PersonCreator.personToBeSavedMock();
		BDDMockito.when(personServiceMock.findAll(ArgumentMatchers.any())).thenReturn(persons);
		BDDMockito.when(personServiceMock.findByGender(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(personsByGender);
		BDDMockito.when(personServiceMock.findByAdressCity(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(personsByAdressCity);
		BDDMockito.when(personServiceMock.findByAdressState(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(personsByAdressState);
		BDDMockito.when(personServiceMock.findById(ArgumentMatchers.anyLong())).thenReturn(person);
		BDDMockito.when(personServiceMock.save(ArgumentMatchers.any())).thenReturn(person);
		BDDMockito.doNothing().when(personServiceMock).update(ArgumentMatchers.any());
		BDDMockito.doNothing().when(personServiceMock).deleteById(ArgumentMatchers.any());
		BDDMockito.doNothing().when(personServiceMock).updatePassword(ArgumentMatchers.any());
		BDDMockito.when(personServiceMock.findByEmail(ArgumentMatchers.anyString())).thenReturn(person);
		BDDMockito.when(jwtConfigMock.extractUsername(ArgumentMatchers.anyString())).thenReturn("paulo@email.com");
		BDDMockito.when(passwordEncoder.encode(ArgumentMatchers.anyString())).thenReturn("qdqwdwqdwqylxalyxal");
		BDDMockito.when(passwordEncoder.matches(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(true);
	}

	@Test
	@DisplayName("Should be return a list of all persons")
	void findAll() {
		Page<Person> personsPage = personController.get(null, Map.of()).getBody();
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
	void findAllByGender() {
		Page<Person> personsByGender = personController.get(null, Map.of("gender", "Feminino")).getBody();
		
		Assertions.assertThat(personsByGender.getContent().get(0).getGender()).isEqualTo("Feminino");
		Assertions.assertThat(personsByGender.getContent().get(0).getFirstname()).isEqualTo("Thais");
	}
	
	@Test
	@DisplayName("Should be return a list of persons by adress state")
	void findAllByAdressState() {
		Page<Person> personsByAdressState = personController.get(null, Map.of("adressState", "São Paulo")).getBody();
		
		Assertions.assertThat(personsByAdressState.getContent().get(0).getAdress().getState()).isEqualTo("São Paulo");
		Assertions.assertThat(personsByAdressState.getContent().get(0).getFirstname()).isEqualTo("Renata");
	}
	
	@Test
	@DisplayName("Should be return a list of persons by adress city")
	void findAllByAdressCity() {
		Page<Person> personsByAdressState = personController.get(null, Map.of("adressCity", "Guaramiranga")).getBody();
		
		Assertions.assertThat(personsByAdressState.getContent().get(0).getAdress().getCity()).isEqualTo("Guaramiranga");
		Assertions.assertThat(personsByAdressState.getContent().get(0).getFirstname()).isEqualTo("Carlos");
	}
	
	@Test
	@DisplayName("Should be return a person by id")
	void findById() {
		Person person = personController.getById(1L).getBody();
		
		Assertions.assertThat(person.getFirstname()).isEqualTo("Paulo");
		Assertions.assertThat(person.getId()).isEqualTo(1L);
	}
	
	@Test
	@DisplayName("Should be return null when person not exists")
	void findByIdAndReturnNull() {
		BDDMockito.when(personController.getById(ArgumentMatchers.anyLong()))
			.thenReturn(null);
		
		Person person = personController.getById(1L).getBody();
		
		Assertions.assertThat(person).isNull();
	}
	
	@Test
	@DisplayName("Should be save a person and return her saved")
	void save() {
		Person person = personController.post(PersonCreator.personPostDTOMock()).getBody();
		
		Assertions.assertThat(person).isNotNull();
		Assertions.assertThat(person.getLastname()).isEqualTo("Dutra");
	}
	
	@Test
	@DisplayName("Should be put a person")
	void put() {
		Assertions.assertThatCode(()-> personController.put(PersonCreator.personPutDTOMock(), PersonCreator.TokenJWT()))
			.doesNotThrowAnyException();
	}
	
	@Test
	@DisplayName("should be put the password of a person")
	void putPassword() {
		Assertions.assertThatCode(()-> personController.putPassword(PersonCreator.PasswordPutDTOMock(), PersonCreator.TokenJWT()))
		.doesNotThrowAnyException();
	}
	
	@Test
	@DisplayName("Should be delete a person")
	void delete() {
		Assertions.assertThatCode(() -> personController.delete(null))
		.doesNotThrowAnyException();
	}

}
