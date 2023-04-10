package app.model;

import java.io.Serializable;

public class Role implements Serializable {
    private int roleId;
    private RoleName name;

    public Role(int id, RoleName name) {
        this.roleId = id;
        this.name = name;
    }

    public Role() {
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + roleId +
                ", name=" + name +
                '}';
    }
}
