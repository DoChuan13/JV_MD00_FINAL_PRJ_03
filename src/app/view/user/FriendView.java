package app.view.user;

import app.config.InputConfig;
import app.config.MenuConst;
import app.controller.FriendController;
import app.controller.UserController;
import app.model.Friend;
import app.model.User;
import app.view.main.MainView;
import app.view.user.ui.FriendUI;

import java.util.LinkedList;
import java.util.List;

public class FriendView {
    private static final UserController userController = new UserController();
    private static final FriendController friendController = new FriendController();
    private List<User> searchUserResult = new LinkedList<>();
    private User loginUser = userController.getLoginUser();
    private List<Friend> friendList = friendController.getListAllFriend(loginUser);
    private List<Friend> acceptedFriends = friendController.getAcceptedFriendList(loginUser);
    private List<Friend> pendingRequestFriends = friendController.getPendingFriendList(loginUser);
    private List<Friend> sentRequestFriends = friendController.getSentRequestList(loginUser);

    /*========================================View Friend Page Start========================================*/
    public void manageViewFriendPage() {
        int option;
        FriendUI.showMenuFriendView(pendingRequestFriends, sentRequestFriends);
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 1:
                manageAllFriendList();
                break;
            case 2:
                manageAddNewFriend();
                break;
            case 3:
                manageFriendRequest();
            case 4:
                manageFriendSent();
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
                FriendUI.showMenuFriendView(pendingRequestFriends, sentRequestFriends);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                manageViewFriendPage();
        }
    }

    private void manageFriendSent() {
        int option = 0;
        FriendUI.showMenuRequestSent(option, sentRequestFriends, loginUser);
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 8:
                cancelSentRequest(option);
                break;
            case 9:
                resetTempValue();
                manageViewFriendPage();
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                FriendUI.showMenuRequestSent(option, sentRequestFriends, loginUser);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                manageFriendRequest();
        }
    }


    private void manageFriendRequest() {
        int option = 0;
        FriendUI.showMenuConfirmFriendRequest(option, pendingRequestFriends, loginUser);
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 8:
                confirmFriendRequest(option);
                break;
            case 9:
                resetTempValue();
                manageViewFriendPage();
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                FriendUI.showMenuConfirmFriendRequest(option, pendingRequestFriends, loginUser);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                manageFriendRequest();
        }
    }

    private void confirmFriendRequest(int option) {
        FriendUI.showMenuConfirmFriendRequest(option, pendingRequestFriends, loginUser);
        if (pendingRequestFriends.size() == 0) {
            System.out.print(MenuConst.FRIEND_EMPTY);
            InputConfig.pressAnyKey();
        } else {
            System.out.print(MenuConst.INPUT_FR_ID_TO_CONFIRM);
            int friendId = InputConfig.getInteger();
            //Select Id and confirm
            Friend friend = friendController.getPendingFriendById(friendId, loginUser);
            if (friend == null) {
                FriendUI.showMenuConfirmFriendRequest(option, pendingRequestFriends, loginUser);
                System.out.print(MenuConst.RESULT_NOT_FOUND);
                InputConfig.pressAnyKey();
            } else {
                while (true) {
                    FriendUI.showMenuConfirmFriendRequest(option, pendingRequestFriends, loginUser);
                    System.out.println(MenuConst.REQUIRE_FRIEND_STATUS);
                    System.out.print(MenuConst.CHANGE_FRIEND_STATUS + "(for Friend Id " + friendId + ":) ");
                    String status = InputConfig.getString();
                    if (status.equalsIgnoreCase("Accept")) {
                        FriendUI.showMenuConfirmFriendRequest(option, pendingRequestFriends, loginUser);
                        System.out.print(MenuConst.CONFIRM_ACCEPT);
                        String confirm = InputConfig.getString();
                        if (confirm.equalsIgnoreCase("Y")) {
                            friend.setStatus(MenuConst.FRIEND_ACCEPTED);
                            resetTempValue();
                            FriendUI.showMenuConfirmFriendRequest(option, pendingRequestFriends, loginUser);
                            System.out.print(MenuConst.ACCEPT_SUCCESS);
                            InputConfig.pressAnyKey();
                            break;
                        }
                    }
                    if (status.equalsIgnoreCase("Reject")) {
                        FriendUI.showMenuConfirmFriendRequest(option, pendingRequestFriends, loginUser);
                        System.out.print(MenuConst.CONFIRM_REJECT);
                        String confirm = InputConfig.getString();
                        FriendUI.showMenuConfirmFriendRequest(option, pendingRequestFriends, loginUser);
                        if (confirm.equalsIgnoreCase("Y")) {
                            friend.setStatus(MenuConst.FRIEND_REJECT);
                            resetTempValue();
                            FriendUI.showMenuConfirmFriendRequest(option, pendingRequestFriends, loginUser);
                            System.out.print(MenuConst.REJECT_SUCCESS);
                            InputConfig.pressAnyKey();
                            break;
                        }
                    } else {
                        FriendUI.showMenuConfirmFriendRequest(option, pendingRequestFriends, loginUser);
                        System.out.println(MenuConst.INVALID_STATUS);
                        InputConfig.pressAnyKey();
                    }
                }
                friendController.changeFriendStatus(friend);
                resetTempValue();
            }
        }
        manageFriendRequest();
    }

    private void cancelSentRequest(int option) {
        FriendUI.showMenuRequestSent(option, sentRequestFriends, loginUser);
        if (sentRequestFriends.size() == 0) {
            System.out.print(MenuConst.FRIEND_EMPTY);
            InputConfig.pressAnyKey();
        } else {
            System.out.print(MenuConst.INPUT_FR_ID_TO_REMOVE);
            int friendId = InputConfig.getInteger();
            Friend friend = friendController.getSentRequestByIdAndUser(friendId, loginUser);
            FriendUI.showMenuRequestSent(option, sentRequestFriends, loginUser);
            if (friend == null) {
                System.out.print(MenuConst.RESULT_NOT_FOUND);
                InputConfig.pressAnyKey();
            } else {
                System.out.print(MenuConst.CONFIRM_DELETE_REQUEST);
                String confirm = InputConfig.getString();
                if (confirm.equalsIgnoreCase("y")) {
                    friendController.deleteFriend(friend);
                    resetTempValue();
                    FriendUI.showMenuRequestSent(option, sentRequestFriends, loginUser);
                    System.out.print(MenuConst.DELETE_REQUEST_SUCCESS);
                    InputConfig.pressAnyKey();
                }
            }
        }
        manageFriendSent();
    }

    private void manageAddNewFriend() {
        int option = 0;
        FriendUI.showMenuFindNewFriend(option, searchUserResult, friendList, loginUser);
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 8:
                sentAddFriendRequest(option);
                break;
            case 9:
                resetTempValue();
                manageViewFriendPage();
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                FriendUI.showMenuFindNewFriend(option, searchUserResult, friendList, loginUser);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                manageAddNewFriend();
        }
    }


    private void manageAllFriendList() {
        int option = 0;
        FriendUI.showMenuAllFriendView(option, acceptedFriends, loginUser);
        System.out.print(MenuConst.SELECT_OPTION);
        option = InputConfig.getInteger();
        switch (option) {
            case 8:
                viewFriendInfoDetail(option);
                break;
            case 9:
                resetTempValue();
                manageViewFriendPage();
                break;
            case 10:
                new MainView().logout();
                break;
            case 0:
                MainView.exitApplication();
            default:
                FriendUI.showMenuAllFriendView(option, acceptedFriends, loginUser);
                MainView.showInvalidOption();
                InputConfig.pressAnyKey();
                manageAllFriendList();
        }

    }

    private void viewFriendInfoDetail(int option) {
        if (acceptedFriends.size() == 0) {
            FriendUI.showMenuAllFriendView(option, acceptedFriends, loginUser);
            System.out.print(MenuConst.FRIEND_EMPTY);
            InputConfig.pressAnyKey();
            manageAllFriendList();
        } else {
            FriendUI.showMenuAllFriendView(option, acceptedFriends, loginUser);
            System.out.print(MenuConst.INPUT_FR_ID_TO_SEE);
            int friendId = InputConfig.getInteger();
            Friend friend = friendController.getAcceptedFriendById(friendId, loginUser);
            if (friend == null) {
                FriendUI.showMenuAllFriendView(option, acceptedFriends, loginUser);
                System.out.print(MenuConst.RESULT_NOT_FOUND);
                InputConfig.pressAnyKey();
                manageAllFriendList();
            } else {
                int action = 0;
                FriendUI.showMenuAFriendDetailView(action, friend, loginUser);
                System.out.print(MenuConst.SELECT_OPTION);
                action = InputConfig.getInteger();
                switch (action) {
                    case 8:
                        cancelFriendStatus(action, friend);
                        break;
                    case 9:
                        resetTempValue();
                        manageAllFriendList();
                        break;
                    case 10:
                        new MainView().logout();
                        break;
                    case 0:
                        MainView.exitApplication();
                    default:
                        FriendUI.showMenuAFriendDetailView(action, friend, loginUser);
                        MainView.showInvalidOption();
                        InputConfig.pressAnyKey();
                        manageAllFriendList();
                }
            }
        }
    }

    private void cancelFriendStatus(int action, Friend friend) {
        FriendUI.showMenuAFriendDetailView(action, friend, loginUser);
        System.out.print(MenuConst.CONFIRM_UN_FRIEND);
        String confirm = InputConfig.getString();
        if (confirm.equalsIgnoreCase("y")) {
            friendController.deleteFriend(friend);
            resetTempValue();
            System.out.print(MenuConst.UNFRIEND_SUCCESS);
            InputConfig.pressAnyKey();
        }
        manageAllFriendList();
    }

    private void sentAddFriendRequest(int option) {
        if (searchUserResult.size() == 0) {
            FriendUI.showMenuFindNewFriend(option, searchUserResult, friendList, loginUser);
            System.out.print(MenuConst.INPUT_SEARCH_NAME);
            String name = InputConfig.getString();
            searchUserResult = userController.findUserWithoutMe(loginUser, name);//filter Me and Admin/PM
            if (searchUserResult.size() == 0) {
                FriendUI.showMenuFindNewFriend(option, searchUserResult, friendList, loginUser);
                System.out.print(MenuConst.RESULT_NOT_FOUND);
                InputConfig.pressAnyKey();
            }
            manageAddNewFriend();
        } else {
            FriendUI.showMenuFindNewFriend(option, searchUserResult, friendList, loginUser);
            System.out.print(MenuConst.INPUT_FR_ID_TO_ADD);
            int id = InputConfig.getInteger();
            User friendUser = selectUserToAddFriend(id);
            FriendUI.showMenuFindNewFriend(option, searchUserResult, friendList, loginUser);
            if (friendUser == null) {
                System.out.print(MenuConst.RESULT_NOT_FOUND);
                InputConfig.pressAnyKey();
                manageAddNewFriend();
            } else {
                boolean checkFriendStatus = true;
                Friend rejectedFriend = null;
                for (Friend friend : friendList) {
                    //Remove sent request
                    checkFriendStatus = removeSentRequest(option, id, checkFriendStatus, friend);
                    //Remove friend
                    checkFriendStatus = removeFriendStatus(option, id, checkFriendStatus, friend);
                    //Confirm friend request
                    checkFriendStatus = responseFriendRequest(option, id, checkFriendStatus, friend);
                    if (friend.getFriend1().getUserId() == id) {
                        if (friend.getStatus().equalsIgnoreCase(MenuConst.FRIEND_REJECT)) {
                            rejectedFriend = friend;
                        }
                    }
                }
                if (checkFriendStatus) {
                    //Send friend request
                    sentFriendRequest(option, friendUser, rejectedFriend);
                }
                manageViewFriendPage();
            }
        }
    }

    private void sentFriendRequest(int option, User friendUser, Friend rejectedFriend) {
        FriendUI.showMenuFindNewFriend(option, searchUserResult, friendList, loginUser);
        System.out.print(MenuConst.FRIEND_REQUEST_CONFIRM);
        String confirm = InputConfig.getString();
        if (confirm.equalsIgnoreCase("Y")) {
            int friendId = friendController.generateFriendId();
            Friend newFriend;
            if (rejectedFriend == null) {
                newFriend = new Friend(friendId, loginUser, friendUser, MenuConst.FRIEND_PENDING);
            } else {
                rejectedFriend.setFriend1(loginUser);
                rejectedFriend.setFriend2(friendUser);
                rejectedFriend.setStatus(MenuConst.FRIEND_PENDING);
                newFriend = rejectedFriend;
            }
            friendController.sentFriendRequest(newFriend);
            friendList = friendController.getListAllFriend(loginUser);
            FriendUI.showMenuFindNewFriend(option, searchUserResult, friendList, loginUser);
            System.out.print(MenuConst.SEND_FRIEND_REQUEST_SUCCESS);
            resetTempValue();
            InputConfig.pressAnyKey();
            manageViewFriendPage();
        } else manageAddNewFriend();
    }

    private boolean responseFriendRequest(int option, int id, boolean checkFriendStatus, Friend friend) {
        if (friend.getFriend1().getUserId() == id) {
            if (friend.getStatus().equalsIgnoreCase(MenuConst.FRIEND_PENDING)) {
                checkFriendStatus = false;
                FriendUI.showMenuFindNewFriend(option, searchUserResult, friendList, loginUser);
                System.out.print(MenuConst.FRIEND_REQUEST_ALERT);
                String confirmAction = InputConfig.getString();
                if (confirmAction.equalsIgnoreCase("Y")) {
                    //Confirm Friend Request
                    while (true) {
                        FriendUI.showMenuFindNewFriend(option, searchUserResult, friendList, loginUser);
                        System.out.println(MenuConst.REQUIRE_FRIEND_STATUS);
                        System.out.print(MenuConst.CHANGE_FRIEND_STATUS + "(with User " + friend.getFriend1().getName() + ":) ");
                        String status = InputConfig.getString();
                        if (status.equalsIgnoreCase("Accept")) {
                            FriendUI.showMenuFindNewFriend(option, searchUserResult, friendList, loginUser);
                            System.out.print(MenuConst.CONFIRM_ACCEPT);
                            String confirm = InputConfig.getString();
                            if (confirm.equalsIgnoreCase("Y")) {
                                friend.setStatus(MenuConst.FRIEND_ACCEPTED);
                                friendList = friendController.getListAllFriend(loginUser);
                                FriendUI.showMenuFindNewFriend(option, searchUserResult, friendList, loginUser);
                                System.out.print(MenuConst.ACCEPT_SUCCESS);
                                resetTempValue();
                                InputConfig.pressAnyKey();
                                break;
                            }
                        }
                        if (status.equalsIgnoreCase("Reject")) {
                            FriendUI.showMenuFindNewFriend(option, searchUserResult, friendList, loginUser);
                            System.out.print(MenuConst.CONFIRM_REJECT);
                            String confirm = InputConfig.getString();
                            FriendUI.showMenuFindNewFriend(option, searchUserResult, friendList, loginUser);
                            if (confirm.equalsIgnoreCase("Y")) {
                                friend.setStatus(MenuConst.FRIEND_REJECT);
                                friendList = friendController.getListAllFriend(loginUser);
                                FriendUI.showMenuFindNewFriend(option, searchUserResult, friendList, loginUser);
                                System.out.print(MenuConst.REJECT_SUCCESS);
                                resetTempValue();
                                InputConfig.pressAnyKey();
                                break;
                            }
                        } else {
                            FriendUI.showMenuFindNewFriend(option, searchUserResult, friendList, loginUser);
                            System.out.println(MenuConst.INVALID_STATUS);
                            InputConfig.pressAnyKey();
                        }
                    }


                    friendController.deleteFriend(friend);
                    FriendUI.showMenuFindNewFriend(option, searchUserResult, friendList, loginUser);
                    System.out.print(MenuConst.REMOVE_FRIEND_STATUS_SUCCESS);
                    resetTempValue();
                    InputConfig.pressAnyKey();
                    manageViewFriendPage();
                } else manageAddNewFriend();
            }
        }
        friendController.changeFriendStatus(friend);
        resetTempValue();
        return checkFriendStatus;
    }

    private boolean removeFriendStatus(int option, int id, boolean checkFriendStatus, Friend friend) {
        if (friend.getFriend1().getUserId() == id || friend.getFriend2().getUserId() == id) {
            if (friend.getStatus().equalsIgnoreCase(MenuConst.FRIEND_ACCEPTED)) {
                checkFriendStatus = false;
                FriendUI.showMenuFindNewFriend(option, searchUserResult, friendList, loginUser);
                System.out.print(MenuConst.FRIEND_ALREADY_ALERT);
                String confirm = InputConfig.getString();
                if (confirm.equalsIgnoreCase("Y")) {
                    friendController.deleteFriend(friend);
                    friendList = friendController.getListAllFriend(loginUser);
                    FriendUI.showMenuFindNewFriend(option, searchUserResult, friendList, loginUser);
                    resetTempValue();
                    System.out.print(MenuConst.REMOVE_FRIEND_STATUS_SUCCESS);
                    InputConfig.pressAnyKey();
                    manageViewFriendPage();
                } else manageAddNewFriend();
            }
        }
        return checkFriendStatus;
    }

    private boolean removeSentRequest(int option, int id, boolean checkFriendStatus, Friend friend) {
        if (friend.getFriend2().getUserId() == id) {
            if (!friend.getStatus().equalsIgnoreCase(MenuConst.FRIEND_ACCEPTED)) {
                checkFriendStatus = false;
                FriendUI.showMenuFindNewFriend(option, searchUserResult, friendList, loginUser);
                System.out.print(MenuConst.FRIEND_PENDING_ALERT);
                String confirm = InputConfig.getString();
                if (confirm.equalsIgnoreCase("Y")) {
                    friendController.deleteFriend(friend);
                    friendList = friendController.getListAllFriend(loginUser);
                    FriendUI.showMenuFindNewFriend(option, searchUserResult, friendList, loginUser);
                    System.out.print(MenuConst.REMOVE_FRIEND_REQUEST_SUCCESS);
                    resetTempValue();
                    InputConfig.pressAnyKey();
                    manageViewFriendPage();
                } else manageAddNewFriend();
            }
        }
        return checkFriendStatus;
    }

    private User selectUserToAddFriend(int id) {
        for (User user : searchUserResult) {
            if (user.getUserId() == id) {
                return user;
            }
        }
        return null;
    }

    /*========================================View Friend Page End========================================*/

    private void resetTempValue() {
        loginUser = userController.getLoginUser();
        searchUserResult = new LinkedList<>();
        acceptedFriends = friendController.getAcceptedFriendList(loginUser);
        pendingRequestFriends = friendController.getPendingFriendList(loginUser);
        sentRequestFriends = friendController.getSentRequestList(loginUser);

        friendList = friendController.getListAllFriend(loginUser);
    }
}
