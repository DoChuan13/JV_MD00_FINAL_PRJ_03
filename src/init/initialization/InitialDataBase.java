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
    UserServiceIPML userServiceIPML = new UserServiceIPML();
    FriendServiceIPLM friendServiceIPLM = new FriendServiceIPLM();
    PostServiceIMPL postServiceIMPL = new PostServiceIMPL();
    List<User> userList = userServiceIPML.findAll();
    List<Friend> friendList = friendServiceIPLM.findAll();
    List<Post> postList = postServiceIMPL.findAll();


    //Role......
    Set<Role> adminRole = generateRole(1, RoleName.ADMIN);
    Set<Role> pmRole = generateRole(2, RoleName.PM);
    Set<Role> userRole = generateRole(3, RoleName.USER);

    //User...
    User admin = new User(1, "admin", "admin", null, "admin@admin.com", "Admin@13", false, adminRole);
    User pm = new User(2, "Product Manager", "pmUser", null, "pmUser@gmail.com", "ProductManager@13", false, pmRole);
    User dochuan = new User(3, "Do Chuan", "DoChuan", true, "chuan@gmail.com", "DoChuan@13", false, userRole);
    User minhthu = new User(4, "Minh Thu", "MinhThu", false, "thu@gmail.com", "MinhThu@13", false, userRole);
    //Friend
    Friend friend1 = new Friend(1, dochuan, minhthu, MenuConst.FRIEND_ACCEPTED);
    User ducvuong = new User(5, "Duc Vuong", "DucVuong", true, "vuong@gmail.com", "DucVuong@13", false, userRole);
    Friend friend2 = new Friend(2, ducvuong, dochuan, MenuConst.FRIEND_PENDING);
    User haiyen = new User(6, "Hai Yen", "HaiYen", false, "yen@gmail.com", "HaiYen@13", false, userRole);
    Friend friend3 = new Friend(3, haiyen, dochuan, MenuConst.FRIEND_ACCEPTED);

    Friend friend4 = new Friend(4, ducvuong, haiyen, MenuConst.FRIEND_PENDING);
    User manhson = new User(7, "Manh Son", "ManhSon", false, "son@gmail.com", "ManhSon@13", false, userRole);
    Friend friend5 = new Friend(5, dochuan, manhson, MenuConst.FRIEND_REJECT);
    User huuhuy = new User(8, "Huu Huy", "HuuHuy", false, "huy@gmail.com", "HuuHuy@13", false, userRole);
    Friend friend6 = new Friend(6, huuhuy, dochuan, MenuConst.FRIEND_REJECT);


    public InitialDataBase() {
        initialUserDatabase();
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

    private Set<Role> generateRole(int id, RoleName role) {
        Role userRole = new Role(id, role);
        Set<Role> userGroup = new HashSet<>();
        userGroup.add(userRole);
        return userGroup;
    }
}
