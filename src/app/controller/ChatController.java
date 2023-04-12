package app.controller;

import app.model.Chat;
import app.model.User;
import app.service.chat.ChatServiceIPLM;

import java.util.List;

public class ChatController {
    private final ChatServiceIPLM chatService = new ChatServiceIPLM();

    public List<Chat> getAllChatList() {
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

    public Chat findChatDetailByIDAndUser(int id, User loginUser) {
        return chatService.findChatDetailByIdAndUser(id, loginUser);
    }

    public void removeChatByChatUser(Chat startChat) {
        chatService.save(startChat);
    }
}
