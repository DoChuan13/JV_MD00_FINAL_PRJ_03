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

    private static boolean isLikedFriend(List<Like> likeList, boolean likedFriend, int friend1Id, int friend2Id) {
        for (Like like : likeList) {
            int likedUserId = like.getLikedUser().getUserId();
            if (likedUserId == friend1Id || likedUserId == friend2Id) {
                likedFriend = true;
                break;
            }
        }
        return likedFriend;
    }

    private static boolean isCommentedFriend(List<Comment> commentList, boolean commentedFriend, int friend1Id, int friend2Id) {
        for (Comment comment : commentList) {
            int commentedUserId = comment.getCommentUser().getUserId();
            if (commentedUserId == friend1Id || commentedUserId == friend2Id) {
                commentedFriend = true;
                break;
            }
        }
        return commentedFriend;
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
    public List<Post> getPostListForUser(User loginUser) {
        List<Post> postResult = new LinkedList<>();
        for (Post post : postList) {
            int ownUserId = post.getOwnUser().getUserId();
            if (ownUserId == loginUser.getUserId()) {
                postResult.add(post);
            } else if (post.getPostStatus().equalsIgnoreCase(MenuConst.POST_PUBLIC)) {
                List<Friend> acceptedFriendList = friendService.getAcceptedFriendList(loginUser);
                List<Like> likeList = post.getLikeList();
                List<Comment> commentList = post.getCommentList();
                boolean likedFriend = false, commentedFriend = false, ownFriend = false;
                for (Friend friend : acceptedFriendList) {
                    int friend1Id = friend.getFriend1().getUserId();
                    int friend2Id = friend.getFriend2().getUserId();
                    if (friend.getStatus().equalsIgnoreCase(MenuConst.FRIEND_ACCEPTED)) {
                        //Friend own this Post
                        if ((ownUserId == friend1Id || ownUserId == friend2Id)) {
                            ownFriend = true;
                            break;
                        } else {
                            //Friend don't own this Post
                            //Check Friend liked this Post
                            likedFriend = isLikedFriend(likeList, likedFriend, friend1Id, friend2Id);
                            //Check Friend comment this Post
                            commentedFriend = isCommentedFriend(commentList, commentedFriend, friend1Id, friend2Id);
                        }
                    }
                }
                if (ownFriend || likedFriend || commentedFriend) {
                    postResult.add(post);
                }
            } else if (post.getPostStatus().equalsIgnoreCase(MenuConst.POST_FRIEND)) {
                List<Friend> acceptedFriendList = friendService.getAcceptedFriendList(loginUser);
                boolean ownFriend = false;
                for (Friend friend : acceptedFriendList) {
                    int friend1Id = friend.getFriend1().getUserId();
                    int friend2Id = friend.getFriend2().getUserId();
                    if ((ownUserId == friend1Id || ownUserId == friend2Id) &&
                            friend.getStatus().equalsIgnoreCase(MenuConst.FRIEND_ACCEPTED)) {
                        ownFriend = true;
                        break;
                    }
                }
                if (ownFriend) {
                    postResult.add(post);
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

    public List<Post> findOwnerPostList(User ownUser) {
        List<Post> postList = new LinkedList<>();
        for (Post post : postList) {
            if (post.getOwnUser().getUserId() == ownUser.getUserId()) {
                postList.add(post);
            }
        }
        return postList;
    }

    public void createNewComment(Post detailPost, Comment newComment) {
        Post post = findById(detailPost.getPostId());
        if (post != null) {
            List<Comment> commentList = post.getCommentList();
            commentList.add(newComment);
            save(post);
        }
    }
}
