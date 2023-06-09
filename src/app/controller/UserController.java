package app.controller;

import app.config.IOFileConfig;
import app.config.MenuConst;
import app.dto.request.SignInDTO;
import app.dto.request.SignUpDTO;
import app.dto.responese.ResponseMessage;
import app.model.Role;
import app.model.RoleName;
import app.model.User;
import app.service.role.IRoleService;
import app.service.role.RoleServiceIPLM;
import app.service.user.UserServiceIPML;
import init.DataBase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {
    private final UserServiceIPML userService = new UserServiceIPML();
    private final IRoleService roleService = new RoleServiceIPLM();

    public UserController() {
    }

    public ResponseMessage register(SignUpDTO signUpDTO) {
        if (userService.existByUserName(signUpDTO.getUserName())) {
            return new ResponseMessage(MenuConst.EXIST_USER_ACCOUNT);
        }
        if (userService.existByEmail(signUpDTO.getEmail())) {
            return new ResponseMessage(MenuConst.EXIST_EMAIL);
        }
        Set<Role> roleSet = new HashSet<>();
        Set<String> strRole = signUpDTO.getStrRole();
        strRole.forEach(role -> {
            switch (role) {
                case MenuConst.ROLE_ADMIN -> roleSet.add(roleService.findByRoleName(RoleName.ADMIN));
                case MenuConst.ROLE_PM -> roleSet.add(roleService.findByRoleName(RoleName.PM));
                default -> roleSet.add(roleService.findByRoleName(RoleName.USER));
            }
        });
        User user = new User(signUpDTO.getId(), signUpDTO.getName(), signUpDTO.getUserName(),
                signUpDTO.getEmail(), signUpDTO.getPassword(), roleSet);
        userService.save(user);
        return new ResponseMessage(MenuConst.CREATE_USER_SUCCESS);
    }

    public ResponseMessage login(SignInDTO signInDTO) {
        String checkLogin = userService.checkLogin(signInDTO.getUserName(), signInDTO.getPassword());
        return switch (checkLogin) {
            case MenuConst.ACCOUNT_DONT_EXIST -> new ResponseMessage(MenuConst.ACCOUNT_DONT_EXIST);
            case MenuConst.PASSWORD_INCORRECT -> new ResponseMessage(MenuConst.PASSWORD_INCORRECT);
            case MenuConst.BLOCKED_USER -> new ResponseMessage(MenuConst.BLOCKED_USER);
            default -> new ResponseMessage(MenuConst.LOGIN_SUCCESS);
        };
    }

    public User getLoginUser() {
        return userService.getLoginUser();
    }

    public List<User> getUserList() {
        return userService.findAll();
    }

    public void updateCurrentUser(User user) {
        userService.save(user);
    }

    public void logoutUser() {
        new IOFileConfig<User>().clearFile(DataBase.PATH_USER_LOGIN);
    }

    public User findUserById(int id) {
        return userService.findById(id);
    }

    public boolean checkValidateExistAccount(User currentUser) {
        return userService.checkValidateExistAccount(currentUser);
    }

    public void synchronizedLoginUser(User loginUser) {
        userService.synchronizedLoginUser(loginUser);
    }

    public List<User> findUserWithoutMe(User loginUser, String name) {
        return userService.findUserWithoutMe(loginUser, name);
    }

    public void deleteUserInfo(User user) {
        userService.delete(user.getUserId());
    }

    public int generateUserId() {
        return userService.generateUserId();
    }
}
