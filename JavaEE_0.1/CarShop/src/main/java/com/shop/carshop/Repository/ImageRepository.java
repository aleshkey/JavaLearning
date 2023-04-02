package com.shop.carshop.Repository;

import com.shop.carshop.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ImageRepository {

    private SessionFactory sessionFactory;

    private Session session;

    private SessionFactory buildSessionFactory(){
        Configuration configuration = new Configuration().addAnnotatedClass(Ad.class).addAnnotatedClass(Car.class).addAnnotatedClass(Image.class).addAnnotatedClass(Phone.class).addAnnotatedClass(User.class);
        return  configuration.buildSessionFactory();
    }

    public int save(Image image){
        sessionFactory = buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(image);
        int id = image.getId();
        session.getTransaction().commit();
        sessionFactory.close();
        return id;
    }

    public void remove(Image image){
        sessionFactory = buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(image);
        session.getTransaction().commit();
        sessionFactory.close();
    }

    public Image get(int id){
        sessionFactory = buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Image res = session.get(Image.class, id);
        session.getTransaction().commit();
        sessionFactory.close();
        return res;
    }
}
