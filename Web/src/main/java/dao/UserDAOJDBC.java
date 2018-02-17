package dao;

import model.User;
import model.Role;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAOJDBC implements UserDAO{
    private final Connection conn;


    public UserDAOJDBC(Connection conn) {
        this.conn = conn;
    }

   /* @Override
    public Role getRole(User user) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM USERS WHERE LOGIN=" + login + " AND PASSWORD=" + password);
            while (rs.next()) {
                return new User(rs.getLong("id"), rs.getString("name"), rs.getString("login"),
                        rs.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/


    @Override
    public User getUserLoginPass(String login, String password) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM USERS WHERE LOGIN='" + login + "' AND PASSWORD='" + password +"'");
            while (rs.next()) {
                return new User(rs.getLong("id"), rs.getString("name"), rs.getString("login"),
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        try {
            List<User> users = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM USERS");

            while (rs.next()) {
                users.add(new User(rs.getLong("id"), rs.getString("name"), rs.getString("login"),
                        rs.getString("password")));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE USERS SET NAME='" + user.getName() + "', LOGIN='"
                    + user.getLogin() + "', PASSWORD='" + user.getPassword() + "', ROLE='" + user.getRole() + "' WHERE ID=" + user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long setUser(User user) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO USERS (NAME, LOGIN, PASSWORD, ROLE) VALUES ('" + user.getName() + "', '" + user.getLogin() +
                    "', '" + user.getPassword() + "', '" + user.getRole() + "')");
            return user.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public User getUser(long id) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM USERS WHERE ID=" + id);
            while (rs.next()) {
                return new User(rs.getLong("id"), rs.getString("name"), rs.getString("login"),
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteUser(User user) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM USERS where id=" + user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
