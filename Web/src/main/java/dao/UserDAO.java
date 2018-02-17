package dao;

import model.User;

import java.util.List;

public interface UserDAO {
    User getUser(long id);

    long setUser(User user);

    List<User> getAll();

    void deleteUser(User user);

    void updateUser(User user);

    User getUserLoginPass(String login, String password);
}
