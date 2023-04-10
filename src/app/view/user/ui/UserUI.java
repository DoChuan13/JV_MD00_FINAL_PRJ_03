package app.view.user.ui;

import app.config.BreakConfig;
import app.config.ColorConfig;
import app.config.MenuConst;

public final class UserUI {
    public static void showMenuUserManager() {
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_USER_PROFILE);

        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  1. " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Home Page");
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  2. " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "User Info");
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  3. " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Post List");
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  4. " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Friend List");
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  5. " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Chat List");

        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_MID_13);
        System.out.println(MenuConst.FOOTER);
    }
}
