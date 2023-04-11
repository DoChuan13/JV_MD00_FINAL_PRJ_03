package app.view.post;

import app.config.BreakConfig;
import app.config.ColorConfig;
import app.config.MenuConst;
import app.controller.UserController;
import app.model.Comment;
import app.model.Like;
import app.model.Post;
import app.model.User;

import java.util.List;

public final class PostUI {
    public static void showMenuPostPage() {
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_POST);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  1. " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Create New Post");
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  2. " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "View All Post");
        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }

    public static void showMenuCreateNewPost(int option, String postContent, String postStatus) {
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_NEW_POST);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + (option == 1 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "  1. " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Post Content:");
        breakLineContent(option, (byte) 1, postContent);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + (option == 2 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "  2. " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Post Status:");
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + (option == 2 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "     " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, postStatus);
        System.out.println(MenuConst.BREAK_LINE);

        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.ACTION_COLOR + "  8. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Create Post");
        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }


    public static void showMenuUserPostPage(int option, List<Post> postList) {
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_ALL_POST);
        if (postList.size() == 0) {
            System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_NO_CONTENT + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, MenuConst.HAVE_NO_POST_HOME);
        } else {
            for (Post post : postList) {
                showPostInfo(post);
            }
        }
        System.out.println(MenuConst.BREAK_LINE);

        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 8 ? ColorConfig.ACTIVE_COLOR : ColorConfig.ACTION_COLOR) + "  8. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "See Detail");
        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }

    public static void showMenuPostDetail(Post detailPost, User loginUser) {
        boolean existLike = false;
        for (Like like : detailPost.getLikeList()) {
            if (like.getLikedUser().getUserId() == loginUser.getUserId() && like.getLikedUser().getCreatedTime() == loginUser.getCreatedTime()) {
                existLike = true;
                break;
            }
        }

        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_POST_DETAIL);

        showPostInfo(detailPost);
        showCommentDetail(detailPost);
        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.ACTION_COLOR + "  4. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Show Liked List");
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.ACTION_COLOR + "  5. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, (existLike ? "Unlike Post" : "Like Post"));
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.ACTION_COLOR + "  6. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "New Comment");
        if (loginUser.getUserId() == detailPost.getOwnUser().getUserId()) {
            System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.ACTION_COLOR + "  7. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Edit Post");
            System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.ACTION_COLOR + "  8. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Delete Post");
        }
        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }

    public static void showMenuWriteNewComment(int option, Post post, String newComment, User loginUser) {
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_POST_DETAIL);
        showPostInfo(post);
        showCommentDetail(post);
        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "User: " + loginUser.getName());
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + (option == 7 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "  => " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Comment");
        breakLineContent(option, (byte) 7, newComment);
        System.out.println(MenuConst.BREAK_LINE);

        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.ACTION_COLOR + "  7. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Write Comment");
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.ACTION_COLOR + "  8. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Post Comment");

        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }

    public static void showMenuUpdatePostDetail(int option, Post post) {
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_UPDATE_POST_DETAIL);

        showUpdatePostDetail(option, post);
        showCommentDetail(post);
        System.out.println(MenuConst.BREAK_LINE);

        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.ACTION_COLOR + "  6. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Change Content");
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.ACTION_COLOR + "  7. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Change Status");
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.ACTION_COLOR + "  8. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Update Post");
        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }

    public static void showPostInfo(Post post) {
        String postIdView = "(" + "Post ID: " + post.getPostId() + ")";
        String ownerUserView = "(User: " + post.getOwnUser().getName() + ")";
        //String postContentView = "(Content)";
        String postStatusView = "Status: " + post.getPostStatus();
        String postLikeView = "Like: " + post.getLikeList().size();
        String postCommentView = "Comment: " + post.getCommentList().size();

        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.POST_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, ownerUserView, postIdView);
        //System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "     " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, postContentView);
        breakLineContent((byte) 0, (byte) 1, post.getPostContent());
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "     " + MenuConst.WIDTH_3_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, postStatusView, postLikeView, postCommentView);
        System.out.println(MenuConst.BLANK_LINE);
    }

    private static void showUpdatePostDetail(int option, Post post) {
        String postIdView = "(" + "Post ID: " + post.getPostId() + ")";
        //String postContentView = "(Content)";
        String postStatusView = "Status: " + post.getPostStatus();
        String postLikeView = "Like: " + post.getLikeList().size();
        String postCommentView = "Comment: " + post.getCommentList().size();
        String ownerUserView = "User: " + post.getOwnUser().getName();

        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.POST_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, ownerUserView, postIdView);
        //System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + (option == 6 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "     " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, postContentView);
        breakLineContent(option, (byte) 6, post.getPostContent());
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + (option == 7 ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "     " + MenuConst.WIDTH_3_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, postStatusView, postLikeView, postCommentView);
        System.out.println(MenuConst.BLANK_LINE);

    }

    public static void breakLineContent(int option, byte active, String postContent) {
        String formatStr = ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + (option == active ? ColorConfig.ACTIVE_COLOR : ColorConfig.INACTIVE_COLOR) + "     " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET;
        if (postContent.length() == 0) {
            System.out.printf(formatStr, postContent);
        } else {
            byte length = 1;
            StringBuilder tempStr = new StringBuilder();
            for (int j = 0; j < postContent.length(); j++) {
                tempStr.append(postContent.charAt(j));
                if (j == length * 75) {
                    System.out.printf(formatStr, tempStr);
                    tempStr = new StringBuilder();
                    length++;
                }
            }
            System.out.printf(formatStr, tempStr);
        }
    }

    private static void showCommentDetail(Post post) {
        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Comment: ");
        for (Comment comment : post.getCommentList()) {
            User commentUser = new UserController().findUserById(comment.getCommentUser().getUserId());
            showAllComment(comment, commentUser);
        }
    }

    private static void showAllComment(Comment comment, User commentUser) {
        String commentUserView = "User: " + comment.getCommentUser().getName();
        String userStatusView = "Status: " + ((commentUser == null) ? "Unavailable" : (commentUser.getCreatedTime() != comment.getCommentUser().getCreatedTime() ? "Unavailable" : "Active"));
        String userIdView = "User Id: " + ((commentUser == null) ? "Unknown" : (commentUser.getCreatedTime() != (comment.getCommentUser().getCreatedTime()) ? "Unknown" : comment.getCommentUser().getUserId()));
        //String commentContentView = "(Content)";
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_3_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, commentUserView, userStatusView, userIdView);
        //System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, commentContentView);
        breakLineContent((byte) 0, (byte) 1, comment.getComment());
        System.out.println(MenuConst.BLANK_LINE);
    }

    public static void showMenuLikedUserList(Post post) {
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_VIEW_LIKE_POST);

        String commentUserTitle = "User: ";
        String userStatusTitle = "Status: ";
        String userIdTitle = "User Id: ";
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_3_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, commentUserTitle, userStatusTitle, userIdTitle);
        if (post.getCommentList().size() == 0) {
            System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_NO_CONTENT + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, MenuConst.HAVE_NO_LIKE_IN_POST);
            System.out.println(MenuConst.BLANK_LINE);
        } else {
            for (Comment comment : post.getCommentList()) {
                User commentUser = new UserController().findUserById(comment.getCommentUser().getUserId());
                showAllLiked(comment, commentUser);
            }
            System.out.println(MenuConst.BLANK_LINE);
        }
        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }

    public static void showAllLiked(Comment comment, User commentUser) {
        String commentUserView = comment.getCommentUser().getName();
        String userStatusView = ((commentUser == null) ? "Unavailable" : (commentUser.getCreatedTime() != comment.getCommentUser().getCreatedTime() ? "Unavailable" : "Active"));
        String userIdView = String.valueOf((commentUser == null) ? "Unknown" : (commentUser.getCreatedTime() != (comment.getCommentUser().getCreatedTime()) ? "Unknown" : comment.getCommentUser().getUserId()));
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "     " + MenuConst.WIDTH_3_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, commentUserView, userStatusView, userIdView);
    }
}
