package com.example.demo.utils;

import com.example.demo.DTO.AdressPostDTO;
import com.example.demo.DTO.AdressPutDTO;
import com.example.demo.DTO.PasswordPutDTO;
import com.example.demo.DTO.PersonPostDTO;
import com.example.demo.DTO.PersonPutDTO;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.model.Adress;
import com.example.demo.model.Person;

public class PersonCreator {
	public static Person personToBeSaved() {
		PersonPostDTO person = new PersonPostDTO();
		AdressPostDTO adress = new AdressPostDTO();
		person.setFirstname("Paulo");
		person.setEmail("paulo@email.com");
		person.setLastname("Dutra");
		person.setCpf("111.000.111-00");
		person.setBirthDate("09/12/1999");
		person.setGender("Masculino");
		person.setPhone("8599991111");
		person.setPassword("12345678");
		person.setPasswordConfirm("12345678");
		adress.setState("Ceará");
		adress.setCity("Fortaleza");
		adress.setDistrict("Dionisio Torres");
		adress.setStreet("Rua Isac Amaral");
		adress.setStreetNumber("751");
		person.setAdress(adress);
		return PersonMapper.INSTANCE.toPerson(person);
	} 
	
	public static PersonPutDTO personPutDTOMock() {
		PersonPutDTO person = new PersonPutDTO();
		AdressPutDTO adress = new AdressPutDTO();
		person.setId(1L);
		person.setLastname("Eduardo");
		adress.setId(1L);
		adress.setDistrict("Aldeota");
		person.setAdress(adress);
		
		return person;
	}
	
	public static PasswordPutDTO PasswordPutDTOMock() {
		PasswordPutDTO person = new PasswordPutDTO();
		person.setId(1L);
		person.setOldPassword("12345678");
		person.setPassword("87654321");
		person.setPasswordConfirm("87654321");
		
		return person;
	}
	
	public static PersonPostDTO personPostDTOMock() {
		PersonPostDTO person = new PersonPostDTO();
		AdressPostDTO adress = new AdressPostDTO();
		person.setFirstname("Paulo");
		person.setEmail("paulo@email.com");
		person.setLastname("Dutra");
		person.setCpf("111.000.111-00");
		person.setBirthDate("09/12/1999");
		person.setGender("Masculino");
		person.setPhone("8599991111");
		person.setPassword("12345678");
		person.setPasswordConfirm("12345678");
		adress.setState("Ceará");
		adress.setCity("Fortaleza");
		adress.setDistrict("Dionisio Torres");
		adress.setStreet("Rua Isac Amaral");
		adress.setStreetNumber("751");
		person.setAdress(adress);
		
		return person;
	}
	
	public static Person personToBeSavedMock() {
		Person person = new Person();
		Adress adress = new Adress();
		person.setId(1L);
		person.setFirstname("Paulo");
		person.setEmail("paulo@email.com");
		person.setLastname("Dutra");
		person.setCpf("111.000.111-00");
		person.setBirthDate("09/12/1999");
		person.setGender("Masculino");
		person.setPhone("8599991111");
		adress.setId(1L);
		adress.setState("Ceará");
		adress.setCity("Fortaleza");
		adress.setDistrict("Dionisio Torres");
		adress.setStreet("Rua Isac Amaral");
		adress.setStreetNumber("751");
		person.setAdress(adress);
		return person;
	}
	
	public static Person personToBeSavedByAdressCityMock() {
		Person person = new Person();
		Adress adress = new Adress();
		person.setId(2L);
		person.setFirstname("Carlos");
		person.setEmail("carlos@email.com");
		person.setLastname("Dias");
		person.setCpf("111.001.111-00");
		person.setBirthDate("09/12/2000");
		person.setGender("Masculino");
		person.setPhone("8599991111");
		adress.setId(2L);
		adress.setState("Ceará");
		adress.setCity("Guaramiranga");
		adress.setDistrict("qdwqdwqdwqdw");
		adress.setStreet("dwqdwqdwqdq");
		adress.setStreetNumber("751");
		person.setAdress(adress);
		return person;
	}
	
	public static Person personToBeSavedByAdressStateMock() {
		Person person = new Person();
		Adress adress = new Adress();
		person.setId(2L);
		person.setFirstname("Renata");
		person.setEmail("renata@email.com");
		person.setLastname("Dias");
		person.setCpf("111.001.111-00");
		person.setBirthDate("09/12/2000");
		person.setGender("Feminino");
		person.setPhone("8599991111");
		adress.setId(2L);
		adress.setState("São Paulo");
		adress.setCity("São Paulo");
		adress.setDistrict("Santana");
		adress.setStreet("dadsdsads");
		adress.setStreetNumber("751");
		person.setAdress(adress);
		return person;
	}
	
	public static Person personToBeSavedByGenderMock() {
		Person person = new Person();
		Adress adress = new Adress();
		person.setId(2L);
		person.setFirstname("Thais");
		person.setEmail("thais@email.com");
		person.setLastname("Dias");
		person.setCpf("111.001.111-00");
		person.setBirthDate("09/12/2000");
		person.setGender("Feminino");
		person.setPhone("8599991111");
		adress.setId(2L);
		adress.setState("Ceará");
		adress.setCity("Fortaleza");
		adress.setDistrict("Montese");
		adress.setStreet("Rua Júlio César");
		adress.setStreetNumber("751");
		person.setAdress(adress);
		return person;
	}
	
	public static Person personToBeUpdated(Long personId, Long adressId) {
		PersonPutDTO person = new PersonPutDTO();
		AdressPutDTO adress = new AdressPutDTO();
		person.setId(personId);
		person.setLastname("Eduardo");
		adress.setId(adressId);
		adress.setDistrict("Aldeota");
		person.setAdress(adress);
		return PersonMapper.INSTANCE.toPerson(person);
	}
	
	public static Person personPasswordToBeUpdated(Long personId) {
		PasswordPutDTO person = new PasswordPutDTO();
		person.setId(1L);
		person.setOldPassword("12345678");
		person.setPassword("87654321");
		person.setPasswordConfirm("87654321");
		return PersonMapper.INSTANCE.toPerson(person);
	}
	
	public static String TokenJWT() {
		return "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXVsb0BlbWFpbC5jb20iLCJleHAiOjE2MjEyMTQzNDgsImlhdCI6MTYyMTE3ODM0OH0.UVTwa8nv-U3U9EZw5iKtvWKceVzi_R-btFJs6u7vd0U";
	}
}
