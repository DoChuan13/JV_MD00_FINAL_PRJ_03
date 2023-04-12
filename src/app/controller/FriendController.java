package app.controller;

import app.model.Friend;
import app.model.User;
import app.service.friend.FriendServiceIPLM;

import java.util.List;

public class FriendController {
    private final FriendServiceIPLM friendService = new FriendServiceIPLM();

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
        return friendService.getAcceptedFriendById(id, loginUser);
    }

    public Friend getPendingFriendById(int id, User loginUser) {
        return friendService.getPendingFriendByID(id, loginUser);
    }

    public Friend getSentRequestByIdAndUser(int id, User loginUser) {
        return friendService.getSentRequestByIdAndUser(id, loginUser);
    }

    public void sentFriendRequest(Friend friend) {
        friendService.save(friend);
    }

    public List<Friend> getListAllFriend(User user) {
        return friendService.getListAllFriend(user);
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
