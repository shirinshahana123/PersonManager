package com.example.personmanager.repository;

import com.example.personmanager.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Integer> {
    List<Person> findByNameIgnoreCase(String name);
}

