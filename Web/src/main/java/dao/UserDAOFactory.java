package dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.w3c.dom.UserDataHandler;
import util.DBHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class UserDAOFactory {

    public UserDAO getDAO() {

        Properties prop = new Properties();
        InputStream input;
        UserDAO dao = null;
        DBHelper.getInstance();
        RoleDAO roleDAO =  null;

        try {

            String filename = "config.properties";
            input = getClass().getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                throw new NullPointerException();
            }
            prop.load(input);
            if (prop.getProperty("whatUse").equalsIgnoreCase("hibernate")) {
                Configuration configuration = DBHelper.getMySqlConfigurationHibernate();
                SessionFactory sessionFactory = DBHelper.createSessionFactory(configuration);
                Session session = sessionFactory.openSession();
                dao = new UserDAOHibernate(session);
                return dao;
            } else if (prop.getProperty("whatUse").equalsIgnoreCase("jdbc")) {
                Connection conn = DBHelper.getMysqlConnectionJDBC();
                dao = new UserDAOJDBC(conn);
                return dao;
            }
            input.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return dao;
    }
}
