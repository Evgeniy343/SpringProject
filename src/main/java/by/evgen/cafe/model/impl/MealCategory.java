package by.evgen.cafe.model.impl;

public enum MealCategory {
    SHAWARMA("Shawarma"),
    PIZZA("Pizza"),
    SUSHI("Sushi"),
    BURGERS("Burgers"),
    HOT("Hot"),
    GRILLED("Grilled"),
    DESSERT("Dessert");

    private final String name;

    MealCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
