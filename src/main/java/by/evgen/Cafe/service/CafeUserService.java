package by.evgen.Cafe.service;

import by.evgen.Cafe.exception.MealNotFoundException;
import by.evgen.Cafe.exception.UserNotFoundException;
import by.evgen.Cafe.model.impl.CafeUserModel;
import by.evgen.Cafe.model.impl.MealModel;
import by.evgen.Cafe.repository.CafeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CafeUserService implements CafeService<CafeUserModel>{
    public static final String USER_NOT_FOUND_MESSAGE = "This user not found!";
    private final CafeUserRepository repository;

    @Autowired
    public CafeUserService(CafeUserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public CafeUserModel findById(Long id) throws UserNotFoundException {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE));
    }

    @Override
    public List<CafeUserModel> findAll(){
        List<CafeUserModel> cafeUsers = new ArrayList<>();
        repository.findAll().forEach(cafeUsers::add);
        return cafeUsers;
    }

    @Override
    public void save(CafeUserModel cafeUser) throws UserNotFoundException {
        CafeUserModel userShouldBeUpdated = repository.findById(cafeUser.getId())
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE));
        userShouldBeUpdated.setLogin(cafeUser.getLogin());
        userShouldBeUpdated.setPassword(cafeUser.getPassword());
        userShouldBeUpdated.setRole(cafeUser.getRole());
        userShouldBeUpdated.setName(cafeUser.getName());
        repository.save(userShouldBeUpdated);
    }

    @Override
    public void deleteById(Long id){
        repository.deleteById(id);
    }

}
