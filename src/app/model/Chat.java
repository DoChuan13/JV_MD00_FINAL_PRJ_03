package app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Chat implements Serializable {
    private int chatId;
    private User startUser;
    private User targetUser;
    private Date startUserIn;
    private Date targetUserIn;
    private Date startTime = new Date();
    private Date latestTime = new Date();
    private List<ChatDetail> chatContent = new LinkedList<>();

    public Chat() {
    }

    public Chat(int chatId, User startUser, User targetUser) {
        this.chatId = chatId;
        this.startUser = startUser;
        this.targetUser = targetUser;
    }

    public Chat(int chatId, User startUser, User targetUser, List<ChatDetail> chatContent) {
        this.chatId = chatId;
        this.startUser = startUser;
        this.targetUser = targetUser;
        this.chatContent = chatContent;
    }

    public Date isUser1Out() {
        return startUserIn;
    }

    public void setStartUserIn(Date startUserIn) {
        this.startUserIn = startUserIn;
    }

    public Date isUser2Out() {
        return targetUserIn;
    }

    public void setTargetUserIn(Date targetUserIn) {
        this.targetUserIn = targetUserIn;
    }

    public Date getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(Date latestTime) {
        this.latestTime = latestTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getChatId() {
        return chatId;
    }

    public User getStartUser() {
        return startUser;
    }

    public void setStartUser(User startUser) {
        this.startUser = startUser;
    }

    public User getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(User targetUser) {
        this.targetUser = targetUser;
    }

    public List<ChatDetail> getChatContent() {
        return chatContent;
    }

    public void setChatContent(List<ChatDetail> chatContent) {
        this.chatContent = chatContent;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "chatId=" + chatId +
                ", user1=" + startUser +
                ", user2=" + targetUser +
                ", User1Out=" + startUserIn +
                ", User2Out=" + targetUserIn +
                ", startTime=" + startTime +
                ", latestTime=" + latestTime +
                ", chatContent=" + chatContent +
                '}';
    }
}
