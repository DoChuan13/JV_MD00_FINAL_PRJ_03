package app.service.user;

import app.model.User;
import app.service.generic.IGenericService;

import java.util.List;

public interface IUserService extends IGenericService<User> {
    boolean existByUserName(String userName);

    boolean existByEmail(String email);

    String checkLogin(String userName, String password);

    User getLoginUser();

    List<User> findByName(String name);

    int generateUserId();
}
