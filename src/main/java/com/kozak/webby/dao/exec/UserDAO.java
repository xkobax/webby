package com.kozak.webby.dao.exec;

import com.kozak.webby.dao.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

public class UserDAO {

    private static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()).buildServiceRegistry();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public List<User> getAll() {
        Session session = createSessionFactory().openSession();
        session.beginTransaction();
        List<User> result = (List<User>) session.createQuery("from USERS").list();
        session.close();
        return result;
    }
}
