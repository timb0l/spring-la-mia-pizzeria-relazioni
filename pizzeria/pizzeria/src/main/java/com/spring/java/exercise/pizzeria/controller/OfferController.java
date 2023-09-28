package com.spring.java.exercise.pizzeria.controller;

import com.spring.java.exercise.pizzeria.model.Pizza;
import com.spring.java.exercise.pizzeria.model.SpecialOffer;
import com.spring.java.exercise.pizzeria.repository.OfferRepository;
import com.spring.java.exercise.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/offers")
public class OfferController {
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/create")
    public String create(@RequestParam("pizzaId") Integer pizzaId, Model model){
        Optional<Pizza> pizzaResult = pizzaRepository.findById(pizzaId);
        if (pizzaResult.isPresent()){
            Pizza pizza = pizzaResult.get();
            SpecialOffer specialOffer = new SpecialOffer();
            specialOffer.setPizza(pizza);
            specialOffer.setTitle("Title");
            specialOffer.setStartDate(LocalDate.now());
            specialOffer.setEndDate(LocalDate.now().plusDays(30));
            model.addAttribute("specialOffer", specialOffer);
            return "offers/form";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza " + pizzaId + " not found.");
        }
    }
    @PostMapping("/create")
    public String doCreate(@ModelAttribute("specialOffer") SpecialOffer specialOffer){
        offerRepository.save(specialOfferForm);
        return "redirect:/pizza/show" + specialOfferForm.getPizza().getId();
    }
    @GetMapping("/edit/{offerId}")
    public String edit(@PathVariable("offerId")Integer id, Model model) {
        Optional<SpecialOffer> specialOfferResult = offerRepository.findById(id);
        if (specialOfferResult.isPresent()) {
            model.addAttribute("specialOffer", specialOfferResult.get());
            return "offers/edit";
        } else {
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/edit/{offerId}")
    public String doEdit(@PathVariable("offerId") Integer offerId, @ModelAttribute("specialOffer") SpecialOffer specialOffer){
        specialOfferForm.setId(offerId);
        offerRepository.save(specialOfferForm);
        return "redirect:/pizza/show/" + specialOfferForm.getPizza().getId();
    }
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Optional<SpecialOffer> specialOfferResult = offerRepository.findById(id);
        if (specialOfferResult.isPresent()){
            Integer pizzaId = specialOfferResult.get().getPizza().getId();
            return "redirect:/pizza/show" + pizzaId;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}



