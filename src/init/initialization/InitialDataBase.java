package init.initialization;

import app.config.IOFileConfig;
import app.config.MenuConst;
import app.controller.ChatController;
import app.controller.RoleController;
import app.model.*;
import app.service.friend.FriendServiceIPLM;
import app.service.post.PostServiceIMPL;
import app.service.user.UserServiceIPML;
import init.DataBase;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class InitialDataBase {
    private static final UserServiceIPML userService = new UserServiceIPML();
    private static final FriendServiceIPLM friendService = new FriendServiceIPLM();
    private static final PostServiceIMPL postService = new PostServiceIMPL();
    private static final ChatController chatController = new ChatController();
    private static final RoleController roleController = new RoleController();
    //Role......
    private final List<User> userList = userService.findAll();
    private final List<Friend> friendList = friendService.findAll();
    private final List<Post> postList = postService.findAll();
    private final List<Chat> chatList = chatController.getAllChatList();
    private final Set<Role> adminRole = roleController.createNewRoleGroup(RoleName.ADMIN);
    private final Set<Role> pmRole = roleController.createNewRoleGroup(RoleName.PM);
    private final Set<Role> userRole = roleController.createNewRoleGroup(RoleName.USER);

    //Friend
    private User doChuan, minhThu, ducVuong, haiYen, manhSon, huuHuy, ducDat, dinhHong;
    /*================================================================================================================*/

    public InitialDataBase() {
        initialUserDB();
    }

    private void initialUserDB() {
        if (userList.size() == 0) {
            User admin, pm;
            admin = new User(1, "admin", "admin", null, "admin@admin.com", "Admin@13", false, adminRole);
            pm = new User(2, "Director", "pmUser", null, "pmUser@gmail.com", "ProductManager@13", false, pmRole);
            doChuan = new User(3, "Do Chuan", "DoChuan", true, "chuan@gmail.com", "DoChuan@13", false, userRole);
            minhThu = new User(4, "Minh Thu", "MinhThu", false, "thu@gmail.com", "MinhThu@13", false, userRole);
            ducVuong = new User(5, "Duc Vuong", "DucVuong", true, "vuong@gmail.com", "DucVuong@13", false, userRole);
            haiYen = new User(6, "Hai Yen", "HaiYen", false, "yen@gmail.com", "HaiYen@13", false, userRole);
            manhSon = new User(7, "Manh Son", "ManhSon", false, "son@gmail.com", "ManhSon@13", false, userRole);
            huuHuy = new User(8, "Huu Huy", "HuuHuy", false, "huy@gmail.com", "HuuHuy@13", false, userRole);
            ducDat = new User(9, "Duc Dat", "DucDat", false, "dat@gmail.com", "DucDat@13", true, userRole);
            dinhHong = new User(10, "Dinh Hong", "DinhHong", false, "hong@gmail.com", "DinhHong@13", true, userRole);
            userList.add(admin);
            userList.add(pm);
            userList.add(doChuan);
            userList.add(minhThu);
            userList.add(ducVuong);
            userList.add(haiYen);
            userList.add(manhSon);
            userList.add(huuHuy);
            userList.add(ducDat);
            userList.add(dinhHong);
            new IOFileConfig<User>().writeFile(DataBase.PATH_USER, userList);
            intPostDB();
            initFriendDB();
            intChatDB();
        }
    }

    private void initFriendDB() {
        if (friendList.size() == 0) {
            Friend friend1, friend2, friend3, friend4, friend5, friend6, friend7, friend8;
            friend1 = new Friend(1, doChuan, minhThu, MenuConst.FRIEND_ACCEPTED);
            friend2 = new Friend(2, ducVuong, doChuan, MenuConst.FRIEND_PENDING);
            friend3 = new Friend(3, haiYen, doChuan, MenuConst.FRIEND_ACCEPTED);
            friend4 = new Friend(4, ducVuong, haiYen, MenuConst.FRIEND_PENDING);
            friend5 = new Friend(5, doChuan, manhSon, MenuConst.FRIEND_REJECT);
            friend6 = new Friend(6, huuHuy, doChuan, MenuConst.FRIEND_REJECT);
            friend7 = new Friend(7, ducDat, doChuan, MenuConst.FRIEND_PENDING);
            friend8 = new Friend(8, dinhHong, doChuan, MenuConst.FRIEND_REJECT);
            friendList.add(friend1);
            friendList.add(friend2);
            friendList.add(friend3);
            friendList.add(friend4);
            friendList.add(friend5);
            friendList.add(friend6);
            friendList.add(friend7);
            friendList.add(friend8);
            new IOFileConfig<Friend>().writeFile(DataBase.PATH_FRIEND, friendList);
        }
    }

    private void intPostDB() {
        if (postList.size() == 0) {
            Post post1, post2, post3, post4, post5, post6, post7, post8, post9, post10;
            Comment comment11, comment12, comment13, comment51, comment52, comment53;
            Like like11, like12, like13, like51, like52, like53, like54, like55;
            List<Like> likeList1 = new LinkedList<>();
            List<Comment> commentList1 = new LinkedList<>();
            List<Like> likeList5 = new LinkedList<>();
            List<Comment> commentList5 = new LinkedList<>();
            post1 = new Post(1, doChuan, "DO CHUAN - Xin chao Java 10 (Day la bai Post Public)", MenuConst.POST_PUBLIC);
            post2 = new Post(2, doChuan, "DO CHUAN - Xin chao Java 10 (Day la bai Post Friend)", MenuConst.POST_FRIEND);
            post3 = new Post(3, doChuan, "DO CHUAN - Xin chao Java 10 (Day la bai Post Private)", MenuConst.POST_PRIVATE);
            post4 = new Post(4, minhThu, "MINH THU - Xin chao Java 10 (Day la bai Post Public)", MenuConst.POST_PUBLIC);
            post5 = new Post(5, minhThu, "MINH THU - Xin chao Java 10 (Day la bai Post Friend)", MenuConst.POST_FRIEND);
            post6 = new Post(6, minhThu, "MINH THU - Xin chao Java 10 (Day la bai Post Private)", MenuConst.POST_PRIVATE);
            post7 = new Post(7, manhSon, "MU hình như lại thua, gáy lên ae!!!!!", MenuConst.POST_FRIEND);
            post8 = new Post(8, manhSon, "Vao chương trình thôi ae!!!!!", MenuConst.POST_PRIVATE);
            post9 = new Post(9, huuHuy, "Hôm nay ngày mưa gió, lại phải tắm trong cồn", MenuConst.POST_PUBLIC);
            post10 = new Post(10, huuHuy, "Chán như con gián", MenuConst.POST_PRIVATE);
            like11 = new Like(1, doChuan);
            like12 = new Like(2, minhThu);
            like13 = new Like(3, huuHuy);
            like51 = new Like(4, doChuan);
            like52 = new Like(5, minhThu);
            like53 = new Like(6, ducVuong);
            like54 = new Like(7, haiYen);
            like55 = new Like(8, manhSon);
            comment11 = new Comment(1, minhThu, "Welcome to Join Java10");
            comment12 = new Comment(2, doChuan, "Thank you so much");
            comment13 = new Comment(3, huuHuy, "Newbie??? Haha");
            comment51 = new Comment(4, doChuan, "I have no Idea");
            comment52 = new Comment(5, minhThu, "Take away");
            comment53 = new Comment(6, manhSon, "AOE???");
            likeList1.add(like11);
            likeList1.add(like12);
            likeList1.add(like13);
            likeList5.add(like51);
            likeList5.add(like52);
            likeList5.add(like53);
            likeList5.add(like54);
            likeList5.add(like55);
            commentList1.add(comment11);
            commentList1.add(comment12);
            commentList1.add(comment13);
            commentList5.add(comment51);
            commentList5.add(comment52);
            commentList5.add(comment53);
            postList.add(post1);
            postList.add(post2);
            postList.add(post3);
            postList.add(post4);
            postList.add(post5);
            postList.add(post6);
            postList.add(post7);
            postList.add(post8);
            postList.add(post9);
            postList.add(post10);
            post1.setLikeList(likeList1);
            post1.setCommentList(commentList1);
            post5.setLikeList(likeList5);
            post5.setCommentList(commentList5);
            new IOFileConfig<Post>().writeFile(DataBase.PATH_POST, postList);
        }
    }

    private void intChatDB() {
        if (chatList.size() == 0) {
            ChatDetail doChuan1, doChuan2, doChuan11, doChuan21, minhThu1, minhThu2, huuHuy1, huuHuy2;
            doChuan1 = new ChatDetail(1, "Hello, How are you?", doChuan);
            doChuan2 = new ChatDetail(2, "Where do you come from?", doChuan);
            doChuan11 = new ChatDetail(1, "Hello, How are you?", doChuan);
            doChuan21 = new ChatDetail(2, "Where do you come from?", doChuan);
            minhThu1 = new ChatDetail(3, "I'm fine, thank you, and you", minhThu);
            minhThu2 = new ChatDetail(4, "Take away.....!!!!!", minhThu);
            huuHuy1 = new ChatDetail(5, "Never Mind", huuHuy);
            huuHuy2 = new ChatDetail(6, "I don't under English!!! Cút", huuHuy);
            List<ChatDetail> chatDetailList1 = new LinkedList<>();
            List<ChatDetail> chatDetailList2 = new LinkedList<>();
            chatDetailList1.add(doChuan1);
            chatDetailList1.add(minhThu1);
            chatDetailList1.add(doChuan2);
            chatDetailList1.add(minhThu2);
            chatDetailList2.add(doChuan11);
            chatDetailList2.add(huuHuy1);
            chatDetailList2.add(doChuan21);
            chatDetailList2.add(huuHuy2);
            Chat chatList1 = new Chat(1, doChuan, minhThu, chatDetailList1);
            Chat chatList2 = new Chat(2, doChuan, huuHuy, chatDetailList2);
            chatList.add(chatList1);
            chatList.add(chatList2);
            new IOFileConfig<Chat>().writeFile(DataBase.PATH_CHAT, chatList);
        }
    }
}
