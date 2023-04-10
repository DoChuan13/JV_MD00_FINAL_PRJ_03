package app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Post implements Serializable {
    private int postId;
    private User ownUser;
    private String postContent;
    private String postStatus;
    private Date postTime = new Date();
    private Date modifiedTime = new Date();
    private List<Comment> commentList = new LinkedList<>();
    private List<Like> likeList = new LinkedList<>();

    public Post(int postId, User ownUser, String postContent, String postStatus) {
        this.postId = postId;
        this.ownUser = ownUser;
        this.postContent = postContent;
        this.postStatus = postStatus;
    }

    public Post(int postId, User ownUser, String postContent, String postStatus, List<Comment> commentList, List<Like> likeList) {
        this.postId = postId;
        this.ownUser = ownUser;
        this.postStatus = postStatus;
        this.postContent = postContent;
        this.commentList = commentList;
        this.likeList = likeList;
    }

    public Post() {
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public User getOwnUser() {
        return ownUser;
    }

    public void setOwnUser(User ownUser) {
        this.ownUser = ownUser;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Like> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<Like> likeList) {
        this.likeList = likeList;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", ownUser=" + ownUser +
                ", postContent='" + postContent + '\'' +
                ", postContent='" + postStatus + '\'' +
                ", postTime=" + postTime +
                ", modifiedTime=" + modifiedTime +
                ", commentList=" + commentList +
                ", likeList=" + likeList +
                '}';
    }
}
