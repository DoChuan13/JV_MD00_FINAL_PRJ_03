package app.view.admin;

import app.config.BreakConfig;
import app.config.ColorConfig;
import app.config.MenuConst;
import app.model.RoleName;
import app.model.User;
import app.view.user.ui.UserInfoUI;

import java.util.ArrayList;
import java.util.List;

public final class AdminUI {
    public static void showMenuAdminManager(User loginUser) {
        BreakConfig.clearScreen();
        RoleName currentRole = new ArrayList<>(loginUser.getRole()).get(0).getName();
        if (currentRole == RoleName.ADMIN) System.out.println(MenuConst.HEADER_ADMIN);
        else System.out.println(MenuConst.HEADER_PM);

        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  1. " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, (currentRole == RoleName.ADMIN) ? "Admin Profile" : "PM Profile");
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  2. " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "User Management");

        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_MID_13);
        System.out.println(MenuConst.FOOTER);
    }

    public static void showMenuAdminProfile(User loginUser) {
        RoleName currentRole = new ArrayList<>(loginUser.getRole()).get(0).getName();

        BreakConfig.clearScreen();
        if (currentRole == RoleName.ADMIN) System.out.println(MenuConst.HEADER_ADMIN_PROFILE);
        else System.out.println(MenuConst.HEADER_PM_PROFILE);
        showUserInfo(loginUser);

        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.ACTION_COLOR + "  8. " + MenuConst.WIDTH_2_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Change Password");
        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }


    public static void showMenuAdminChangePassword(int option, String password, String newPassword, String rePassword) {
        UserInfoUI.showMenuUpdatePassword(option, password, newPassword, rePassword);
    }

    public static void showMenuAdminUpdatePassword(int option, String password, String newPassword, String rePassword) {
        UserInfoUI.showMenuUpdatePassword(option, password, newPassword, rePassword);
    }

    public static void showMenuAdminUserManage(int option, List<User> userList) {
        String userId = "Id";
        String userName = "User Name";
        String name = "Full Name";
        String status = "Status";
        String role = "Role";
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_ADMIN_ACC_LIST);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + " " + MenuConst.WIDTH_ACC_LIST + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, userId, userName, name, status, role);
        for (User user : userList) {
            showUserDetailInfo(user);
        }
        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 8 ? ColorConfig.ACTIVE_COLOR : ColorConfig.ACTION_COLOR) + "  8. " + MenuConst.WIDTH_2_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "See Detail");
        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }

    public static void showMenuAdminActionUser(int option, User user, User loginUser) {
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_USR_DETAIL_PROFILE);
        showUserInfo(user);
        RoleName roleNameUser = new ArrayList<>(user.getRole()).get(0).getName();
        RoleName roleNameLogin = new ArrayList<>(loginUser.getRole()).get(0).getName();

        String actionBlockUser = ColorConfig.BORDER_COLOR + "|" + (option == 6 ? ColorConfig.ACTIVE_COLOR : ColorConfig.ACTION_COLOR) + "  6. " + MenuConst.WIDTH_2_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET;
        String actionDeleteUser = ColorConfig.BORDER_COLOR + "|" + (option == 7 ? ColorConfig.ACTIVE_COLOR : ColorConfig.ACTION_COLOR) + "  7. " + MenuConst.WIDTH_2_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET;
        String actionChangeRole = ColorConfig.BORDER_COLOR + "|" + (option == 8 ? ColorConfig.ACTIVE_COLOR : ColorConfig.ACTION_COLOR) + "  8. " + MenuConst.WIDTH_2_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET;
        if (roleNameLogin == RoleName.ADMIN) {
            if (roleNameUser != RoleName.ADMIN) {
                System.out.println(MenuConst.BREAK_LINE);
                System.out.printf(actionBlockUser, (!user.isStatus() ? "Block User" : "Unblock User"));
                System.out.printf(actionDeleteUser, "Delete User");
                System.out.printf(actionChangeRole, "Change Role");
            }
        }
        if (roleNameLogin == RoleName.PM) {
            if (roleNameUser == RoleName.USER) {
                System.out.println(MenuConst.BREAK_LINE);
                System.out.printf(actionBlockUser, (!user.isStatus() ? "Block User" : "Unblock User"));
                System.out.printf(actionDeleteUser, "Delete User");
            }
        }
        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }

    private static void showUserDetailInfo(User user) {
        String userId = String.valueOf(user.getUserId());
        String userName = user.getUserName();
        String name = user.getName();
        String status = (!user.isStatus()) ? "Active" : "Blocked";
        String roleName = String.valueOf(new ArrayList<>(user.getRole()).get(0).getName());
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + " " + MenuConst.WIDTH_ACC_LIST + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, userId, userName, name, status, roleName);
        System.out.println(MenuConst.BLANK_LINE);
    }

    private static void showUserInfo(User user) {
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + "  *. " + MenuConst.WITH_ONE_CONTENT + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "UserName: ", user.getUserName());
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + "  *. " + MenuConst.WITH_ONE_CONTENT + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Name: ", user.getName());
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + "  *. " + MenuConst.WITH_ONE_CONTENT + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Name: ", (user.isGender() == null ? "Unknown" : user.isGender() ? "Male" : "Female"));
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + "  *. " + MenuConst.WITH_ONE_CONTENT + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Email: ", user.getEmail());
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + "  *. " + MenuConst.WITH_ONE_CONTENT + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Status: ", (!user.isStatus() ? "Active" : "Blocked"));
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + "  *. " + MenuConst.WITH_ONE_CONTENT + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Role: ", (new ArrayList<>(user.getRole()).get(0).getName()));
    }
}
