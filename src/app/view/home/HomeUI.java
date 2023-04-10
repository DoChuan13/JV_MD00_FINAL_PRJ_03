package app.view.home;

import app.config.BreakConfig;
import app.config.ColorConfig;
import app.config.MenuConst;
import app.model.Post;
import app.model.User;
import app.view.post.PostUI;

import java.util.List;

public class HomeUI {
    public static void showMenuHomePage(int option, List<Post> postList) {
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_HOME);
        //Show all post here
        if (postList.size() == 0) {
            System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.INACTIVE_COLOR + "  => " + MenuConst.WIDTH_2_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "You have no any Post for you in Home Page");
        } else {
            for (Post post : postList) {
                showAllPost(post);
            }
        }
        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 8 ? ColorConfig.ACTIVE_COLOR : ColorConfig.ACTION_COLOR) + "  8. " + MenuConst.WIDTH_2_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "View Post");
        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }

    private static void showAllPost(Post post) {
        PostUI.showPostInfo(post);
    }

    public static void showMenuPostDetail(Post detailPost, User loginUser) {
        PostUI.showMenuPostDetail(detailPost,loginUser);
    }

    public static void showMenuWriteNewComment(int option, Post detailPost, String comment, User loginUser) {
        PostUI.showMenuWriteNewComment(option, detailPost, comment, loginUser);
    }

    public static void showMenuUpdatePostDetail(int option, Post detailPost) {
        PostUI.showMenuUpdatePostDetail(option, detailPost);
    }
}
