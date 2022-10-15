package by.evgen.cafe.service;

import by.evgen.cafe.exception.UserNotFoundException;
import by.evgen.cafe.model.impl.CafeUserModel;

public interface UserService extends CafeService<CafeUserModel> {
    CafeUserModel findByLoginAndPassword(String login, String password) throws UserNotFoundException;

    CafeUserModel findByLogin(String login) throws UserNotFoundException;
}
