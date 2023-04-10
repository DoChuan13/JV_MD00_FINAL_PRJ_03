package app.model;

import java.io.Serializable;

public class Like implements Serializable {
    private int likeId;
    private User likedUser;

    public Like() {
    }

    public Like(int likeId, User likedUser) {
        this.likeId = likeId;
        this.likedUser = likedUser;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public User getLikedUser() {
        return likedUser;
    }

    public void setLikedUser(User likedUser) {
        this.likedUser = likedUser;
    }

    @Override
    public String toString() {
        return "Like{" +
                "likeId=" + likeId +
                ", user=" + likedUser +
                '}';
    }
}
