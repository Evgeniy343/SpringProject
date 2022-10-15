package by.evgen.cafe.service;

import by.evgen.cafe.exception.MealNotFoundException;
import by.evgen.cafe.model.impl.MealModel;

public interface MealService extends CafeService<MealModel> {
    MealModel findByName(String name) throws MealNotFoundException;
}
