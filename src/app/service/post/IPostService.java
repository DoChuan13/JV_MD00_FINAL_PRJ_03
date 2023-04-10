package app.service.post;

import app.model.Post;
import app.model.User;
import app.service.generic.IGenericService;

import java.util.List;

public interface IPostService extends IGenericService<Post> {
    void deletePostByAdmin(User user);

    List<Post> getPostListForUser(User user);

    void likeUnlikePost(Post detailPost, boolean statusLike, User loginUser);

    void synchronizedUserInPost(User user);

    int generatePostId();

    int generateCommentId(Post post);

    int generateLikeId(Post post);
}
