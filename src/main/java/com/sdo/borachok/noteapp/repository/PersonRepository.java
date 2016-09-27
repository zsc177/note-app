package com.sdo.borachok.noteapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdo.borachok.noteapp.model.person.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

	Person findByEmail(String email);

}
