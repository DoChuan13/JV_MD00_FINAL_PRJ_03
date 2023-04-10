package app.model;

import java.io.Serializable;

public class Friend implements Serializable {
    private int friendId;
    private User friend1;
    private User friend2;
    private String status;

    public Friend(int friendId, User friend1, User friend2, String status) {
        this.friendId = friendId;
        this.friend1 = friend1;
        this.friend2 = friend2;
        this.status = status;
    }

    public Friend() {
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public User getFriend1() {
        return friend1;
    }

    public void setFriend1(User friend1) {
        this.friend1 = friend1;
    }

    public User getFriend2() {
        return friend2;
    }

    public void setFriend2(User friend2) {
        this.friend2 = friend2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "friendId=" + friendId +
                ", friend1=" + friend1 +
                ", friend2=" + friend2 +
                ", status='" + status + '\'' +
                '}';
    }
}

