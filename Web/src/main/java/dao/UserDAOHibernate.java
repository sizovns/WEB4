package dao;


import model.User;
import model.Role;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class UserDAOHibernate implements UserDAO, RoleDAO {
    private Session session;

    public UserDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public User getUser(long id) {
        try {
            Query q = session.createQuery("SELECT u FROM User u WHERE id=:id");
            q.setParameter("id", id);
            User user = (User) q.uniqueResult();
            return user;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    //In HQL, only the INSERT INTO … SELECT … is supported; there is no INSERT INTO … VALUES. HQL only support insert from another table.
    //TODO: не возвращать херню
    @Override
    public long setUser(User user) {
        try {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            long id = user.getId();
            transaction.commit();
            return id;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<User> getAll() {
        try {
            return (List<User>) session.createQuery("SELECT u FROM User u").getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteUser(User user) {
        try {
            Transaction transaction = session.beginTransaction();
            Query q = session.createQuery("DELETE User u WHERE u.id=:id");
            q.setParameter("id", user.getId());
            q.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            Transaction transaction = session.beginTransaction();
            Query q = session.createQuery("UPDATE User u SET u.name=:name, u.login=:login, u.password=:password, u.role=:role WHERE u.id=:id");
            q.setParameter("name", user.getName());
            q.setParameter("login", user.getLogin());
            q.setParameter("password", user.getPassword());
            q.setParameter("id", user.getId());
            q.setParameter("role", user.getRole());
            q.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserLoginPass(String login, String password) {
        try {
            Query q = session.createQuery("SELECT u FROM User u WHERE login=:login AND password=:password");
            q.setParameter("login", login);
            q.setParameter("password", password);
            User user = (User) q.uniqueResult();
            return user;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setUserRole(User user, String userRole) {
        try {
            Transaction transaction = session.beginTransaction();
            Role role = new Role();
            role.setUser(user);
            role.setRole(userRole);
            session.save(role);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Role getRole(User user) {
        try {
            Query q = session.createQuery("SELECT r FROM Role r WHERE user:=user");
            q.setParameter("user", user);
            return (Role) q.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
