package init.initialization;

import app.config.IOFileConfig;
import app.config.MenuConst;
import app.model.*;
import app.service.friend.FriendServiceIPLM;
import app.service.post.PostServiceIMPL;
import app.service.user.UserServiceIPML;
import init.DataBase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InitialDataBase {
    private static final UserServiceIPML userServiceIPML = new UserServiceIPML();
    private static final FriendServiceIPLM friendServiceIPLM = new FriendServiceIPLM();
    private static final PostServiceIMPL postServiceIMPL = new PostServiceIMPL();
    private static final List<User> userList = userServiceIPML.findAll();
    private static final List<Friend> friendList = friendServiceIPLM.findAll();
    private static final List<Post> postList = postServiceIMPL.findAll();


    //Role......
    private static final Set<Role> adminRole = generateRole(1, RoleName.ADMIN);
    private static final Set<Role> pmRole = generateRole(2, RoleName.PM);
    private static final Set<Role> userRole = generateRole(3, RoleName.USER);

    //User...
    private static final User admin = new User(1, "admin", "admin", null, "admin@admin.com", "Admin@13", false, adminRole);
    private static final User pm = new User(2, "Product Manager", "pmUser", null, "pmUser@gmail.com", "ProductManager@13", false, pmRole);
    private static final User dochuan = new User(3, "Do Chuan", "DoChuan", true, "chuan@gmail.com", "DoChuan@13", false, userRole);
    private static final User minhthu = new User(4, "Minh Thu", "MinhThu", false, "thu@gmail.com", "MinhThu@13", false, userRole);
    //Friend
    private static final Friend friend1 = new Friend(1, dochuan, minhthu, MenuConst.FRIEND_ACCEPTED);
    private static final User ducvuong = new User(5, "Duc Vuong", "DucVuong", true, "vuong@gmail.com", "DucVuong@13", false, userRole);
    private static final Friend friend2 = new Friend(2, ducvuong, dochuan, MenuConst.FRIEND_PENDING);
    private static final User haiyen = new User(6, "Hai Yen", "HaiYen", false, "yen@gmail.com", "HaiYen@13", false, userRole);
    private static final Friend friend3 = new Friend(3, haiyen, dochuan, MenuConst.FRIEND_ACCEPTED);

    private static final Friend friend4 = new Friend(4, ducvuong, haiyen, MenuConst.FRIEND_PENDING);
    private static final User manhson = new User(7, "Manh Son", "ManhSon", false, "son@gmail.com", "ManhSon@13", false, userRole);
    private static final Friend friend5 = new Friend(5, dochuan, manhson, MenuConst.FRIEND_REJECT);
    private static final User huuhuy = new User(8, "Huu Huy", "HuuHuy", false, "huy@gmail.com", "HuuHuy@13", false, userRole);
    private static final Friend friend6 = new Friend(6, huuhuy, dochuan, MenuConst.FRIEND_REJECT);


    public InitialDataBase() {
        initialUserDatabase();
    }

    private static Set<Role> generateRole(int id, RoleName role) {
        Role userRole = new Role(id, role);
        Set<Role> userGroup = new HashSet<>();
        userGroup.add(userRole);
        return userGroup;
    }

    private void initialUserDatabase() {
        if (userList.size() == 0) {
            userList.add(admin);
            userList.add(pm);
            userList.add(dochuan);
            userList.add(minhthu);
            userList.add(ducvuong);
            userList.add(haiyen);
            userList.add(manhson);
            userList.add(huuhuy);
            new IOFileConfig().writeFile(DataBase.PATH_USER, userList);
        }
        if (friendList.size() == 0) {
            friendList.add(friend1);
            friendList.add(friend2);
            friendList.add(friend3);
            friendList.add(friend4);
            friendList.add(friend5);
            friendList.add(friend6);
            new IOFileConfig().writeFile(DataBase.PATH_FRIEND, friendList);
        }
    }
}
