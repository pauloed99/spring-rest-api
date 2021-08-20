package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Adress {
	
	public Adress() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String state;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String district;
	
	@Column(nullable = false)
	private String street;

	@Column(nullable = false)
	private String streetNumber;
	
	private String apartmentNumber;
	
	@JsonIgnore
	@OneToOne(mappedBy = "adress")
	private Person person;
	
}
