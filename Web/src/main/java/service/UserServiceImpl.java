package service;

import dao.RoleDAO;
import dao.UserDAO;
import dao.UserDAOFactory;
import model.User;
import model.Role;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO dao;
    private RoleDAO roleDAO;

    public UserServiceImpl() {
        UserDAOFactory daoFactory = new UserDAOFactory();
        dao = daoFactory.getDAO();

    }



    @Override
    public Role getRole(User user) {
        return roleDAO.getRole(user);
    }

    @Override
    public void setUserRole(User user, String role) {
        roleDAO.setUserRole(user, role);
    }

    @Override
    public User getUserLoginPass(String login, String password) {
        return dao.getUserLoginPass(login, password);
    }

    @Override
    public User getUser(long id) {
        return dao.getUser(id);
    }

    @Override
    public long setUser(User user) {
        return dao.setUser(user);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public void deleteUser(User user) {
        dao.deleteUser(user);
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }
}
