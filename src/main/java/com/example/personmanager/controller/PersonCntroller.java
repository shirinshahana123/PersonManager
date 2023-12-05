package com.example.personmanager.controller;

import com.example.personmanager.model.Person;
import com.example.personmanager.repository.PersonRepository;
import com.example.personmanager.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/person")

public class PersonCntroller {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    @RequestMapping
    public List<Person> listOfAllPersons(@RequestParam(value = "name", required = false)  String name){
        if(name==null)
            return personRepository.findAll();
        return personRepository.findByNameIgnoreCase(name);


    }
    @RequestMapping(value="/{id}")
     ResponseEntity  getAPersonWithId(@PathVariable("id") Integer id){

        Optional<Person> optionalPerson = personRepository.findById(id);
        if(optionalPerson.isPresent())
            return ResponseEntity.ok(optionalPerson.get());
        return ResponseEntity.badRequest().body("Could not find any person...");

    }

    @PutMapping(value="/{id}")
    ResponseEntity  updatePerson(@PathVariable("id") Integer id, @RequestBody Person person){

        return personService.updatePersonService(id, person);


    }




}
