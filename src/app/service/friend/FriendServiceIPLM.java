package app.service.friend;

import app.config.IOFileConfig;
import app.config.MenuConst;
import app.model.Friend;
import app.model.User;
import app.service.generic.IDataBaseService;
import init.DataBase;

import java.util.LinkedList;
import java.util.List;

public class FriendServiceIPLM implements IFriendService, IDataBaseService<Friend> {
    private static final List<Friend> friendList;

    static {
        friendList = new IOFileConfig<Friend>().readFile(DataBase.PATH_FRIEND);
    }

    @Override
    public List<Friend> findAll() {
        return friendList;
    }

    @Override
    public void save(Friend friend) {
        Friend currentFriend = findById(friend.getFriendId());
        if (currentFriend == null) {
            friendList.add(friend);
        } else {
            int index = friendList.indexOf(currentFriend);
            friendList.set(index, friend);
        }
        updateDatabase(DataBase.PATH_FRIEND, friendList);
    }

    @Override
    public Friend findById(int id) {
        for (Friend friend : friendList) {
            if (friend.getFriendId() == id) {
                return friend;
            }
        }
        return null;
    }

    @Override
    public Friend delete(int id) {
        Friend currentFriend = findById(id);
        if (currentFriend != null) {
            friendList.remove(currentFriend);
        }
        updateDatabase(DataBase.PATH_FRIEND, friendList);
        return currentFriend;
    }

    @Override
    public void updateDatabase(String pathName, List<Friend> list) {
        new IOFileConfig<Friend>().writeFile(pathName, list);
    }

    @Override
    public void deleteByFriend(Friend friend) {
        Friend currentFriend = findById(friend.getFriendId());
        if (currentFriend != null) {
            friendList.remove(currentFriend);
        }
        updateDatabase(DataBase.PATH_FRIEND, friendList);
    }

    @Override
    public void deleteFriendByAdmin(User user) {
        for (Friend friend : friendList) {
            if (friend.getFriend1().getUserId() == user.getUserId() || friend.getFriend2().getUserId() == user.getUserId()) {
                boolean result = friendList.remove(friend);
                updateDatabase(DataBase.PATH_FRIEND, friendList);
                return;
            }
        }
    }

    @Override
    public List<Friend> getAcceptedFriendList(User loginUser) {
        List<Friend> friendsResult = new LinkedList<>();
        for (Friend friend : friendList) {
            if (friend.getStatus().equalsIgnoreCase(MenuConst.FRIEND_ACCEPTED)) {
                if (friend.getFriend1().getUserId() == loginUser.getUserId() ||
                        friend.getFriend2().getUserId() == loginUser.getUserId()) {
                    friendsResult.add(friend);
                }
            }
        }
        return friendsResult;
    }

    @Override
    public List<Friend> getPendingFriendList(User loginUser) {
        List<Friend> friendsResult = new LinkedList<>();
        for (Friend friend : friendList) {
            if (friend.getFriend2().getUserId() == loginUser.getUserId()) {
                if (!friend.getStatus().equalsIgnoreCase(MenuConst.FRIEND_ACCEPTED)) {
                    friendsResult.add(friend);
                }
            }
        }
        return friendsResult;
    }

    @Override
    public List<Friend> getSentRequestList(User loginUser) {
        List<Friend> friendsResult = new LinkedList<>();
        for (Friend friend : friendList) {
            if (friend.getFriend1().getUserId() == loginUser.getUserId()) {
                if (!friend.getStatus().equalsIgnoreCase(MenuConst.FRIEND_ACCEPTED)) {
                    friendsResult.add(friend);
                }
            }
        }
        return friendsResult;
    }

    @Override
    public void synchronizedUserInFriend(User user) {
        for (Friend friend : friendList) {
            if (friend.getFriend1().getUserId() == user.getUserId()) {
                friend.setFriend1(user);
            }
            if (friend.getFriend2().getUserId() == user.getUserId()) {
                friend.setFriend2(user);
            }
        }
        updateDatabase(DataBase.PATH_FRIEND, friendList);
    }

    @Override
    public int generateFriendId() {
        int id = 0;
        for (Friend friend : friendList) {
            if (friend.getFriendId() > id) {
                id = friend.getFriendId();
            }
        }
        return ++id;
    }
}
