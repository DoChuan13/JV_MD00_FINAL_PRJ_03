package app.controller;

import app.model.Comment;
import app.model.Post;
import app.model.User;
import app.service.post.IPostService;
import app.service.post.PostServiceIMPL;

import java.util.LinkedList;
import java.util.List;

public class PostController {
    private final IPostService postService = new PostServiceIMPL();

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

    public Post findPostById(int id) {
        return postService.findById(id);
    }

    public void createNewComment(Post detailPost, Comment newComment) {
        Post post = findPostById(detailPost.getPostId());
        if (post != null) {
            List<Comment> commentList = post.getCommentList();
            commentList.add(newComment);
            createNewPost(post);
        }
    }

    public void deleteCurrentPost(Post detailPost) {
        postService.delete(detailPost.getPostId());
    }

    public List<Post> findOwnerPostList(User ownUser) {
        List<Post> postList = new LinkedList<>();
        for (Post post : getPostList()) {
            if (post.getOwnUser().getUserId() == ownUser.getUserId()) {
                postList.add(post);
            }
        }
        return postList;
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
