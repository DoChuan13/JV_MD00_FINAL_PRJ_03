package app.service.chat;

import app.model.Chat;
import app.model.User;
import app.service.generic.IGenericService;

import java.util.List;

public interface IChatService extends IGenericService<Chat> {
    void synchronizedUserInChat(User user);

    int generateChatId();

    int generateChatDetailId(Chat chat);

    List<Chat> findAllChatByUser(User loginUser);

    Chat findChatDetailBy2Users(User targetUser, User loginUser);

    void deleteChatByAdmin(User user);
}
