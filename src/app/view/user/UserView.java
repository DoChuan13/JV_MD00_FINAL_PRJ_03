package app.view.user;

import app.config.InputConfig;
import app.config.MenuConst;
import app.view.home.HomeView;
import app.view.main.MainView;
import app.view.post.PostView;
import app.view.user.ui.UserUI;

public class UserView {
    public UserView() {
    }

    /*========================================User Info Manager========================================*/
    public void managerUserDetail() {
        int option;
        UserUI.showMenuUserManager();
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 1:
                new HomeView().viewHomePage();
                break;
            case 2:
                new UserInfoView().viewUserInfoPage();
                break;
            case 3:
                new PostView().viewPostPage();
                break;
            case 4:
                new FriendView().manageViewFriendPage();
                break;
            case 5:
                new ChatView().viewChatPage();
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                UserUI.showMenuUserManager();
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                managerUserDetail();
        }
    }
}
