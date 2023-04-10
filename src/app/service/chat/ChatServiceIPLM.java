package app.service.chat;

import app.config.IOFileConfig;
import app.model.Chat;
import app.model.ChatDetail;
import app.model.User;
import app.service.generic.IDataBaseService;
import init.DataBase;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ChatServiceIPLM implements IChatService, IDataBaseService<Chat> {
    private static final List<Chat> chatList;

    static {
        chatList = new IOFileConfig<Chat>().readFile(DataBase.PATH_CHAT);
    }

    @Override
    public List<Chat> findAll() {
        return chatList;
    }

    @Override
    public void save(Chat chat) {
        Chat currentChat = findById(chat.getChatId());
        if (currentChat == null) {
            chatList.add(chat);
        } else {
            int index = chatList.indexOf(currentChat);
            chatList.set(index, chat);
        }
        updateDatabase(DataBase.PATH_CHAT, chatList);
    }

    @Override
    public Chat findById(int id) {
        for (Chat chat : chatList) {
            if (chat.getChatId() == id) {
                return chat;
            }
        }
        return null;
    }

    @Override
    public Chat delete(int id) {
        Chat currentChat = findById(id);
        if (currentChat != null) {
            chatList.remove(currentChat);
        }
        updateDatabase(DataBase.PATH_CHAT, chatList);
        return currentChat;
    }

    @Override
    public void updateDatabase(String pathName, List<Chat> t) {
        new IOFileConfig<Chat>().writeFile(DataBase.PATH_CHAT, chatList);
    }

    @Override
    public void synchronizedUserInChat(User user) {
        for (Chat chat : chatList) {
            if (chat.getStartUser().getUserId() == user.getUserId()) {
                chat.setStartUser(user);
            }
            if (chat.getTargetUser().getUserId() == user.getUserId()) {
                chat.setTargetUser(user);
            }
        }
        updateDatabase(DataBase.PATH_CHAT, chatList);
    }

    @Override
    public List<Chat> findAllChatByUser(User loginUser) {
        List<Chat> chatResult = new LinkedList<>();
        for (Chat chat : chatList) {
            User user1 = chat.getStartUser();
            Date timeIn1 = chat.getStartIn();
            User user2 = chat.getTargetUser();
            Date timeIn2 = chat.getTargetIn();
            if ((user1.getUserId() == loginUser.getUserId() && timeIn1 != null) || (user2.getUserId() == loginUser.getUserId() && timeIn2 != null)) {
                chatResult.add(chat);
            }
        }
        return chatResult;
    }

    @Override
    public Chat findChatDetailBy2Users(User targetUser, User loginUser) {
        for (Chat chat : findAllChatByUser(loginUser)) {
            User user1 = chat.getStartUser();
            User user2 = chat.getTargetUser();
            if (user1.getUserId() == targetUser.getUserId() || user2.getUserId() == targetUser.getUserId()) {
                return chat;
            }
        }
        return null;
    }

    @Override
    public void deleteChatByAdmin(User user) {
        for (Chat chat : chatList) {
            if (chat.getStartUser().getUserId() == user.getUserId() || chat.getTargetUser().getUserId() == user.getUserId()) {
                chatList.remove(chat);
                updateDatabase(DataBase.PATH_CHAT, chatList);
                return;
            }
        }
    }

    @Override
    public int generateChatId() {
        int id = 0;
        for (Chat chat : chatList) {
            if (chat.getChatId() > id) {
                id = chat.getChatId();
            }
        }
        return ++id;
    }

    @Override
    public int generateChatDetailId(Chat chat) {
        int id = 0;
        for (ChatDetail chatDetail : chat.getChatContent()) {
            if (chatDetail.getChatDetailId() > id) {
                id = chatDetail.getChatDetailId();
            }
        }
        return ++id;
    }
}
