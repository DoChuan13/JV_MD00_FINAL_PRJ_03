package app.view.user;

import app.config.InputConfig;
import app.config.MenuConst;
import app.controller.ChatController;
import app.controller.UserController;
import app.model.Chat;
import app.model.ChatDetail;
import app.model.RoleName;
import app.model.User;
import app.view.main.MainView;
import app.view.user.ui.ChatUI;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ChatView {
    private static final UserController userController = new UserController();
    private static final ChatController chatController = new ChatController();
    private User loginUser = userController.getLoginUser();
    private List<Chat> chatList = chatController.findAllChatByUser(loginUser);
    private ChatDetail chatSession = new ChatDetail(1, "", loginUser);
    private List<User> searchUserResult = new LinkedList<>();
    private String findName = "", chatContent = "";

    /*========================================View Chat Page Start========================================*/
    public void viewChatPage() {
        ChatUI.showMenuChatList(chatList);
        System.out.print(MenuConst.SELECT_OPTION);
        int option = InputConfig.getInteger();
        switch (option) {
            case 1:
                manageNewChat();
                break;
            case 2:
                showCurrentChatList(option);
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
                ChatUI.showMenuChatList(chatList);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                viewChatPage();
        }
    }

    private void manageNewChat() {
        int option = 0;
        ChatUI.showMenuNewChat(option, searchUserResult);
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 8:
                createNewChat(option);
                break;
            case 9:
                resetTempValue();
                viewChatPage();
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                ChatUI.showMenuNewChat(option, searchUserResult);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                manageNewChat();
        }
    }

    private void createNewChat(int option) {
        if (searchUserResult.size() == 0) {
            ChatUI.showMenuNewChat(option, searchUserResult);
            System.out.print(MenuConst.INPUT_SEARCH_NAME);
            findName = InputConfig.getString();
            searchUserResult = userController.findUserWithoutMe(loginUser, findName);
            if (searchUserResult.size() == 0) {
                ChatUI.showMenuNewChat(option, searchUserResult);
                System.out.print(MenuConst.RESULT_NOT_FOUND);
                InputConfig.pressAnyKey();
            }
            manageNewChat();
        } else {
            ChatUI.showMenuNewChat(option, searchUserResult);
            System.out.print(MenuConst.INPUT_FR_ID_TO_CHAT);
            int id = InputConfig.getInteger();
            User chatUser = selectUserToStartChat(id);
            ChatUI.showMenuNewChat(option, searchUserResult);
            if (chatUser == null) {
                System.out.print(MenuConst.RESULT_NOT_FOUND);
                InputConfig.pressAnyKey();
                createNewChat(option);
            } else {
                startChatDetail(chatUser);
            }
        }
    }

    private void startChatDetail(User chatUser) {
        Chat startChat = chatController.findChatDetailBy2Users(chatUser, loginUser);
        if (startChat == null) {
            int chatId = chatController.generateChatId();
            startChat = new Chat(chatId, loginUser, chatUser);
        }
        sentChatDetail(startChat);
    }

    private void sentChatDetail(Chat startChat) {
        int option = 0;
        ChatUI.showMenuChatDetail(option, startChat, chatSession, loginUser);
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 7:
                if (checkAbilityDeleteChat(startChat, loginUser) == 0) {
                    ChatUI.showMenuChatList(chatList);
                    System.out.print(MenuConst.CANT_DELETE_EMPTY_CHAT);
                    MainView.showInvalidOption();
                    InputConfig.pressAnyKey();
                    sentChatDetail(startChat);
                } else {
                    deleteChat(option, startChat);
                }
                break;
            case 8:
                RoleName roleUser1 = new ArrayList<>(startChat.getStartUser().getRole()).get(0).getName();
                RoleName roleUser2 = new ArrayList<>(startChat.getTargetUser().getRole()).get(0).getName();
                if (roleUser1 != RoleName.USER || roleUser2 != RoleName.USER) {
                    ChatUI.showMenuChatDetail(option, startChat, chatSession, loginUser);
                    System.out.print(MenuConst.DENY_PERMISSION_CHAT);
                    InputConfig.pressAnyKey();
                    sentChatDetail(startChat);
                } else {
                    if (chatSession.getContent().length() == 0) {
                        writeNewChat(option, startChat);
                    } else {
                        sentChat(option, startChat, loginUser);
                    }
                }
                break;
            case 9:
                resetTempValue();
                viewChatPage();
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                ChatUI.showMenuChatList(chatList);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                sentChatDetail(startChat);
        }
    }

    private void deleteChat(int option, Chat startChat) {
        ChatUI.showMenuChatDetail(option, startChat, chatSession, loginUser);
        System.out.print(MenuConst.CONFIRM_DELETE_CHAT);
        String confirm = InputConfig.getString();
        if (confirm.equalsIgnoreCase("y")) {
            //Delete Chat in here
            ChatUI.showMenuChatDetail(option, startChat, chatSession, loginUser);
            if (startChat.getStartUser().getUserId() == loginUser.getUserId()) {
                startChat.setStartIn(null);
            } else {
                startChat.setTargetIn(null);
            }
            chatController.removeChatByChatUser(startChat);
            resetTempValue();
            System.out.print(MenuConst.DELETE_CHAT_SUCCESS);
            InputConfig.pressAnyKey();
            viewChatPage();
        } else {
            sentChatDetail(startChat);
        }
    }

    private void sentChat(int option, Chat startChat, User loginUser) {
        List<ChatDetail> chatDetailList = startChat.getChatContent();
        chatDetailList.add(chatSession);
        chatController.sentNewChat(startChat);
        resetTempValue();
        ChatUI.showMenuChatDetail(option, startChat, chatSession, loginUser);
        System.out.print(MenuConst.SENT_CHAT_SUCCESS);
        InputConfig.pressAnyKey();
        sentChatDetail(startChat);
    }

    private void writeNewChat(int option, Chat startChat) {
        ChatUI.showMenuChatDetail(option, startChat, chatSession, loginUser);
        System.out.print(MenuConst.INPUT_CHAT);
        chatContent = InputConfig.getString();
        if (startChat.getStartUser().getUserId() == loginUser.getUserId() &&
                startChat.getTargetIn() == null) {
            startChat.setTargetIn(new Date());
        } else if (startChat.getTargetUser().getUserId() == loginUser.getUserId() &&
                startChat.getStartIn() == null) {
            startChat.setStartIn(new Date());
        }
        int chatDetailId = chatController.generateChatDetailId(startChat);
        chatSession = new ChatDetail(chatDetailId, chatContent, loginUser);
        sentChatDetail(startChat);
    }

    private void showCurrentChatList(int option) {
        ChatUI.showMenuCurrentChatList(option, chatList, loginUser);
        System.out.print(MenuConst.SELECT_OPTION);
        int action = InputConfig.getInteger();
        switch (action) {
            case 8:
                if (chatList.size() != 0) {
                    directToChatDetail(action);
                } else {
                    ChatUI.showMenuCurrentChatList(action, chatList, loginUser);
                    MainView.showInvalidOption();
                    InputConfig.pressAnyKey();
                    showCurrentChatList(option);
                }
                break;
            case 9:
                resetTempValue();
                viewChatPage();
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                ChatUI.showMenuCurrentChatList(action, chatList, loginUser);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                showCurrentChatList(option);
        }
    }

    private void directToChatDetail(int option) {
        ChatUI.showMenuCurrentChatList(option, chatList, loginUser);
        System.out.print(MenuConst.INPUT_CHAT_ID);
        int id = InputConfig.getInteger();
        Chat chatDetail = chatController.findChatDetailByIDAndUser(id, loginUser);
        if (chatDetail == null) {
            ChatUI.showMenuCurrentChatList(option, chatList, loginUser);
            System.out.print(MenuConst.RESULT_NOT_FOUND);
            InputConfig.pressAnyKey();
            showCurrentChatList(option);
        } else {
            sentChatDetail(chatDetail);
        }
    }

    private User selectUserToStartChat(int id) {
        for (User user : searchUserResult) {
            if (user.getUserId() == id) {
                return user;
            }
        }
        return null;
    }

    private int checkAbilityDeleteChat(Chat startChat, User loginUser) {
        int count = 0;
        long timeIn;
        if (startChat.getStartUser().getUserId() == loginUser.getUserId()) {
            timeIn = startChat.getStartIn().getTime();
        } else {
            timeIn = startChat.getTargetIn().getTime();
        }
        for (ChatDetail chatDetail : startChat.getChatContent()) {
            long sentTime = chatDetail.getSentTime().getTime();
            if (sentTime - timeIn >= 0) {
                count++;
            }
        }
        return count;
    }

    /*========================================View Chat Page End========================================*/
    private void resetTempValue() {
        chatList = chatController.findAllChatByUser(loginUser);
        loginUser = userController.getLoginUser();
        chatSession = new ChatDetail(1, "", loginUser);
        searchUserResult = new LinkedList<>();
        findName = chatContent = "";
    }
}
