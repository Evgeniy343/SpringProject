package by.evgen.Cafe.model.impl;

import by.evgen.Cafe.model.CafeModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "meal")
@NoArgsConstructor
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
    @NotNull(message = "Meal price should not be empty")
    @Min(value = 0,message = "Meal price should be greater than 0")
    private Double price;

    @Column(name = "category_id")
    @Enumerated(EnumType.ORDINAL)
    private MealCategory category;

    public MealModel(String name, Double price, MealCategory category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
