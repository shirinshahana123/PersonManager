package com.example.personmanager.controller;

import com.example.personmanager.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/cars")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @GetMapping
    ResponseEntity getAllcars(){
         return ResponseEntity.ok(carRepository.findAll());
    }


}
