package app.view.user.ui;

import app.config.BreakConfig;
import app.config.ColorConfig;
import app.config.MenuConst;
import app.model.Friend;
import app.model.User;

import java.util.List;

public final class FriendUI {

    public static void showMenuFriendView(List<Friend> pendingRequestFriends, List<Friend> sentRequestFriends) {
        int pending = pendingRequestFriends.size();
        int sent = sentRequestFriends.size();
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_FRIEND);

        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  1. " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "View All Friend");
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  2. " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Add New Friend");
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  3. " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Friend Request List " + "(" + pending + ")");
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  4. " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Sent Request List " + "(" + sent + ")");

        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }

    public static void showMenuAllFriendView(int option, List<Friend> acceptedFriends, User currentUser) {

        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_FRIEND_LIST);
        showAllFriend(acceptedFriends, currentUser);

        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 8 ? ColorConfig.ACTIVE_COLOR : ColorConfig.ACTION_COLOR) + "  8. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "View Friend Detail");

        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }

    private static void showAllFriend(List<Friend> acceptedFriends, User currentUser) {
        if (acceptedFriends.size() == 0) {
            System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "You have no any Friend");
        } else {
            String friendName;
            for (Friend friend : acceptedFriends) {
                if (friend.getFriend1().getUserId() == currentUser.getUserId()) {
                    friendName = friend.getFriend2().getName();
                    String friendId = "Friend Id: " + friend.getFriendId();
                    String friendNameView = "Name: " + friendName;
                    System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, friendId, friendNameView);
                } else {
                    friendName = friend.getFriend1().getName();
                    String friendId = "Friend Id: " + friend.getFriendId();
                    String friendNameView = "Name: " + friendName;
                    System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, friendId, friendNameView);
                }
            }
        }
    }

    public static void showMenuAFriendDetailView(int option, Friend friend, User loginUser) {
        User friendUser = friend.getFriend1().getUserId() == loginUser.getUserId() ? friend.getFriend2() : friend.getFriend1();

        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_PROFILE_INFO);

        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.INACTIVE_COLOR + "  *. " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Name: ", friendUser.getName());
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.INACTIVE_COLOR + "  *. " + MenuConst.WIDTH_2_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Sex: ", (friendUser.isGender() == null ? "Unknown" : friendUser.isGender() ? "Male" : "Female"));
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.INACTIVE_COLOR + "  *. " + MenuConst.WIDTH_2_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Email: ", friendUser.getEmail());

        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 8 ? ColorConfig.ACTIVE_COLOR : ColorConfig.ACTION_COLOR) + "  8. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Un Friend");

        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }

    public static void showMenuFindNewFriend(int option, List<User> searchUserResult, List<Friend> friendList, User loginUser) {
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_ADD_FRIEND);
        if (searchUserResult.size() == 0) {
            System.out.println(MenuConst.BLANK_LINE);
        } else {
            for (User searchUser : searchUserResult) {
                String status = "Status: Un Friend";
                for (Friend friend : friendList) {
                    status = "Status: ";
                    if (friend.getFriend1().getUserId() == searchUser.getUserId() || friend.getFriend2().getUserId() == searchUser.getUserId()) {
                        if (friend.getStatus().equals(MenuConst.FRIEND_ACCEPTED)) {
                            status += "Friend";
                            break;
                        } else if (friend.getFriend1().getUserId() == loginUser.getUserId() && friend.getStatus().equals(MenuConst.FRIEND_PENDING)) {
                            status += "Sent Request";
                            break;
                        } else if (friend.getFriend2().getUserId() == loginUser.getUserId() && friend.getStatus().equals(MenuConst.FRIEND_PENDING)) {
                            status += "Friend Request";
                            break;
                        } else {
                            status += "Un Friend";
                        }
                    } else {
                        status += "Un Friend";
                    }
                }
                String userId = "User Id: " + searchUser.getUserId();
                String name = "Name: " + searchUser.getName();
                System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_3_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, userId, name, status);
                System.out.println(MenuConst.BLANK_LINE);
            }
        }
        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 8 ? ColorConfig.ACTIVE_COLOR : ColorConfig.ACTION_COLOR) + "  8. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, (searchUserResult.size() == 0 ? "Find Friend" : "Add a Friend"));

        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }

    public static void showMenuConfirmFriendRequest(int option, List<Friend> pendingRequestFriends, User loginUser) {
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_FRIEND_REQUEST);
        if (pendingRequestFriends.size() == 0) {
            System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "You have no any Friend Request");
            System.out.println(MenuConst.BLANK_LINE);
        } else {
            for (Friend friend : pendingRequestFriends) {
                String name = "Name: ";
                String userId = "Friend Id: " + friend.getFriendId();
                String status = "Status: " + (friend.getStatus().equalsIgnoreCase(MenuConst.FRIEND_PENDING) ? "Pending" : friend.getStatus().equalsIgnoreCase(MenuConst.FRIEND_ACCEPTED) ? "Accepted" : "Rejected");
                if (friend.getFriend1().getUserId() != loginUser.getUserId()) {
                    name += friend.getFriend1().getName();
                } else {
                    name += friend.getFriend2().getName();
                }
                System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_3_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, userId, name, status);
                System.out.println(MenuConst.BLANK_LINE);
            }
        }
        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 8 ? ColorConfig.ACTIVE_COLOR : ColorConfig.ACTION_COLOR) + "  8. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Confirm Request");

        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }

    public static void showMenuRequestSent(int option, List<Friend> sentRequestList, User loginUser) {
        BreakConfig.clearScreen();
        System.out.println(MenuConst.HEADER_SENT_REQUEST);
        if (sentRequestList.size() == 0) {
            System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_1_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "You have no any Sent Request");
            System.out.println(MenuConst.BLANK_LINE);
        } else {
            for (Friend friend : sentRequestList) {
                String name = "Name: ";
                String userId = "Friend Id: " + friend.getFriendId();
                String status = "Status: " + "Sent Request";
                if (friend.getFriend1().getUserId() != loginUser.getUserId()) {
                    name += friend.getFriend1().getName();
                } else {
                    name += friend.getFriend2().getName();
                }
                System.out.printf(ColorConfig.BORDER_COLOR + "|" + ColorConfig.BORDER_COLOR + "  => " + MenuConst.WIDTH_3_COL + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, userId, name, status);
                System.out.println(MenuConst.BLANK_LINE);
            }
        }
        System.out.println(MenuConst.BREAK_LINE);
        System.out.printf(ColorConfig.BORDER_COLOR + "|" + (option == 8 ? ColorConfig.ACTIVE_COLOR : ColorConfig.ACTION_COLOR) + "  8. " + MenuConst.WIDTH_1_COL + ColorConfig.RESET + ColorConfig.BORDER_COLOR + "|\n" + ColorConfig.RESET, "Cancel Request");

        System.out.println(MenuConst.BREAK_LINE);
        System.out.println(MenuConst.SYS_CTR_FUL_123);
        System.out.println(MenuConst.FOOTER);
    }
}
