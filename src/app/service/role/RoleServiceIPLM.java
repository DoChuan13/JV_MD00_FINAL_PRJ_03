package app.service.role;

import app.model.Role;
import app.model.RoleName;

import java.util.LinkedList;
import java.util.List;

public class RoleServiceIPLM implements IRoleService {
    static List<Role> roleList = new LinkedList<>();

    static {
        roleList.add(new Role(1, RoleName.ADMIN));
        roleList.add(new Role(2, RoleName.PM));
        roleList.add(new Role(3, RoleName.USER));
    }

    @Override
    public List<Role> findAll() {
        return roleList;
    }


    @Override
    public Role findByRoleName(RoleName name) {
        for (Role role : roleList) {
            if (role.getName() == name) {
                return role;
            }
        }
        return null;
    }
}
