package com.example.personmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue
    private int id;
    private String model;
    @ManyToOne
    @JoinColumn(name="owner_id")
    @JsonIgnore
    private Person owner;

}
