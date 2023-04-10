package app.view.user.ui;

import app.config.BreakConfig;
import app.config.ColorConfig;
import app.config.MenuConst;
import app.model.Chat;
import app.model.ChatDetail;
import app.model.User;
import app.view.post.PostUI;

import java.util.List;

public final class ChatUI {
    public static void showMenuChatList(List<Chat> chatList) {
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_CHAT_PAGE);

        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  1. " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "New Chat");
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  2. " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Chat List " + "(" + chatList.size() + ")");

        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }

    public static void showMenuNewChat(int option, List<User> userList) {
        String friendIdTitle = "User Id: ";
        String friendNameViewTitle = "Full Name: ";
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_CHAT_PAGE);
        if (userList.size() == 0) {
            System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "You have no any Result");
        } else {
            System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, friendIdTitle, friendNameViewTitle);
        }
        for (User user : userList) {
            showUserList(user);
        }

        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 8 ? ColorConfig.ACTIVE_COLOR : ColorConfig.ACTION_COLOR) + "  8. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, (userList.size() == 0 ? "Find Account to Start..." : "Select a Account ID to Chat..."));

        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }

    public static void showMenuChatDetail(int option, Chat currentChat, ChatDetail chatDetail, User loginUser) {
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_CHAT_DETAIL);
        if (currentChat.getChatContent().size() == 0) {
            System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "You have no any Chat Content");
            System.out.println(MenuConst.BLANK_LINE);
        } else {
            long timeIn;
            if (currentChat.getStartUser().getUserId() == loginUser.getUserId()) {
                timeIn = currentChat.getStartIn().getTime();
            } else {
                timeIn = currentChat.getTargetIn().getTime();
            }
            for (ChatDetail chatContent : currentChat.getChatContent()) {
                if (timeIn <= chatContent.getSentTime().getTime()) {
                    if (chatContent.getUser().getUserId() == loginUser.getUserId()) {
                        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "(User: " + chatContent.getUser().getName() + ")");
                        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "     " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "(Content): " + chatContent.getContent());
                    } else {
                        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  <= " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "(User: " + chatContent.getUser().getName() + ")");
                        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "     " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "(Content): " + chatContent.getContent());
                    }
                    System.out.println(MenuConst.BLANK_LINE);
                }
            }
        }

        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "User: " + loginUser.getName());
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + (option == 8 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "     " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Chat Content: ");
        breakLineContent(option, chatDetail.getContent());

        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.ACTION_COLOR + "  7. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Delete Chat");
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.ACTION_COLOR + "  8. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, (chatDetail.getContent().length() == 0 ? "Write Chat Content..." : "Send Chat..."));
        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }

    private static void showUserList(User user) {
        String friendId = String.valueOf(user.getUserId());
        String friendName = user.getUserName();
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, friendId, friendName);
        System.out.println(MenuConst.BLANK_LINE);
    }

    private static void breakLineContent(int option, String chatContent) {
        PostUI.breakLineContent(option, (byte) 8, chatContent);
    }

    public static void showMenuCurrentChatList(int option, List<Chat> chatList, User loginUser) {
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_CHAT_LIST);
        if (chatList.size() == 0) {
            System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "     " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "You have no any Chat");
            System.out.println(MenuConst.BLANK_LINE);
        } else {
            for (Chat chat : chatList) {
                String targetChat;
                if (chat.getStartUser().getUserId() == loginUser.getUserId()) {
                    targetChat = chat.getTargetUser().getName();
                } else {
                    targetChat = chat.getStartUser().getName();
                }
                String chatIdView = "Chat ID: " + chat.getChatId();
                String chatUserView = "User: " + targetChat;
                System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, chatIdView, chatUserView);
            }
            System.out.println(MenuConst.BLANK_LINE);
        }

        if (chatList.size() != 0) {
            System.out.println(MenuConst.BREAK_LINE);
            System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 8 ? ColorConfig.ACTIVE_COLOR : ColorConfig.ACTION_COLOR) + "  8. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Choose a Chat...");
        }
        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }
}
