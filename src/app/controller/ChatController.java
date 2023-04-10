package app.controller;

import app.model.Chat;
import app.model.ChatDetail;
import app.model.User;
import app.service.chat.ChatServiceIPLM;
import app.service.chat.IChatService;
import app.view.user.ChatView;

import java.util.List;

public class ChatController {
    private final IChatService chatService = new ChatServiceIPLM();

    public List<Chat> getChatList() {
        return chatService.findAll();
    }

    public void synchronizedUserInChat(User user) {
        chatService.synchronizedUserInChat(user);
    }

    public void deleteChatByAdmin(User user) {
        chatService.deleteChatByAdmin(user);
    }

    public List<Chat> findAllChatByUser(User loginUser) {
        return chatService.findAllChatByUser(loginUser);
    }

    public Chat findChatDetailBy2Users(User targetUser, User loginUser) {
        return chatService.findChatDetailBy2Users(targetUser, loginUser);
    }

    public int generateChatId() {
        return chatService.generateChatId();
    }

    public int generateChatDetailId(Chat startChat) {
        return chatService.generateChatDetailId(startChat);
    }

    public void sentNewChat(Chat startChat) {
        chatService.save(startChat);

    }
}
