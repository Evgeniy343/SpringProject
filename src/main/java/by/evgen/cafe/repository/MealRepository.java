package by.evgen.cafe.repository;

import by.evgen.cafe.model.impl.MealModel;
import org.springframework.data.repository.CrudRepository;

public interface MealRepository extends CrudRepository<MealModel, Long> {
}
