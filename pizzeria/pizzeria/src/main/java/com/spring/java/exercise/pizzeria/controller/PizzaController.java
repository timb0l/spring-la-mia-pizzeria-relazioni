package com.spring.java.exercise.pizzeria.controller;


import com.spring.java.exercise.pizzeria.model.Ingredient;
import com.spring.java.exercise.pizzeria.model.Pizza;
import com.spring.java.exercise.pizzeria.repository.IngredientRepository;
import com.spring.java.exercise.pizzeria.repository.PizzaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @GetMapping
    public String index(@RequestParam(name = "keyword")Optional<String> search, Model model){
        List<Pizza> pizzaList;
        String keyword = "";
        if(search.isPresent()){
            keyword=search.get();
            pizzaList = pizzaRepository.findByNameContainingOrDescriptionContaining(keyword,keyword);
        }else {
            pizzaList= pizzaRepository.findAll();
        }
    model.addAttribute("pizza", pizzaList);
    model.addAttribute("keyword", keyword);
    return "pizze/list";
    }

    @GetMapping("/show/{pizzaId}")
    public String show(@PathVariable("pizzaId") Integer id, Model model){
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);
        if (pizzaOptional.isPresent()){
            Pizza pizzadb = pizzaOptional.get();
            return "pizze/detail";
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/create")
    public String create(Model model){
        List<Ingredient> ingredientList = ingredientRepository.findAll();
        model.addAttribute("ingredientList", ingredientList);
        model.addAttribute("pizze", new Pizza());
        return "pizze/form";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("pizze") Pizza formPizza, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "pizze/form";
        } pizzaRepository.save(formPizza);
        return "redirect:/pizza";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id")Integer id, Model model){
        Optional<Pizza> result = pizzaRepository.findById(id);
        if(result.isPresent()){
            model.addAttribute("pizze", result.get());
            model.addAttribute("ingredientList", ingredientRepository.findAll());
            return "pizze/update";
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza " + id + " not found.");
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("pizze") Pizza formPizza, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("ingredientList", ingredientRepository.findAll());
            return "pizze/update";
        }pizzaRepository.save(formPizza);
        return "redirect:/pizza";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        pizzaRepository.deleteById(id);
        return "redirect:/pizza";
    }
}