package app.view;

import app.controller.UserController;
import app.model.RoleName;
import app.model.User;
import app.view.admin.AdminView;
import app.view.main.MainView;
import app.view.pm.PmView;
import app.view.user.UserView;
import init.initialization.InitialDataBase;

import java.util.ArrayList;

public class Main {
    private static final UserController userController = new UserController();

    public Main() {
        User loginUser = userController.getLoginUser();
        if (loginUser == null) {
            new MainView().showMainIndex();
        } else {
            RoleName roleName = new ArrayList<>(loginUser.getRole()).get(0).getName();
            if (roleName == RoleName.USER) {
                new UserView().managerUserDetail();
            }
            if (roleName == RoleName.ADMIN) {
                new AdminView().managerAdminDetail();
            }
            if (roleName == RoleName.PM) {
                new PmView().managerPmDetail();
            } else new MainView().showMainIndex();
        }
    }

    public static void main(String[] args) {
        new InitialDataBase();
        new Main();
    }
}