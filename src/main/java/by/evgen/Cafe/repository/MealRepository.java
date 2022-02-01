package by.evgen.Cafe.repository;

import by.evgen.Cafe.model.impl.MealModel;
import org.springframework.data.repository.CrudRepository;

public interface MealRepository extends CrudRepository<MealModel,Long> {
}
