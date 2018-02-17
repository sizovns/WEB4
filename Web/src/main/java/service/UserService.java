package service;

import model.Role;
import model.User;

import java.util.List;

public interface UserService {
    User getUser(long id);

    long setUser(User user);

    List<User> getAll();

    void deleteUser(User user);

    void updateUser(User user);

    Role getRole(User user);

    void setUserRole(User user, String role);

    User getUserLoginPass(String login, String password);
}
