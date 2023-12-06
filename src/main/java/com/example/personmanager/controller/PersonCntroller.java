package com.example.personmanager.controller;

import com.example.personmanager.model.Person;
import com.example.personmanager.repository.PersonRepository;
import com.example.personmanager.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")

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


    @RequestMapping(value="/{id}/cars")
    ResponseEntity  getAllPersonOwnedCars(@PathVariable("id") Integer id){

        return ResponseEntity.ok(personService.getAllOwnedCars(id));

    }

    @PutMapping(value="/{id}")
    ResponseEntity  updatePerson(@PathVariable("id") Integer id, @RequestBody Person person){

        return personService.updatePersonService(id, person);


    }

    @DeleteMapping(value="/{id}")
    ResponseEntity deletePerson(@PathVariable("id") Integer id){
        personRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    ResponseEntity createPerson(@RequestBody Person person, UriComponentsBuilder uriComponentsBuilder){
        person.getOwnedCars().forEach(car -> car.setOwner(person));

        Person createdPerson = personRepository.save(person);
        URI location = uriComponentsBuilder.path("/api/persons/{id}").buildAndExpand(createdPerson.getId()).toUri();
        return ResponseEntity.created(location).body(createdPerson);
    }


}
