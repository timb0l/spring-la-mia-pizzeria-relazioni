package com.spring.java.exercise.pizzeria.model;

import jakarta.persistence.*;

@Entity
@Table(name="ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String Name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
