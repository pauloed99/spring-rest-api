package com.example.demo.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

import com.example.demo.DTO.PasswordPutDTO;
import com.example.demo.DTO.PersonPostDTO;
import com.example.demo.DTO.PersonPutDTO;
import com.example.demo.model.Person;

@Mapper
public abstract class PersonMapper {
	public static final PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
	
	public abstract Person toPerson(PersonPostDTO personPostDTO);
	public abstract Person toPerson(PersonPutDTO personPutDTO);
	public abstract Person toPerson(PasswordPutDTO passwordPutDTO);
}
