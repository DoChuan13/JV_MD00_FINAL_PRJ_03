package app.view.user.ui;

import app.config.BreakConfig;
import app.config.ColorConfig;
import app.config.MenuConst;
import app.model.User;

public final class UserInfoUI {
    public static void showMenuUpdateUserInfo(int option, User user, String password) {
        String hiddenPass = "*".repeat(password.length());
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_PROFILE_INFO);

        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 1 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "  " + (option != -1 ? "1" : "*") + ". " + MenuConst.WITH_ONE_CONTENT + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "UserName: ", user.getUserName());
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 2 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "  " + (option != -1 ? "2" : "*") + ". " + MenuConst.WITH_ONE_CONTENT + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Name: ", user.getName());
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 3 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "  " + (option != -1 ? "3" : "*") + ". " + MenuConst.WITH_ONE_CONTENT + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Sex: ", (user.isGender() == null ? "Unknown" : user.isGender() ? "Male" : "Female"));
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 4 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "  " + (option != -1 ? "4" : "*") + ". " + MenuConst.WITH_ONE_CONTENT + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Email: ", user.getEmail());
        if (option != -1) {
            System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 5 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "  5. " + MenuConst.WITH_ONE_CONTENT + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Password: ", hiddenPass);
        }

        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.ACTION_COLOR + "  7. " + MenuConst.WIDTH_2_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, (option == -1 ? "Change Information" : "Update Information"));
        if (option == -1) {
            System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.ACTION_COLOR + "  8. " + MenuConst.WIDTH_2_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Change Password");
        }
        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }

    public static void showMenuUpdatePassword(int option, String password, String newPassword, String rePassword) {
        String hiddenPass = "*".repeat(password.length());
        String hiddenNewPass = "*".repeat(newPassword.length());
        String hiddenRepass = "*".repeat(rePassword.length());
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_PROFILE_PASS);

        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 1 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "  " + (option != -1 ? "1" : "*") + ". %-30s%-53s" + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Password: ", hiddenPass);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 2 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "  " + (option != -1 ? "2" : "*") + ". %-30s%-53s" + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "New Password: ", hiddenNewPass);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 3 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "  " + (option != -1 ? "3" : "*") + ". %-30s%-53s" + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Re-Password: ", hiddenRepass);

        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 8 ? ColorConfig.ACTIVE_COLOR : ColorConfig.ACTION_COLOR) + "  8. " + MenuConst.WIDTH_2_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Update Password");
        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }
}
