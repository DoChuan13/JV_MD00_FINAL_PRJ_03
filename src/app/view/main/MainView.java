package app.view.main;

import app.config.InputConfig;
import app.config.MenuConst;
import app.config.ValidateConfig;
import app.controller.UserController;
import app.dto.request.SignInDTO;
import app.dto.request.SignUpDTO;
import app.dto.responese.ResponseMessage;
import app.model.RoleName;
import app.model.User;
import app.view.admin.AdminView;
import app.view.pm.PmView;
import app.view.user.UserView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainView {
    private static final UserController userController = new UserController();
    private static User loginUser = userController.getLoginUser();
    private static User tempUser = new User();
    private static String userName = "", password = "", rePassword = "";

    public static void exitApplication() {
        MainUI.showAlertExitApp();
        System.exit(0);
    }

    public static void showInvalidOption() {
        System.out.print(MenuConst.INVALID_OPTION);
    }


    /*========================================Main Index========================================*/

    public void showMainIndex() {
        MainUI.showMenuMain();
        System.out.print(MenuConst.CHOOSE_OPTION);
        int option = InputConfig.getInteger();
        switch (option) {
            case 1:
                loginUser();
                break;
            case 2:
                registerNewUser();
                break;
            case 0:
                MainView.exitApplication();
            default:
                MainUI.showMenuMain();
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                showMainIndex();
        }
    }

    public void loginUser() {
        int option = -1;
        MainUI.showMenuLogin(option, userName, password);
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 1:
                getUserName(option);
                break;
            case 2:
                getPassword(option);
                break;
            case 3:
                checkLogin(option);
                break;
            case 9:
                showMainIndex();
                break;
            case 0:
                MainView.exitApplication();
            default:
                MainUI.showMenuLogin(option, userName, password);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                loginUser();
        }
    }

    public void registerNewUser() {
        int option = -1;
        MainUI.showMenuRegister(option, tempUser, rePassword);
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 1:
                getNameRegister(option);
                break;
            case 2:
                getUserNameRegister(option);
                break;
            case 3:
                getEmailRegister(option);
                break;
            case 4:
                getPasswordRegister(option);
                break;
            case 5:
                getRePasswordRegister(option);
                break;
            case 6:
                checkRegister();
                break;
            case 9:
                tempUser = new User();
                showMainIndex();
                break;
            case 0:
                MainView.exitApplication();
            default:
                MainUI.showMenuRegister(option, tempUser, rePassword);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                registerNewUser();
        }
    }

    /*========================================Login Detail========================================*/

    private void checkLogin(int option) {
        MainUI.showMenuLogin(option, userName, password);
        boolean emptyField = userName.equalsIgnoreCase("") || password.equalsIgnoreCase("");
        if (emptyField) {
            System.out.print(MenuConst.EMPTY_ALERT);
            InputConfig.pressAnyKey();
            loginUser();
        } else if (!checkValidateLogin(userName, password)) {
            InputConfig.pressAnyKey();
            loginUser();
        } else {
            resetTempValue();
            User user = userController.getLoginUser();
            RoleName roleName = new ArrayList<>(user.getRole()).get(0).getName();
            MainUI.showMenuWelcome(loginUser);
            System.out.print(MenuConst.DIRECT_TO_DETAIL);
            if (roleName == RoleName.USER) {
                //System.out.println("Welcome User");
                InputConfig.pressAnyKey();
                new UserView().managerUserDetail();
            } else if (roleName == RoleName.PM) {
                //System.out.println("Welcome Product Manager");
                InputConfig.pressAnyKey();
                new PmView().managerPmDetail();
            } else {
                //System.out.println("Welcome Admin");
                InputConfig.pressAnyKey();
                new AdminView().managerAdminDetail();
            }
        }
    }

    private boolean checkValidateLogin(String userName, String password) {
        SignInDTO signInDTO = new SignInDTO(userName, password);
        ResponseMessage message = userController.login(signInDTO);
        System.out.println(message.getMessage());
        return message.getMessage().equals(MenuConst.LOGIN_SUCCESS);
    }

    private void getPassword(int option) {
        MainUI.showMenuLogin(option, userName, password);
        System.out.print(MenuConst.INPUT_PASSWORD);
        password = InputConfig.getString();
        loginUser();
    }

    private void getUserName(int option) {
        MainUI.showMenuLogin(option, userName, password);
        System.out.print(MenuConst.INPUT_USERNAME);
        userName = InputConfig.getString();
        loginUser();
    }

    /*========================================Log Out========================================*/
    public void logout() {
        userController.logoutUser();
        showMainIndex();
    }

    /*========================================Register Detail========================================*/

    private void checkRegister() {
        boolean emptyField = tempUser.getName().equalsIgnoreCase("") || tempUser.getUserName().equalsIgnoreCase("") || tempUser.getEmail().equalsIgnoreCase("") || tempUser.getPassword().equalsIgnoreCase("") || rePassword.equalsIgnoreCase("");
        if (emptyField) {
            System.out.print(MenuConst.EMPTY_ALERT);
            InputConfig.pressAnyKey();
            registerNewUser();
        } else if (!tempUser.getPassword().equals(rePassword)) {
            System.out.print(MenuConst.UNMATCHED_PASSWORD);
            InputConfig.pressAnyKey();
            registerNewUser();
        } else {
            int id = userController.generateUserId();
            Set<String> role = new HashSet<>();
            role.add(MenuConst.ROLE_USER);
            SignUpDTO signUpDTO = new SignUpDTO(id, tempUser.getName(), tempUser.getUserName(), tempUser.getEmail(), tempUser.getPassword(), role);
            ResponseMessage message = userController.register(signUpDTO);
            if (message.getMessage().equals(MenuConst.EXIST_USER_ACCOUNT)) {
                System.out.print(MenuConst.EXIST_USER_ACCOUNT);
                registerNewUser();
            } else if (message.getMessage().equals(MenuConst.EXIST_EMAIL)) {
                System.out.print(MenuConst.EXIST_EMAIL);
                registerNewUser();
            } else {
                resetTempValue();
                System.out.println(MenuConst.CREATE_USER_SUCCESS);
                InputConfig.pressAnyKey();
                loginUser();
            }
        }
    }

    private void getRePasswordRegister(int option) {
        while (true) {
            MainUI.showMenuRegister(option, tempUser, rePassword);
            System.out.println(MenuConst.REQUIRE_PASSWORD);
            System.out.print(MenuConst.INPUT_RE_PASSWORD);
            String input = InputConfig.getString();
            boolean validatePassword = ValidateConfig.validatePassword(input);
            if (validatePassword) {
                rePassword = input;
                break;
            } else {
                MainUI.showMenuRegister(option, tempUser, rePassword);
                System.out.print(MenuConst.INVALID_PASSWORD);
                InputConfig.pressAnyKey();
            }
        }
        registerNewUser();
    }

    private void getPasswordRegister(int option) {
        while (true) {
            MainUI.showMenuRegister(option, tempUser, rePassword);
            System.out.println(MenuConst.REQUIRE_PASSWORD);
            System.out.print(MenuConst.INPUT_PASSWORD);
            String input = InputConfig.getString();
            boolean validatePassword = ValidateConfig.validatePassword(input);
            if (validatePassword) {
                tempUser.setPassword(input);
                break;
            } else {
                MainUI.showMenuRegister(option, tempUser, rePassword);
                System.out.print(MenuConst.INVALID_PASSWORD);
                InputConfig.pressAnyKey();
            }
        }
        registerNewUser();
    }

    private void getEmailRegister(int option) {
        while (true) {
            MainUI.showMenuRegister(option, tempUser, rePassword);
            System.out.println(MenuConst.REQUIRE_EMAIL);
            System.out.print(MenuConst.INPUT_EMAIL);
            String input = InputConfig.getString();
            boolean validateEmail = ValidateConfig.validateEmail(input);
            if (validateEmail) {
                tempUser.setEmail(input);
                break;
            } else {
                MainUI.showMenuRegister(option, tempUser, rePassword);
                System.out.print(MenuConst.INVALID_EMAIL);
                InputConfig.pressAnyKey();
            }
        }
        registerNewUser();
    }

    private void getUserNameRegister(int option) {
        while (true) {
            MainUI.showMenuRegister(option, tempUser, rePassword);
            System.out.println(MenuConst.REQUIRE_ACCOUNT);
            System.out.print(MenuConst.INPUT_USERNAME);
            String input = InputConfig.getString();
            boolean validateAccount = ValidateConfig.validateUserName(input);
            if (validateAccount) {
                tempUser.setUserName(input);
                break;
            } else {
                MainUI.showMenuRegister(option, tempUser, rePassword);
                System.out.print(MenuConst.INVALID_ACCOUNT);
                InputConfig.pressAnyKey();
            }
        }
        registerNewUser();
    }

    private void getNameRegister(int option) {
        while (true) {
            MainUI.showMenuRegister(option, tempUser, rePassword);
            System.out.println(MenuConst.REQUIRE_NAME);
            System.out.print(MenuConst.INPUT_NAME);
            String input = InputConfig.getString();
            boolean validateName = ValidateConfig.validateName(input);
            if (validateName) {
                tempUser.setName(input);
                break;
            } else {
                MainUI.showMenuRegister(option, tempUser, rePassword);
                System.out.print(MenuConst.INVALID_NAME);
                InputConfig.pressAnyKey();
            }
        }
        registerNewUser();
    }

    private void resetTempValue() {
        userName = password = rePassword = "";
        tempUser = new User();
        loginUser = userController.getLoginUser();
    }
}
