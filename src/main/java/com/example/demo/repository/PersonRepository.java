package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	Page<Person> findByGender(String gender, Pageable pageable);

	Page<Person> findByAdressState(String adressState, Pageable pageable);

	Page<Person> findByAdressCity(String adressCity, Pageable pageable);
	
	Optional<Person> findByEmail(String email);
	
}
