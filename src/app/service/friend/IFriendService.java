package app.service.friend;

import app.model.Friend;
import app.model.User;
import app.service.generic.IGenericService;

import java.util.List;

public interface IFriendService extends IGenericService<Friend> {
    void deleteByFriend(Friend friend);

    void deleteFriendByAdmin(User user);

    List<Friend> getAcceptedFriendList(User user);

    List<Friend> getPendingFriendList(User user);

    List<Friend> getSentRequestList(User user);

    void synchronizedUserInFriend(User user);

    int generateFriendId();
}
