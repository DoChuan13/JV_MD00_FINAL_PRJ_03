package app.view.home;

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
import app.view.post.PostView;
import app.view.user.UserView;

import java.util.List;

public class HomeView {
    private static final UserController userController = new UserController();
    private static final PostController postController = new PostController();
    private User loginUser = userController.getLoginUser();
    private List<Post> postList = postController.getPostListForUser(loginUser);

    /*========================================View Home Page Start========================================*/
    private String comment = "";

    public void viewHomePage() {
        int option = 0;
        HomeUI.showMenuHomePage(option, postList);
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 8:
                directToPostDetail(option, postList);
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
                HomeUI.showMenuHomePage(option, postList);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                viewHomePage();
        }
    }

    private void directToPostDetail(int option, List<Post> postList) {
        HomeUI.showMenuHomePage(option, postList);
        if (postList.size() == 0) {
            System.out.print(MenuConst.POST_EMPTY);
            InputConfig.pressAnyKey();
            viewHomePage();
        } else {
            System.out.print(MenuConst.INPUT_POST_ID);
            int postId = InputConfig.getInteger();
            Post detailPost = findPostDetail(postId, postList);
            showPostDetail(detailPost);
        }
    }

    private void showPostDetail(Post detailPost) {
        if (detailPost == null) {
            System.out.print(MenuConst.RESULT_NOT_FOUND);
            InputConfig.pressAnyKey();
            viewHomePage();
        } else {
            HomeUI.showMenuPostDetail(detailPost, loginUser);
            int option;
            System.out.print(MenuConst.SELECT_OPTION);
            option = InputConfig.getInteger();
            switch (option) {
                case 4:
                    showLikedUserList(detailPost);
                    break;
                case 5:
                    likeUnlikePost(detailPost);
                    break;
                case 6:
                    writeNewComment(detailPost);
                    break;
                case 7:
                    if (detailPost.getOwnUser().getUserId() != loginUser.getUserId()) {
                        HomeUI.showMenuPostDetail(detailPost, loginUser);
                        MainView.showInvalidOption();
                        InputConfig.pressAnyKey();
                        showPostDetail(detailPost);
                    } else {
                        updateCurrentPost(detailPost);
                    }
                    break;
                case 8:
                    if (detailPost.getOwnUser().getUserId() != loginUser.getUserId()) {
                        HomeUI.showMenuPostDetail(detailPost, loginUser);
                        MainView.showInvalidOption();
                        InputConfig.pressAnyKey();
                        showPostDetail(detailPost);
                    } else {
                        deleteCurrentPost(detailPost);
                    }
                    break;
                case 9:
                    resetTempValue();
                    viewHomePage();
                    break;
                case 10:
                    new MainView().logout();
                    break;
                case 0:
                    MainView.exitApplication();
                default:
                    HomeUI.showMenuPostDetail(detailPost, loginUser);
                    MainView.showInvalidOption();
                    InputConfig.pressAnyKey();
                    showPostDetail(detailPost);
            }
        }
    }

    private void likeUnlikePost(Post detailPost) {
        HomeUI.showMenuPostDetail(detailPost, loginUser);
        boolean existLike = false;
        for (Like like : detailPost.getLikeList()) {
            if (like.getLikedUser().getUserId() == loginUser.getUserId() &&
                    like.getLikedUser().getCreatedTime() == loginUser.getCreatedTime()) {
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
            HomeUI.showMenuPostDetail(detailPost, loginUser);
            if (existLike) {
                System.out.print(MenuConst.UNLIKE_POST_SUCCESS);
            } else {
                System.out.print(MenuConst.LIKE_POST_SUCCESS);
            }
            InputConfig.pressAnyKey();
        }
        showPostDetail(detailPost);
    }

    public void deleteCurrentPost(Post detailPost) {
        HomeUI.showMenuPostDetail(detailPost, loginUser);
        System.out.print(MenuConst.CONFIRM_DELETE_POST);
        String deleteConfirm = InputConfig.getString();
        if (deleteConfirm.equalsIgnoreCase("Y")) {
            postController.deleteCurrentPost(detailPost);
            System.out.print(MenuConst.DELETE_SUCCESS);
            InputConfig.pressAnyKey();
            viewHomePage();
        }
        showPostDetail(detailPost);
    }

    private void updateCurrentPost(Post detailPost) {
        int option = 0;
        HomeUI.showMenuUpdatePostDetail(option, detailPost);
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
                HomeUI.showMenuUpdatePostDetail(option, detailPost);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                updateCurrentPost(detailPost);
        }
    }

    private void updatePostInfo(int option, Post detailPost) {
        boolean emptyField = detailPost.getPostContent().equals("") || detailPost.getPostStatus().equals("");
        HomeUI.showMenuUpdatePostDetail(option, detailPost);
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

    private String getUpdatePostContent(int option, Post detailPost) {
        while (true) {
            HomeUI.showMenuUpdatePostDetail(option, detailPost);
            System.out.println(MenuConst.REQUIRE_POST_CONTENT);
            System.out.print(MenuConst.INPUT_POST);
            String input = InputConfig.getString();
            boolean validatePostComment = ValidateConfig.validatePostComment(input);
            if (validatePostComment) {
                return input;
            } else {
                HomeUI.showMenuUpdatePostDetail(option, detailPost);
                System.out.print(MenuConst.INVALID_CONTENT);
                InputConfig.pressAnyKey();
            }
        }
    }

    private String getUpdatePostStatus(int option, Post detailPost) {
        while (true) {
            HomeUI.showMenuUpdatePostDetail(option, detailPost);
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
                HomeUI.showMenuUpdatePostDetail(option, detailPost);
                System.out.print(MenuConst.INVALID_STATUS);
                InputConfig.pressAnyKey();
            }
        }
    }

    private void writeNewComment(Post detailPost) {
        int option = 0;
        HomeUI.showMenuWriteNewComment(option, detailPost, comment, loginUser);
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
                HomeUI.showMenuWriteNewComment(option, detailPost, comment, loginUser);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                writeNewComment(detailPost);
        }
    }

    private void getNewComment(Post detailPost, int option) {
        while (true) {
            HomeUI.showMenuWriteNewComment(option, detailPost, comment, loginUser);
            System.out.println(MenuConst.REQUIRE_POST_CONTENT);
            System.out.print(MenuConst.INPUT_COMMENT);
            String input = InputConfig.getString();
            boolean validatePostComment = ValidateConfig.validatePostComment(input);
            if (validatePostComment) {
                comment = input;
                break;
            }
            HomeUI.showMenuWriteNewComment(option, detailPost, comment, loginUser);
            System.out.print(MenuConst.INVALID_CONTENT);
        }
        writeNewComment(detailPost);
    }

    private void checkNewComment(Post detailPost, int option) {
        boolean emptyField = comment.equalsIgnoreCase("");
        HomeUI.showMenuWriteNewComment(option, detailPost, comment, loginUser);
        if (emptyField) {
            System.out.print(MenuConst.EMPTY_ALERT);
            InputConfig.pressAnyKey();
            writeNewComment(detailPost);
        } else {
            int id = postController.generateCommentId(detailPost);
            Comment newComment = new Comment(id, loginUser, comment);
            postController.createNewComment(detailPost, newComment);
            resetTempValue();
            System.out.print(MenuConst.COMMENT_SUCCESS);
            showPostDetail(detailPost);
        }
    }

    private Post findPostDetail(int postId, List<Post> postList) {
        for (Post post : postList) {
            if (post.getPostId() == postId) {
                return post;
            }
        }
        return null;
    }

    private void showLikedUserList(Post detailPost) {
        new PostView().showLikedUserList(detailPost);
    }

    /*========================================View Home Page End========================================*/
    private void resetTempValue() {
        loginUser = userController.getLoginUser();
        postList = postController.getPostListForUser(loginUser);
        comment = "";
    }
}
