package by.evgen.Cafe.service;

import by.evgen.Cafe.exception.UserNotFoundException;
import by.evgen.Cafe.model.CafeUserModel;
import by.evgen.Cafe.repository.CafeUserRepository;

import java.util.ArrayList;
import java.util.List;

public class CafeUserService {
    private CafeUserRepository userRepository;

    public CafeUserModel findById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("This user not found!"));
    }

    public List<CafeUserModel> findAll(){
        List<CafeUserModel> cafeUsers = new ArrayList<>();
        userRepository.findAll().forEach(cafeUsers::add);
        return cafeUsers;
    }

    public void saveUser(CafeUserModel cafeUser){
        userRepository.save(cafeUser);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

}
