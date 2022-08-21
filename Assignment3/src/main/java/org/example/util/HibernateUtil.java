package org.example.util;

import org.example.model.Director;
import org.example.model.HollywoodMovies;
import org.example.model.Movie;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory factory;

    public static SessionFactory getSessionFactory(){
        try {
            Configuration configuration = new Configuration();
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
            properties.put(Environment.URL, "jdbc:sqlserver://localhost;databaseName=shoping_portal;trustServerCertificate=true;instanceName=SQLEXPRESS");
            properties.put(Environment.USER, "sa");
            properties.put(Environment.PASS, "password_123");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServer2008Dialect");
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.HBM2DDL_AUTO, "update");

            configuration.setProperties(properties);
            configuration.addAnnotatedClass(Director.class);
            configuration.addAnnotatedClass(Movie.class);
            configuration.addAnnotatedClass(HollywoodMovies.class);

            ServiceRegistry registryBuilder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            factory = configuration.buildSessionFactory(registryBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return factory;
    }
}
