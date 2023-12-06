package com.example.personmanager.repository;

import com.example.personmanager.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {

    List<Car> findAllByOwnerId(Integer owner_id);

}

