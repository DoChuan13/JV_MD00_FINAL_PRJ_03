package app.model;

import java.io.Serializable;
import java.util.Date;

public class ChatDetail implements Serializable {
    private int chatDetailId;
    private String content;
    private User user;
    private Date sentTime = new Date();

    public ChatDetail(int chatDetailId, String content, User user) {
        this.chatDetailId = chatDetailId;
        this.content = content;
        this.user = user;
    }

    public ChatDetail() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getChatDetailId() {
        return chatDetailId;
    }

    public void setChatDetailId(int chatDetailId) {
        this.chatDetailId = chatDetailId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getSentTime() {
        return sentTime;
    }

    @Override
    public String toString() {
        return "ChatDetail{" +
                "chatDetailId=" + chatDetailId +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", sentTime=" + sentTime +
                '}';
    }
}
