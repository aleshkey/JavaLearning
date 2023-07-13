package com.shop.carshop.constants;

import com.shop.carshop.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Constants {
    public static SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory(){
        Configuration configuration = new Configuration().addAnnotatedClass(Ad.class).addAnnotatedClass(Car.class).addAnnotatedClass(Image.class).addAnnotatedClass(Phone.class).addAnnotatedClass(User.class);
        return  configuration.buildSessionFactory();
    }

    public static Session session;
}
