package app.controller;

import app.model.Comment;
import app.model.Post;
import app.model.User;
import app.service.post.PostServiceIMPL;

import java.util.List;

public class PostController {
    private final PostServiceIMPL postService = new PostServiceIMPL();

    public PostController() {
    }

    public List<Post> getPostList() {
        return postService.findAll();
    }

    public List<Post> getPostListForUser(User user) {
        return postService.getPostListForUser(user);
    }

    public void createNewPost(Post post) {
        postService.save(post);
    }

    public void updateCurrentPost(Post post) {
        postService.save(post);
    }

    public void createNewComment(Post detailPost, Comment newComment) {
        postService.createNewComment(detailPost, newComment);
    }

    public void deleteCurrentPost(Post detailPost) {
        postService.delete(detailPost.getPostId());
    }

    public List<Post> findOwnerPostList(User ownUser) {
        return postService.findOwnerPostList(ownUser);
    }

    public void synchronizedUserInPost(User user) {
        postService.synchronizedUserInPost(user);
    }

    public int generatePostId() {
        return postService.generatePostId();
    }

    public int generateCommentId(Post post) {
        return postService.generateCommentId(post);
    }

    public void deletePostByAdmin(User user) {
        postService.deletePostByAdmin(user);
    }

    public void likeUnlikePost(Post detailPost, boolean existLike, User loginUser) {
        postService.likeUnlikePost(detailPost, existLike, loginUser);
    }
}
