package app.view.admin;

import app.config.InputConfig;
import app.config.MenuConst;
import app.config.ValidateConfig;
import app.controller.*;
import app.model.Role;
import app.model.RoleName;
import app.model.User;
import app.view.main.MainView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AdminView {
    private static final UserController userController = new UserController();
    private static final FriendController friendController = new FriendController();
    private static final PostController postController = new PostController();
    private static final ChatController chatController = new ChatController();
    private static final RoleController roleController = new RoleController();
    private User loginUser = userController.getLoginUser();
    private List<User> userList = userController.getUserList();
    private String password = "", newPassword = "", rePassword = "";

    public AdminView() {
    }

    public void managerAdminDetail() {
        int option;
        AdminUI.showMenuAdminManager(loginUser);
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 1:
                showAdminProfilePage();
                break;
            case 2:
                manageUserList();
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                AdminUI.showMenuAdminManager(loginUser);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                managerAdminDetail();
        }
    }

    public void showAdminProfilePage() {
        AdminUI.showMenuAdminProfile(loginUser);
        System.out.print(MenuConst.SELECT_OPTION);
        int option = InputConfig.getInteger();
        switch (option) {
            case 8:
                changePasswordInfo();
                break;
            case 9:
                resetTempValue();
                managerAdminDetail();
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                AdminUI.showMenuAdminProfile(loginUser);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                showAdminProfilePage();
        }
    }

    public void manageUserList() {
        int option = 0;
        AdminUI.showMenuAdminUserManage(option, userList);
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 8:
                showUserInfoDetail(option);
                break;
            case 9:
                resetTempValue();
                managerAdminDetail();
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                AdminUI.showMenuAdminUserManage(option, userList);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                manageUserList();
        }
    }

    private void changePasswordInfo() {
        int option = 0;
        AdminUI.showMenuAdminChangePassword(option, password, newPassword, rePassword);
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
                checkChangePassword(option);
                break;
            case 9:
                resetTempValue();
                showAdminProfilePage();
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                AdminUI.showMenuAdminChangePassword(option, password, newPassword, rePassword);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                changePasswordInfo();
        }
    }

    private void checkChangePassword(int option) {
        boolean emptyField = password.equalsIgnoreCase("") || newPassword.equalsIgnoreCase("") || rePassword.equalsIgnoreCase("");
        boolean checkCurrentPassword = checkCurrentPassword();
        AdminUI.showMenuAdminChangePassword(option, password, newPassword, rePassword);
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
            updateAllDbForAdmin();
            System.out.print(MenuConst.UPDATE_SUCCESS);
            resetTempValue();
            InputConfig.pressAnyKey();
            managerAdminDetail();
        }
    }

    private void inputReNewPassword(int option) {
        while (true) {
            AdminUI.showMenuAdminUpdatePassword(option, password, newPassword, rePassword);
            System.out.println(MenuConst.REQUIRE_PASSWORD);
            System.out.print(MenuConst.INPUT_RE_PASSWORD);
            String input = InputConfig.getString();
            boolean validatePassword = ValidateConfig.validatePassword(input);
            if (validatePassword) {
                rePassword = input;
                break;
            } else {
                AdminUI.showMenuAdminUpdatePassword(option, password, newPassword, rePassword);
                System.out.print(MenuConst.INVALID_PASSWORD);
                InputConfig.pressAnyKey();
            }
        }
        changePasswordInfo();
    }

    private void inputNewPassword(int option) {
        while (true) {
            AdminUI.showMenuAdminUpdatePassword(option, password, newPassword, rePassword);
            System.out.println(MenuConst.REQUIRE_PASSWORD);
            System.out.print(MenuConst.INPUT_NEW_PASSWORD);
            String input = InputConfig.getString();
            boolean validatePassword = ValidateConfig.validatePassword(input);
            if (validatePassword) {
                newPassword = input;
                break;
            } else {
                AdminUI.showMenuAdminUpdatePassword(option, password, newPassword, rePassword);
                System.out.print(MenuConst.INVALID_PASSWORD);
                InputConfig.pressAnyKey();
            }
        }
        changePasswordInfo();
    }

    private void inputCurrentPassword(int option) {
        while (true) {
            AdminUI.showMenuAdminUpdatePassword(option, password, newPassword, rePassword);
            System.out.println(MenuConst.REQUIRE_PASSWORD);
            System.out.print(MenuConst.INPUT_PASSWORD);
            String input = InputConfig.getString();
            boolean validatePassword = ValidateConfig.validatePassword(input);
            if (validatePassword) {
                password = input;
                break;
            } else {
                AdminUI.showMenuAdminUpdatePassword(option, password, newPassword, rePassword);
                System.out.print(MenuConst.INVALID_PASSWORD);
                InputConfig.pressAnyKey();
            }
        }
        changePasswordInfo();
    }

    private boolean checkCurrentPassword() {
        return loginUser.getPassword().equals(password);
    }

    private void showUserInfoDetail(int option) {
        AdminUI.showMenuAdminUserManage(option, userList);
        System.out.print(MenuConst.INPUT_USER_ID);
        int userId = InputConfig.getInteger();
        User user = userController.findUserById(userId);
        if (user == null) {
            AdminUI.showMenuAdminUserManage(option, userList);
            System.out.print(MenuConst.RESULT_NOT_FOUND);
            InputConfig.pressAnyKey();
            manageUserList();
        } else {
            showUserActionDetail(user);
        }
    }

    private void showUserActionDetail(User user) {
        int action = 0;
        AdminUI.showMenuAdminActionUser(action, user, loginUser);
        System.out.print(MenuConst.SELECT_OPTION);
        action = InputConfig.getInteger();
        switch (action) {
            case 6:
                blockUser(action, user);
                break;
            case 7:
                deleteUser(action, user);
                break;
            case 8:
                changeRole(action, user);
                break;
            case 9:
                resetTempValue();
                manageUserList();
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                AdminUI.showMenuAdminActionUser(action, user, loginUser);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                showUserActionDetail(user);
        }
    }

    private void blockUser(int action, User user) {
        RoleName userRole = new ArrayList<>(user.getRole()).get(0).getName();
        RoleName loginUserRole = new ArrayList<>(loginUser.getRole()).get(0).getName();
        if ((loginUserRole == RoleName.PM && userRole == RoleName.PM) || userRole == RoleName.ADMIN) {
            AdminUI.showMenuAdminActionUser(action, user, loginUser);
            MainView.showInvalidOption();
            InputConfig.pressAnyKey();
            showUserActionDetail(user);
        } else {
            blockUserAction(action, user);
        }
    }

    private void blockUserAction(int action, User user) {
        AdminUI.showMenuAdminActionUser(action, user, loginUser);
        if (!user.isStatus()) {
            System.out.print(MenuConst.CONFIRM_BLOCK_USER);
        } else System.out.print(MenuConst.CONFIRM_UNBLOCK_USER);
        String confirm = InputConfig.getString();
        if (confirm.equalsIgnoreCase("Y")) {
            user.setStatus(!user.isStatus());
            updateAllDbUserInfo(user);
            resetTempValue();
            AdminUI.showMenuAdminActionUser(action, user, loginUser);
            if (!user.isStatus()) {
                System.out.print(MenuConst.UNBLOCK_USER_SUCCESS);
            } else System.out.print(MenuConst.BLOCK_USER_SUCCESS);
            InputConfig.pressAnyKey();
        }
        showUserActionDetail(user);
    }

    private void deleteUser(int action, User user) {
        RoleName userRole = new ArrayList<>(user.getRole()).get(0).getName();
        RoleName loginUserRole = new ArrayList<>(loginUser.getRole()).get(0).getName();
        if ((loginUserRole == RoleName.PM && userRole == RoleName.PM) || userRole == RoleName.ADMIN) {
            AdminUI.showMenuAdminActionUser(action, user, loginUser);
            MainView.showInvalidOption();
            InputConfig.pressAnyKey();
            showUserActionDetail(user);
        } else {
            deleteUserAction(action, user);
        }
    }

    private void deleteUserAction(int action, User user) {
        AdminUI.showMenuAdminActionUser(action, user, loginUser);
        System.out.print(MenuConst.CONFIRM_DELETE_USER);
        String confirm = InputConfig.getString();
        if (confirm.equalsIgnoreCase("Y")) {
            user.setStatus(!user.isStatus());
            userController.deleteUserInfo(user);
            deleteAllDbUserInfo(user);
            resetTempValue();
            System.out.print(MenuConst.DELETE_USER_SUCCESS);
            InputConfig.pressAnyKey();
            manageUserList();
        } else showUserActionDetail(user);
    }

    private void changeRole(int action, User user) {
        RoleName userRole = new ArrayList<>(user.getRole()).get(0).getName();
        RoleName loginUserRole = new ArrayList<>(loginUser.getRole()).get(0).getName();
        if (loginUserRole == RoleName.PM || userRole == RoleName.ADMIN) {
            AdminUI.showMenuAdminActionUser(action, user, loginUser);
            MainView.showInvalidOption();
            InputConfig.pressAnyKey();
            showUserActionDetail(user);
        } else {
            changeRoleAction(action, user);
        }
    }

    private void changeRoleAction(int option, User user) {
        AdminUI.showMenuAdminActionUser(option, user, loginUser);
        RoleName currentRole = new ArrayList<>(user.getRole()).get(0).getName();
        RoleName newRole = currentRole == RoleName.PM ? RoleName.USER : RoleName.PM;
        System.out.println("Change from Current Role: " + currentRole + " => New Role: " + newRole);
        System.out.print(MenuConst.CONFIRM_CHANGE_ROLE);
        String confirm = InputConfig.getString();
        if (confirm.equalsIgnoreCase("Y")) {
            Set<Role> newUserRole = roleController.createNewRoleGroup(newRole);
            user.setRole(newUserRole);
            updateAllDbUserInfo(user);
            AdminUI.showMenuAdminActionUser(option, user, loginUser);
            System.out.print(MenuConst.CHANGE_ROLE_SUCCESS);
            InputConfig.pressAnyKey();
        }
        showUserActionDetail(user);
    }


    private void resetTempValue() {
        loginUser = userController.getLoginUser();
        userList = userController.getUserList();
        password = rePassword = newPassword = "";
    }

    private void updateAllDbForAdmin() {
        userController.updateCurrentUser(loginUser);
        userController.synchronizedLoginUser(loginUser);
    }

    private void updateAllDbUserInfo(User user) {
        userController.updateCurrentUser(user);
        friendController.synchronizedUserInFriend(user);
        postController.synchronizedUserInPost(user);
        chatController.synchronizedUserInChat(user);
    }

    private void deleteAllDbUserInfo(User user) {
        friendController.deleteFriendByAdmin(user);
        postController.deletePostByAdmin(user);
        chatController.deleteChatByAdmin(user);
    }
}
