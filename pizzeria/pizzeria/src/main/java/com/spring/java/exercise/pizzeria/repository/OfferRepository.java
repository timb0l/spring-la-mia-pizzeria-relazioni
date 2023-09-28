package com.spring.java.exercise.pizzeria.repository;

import com.spring.java.exercise.pizzeria.model.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<SpecialOffer, Integer> {
}
