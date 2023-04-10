package app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Chat implements Serializable {
    private int chatId;
    private User startUser;
    private User targetUser;
    private Date startIn = new Date();
    private Date targetIn = new Date();
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

    public Date getStartIn() {
        return startIn;
    }

    public void setStartIn(Date startIn) {
        this.startIn = startIn;
    }

    public Date getTargetIn() {
        return targetIn;
    }

    public void setTargetIn(Date targetIn) {
        this.targetIn = targetIn;
    }

    public Date isUser1Out() {
        return startIn;
    }

    public Date isUser2Out() {
        return targetIn;
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
                ", User1Out=" + startIn +
                ", User2Out=" + targetIn +
                ", startTime=" + startTime +
                ", latestTime=" + latestTime +
                ", chatContent=" + chatContent +
                '}';
    }
}
