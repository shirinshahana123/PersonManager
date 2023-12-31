package com.example.personmanager.service;

import com.example.personmanager.model.Person;
import com.example.personmanager.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;
    public ResponseEntity<?> updatePersonService(Integer id, Person person) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if(optionalPerson.isEmpty())
            return ResponseEntity.badRequest().body("Could not    find any person.....");

        Person existingPerson = optionalPerson.get();
        existingPerson.setAge(person.getAge());
        existingPerson.setName(person.getName());
        return ResponseEntity.ok(personRepository.save(existingPerson));
    }
}
