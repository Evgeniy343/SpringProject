package by.evgen.cafe.service;

import by.evgen.cafe.exception.MealNotFoundException;
import by.evgen.cafe.model.impl.MealModel;
import by.evgen.cafe.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CafeMealService implements MealService {
    public static final String MEAL_NOT_FOUND_MESSAGE = "This meal not found";
    private final MealRepository repository;

    @Autowired
    public CafeMealService(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public MealModel findById(Long id) throws MealNotFoundException {
        return repository.findById(id).orElseThrow(() -> new MealNotFoundException(MEAL_NOT_FOUND_MESSAGE));
    }

    @Override
    public List<MealModel> findAll() {
        List<MealModel> meals = new ArrayList<>();
        repository.findAll().forEach(meals::add);
        return meals;
    }

    @Override
    public void update(MealModel meal) throws MealNotFoundException {
        MealModel mealShouldBeUpdated = repository.findById(meal.getId())
                .orElseThrow(() -> new MealNotFoundException(MEAL_NOT_FOUND_MESSAGE));
        mealShouldBeUpdated.setName(meal.getName());
        mealShouldBeUpdated.setPrice(meal.getPrice());
        mealShouldBeUpdated.setCategory(meal.getCategory());
        repository.save(mealShouldBeUpdated);
    }

    @Override
    public void save(MealModel meal) {
        repository.save(meal);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }


    @Override
    public MealModel findByName(String name) throws MealNotFoundException {
        List<MealModel> meals = new ArrayList<>();
        repository.findAll().forEach(meals::add);
        for (MealModel meal : meals) {
            if (meal.getName().equals(name)) {
                return meal;
            }
        }
        throw new MealNotFoundException(MEAL_NOT_FOUND_MESSAGE);
    }
}
