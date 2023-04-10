package app.service.role;

import app.model.Role;
import app.model.RoleName;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();

    Role findByRoleName(RoleName roleName);
}
