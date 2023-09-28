package com.spring.java.exercise.pizzeria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pizze")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Fill in")
    private String name;
    @Size (min = 2, max = 100)
    @NotBlank(message = "Fill in")
    private String description;
    @NotNull (message = "Price cant be 0")
    @DecimalMin("0.5")
    private BigDecimal price;

    @OneToMany (mappedBy = "pizza", cascade = {CascadeType.REMOVE})
    private List<SpecialOffer> specialOffers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<SpecialOffer> getSpecialOffers() {
        return specialOffers;
    }

    public void setSpecialOffers(List<SpecialOffer> specialOffers) {
        this.specialOffers = specialOffers;
    }
    public boolean hasActiveSpecialOffer(){
        LocalDate currentDate = LocalDate.now();
        for (SpecialOffer offer : specialOffers){
            if (offer.getStartDate().isBefore(currentDate) && offer.getEndDate().isAfter(currentDate)){
                return true;
            }
        }
        return false;
    }
}
