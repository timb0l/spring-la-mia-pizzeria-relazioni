package com.spring.java.exercise.pizzeria.repository;

import com.spring.java.exercise.pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    List<Pizza> findByNameContainingOrDescriptionContaining(String NameSearch, String descriptionSearch);
}
