package app.view.post;

import app.config.InputConfig;
import app.config.MenuConst;
import app.config.ValidateConfig;
import app.controller.PostController;
import app.controller.UserController;
import app.model.Comment;
import app.model.Like;
import app.model.Post;
import app.model.User;
import app.view.main.MainView;
import app.view.user.UserView;

import java.util.List;

public class PostView {
    private final PostController postController = new PostController();
    private final UserController userController = new UserController();
    private final User loginUser = userController.getLoginUser();
    private List<Post> postList = postController.getPostList();
    private String postContent = "", postStatus = "";
    private String comment = "";

    /*========================================View Post Page Start========================================*/
    public void viewPostPage() {
        int option;
        PostUI.showMenuPostPage();
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 1:
                createNewPost();
                break;
            case 2:
                showAllPost();
                break;
            case 9:
                resetTempValue();
                new UserView().managerUserDetail();
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                PostUI.showMenuPostPage();
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                viewPostPage();
        }
    }

    private void createNewPost() {
        int option = 0;
        PostUI.showMenuCreateNewPost(option, postContent, postStatus);
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 1:
                postContent = getNewPostContent(option);
                createNewPost();
                break;
            case 2:
                postStatus = getNewPostStatus(option);
                createNewPost();
                break;
            case 8:
                checkNewPost(option);
                break;
            case 9:
                resetTempValue();
                viewPostPage();
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                PostUI.showMenuCreateNewPost(option, postContent, postStatus);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                createNewPost();
        }
    }

    private void checkNewPost(int option) {
        boolean emptyField = postContent.equals("") || postStatus.equals("");
        PostUI.showMenuCreateNewPost(option, postContent, postStatus);
        if (emptyField) {
            System.out.print(MenuConst.EMPTY_ALERT);
            InputConfig.pressAnyKey();
            createNewPost();
        } else {
            int id = postController.generatePostId();
            Post post = new Post(id, loginUser, postContent, postStatus);
            postController.createNewPost(post);
            System.out.print(MenuConst.CREATE_POST_SUCCESS);
            resetTempValue();
            InputConfig.pressAnyKey();
            viewPostPage();
        }
    }

    private void showAllPost() {
        int option = 0;
        List<Post> postUserList = postController.findOwnerPostList(loginUser);
        PostUI.showMenuUserPostPage(option, postUserList);
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 8:
                directToPostDetail(option, postUserList);
                break;
            case 9:
                resetTempValue();
                viewPostPage();
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                PostUI.showMenuUserPostPage(option, postUserList);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                showAllPost();
        }
    }

    private void directToPostDetail(int option, List<Post> postUserList) {
        PostUI.showMenuUserPostPage(option, postUserList);
        if (postList.size() == 0) {
            System.out.print(MenuConst.POST_EMPTY);
            InputConfig.pressAnyKey();
            showAllPost();
        } else {
            System.out.print(MenuConst.INPUT_POST_ID);
            int postId = InputConfig.getInteger();
            Post detailPost = findPostDetail(postId);
            showPostDetail(detailPost);
        }
    }

    public void showPostDetail(Post detailPost) {
        if (detailPost == null) {
            System.out.print(MenuConst.RESULT_NOT_FOUND);
            InputConfig.pressAnyKey();
            showAllPost();
        } else {
            PostUI.showMenuPostDetail(detailPost, loginUser);
            int option;
            System.out.print(MenuConst.SELECT_OPTION);
            option = InputConfig.getInteger();
            switch (option) {
                case 5:
                    likeUnlikePost(detailPost);
                    break;
                case 6:
                    writeNewComment(detailPost);
                    break;
                case 7:
                    updateCurrentPost(detailPost);
                    break;
                case 8:
                    deleteCurrentPost(detailPost);
                    break;
                case 9:
                    resetTempValue();
                    showAllPost();
                    break;
                case 10:
                    new MainView().logout();
                    break;
                case 0:
                    MainView.exitApplication();
                default:
                    PostUI.showMenuPostDetail(detailPost, loginUser);
                    MainView.showInvalidOption();
                    InputConfig.pressAnyKey();
                    showPostDetail(detailPost);
            }
        }
    }

    private void writeNewComment(Post detailPost) {
        int option = 0;
        PostUI.showMenuWriteNewComment(option, detailPost, comment, loginUser);
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 7:
                getNewComment(detailPost, option);
                break;
            case 8:
                checkNewComment(detailPost, option);
                break;
            case 9:
                resetTempValue();
                showPostDetail(detailPost);
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                PostUI.showMenuWriteNewComment(option, detailPost, comment, loginUser);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                writeNewComment(detailPost);
        }
    }

    private void checkNewComment(Post detailPost, int option) {
        boolean emptyField = comment.equalsIgnoreCase("");
        PostUI.showMenuWriteNewComment(option, detailPost, comment, loginUser);
        if (emptyField) {
            System.out.print(MenuConst.EMPTY_ALERT);
            InputConfig.pressAnyKey();
            writeNewComment(detailPost);
        } else {
            int id = postController.generateCommentId(detailPost);
            Comment newComment = new Comment(id, loginUser, comment);
            postController.createNewComment(detailPost, newComment);
            System.out.print(MenuConst.COMMENT_SUCCESS);
            showAllPost();
        }
    }

    private void getNewComment(Post detailPost, int option) {
        while (true) {
            PostUI.showMenuWriteNewComment(option, detailPost, comment, loginUser);
            System.out.println(MenuConst.REQUIRE_POST_CONTENT);
            System.out.print(MenuConst.INPUT_COMMENT);
            String input = InputConfig.getString();
            boolean validatePostComment = ValidateConfig.validatePostComment(input);
            if (validatePostComment) {
                comment = input;
                break;
            }
            PostUI.showMenuWriteNewComment(option, detailPost, comment, loginUser);
            System.out.print(MenuConst.INVALID_CONTENT);
        }
        writeNewComment(detailPost);
    }

    private void updateCurrentPost(Post detailPost) {
        int option = 0;
        PostUI.showMenuUpdatePostDetail(option, detailPost);
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 6:
                detailPost.setPostContent(getUpdatePostContent(option, detailPost));
                updateCurrentPost(detailPost);
                break;
            case 7:
                detailPost.setPostStatus(getUpdatePostStatus(option, detailPost));
                updateCurrentPost(detailPost);
                break;
            case 8:
                updatePostInfo(option, detailPost);
                break;
            case 9:
                resetTempValue();
                showPostDetail(detailPost);
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                PostUI.showMenuUpdatePostDetail(option, detailPost);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                updateCurrentPost(detailPost);
        }
    }

    private void likeUnlikePost(Post detailPost) {
        PostUI.showMenuPostDetail(detailPost, loginUser);
        boolean existLike = false;
        for (Like like : detailPost.getLikeList()) {
            if (like.getLikedUser().getUserId() == loginUser.getUserId() &&
                    like.getLikedUser().getValidateUserId().equals(loginUser.getValidateUserId())) {
                existLike = true;
                break;
            }
        }
        if (existLike) {
            System.out.print(MenuConst.CONFIRM_UNLIKE_POST);
        } else {
            System.out.print(MenuConst.CONFIRM_LIKE_POST);
        }
        String likeUnlikeConfirm = InputConfig.getString();
        if (likeUnlikeConfirm.equalsIgnoreCase("Y")) {
            postController.likeUnlikePost(detailPost, existLike, loginUser);
            resetTempValue();
            PostUI.showMenuPostDetail(detailPost, loginUser);
            if (existLike) {
                System.out.print(MenuConst.UNLIKE_POST_SUCCESS);
            } else {
                System.out.print(MenuConst.LIKE_POST_SUCCESS);
            }
            InputConfig.pressAnyKey();
        }
        showPostDetail(detailPost);
    }

    private void updatePostInfo(int option, Post detailPost) {
        boolean emptyField = detailPost.getPostContent().equals("") || detailPost.getPostStatus().equals("");
        PostUI.showMenuUpdatePostDetail(option, detailPost);
        if (emptyField) {
            System.out.print(MenuConst.EMPTY_ALERT);
            InputConfig.pressAnyKey();
            updateCurrentPost(detailPost);
        } else {
            postController.updateCurrentPost(detailPost);
            System.out.print(MenuConst.UPDATE_POST_SUCCESS);
            resetTempValue();
            InputConfig.pressAnyKey();
            showPostDetail(detailPost);
        }
    }

    public void deleteCurrentPost(Post detailPost) {
        PostUI.showMenuPostDetail(detailPost, loginUser);
        System.out.print(MenuConst.CONFIRM_DELETE_POST);
        String deleteConfirm = InputConfig.getString();
        if (deleteConfirm.equalsIgnoreCase("Y")) {
            postController.deleteCurrentPost(detailPost);
            System.out.print(MenuConst.DELETE_SUCCESS);
            InputConfig.pressAnyKey();
            showAllPost();
        }
        showPostDetail(detailPost);
    }

    private String getNewPostContent(int option) {
        while (true) {
            PostUI.showMenuCreateNewPost(option, postContent, postStatus);
            System.out.println(MenuConst.REQUIRE_POST_CONTENT);
            System.out.print(MenuConst.INPUT_POST);
            String input = InputConfig.getString();
            boolean validatePostComment = ValidateConfig.validatePostComment(input);
            if (validatePostComment) {
                return input;
            } else {
                PostUI.showMenuCreateNewPost(option, postContent, postStatus);
                System.out.print(MenuConst.INVALID_CONTENT);
                InputConfig.pressAnyKey();
            }
        }
    }

    private String getNewPostStatus(int option) {
        while (true) {
            PostUI.showMenuCreateNewPost(option, postContent, postStatus);
            System.out.println(MenuConst.REQUIRE_STATUS);
            System.out.print(MenuConst.INPUT_POST);
            String input = InputConfig.getString();
            PostUI.showMenuCreateNewPost(option, postContent, postStatus);
            if (input.equalsIgnoreCase(MenuConst.POST_PRIVATE)) {
                return MenuConst.POST_PRIVATE;
            } else if (input.equalsIgnoreCase(MenuConst.POST_FRIEND)) {
                return MenuConst.POST_FRIEND;
            } else if (input.equalsIgnoreCase(MenuConst.POST_PUBLIC)) {
                return MenuConst.POST_PUBLIC;
            } else {
                System.out.print(MenuConst.INVALID_STATUS);
                InputConfig.pressAnyKey();
            }
        }
    }

    private String getUpdatePostContent(int option, Post detailPost) {
        while (true) {
            PostUI.showMenuUpdatePostDetail(option, detailPost);
            System.out.println(MenuConst.REQUIRE_POST_CONTENT);
            System.out.print(MenuConst.INPUT_POST);
            String input = InputConfig.getString();
            boolean validatePostComment = ValidateConfig.validatePostComment(input);
            if (validatePostComment) {
                return input;
            } else {
                PostUI.showMenuUpdatePostDetail(option, detailPost);
                System.out.print(MenuConst.INVALID_CONTENT);
                InputConfig.pressAnyKey();
            }
        }
    }

    private String getUpdatePostStatus(int option, Post detailPost) {
        while (true) {
            PostUI.showMenuUpdatePostDetail(option, detailPost);
            System.out.println(MenuConst.REQUIRE_STATUS);
            System.out.print(MenuConst.INPUT_POST);
            String input = InputConfig.getString();
            if (input.equalsIgnoreCase(MenuConst.POST_PRIVATE)) {
                return MenuConst.POST_PRIVATE;
            } else if (input.equalsIgnoreCase(MenuConst.POST_FRIEND)) {
                return MenuConst.POST_FRIEND;
            } else if (input.equalsIgnoreCase(MenuConst.POST_PUBLIC)) {
                return MenuConst.POST_PUBLIC;
            } else {
                PostUI.showMenuUpdatePostDetail(option, detailPost);
                System.out.print(MenuConst.INVALID_STATUS);
                InputConfig.pressAnyKey();
            }
        }
    }

    /*========================================View Post Page End========================================*/
    /*========================================Others========================================*/
    private void resetTempValue() {
        postList = postController.getPostList();
        postContent = postStatus = comment = "";
    }

    private Post findPostDetail(int postId) {
        List<Post> postUserList = postController.findOwnerPostList(loginUser);
        for (Post post : postUserList) {
            if (post.getPostId() == postId) {
                return post;
            }
        }
        return null;
    }
}
