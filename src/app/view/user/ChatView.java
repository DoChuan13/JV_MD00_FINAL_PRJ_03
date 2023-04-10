package app.view.user;

import app.config.InputConfig;
import app.config.MenuConst;
import app.controller.ChatController;
import app.controller.UserController;
import app.model.Chat;
import app.model.ChatDetail;
import app.model.User;
import app.view.main.MainView;
import app.view.user.ui.ChatUI;

import java.util.LinkedList;
import java.util.List;

public class ChatView {
    private final UserController userController = new UserController();
    private final ChatController chatController = new ChatController();
    private User loginUser = userController.getLoginUser();
    private List<Chat> chatValue = chatController.findAllChatByUser(loginUser);
    private ChatDetail chatDetail = new ChatDetail(1, "", loginUser);
    private List<User> searchUserResult = new LinkedList<>();
    private String findName = "", chatContent = "";

    /*========================================View Chat Page Start========================================*/
    public void viewChatPage() {
        ChatUI.showMenuChatList();
        System.out.print(MenuConst.SELECT_OPTION);
        int option = InputConfig.getInteger();
        switch (option) {
            case 1:
                manageNewChat();
                break;
            case 2:
                showCurrentChatList();
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
                ChatUI.showMenuChatList();
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
        ChatUI.showMenuChatDetail(option, startChat, chatDetail, loginUser);
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 8:
                if (chatDetail.getContent().length() == 0) {
                    writeNewChat(option, startChat);
                } else {
                    sentChat(option, startChat);
                }
                break;
            case 9:
                resetTempValue();
                manageNewChat();
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                ChatUI.showMenuChatList();
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                sentChatDetail(startChat);
        }
    }

    private void sentChat(int option, Chat startChat) {
        List<ChatDetail> chatDetailList = startChat.getChatContent();
        chatDetailList.add(chatDetail);
        chatController.sentNewChat(startChat);
        resetTempValue();
        ChatUI.showMenuChatDetail(option, startChat, chatDetail, loginUser);
        System.out.print(MenuConst.SENT_CHAT_SUCCESS);
        InputConfig.pressAnyKey();
        sentChatDetail(startChat);
    }

    private void writeNewChat(int option, Chat startChat) {
        ChatUI.showMenuChatDetail(option, startChat, chatDetail, loginUser);
        System.out.print(MenuConst.INPUT_CHAT);
        chatContent = InputConfig.getString();
        int chatDetailId = chatController.generateChatDetailId(startChat);
        chatDetail = new ChatDetail(chatDetailId, chatContent, loginUser);
        sentChatDetail(startChat);
    }

    private void showCurrentChatList() {

    }

    private User selectUserToStartChat(int id) {
        for (User user : searchUserResult) {
            if (user.getUserId() == id) {
                return user;
            }
        }
        return null;
    }

    /*========================================View Chat Page End========================================*/
    private void resetTempValue() {
        chatValue = chatController.findAllChatByUser(loginUser);
        loginUser = userController.getLoginUser();
        chatDetail = new ChatDetail(1, "", loginUser);
        searchUserResult = new LinkedList<>();
        findName = chatContent = "";
    }
}
