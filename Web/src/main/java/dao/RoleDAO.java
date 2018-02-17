package dao;

import model.User;
import model.Role;

public interface RoleDAO {
    void setUserRole(User user, String role);

    Role getRole(User user);
}
