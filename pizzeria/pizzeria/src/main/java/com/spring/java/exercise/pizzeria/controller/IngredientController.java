package com.spring.java.exercise.pizzeria.controller;

import com.spring.java.exercise.pizzeria.model.Ingredient;
import com.spring.java.exercise.pizzeria.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class IngredientController {
    @Autowired
    private IngredientRepository ingredientRepository;
    @GetMapping
    public String index(Model model){
        model.addAttribute("ingredientList", ingredientRepository.findAll());
        model.addAttribute("ingredientObject", new Ingredient());
        return "ingredientes/index";
    }
    @PostMapping("/create")
    public String doCreate(@ModelAttribute("ingredientObject") Ingredient ingredientForm){
        ingredientRepository.save(ingredientForm);
        return "redirect:/ingredients";
    }
    @PostMapping("/delete/{ingredientId")
    public String delete(@PathVariable("ingredienteId") Integer id){
        ingredientRepository.deleteById(id);
        return "redirect:/ingredients";
    }
}
