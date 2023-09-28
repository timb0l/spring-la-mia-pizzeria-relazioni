package com.spring.java.exercise.pizzeria.repository;

import com.spring.java.exercise.pizzeria.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public class IngredientRepository implements JpaRepository<Ingredient, Integer> {
}
