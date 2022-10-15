package by.evgen.cafe.model.impl;

import by.evgen.cafe.aspect.LoggingMealModel;
import by.evgen.cafe.model.CafeModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Log4j2
@Entity
@LoggingMealModel
@NoArgsConstructor
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
    @NotNull(message = "Meal price should not be empty")
    @Min(value = 0, message = "Meal price should be greater than 0")
    private Double price;

    @Column(name = "category_id")
    @Enumerated(EnumType.ORDINAL)
    private MealCategory category;

    @Column(name = "image_name")
    private String image;

    public MealModel(String name, Double price, MealCategory category, String image) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public String getImage() {
        return "/cafe/img/menu/" + image;
    }
}

