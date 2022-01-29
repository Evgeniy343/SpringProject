package by.evgen.Cafe.repository;

import by.evgen.Cafe.model.CafeUserModel;
import org.springframework.data.repository.CrudRepository;

public interface CafeUserRepository extends CrudRepository<CafeUserModel, Long> {
}
