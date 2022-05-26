package by.evgen.Cafe.repository;

import by.evgen.Cafe.model.impl.CafeUserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CafeUserRepository extends CrudRepository<CafeUserModel, Long> {
    @Query("SELECT u FROM CafeUserModel u WHERE u.login = :login")
    CafeUserModel getUserByUsername(@Param("login") String login);
}
