package app.view.user;

import app.config.InputConfig;
import app.config.MenuConst;
import app.config.ValidateConfig;
import app.controller.ChatController;
import app.controller.FriendController;
import app.controller.PostController;
import app.controller.UserController;
import app.model.User;
import app.view.main.MainView;
import app.view.user.ui.UserInfoUI;

import java.util.Date;

public class UserInfoView {
    private final UserController userController = new UserController();
    private final FriendController friendController = new FriendController();
    private final PostController postController = new PostController();
    private final ChatController chatController = new ChatController();
    private User loginUser = userController.getLoginUser();
    private long updatedEmailTime = loginUser.getEmailUpdatedTime().getTime();
    private long updatedUserNameTime = loginUser.getUserUpdatedTime().getTime();
    private String password = "", newPassword = "", rePassword = "";

    public UserInfoView() {
    }

    /*========================================View User Page Start========================================*/
    public void viewUserInfoPage() {
        int option = -1;
        UserInfoUI.showMenuUpdateUserInfo(option, loginUser, password);
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 7:
                changeUserInfo();
                break;
            case 8:
                changePasswordInfo();
                break;
            case 9:
                resetTempValue();
                new UserView().managerUserDetail();
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                UserInfoUI.showMenuUpdateUserInfo(option, loginUser, password);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                viewUserInfoPage();
        }
        new UserView().managerUserDetail();
    }

    private void changeUserInfo() {
        int option = 0;
        UserInfoUI.showMenuUpdateUserInfo(option, loginUser, password);
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 1:
                getUserNameInfo(option);
                break;
            case 2:
                getNameInfo(option);
                break;
            case 3:
                getSexInfo(option);
                break;
            case 4:
                getEmailInfo(option);
                break;
            case 5:
                inputPasswordInfo(option);
                break;
            case 7:
                checkUpdateUserInfo(option);
                break;
            case 9:
                resetTempValue();
                viewUserInfoPage();
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                UserInfoUI.showMenuUpdateUserInfo(option, loginUser, password);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                changeUserInfo();
        }
    }

    private void changePasswordInfo() {
        int option = 0;
        UserInfoUI.showMenuUpdatePassword(option, password, newPassword, rePassword);
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 1:
                inputCurrentPassword(option);
                break;
            case 2:
                inputNewPassword(option);
                break;
            case 3:
                inputReNewPassword(option);
                break;
            case 8:
                checkChangePassword();
                break;
            case 9:
                resetTempValue();
                viewUserInfoPage();
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                UserInfoUI.showMenuUpdatePassword(option, password, newPassword, rePassword);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                changePasswordInfo();
        }
    }

    /*========================================View User Page End========================================*/
    /* ========================================User Info Manager========================================*/

    private void checkUpdateUserInfo(int option) {
        boolean emptyField = loginUser.getName().equalsIgnoreCase("") || loginUser.getUserName().equalsIgnoreCase("") || loginUser.getEmail().equalsIgnoreCase("") || password.equalsIgnoreCase("");
        if (emptyField) {
            UserInfoUI.showMenuUpdateUserInfo(option, loginUser, password);
            System.out.print(MenuConst.EMPTY_ALERT);
            InputConfig.pressAnyKey();
            changeUserInfo();
        } else {
            boolean checkValidateExistAccount = userController.checkValidateExistAccount(loginUser);
            if (!checkValidateExistAccount) {
                boolean checkCurrentPassword = checkCurrentPassword();
                UserInfoUI.showMenuUpdateUserInfo(option, loginUser, password);
                if (!checkCurrentPassword) {
                    System.out.print(MenuConst.CURRENT_PASS_INCORRECT);
                    InputConfig.pressAnyKey();
                    changeUserInfo();
                } else {
                    System.out.println(MenuConst.UPDATE_SUCCESS);
                    updateAllDatabase();
                    resetTempValue();
                    InputConfig.pressAnyKey();
                    viewUserInfoPage();
                }
            } else {
                InputConfig.pressAnyKey();
                changeUserInfo();
            }
        }
    }

    private void inputPasswordInfo(int option) {
        UserInfoUI.showMenuUpdateUserInfo(option, loginUser, password);
        System.out.print(MenuConst.INPUT_PASSWORD);
        password = InputConfig.getString();
        changeUserInfo();
    }

    private void getEmailInfo(int option) {
        long validEditTime = new Date().getTime() - updatedEmailTime;
        if (validEditTime <= 600000) {
            UserInfoUI.showMenuUpdateUserInfo(option, loginUser, password);
            Date nextTime = new Date(updatedEmailTime + 600000);
            System.out.println(MenuConst.REQUIRE_NEXT_TIME + nextTime);
            InputConfig.pressAnyKey();
        } else {
            while (true) {
                UserInfoUI.showMenuUpdateUserInfo(option, loginUser, password);
                System.out.println(MenuConst.REQUIRE_EMAIL);
                System.out.print(MenuConst.INPUT_EMAIL);
                String input = InputConfig.getString();
                boolean validateEmail = ValidateConfig.validateEmail(input);
                if (validateEmail) {
                    loginUser.setEmail(input);
                    loginUser.setEmailUpdatedTime(new Date());
                    break;
                } else {
                    UserInfoUI.showMenuUpdateUserInfo(option, loginUser, password);
                    System.out.print(MenuConst.INVALID_EMAIL);
                    InputConfig.pressAnyKey();
                }
            }
        }
        changeUserInfo();
    }

    private void getSexInfo(int option) {
        UserInfoUI.showMenuUpdateUserInfo(option, loginUser, password);
        System.out.print(MenuConst.INPUT_SEX);
        String input = InputConfig.getString();
        if (input.equalsIgnoreCase("true")) {
            loginUser.setGender(true);
        } else if (input.equalsIgnoreCase("null")) {
            loginUser.setGender(null);
        } else loginUser.setGender(false);
        changeUserInfo();
    }

    private void getNameInfo(int option) {
        while (true) {
            UserInfoUI.showMenuUpdateUserInfo(option, loginUser, password);
            System.out.println(MenuConst.REQUIRE_NAME);
            System.out.print(MenuConst.INPUT_NAME);
            String input = InputConfig.getString();
            boolean validateName = ValidateConfig.validateName(input);
            if (validateName) {
                loginUser.setName(input);
                break;
            } else {
                UserInfoUI.showMenuUpdateUserInfo(option, loginUser, password);
                System.out.print(MenuConst.INVALID_NAME);
                InputConfig.pressAnyKey();
            }
        }
        changeUserInfo();
    }

    private void getUserNameInfo(int option) {
        long validEditTime = new Date().getTime() - updatedUserNameTime;
        if (validEditTime <= 600000) {
            UserInfoUI.showMenuUpdateUserInfo(option, loginUser, password);
            Date nextTime = new Date(updatedUserNameTime + 600000);
            System.out.println(MenuConst.REQUIRE_NEXT_TIME + nextTime);
            InputConfig.pressAnyKey();
        } else {
            while (true) {
                UserInfoUI.showMenuUpdateUserInfo(option, loginUser, password);
                System.out.println(MenuConst.REQUIRE_ACCOUNT);
                System.out.print(MenuConst.INPUT_NAME);
                String input = InputConfig.getString();
                boolean validateUserName = ValidateConfig.validateUserName(input);
                if (validateUserName) {
                    loginUser.setUserName(input);
                    loginUser.setUserUpdatedTime(new Date());
                    break;
                } else {
                    UserInfoUI.showMenuUpdateUserInfo(option, loginUser, password);
                    System.out.print(MenuConst.INVALID_ACCOUNT);
                    InputConfig.pressAnyKey();
                }
            }
        }
        changeUserInfo();
    }

    private boolean checkCurrentPassword() {
        return loginUser.getPassword().equals(password);
    }

    /*========================================Others========================================*/
    private void checkChangePassword() {
        boolean emptyField = password.equalsIgnoreCase("") || newPassword.equalsIgnoreCase("") || rePassword.equalsIgnoreCase("");
        boolean checkCurrentPassword = checkCurrentPassword();
        if (emptyField) {
            System.out.print(MenuConst.EMPTY_ALERT);
            InputConfig.pressAnyKey();
            changePasswordInfo();
        } else if (!checkCurrentPassword) {
            System.out.print(MenuConst.CURRENT_PASS_INCORRECT);
            InputConfig.pressAnyKey();
            changePasswordInfo();
        } else if (!newPassword.equals(rePassword)) {
            System.out.print(MenuConst.UNMATCHED_PASSWORD);
            InputConfig.pressAnyKey();
            changePasswordInfo();
        } else {
            loginUser.setPassword(newPassword);
            updateAllDatabase();
            System.out.print(MenuConst.UPDATE_SUCCESS);
            resetTempValue();
            InputConfig.pressAnyKey();
            viewUserInfoPage();
        }
    }

    private void updateAllDatabase() {
        userController.updateCurrentUser(loginUser);
        userController.synchronizedLoginUser(loginUser);
        friendController.synchronizedUserInFriend(loginUser);
        postController.synchronizedUserInPost(loginUser);
        chatController.synchronizedUserInChat(loginUser);
    }

    private void inputReNewPassword(int option) {
        while (true) {
            UserInfoUI.showMenuUpdatePassword(option, password, newPassword, rePassword);
            System.out.println(MenuConst.REQUIRE_PASSWORD);
            System.out.print(MenuConst.INPUT_RE_PASSWORD);
            String input = InputConfig.getString();
            boolean validatePassword = ValidateConfig.validatePassword(input);
            if (validatePassword) {
                rePassword = input;
                break;
            } else {
                UserInfoUI.showMenuUpdatePassword(option, password, newPassword, rePassword);
                System.out.print(MenuConst.INVALID_PASSWORD);
                InputConfig.pressAnyKey();
            }
        }
        changePasswordInfo();
    }

    private void inputNewPassword(int option) {
        while (true) {
            UserInfoUI.showMenuUpdatePassword(option, password, newPassword, rePassword);
            System.out.println(MenuConst.REQUIRE_PASSWORD);
            System.out.print(MenuConst.INPUT_NEW_PASSWORD);
            String input = InputConfig.getString();
            boolean validatePassword = ValidateConfig.validatePassword(input);
            if (validatePassword) {
                newPassword = input;
                break;
            } else {
                UserInfoUI.showMenuUpdatePassword(option, password, newPassword, rePassword);
                System.out.print(MenuConst.INVALID_PASSWORD);
                InputConfig.pressAnyKey();
            }
        }
        changePasswordInfo();
    }

    private void inputCurrentPassword(int option) {
        while (true) {
            UserInfoUI.showMenuUpdatePassword(option, password, newPassword, rePassword);
            System.out.println(MenuConst.REQUIRE_PASSWORD);
            System.out.print(MenuConst.INPUT_PASSWORD);
            String input = InputConfig.getString();
            boolean validatePassword = ValidateConfig.validatePassword(input);
            if (validatePassword) {
                password = input;
                break;
            } else {
                UserInfoUI.showMenuUpdatePassword(option, password, newPassword, rePassword);
                System.out.print(MenuConst.INVALID_PASSWORD);
                InputConfig.pressAnyKey();
            }
        }
        changePasswordInfo();
    }

    /*========================================Others========================================*/
    private void resetTempValue() {
        loginUser = userController.getLoginUser();
        updatedEmailTime = loginUser.getEmailUpdatedTime().getTime();
        updatedUserNameTime = loginUser.getUserUpdatedTime().getTime();
        password = rePassword = newPassword = "";
    }
}
