package by.evgen.Cafe.model.impl;

import by.evgen.Cafe.model.CafeModel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "meal")
public class MealModel implements CafeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Meal name should not be empty")
    @Size(min = 2, max = 50, message = "Meal name should be between 2 and 50 characters")
    private String name;

    @Column(name = "price")
    @NotEmpty(message = "Meal price should not be empty")
    @Min(value = 0,message = "Meal price should be greater than 0")
    private Double price;

    @Column(name = "category_id")
    @Enumerated(EnumType.ORDINAL)
    @NotEmpty(message = "Meal category should not be empty")
    private MealCategory category;
}
