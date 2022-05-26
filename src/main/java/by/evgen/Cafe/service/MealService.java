package by.evgen.Cafe.service;

import by.evgen.Cafe.exception.MealNotFoundException;
import by.evgen.Cafe.model.impl.MealModel;

public interface MealService extends CafeService<MealModel> {
    MealModel findByName(String name) throws MealNotFoundException;
}
