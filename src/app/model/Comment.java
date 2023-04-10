package app.model;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private int commentId;
    private User commentUser;
    private String comment;
    private Date commentTime = new Date();

    public Comment() {
    }

    public Comment(int commentId, User commentUser, String comment) {
        this.commentId = commentId;
        this.commentUser = commentUser;
        this.comment = comment;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public User getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(User commentUser) {
        this.commentUser = commentUser;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", user=" + commentUser +
                ", comment='" + comment + '\'' +
                ", commentTime=" + commentTime +
                '}';
    }
}
