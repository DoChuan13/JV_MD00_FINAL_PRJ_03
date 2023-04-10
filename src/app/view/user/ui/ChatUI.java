package app.view.user.ui;

import app.config.BreakConfig;
import app.config.ColorConfig;
import app.config.MenuConst;
import app.model.Chat;
import app.model.ChatDetail;
import app.model.User;
import app.view.post.PostUI;

import java.util.ArrayList;
import java.util.List;

public final class ChatUI {
    public static void showMenuChatList() {
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_CHAT_PAGE);

        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  1. " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "New Chat");
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  2. " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Chat List");

        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }

    public static void showMenuNewChat(int option, List<User> userList) {
        String userId = "Id";
        String userName = "User Name";
        String name = "Full Name";
        String status = "Status";
        String role = "Role";
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_CHAT_PAGE);
        if (userList != null) {
            if (userList.size() == 0) {
                System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "You have no any Result");
            } else {
                System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + " " + MenuConst.WIDTH_ACC_LIST + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, userId, userName, name, status, role);
            }
            for (User user : userList) {
                showUserList(user);
            }
        } else {
            System.out.println(MenuConst.BLANK_LINE);
        }

        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 8 ? ColorConfig.ACTIVE_COLOR : ColorConfig.ACTION_COLOR) + "  8. " + MenuConst.WIDTH_2_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, (userList.size() == 0 ? "Find Account to Start..." : "Select a Account ID to Chat..."));

        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }

    public static void showMenuChatDetail(int option, Chat currentChatList, ChatDetail chatDetail, User loginUser) {
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_CHAT_DETAIL);
        if (currentChatList.getChatContent().size() == 0) {
            System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "You have no any Chat Content");
            System.out.println(MenuConst.BLANK_LINE);
        } else {
            for (ChatDetail chatContent : currentChatList.getChatContent()) {
                System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "User: " + chatContent.getUser().getName());
                System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Content: " + chatContent.getContent());
                System.out.println(MenuConst.BLANK_LINE);
            }
        }

        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "User: " + loginUser.getName());
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + (option == 8 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "  => " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Chat Content: ");
        breakLineContent(option, chatDetail.getContent());

        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.ACTION_COLOR + "  8. " + MenuConst.WIDTH_2_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, (chatDetail.getContent().length() == 0 ? "Write Chat Content..." : "Send Chat..."));
        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }

    private static void showUserList(User user) {


        String userId = String.valueOf(user.getUserId());
        String userName = user.getUserName();
        String name = user.getName();
        String status = (!user.isStatus()) ? "Active" : "Blocked";
        String roleName = String.valueOf(new ArrayList<>(user.getRole()).get(0).getName());
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + " " + MenuConst.WIDTH_ACC_LIST + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, userId, userName, name, status, roleName);
        System.out.println(MenuConst.BLANK_LINE);
    }

    private static void breakLineContent(int option, String chatContent) {
        PostUI.breakLineContent(option, (byte) 8, chatContent);
    }
}
