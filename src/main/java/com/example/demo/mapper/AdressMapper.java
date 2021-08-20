package com.example.demo.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

import com.example.demo.DTO.AdressPostDTO;
import com.example.demo.DTO.AdressPutDTO;
import com.example.demo.model.Adress;

@Mapper
public abstract class AdressMapper {

	public static final AdressMapper INSTANCE = Mappers.getMapper(AdressMapper.class);

	public abstract Adress toAdress(AdressPostDTO adressPostDTO);
	
	public abstract Adress toAdress(AdressPutDTO adressPutDTO);

}
