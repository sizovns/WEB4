package util;

import model.User;
import model.Role;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {

    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "update";
    private static DBHelper instance;
    private static String dialect, driver, url, username, password, pool_size, show_sql, auto;

    private DBHelper() {
        Properties prop = new Properties();
        InputStream input;
        try {
            String filename = "config.properties";
            input = getClass().getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                throw new NullPointerException();
            }
            prop.load(input);
            dialect = prop.getProperty("dialect");
            driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            pool_size = prop.getProperty("pool_size");
            show_sql = prop.getProperty("show_sql");
            auto = prop.getProperty("auto");
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static DBHelper getInstance() {
        if (instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }

    public static Configuration getMySqlConfigurationHibernate() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Role.class);

        configuration.setProperty("hibernate.dialect", dialect);
        configuration.setProperty("hibernate.connection.driver_class", driver);
        configuration.setProperty("hibernate.connection.url", url);
        configuration.setProperty("hibernate.connection.username", username);
        configuration.setProperty("hibernate.connection.password", password);
        //configuration.setProperty("hibernate.connection.autocommit", "true");
        configuration.setProperty("hibernate.connection.pool_size", pool_size);
        configuration.setProperty("hibernate.show_sql", show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", auto);
        return configuration;
    }

    public static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static Connection getMysqlConnectionJDBC() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
