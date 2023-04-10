package app.service.post;

import app.config.IOFileConfig;
import app.config.MenuConst;
import app.model.*;
import app.service.friend.FriendServiceIPLM;
import app.service.friend.IFriendService;
import app.service.generic.IDataBaseService;
import init.DataBase;

import java.util.LinkedList;
import java.util.List;

public class PostServiceIMPL implements IPostService, IDataBaseService<Post> {
    private static final List<Post> postList;
    private static final IFriendService friendService = new FriendServiceIPLM();

    static {
        postList = new IOFileConfig<Post>().readFile(DataBase.PATH_POST);
    }

    @Override
    public List<Post> findAll() {
        return postList;
    }

    @Override
    public void save(Post post) {
        Post currentPost = findById(post.getPostId());
        if (currentPost == null) {
            postList.add(post);
        } else {
            int index = postList.indexOf(currentPost);
            postList.set(index, post);
        }
        updateDatabase(DataBase.PATH_POST, postList);
    }

    @Override
    public Post findById(int id) {
        for (Post post : postList) {
            if (post.getPostId() == id) {
                return post;
            }
        }
        return null;
    }

    @Override
    public Post delete(int id) {
        Post currentPost = findById(id);
        if (currentPost != null) {
            postList.remove(currentPost);
        }
        updateDatabase(DataBase.PATH_POST, postList);
        return currentPost;
    }

    @Override
    public void updateDatabase(String pathName, List<Post> list) {
        new IOFileConfig<Post>().writeFile(pathName, list);
    }

    @Override
    public void deletePostByAdmin(User user) {
        for (Post post : postList) {
            if (post.getOwnUser().getUserId() == user.getUserId()) {
                postList.remove(post);
                updateDatabase(DataBase.PATH_POST, postList);
                break;
            }
        }
    }

    @Override
    public List<Post> getPostListForUser(User user) {
        List<Post> postResult = new LinkedList<>();
        for (Post post : postList) {
            int ownUserId = post.getOwnUser().getUserId();
            if (ownUserId == user.getUserId()) {
                postResult.add(post);
            } else if (post.getPostStatus().equalsIgnoreCase(MenuConst.POST_PUBLIC)) {
                postResult.add(post);
            } else if (!post.getPostStatus().equalsIgnoreCase(MenuConst.POST_PRIVATE)) {
                List<Friend> acceptedFriendList = friendService.getAcceptedFriendList(user);
                for (Friend friend : acceptedFriendList) {
                    int user1Id = friend.getFriend1().getUserId();
                    int user2Id = friend.getFriend2().getUserId();
                    if ((ownUserId == user1Id || ownUserId == user2Id) &&
                            friend.getStatus().equalsIgnoreCase(MenuConst.FRIEND_ACCEPTED)) {
                        postResult.add(post);
                        break;
                    }
                }
            }
        }
        return postResult;
    }

    @Override
    public void likeUnlikePost(Post detailPost, boolean existLike, User loginUser) {
        for (Post post : postList) {
            if (post.getPostId() == detailPost.getPostId()) {
                List<Like> likeList = post.getLikeList();
                if (existLike) {
                    //Unlike Post
                    for (int i = 0; i < likeList.size(); i++) {
                        if (likeList.get(i).getLikedUser().getUserId() == loginUser.getUserId()) {
                            likeList.remove(i);
                            break;
                        }
                    }
                } else {
                    //Like Post
                    int id = generateLikeId(detailPost);
                    Like like = new Like(id, loginUser);
                    likeList.add(like);
                    break;
                }
            }
        }
        updateDatabase(DataBase.PATH_POST, postList);
    }

    @Override
    public void synchronizedUserInPost(User user) {
        for (Post post : postList) {
            if (post.getOwnUser().getUserId() == user.getUserId()) {
                post.setOwnUser(user);
            }
            for (Like like : post.getLikeList()) {
                if (like.getLikedUser().getUserId() == user.getUserId()) {
                    like.setLikedUser(user);
                }
            }
            for (Comment comment : post.getCommentList()) {
                if (comment.getCommentUser().getUserId() == user.getUserId()) {
                    comment.setCommentUser(user);
                }
            }
        }
        updateDatabase(DataBase.PATH_POST, postList);
    }

    @Override
    public int generatePostId() {
        int id = 0;
        for (Post post : postList) {
            if (post.getPostId() > id) {
                id = post.getPostId();
            }
        }
        return ++id;
    }

    @Override
    public int generateCommentId(Post post) {
        int id = 0;
        List<Comment> commentList = post.getCommentList();
        for (Comment comment : commentList) {
            if (comment.getCommentId() > id) {
                id = comment.getCommentId();
            }
        }
        return ++id;
    }

    @Override
    public int generateLikeId(Post post) {
        int id = 0;
        List<Like> likeList = post.getLikeList();
        for (Like like : likeList) {
            if (like.getLikeId() > id) {
                id = like.getLikeId();
            }
        }
        return ++id;
    }
}
