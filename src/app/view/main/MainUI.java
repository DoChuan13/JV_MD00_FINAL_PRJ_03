package app.view.main;

import app.config.BreakConfig;
import app.config.ColorConfig;
import app.config.MenuConst;
import app.model.RoleName;
import app.model.User;

import java.util.ArrayList;

public final class MainUI {
    /*----------------------------------------------------------MENU----------------------------------------------------------*/
    public static void showMenuMain() {
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_WELCOME);
        System.out.println(MenuConst.BLANK_LINE);

        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.ACTION_COLOR + "  1. " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Login");
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.ACTION_COLOR + "  2. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Register");
        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_LES_1);
        System.out.println(MenuConst.FOOTER);
    }

    /*----------------------------------------------------------Login----------------------------------------------------------*/
    public static void showMenuLogin(int option, String userName, String password) {
        String hiddenPass = "*".repeat(password.length());
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_LOGIN);

        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 1 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "  1. " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "UserName: ", userName);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 2 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "  2. " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Password: ", hiddenPass);

        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 3 ? ColorConfig.ACTIVE_COLOR : ColorConfig.ACTION_COLOR) + "  3. " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Login");
        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_MID_12);
        System.out.println(MenuConst.FOOTER);
    }

    /*----------------------------------------------------------Register----------------------------------------------------------*/
    public static void showMenuRegister(int option, User tempUser, String rePassword) {
        String hiddenPass = "*".repeat(tempUser.getPassword().length());
        String hiddenRepass = "*".repeat(rePassword.length());
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_REGISTER);

        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 1 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "  1. " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Name: ", tempUser.getName());
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 2 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "  2. " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "UserName: ", tempUser.getUserName());
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 3 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "  3. " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Email: ", tempUser.getEmail());
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 4 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "  4. " + MenuConst.WIDTH_2_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Password: ", hiddenPass);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 5 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "  5. " + MenuConst.WIDTH_2_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Re-Password: ", hiddenRepass);

        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.ACTION_COLOR + "  6. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Register");
        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_MID_12);
        System.out.println(MenuConst.FOOTER);
    }

    public static void showMenuWelcome(User loginUser) {
        RoleName roleName = new ArrayList<>(loginUser.getRole()).get(0).getName();
        String loginName = roleName == RoleName.ADMIN ? "" : loginUser.getName();
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_WELCOME);
        System.out.println(MenuConst.BLANK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|%-38sWELCOME %-42s|\n" + ColorConfig.RESET, "", roleName);
        System.out.printf(ColorConfig.BORDER_COLOR + "|%-40s%-48s|\n" + ColorConfig.RESET, "", loginName);

        System.out.println(MenuConst.BLANK_LINE);
        System.out.println(MenuConst.FOOTER);
    }

    /*----------------------------------------------------------ALERT----------------------------------------------------------*/
    public static void showAlertExitApp() {
        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.ACTION_COLOR + "     " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n", "Application will exit");
        System.out.println(MenuConst.BREAK_LINE);
    }
}
