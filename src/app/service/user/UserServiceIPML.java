package app.service.user;

import app.config.IOFileConfig;
import app.config.MenuConst;
import app.model.User;
import app.service.generic.IDataBaseService;
import init.DataBase;

import java.util.LinkedList;
import java.util.List;

public class UserServiceIPML implements IUserService, IDataBaseService<User> {
    private static final List<User> userList;

    static {
        userList = getDatabase(DataBase.PATH_USER);
    }

    private static List<User> getDatabase(String pathUser) {
        return new IOFileConfig<User>().readFile(pathUser);
    }

    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public void save(User user) {
        User currentUser = findById(user.getUserId());
        if (currentUser == null) {
            userList.add(user);
        } else {
            int index = userList.indexOf(currentUser);
            userList.set(index, user);
        }
        updateDatabase(DataBase.PATH_USER, userList);
    }

    public void synchronizedLoginUser(User user) {
        List<User> userLogin = new LinkedList<>();
        userLogin.add(user);
        updateDatabase(DataBase.PATH_USER_LOGIN, userLogin);
    }

    @Override
    public User findById(int id) {
        for (User user : userList) {
            if (user.getUserId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User delete(int id) {
        User currentUser = findById(id);
        if (currentUser != null) {
            userList.remove(currentUser);
        }
        updateDatabase(DataBase.PATH_USER, userList);
        return currentUser;
    }

    @Override
    public boolean existByUserName(String userName) {
        for (User user : userList) {
            if (user.getUserName().equalsIgnoreCase(userName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existByEmail(String email) {
        for (User user : userList) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String checkLogin(String userName, String password) {
        List<User> userLogin = new LinkedList<>();
        for (User user : userList) {
            if (user.getUserName().equalsIgnoreCase(userName)) {
                if (user.isStatus()) {
                    return MenuConst.BLOCKED_USER;
                }
                if (user.getPassword().equals(password)) {
                    userLogin.add(user);
                    updateDatabase(DataBase.PATH_USER_LOGIN, userLogin);
                    return MenuConst.LOGIN_SUCCESS;
                } else return MenuConst.PASSWORD_INCORRECT;
            }
        }
        return MenuConst.ACCOUNT_DONT_EXIST;
    }

    @Override
    public User getLoginUser() {
        List<User> userLogin = getDatabase(DataBase.PATH_USER_LOGIN);
        if (userLogin.size() != 0) {
            return userLogin.get(0);
        }
        return null;
    }

    @Override
    public List<User> findByName(String name) {
        List<User> searchResult = new LinkedList<>();
        for (User user : userList) {
            if (user.getName().toLowerCase().contains(name.toLowerCase())) {
                searchResult.add(user);
            }
        }
        return searchResult;
    }

    @Override
    public int generateUserId() {
        int id = 0;
        for (User user : userList) {
            if (user.getUserId() > id) {
                id = user.getUserId();
            }
        }
        return ++id;
    }

    @Override
    public void updateDatabase(String pathName, List<User> list) {
        new IOFileConfig<User>().writeFile(pathName, list);
    }
}
