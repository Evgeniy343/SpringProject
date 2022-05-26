package by.evgen.Cafe.service;

import by.evgen.Cafe.exception.UserNotFoundException;
import by.evgen.Cafe.model.impl.CafeUserModel;

public interface UserService extends CafeService<CafeUserModel> {
    CafeUserModel findByLoginAndPassword(String login, String password) throws UserNotFoundException;

    CafeUserModel findByLogin(String login) throws UserNotFoundException;
}
