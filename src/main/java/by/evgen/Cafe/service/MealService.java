package by.evgen.Cafe.service;

import by.evgen.Cafe.exception.MealNotFoundException;
import by.evgen.Cafe.model.impl.MealModel;
import by.evgen.Cafe.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MealService implements CafeService<MealModel>{
    public static final String MEAL_NOT_FOUND_MESSAGE = "This meal not found";
    private final MealRepository repository;

    @Autowired
    public MealService(MealRepository repository) {
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
    public void save(MealModel meal) throws MealNotFoundException {
        MealModel mealShouldBeUpdated = repository.findById(meal.getId())
                .orElseThrow(() -> new MealNotFoundException(MEAL_NOT_FOUND_MESSAGE));
        mealShouldBeUpdated.setName(meal.getName());
        mealShouldBeUpdated.setPrice(meal.getPrice());
        mealShouldBeUpdated.setCategory(meal.getCategory());
        repository.save(mealShouldBeUpdated);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
