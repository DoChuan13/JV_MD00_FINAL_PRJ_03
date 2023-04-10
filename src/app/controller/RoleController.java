package app.controller;

import app.model.Role;
import app.model.RoleName;
import app.service.role.IRoleService;
import app.service.role.RoleServiceIPLM;

import java.util.HashSet;
import java.util.Set;

public class RoleController {
    IRoleService roleService = new RoleServiceIPLM();

    public Set<Role> createNewRoleGroup(RoleName roleName) {
        Set<Role> roleSet = new HashSet<>();
        Role role = roleService.findByRoleName(roleName);
        roleSet.add(role);
        return roleSet;
    }
}
