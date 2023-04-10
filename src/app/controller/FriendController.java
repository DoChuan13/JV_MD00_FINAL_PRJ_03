package app.controller;

import app.config.MenuConst;
import app.model.Friend;
import app.model.User;
import app.service.friend.FriendServiceIPLM;
import app.service.friend.IFriendService;

import java.util.LinkedList;
import java.util.List;

public class FriendController {
    private final IFriendService friendService = new FriendServiceIPLM();

    public List<Friend> getFriendList() {
        return friendService.findAll();
    }

    public Friend findFriendByIdAndUser(int id, User loginUser) {
        Friend currentFriend = friendService.findById(id);
        if (currentFriend != null) {
            User friend1 = currentFriend.getFriend1();
            User friend2 = currentFriend.getFriend2();
            if (!currentFriend.getStatus().equalsIgnoreCase(MenuConst.FRIEND_ACCEPTED)) {
                return null;
            }
            if (friend1.getUserId() == loginUser.getUserId() || friend2.getUserId() == loginUser.getUserId()) {
                return currentFriend;
            }
        }
        return null;
    }

    public List<Friend> getAcceptedFriendList(User loginUser) {
        return friendService.getAcceptedFriendList(loginUser);
    }

    public List<Friend> getPendingFriendList(User loginUser) {
        return friendService.getPendingFriendList(loginUser);
    }

    public List<Friend> getSentRequestList(User loginUser) {
        return friendService.getSentRequestList(loginUser);
    }

    public Friend getAcceptedFriendById(int id, User loginUser) {
        for (Friend friend : getAcceptedFriendList(loginUser)) {
            if (friend.getFriendId() == id) {
                return friend;
            }
        }
        return null;
    }

    public Friend getPendingFriendById(int id, User loginUser) {
        for (Friend friend : getPendingFriendList(loginUser)) {
            if (friend.getFriendId() == id) {
                return friend;
            }
        }
        return null;
    }

    public Friend getSentRequestByIdAndUser(int id, User loginUser) {
        for (Friend friend : getSentRequestList(loginUser)) {
            if (friend.getFriendId() == id) {
                return friend;
            }
        }
        return null;
    }

    public void sentFriendRequest(Friend friend) {
        friendService.save(friend);
    }

    public List<Friend> getListAllFriend(User user) {
        List<Friend> friendList = new LinkedList<>();
        for (Friend friend : getFriendList()) {
            User friend1 = friend.getFriend1();
            User friend2 = friend.getFriend2();
            if (friend1.getUserId() == user.getUserId() || friend2.getUserId() == user.getUserId()) {
                friendList.add(friend);
            }
        }
        return friendList;
    }

    public void deleteFriend(Friend friend) {
        friendService.deleteByFriend(friend);
    }

    public void deleteFriendByAdmin(User user) {
        friendService.deleteFriendByAdmin(user);
    }

    public void synchronizedUserInFriend(User user) {
        friendService.synchronizedUserInFriend(user);
    }

    public void changeFriendStatus(Friend friend) {
        friendService.save(friend);
    }

    public int generateFriendId() {
        return friendService.generateFriendId();
    }
}
