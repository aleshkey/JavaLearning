package com.shop.carshop.Repository;

import com.shop.carshop.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

import java.util.List;

public class AdRepository {

    private Session session;

    private SessionFactory sessionFactory;

    private SessionFactory buildSessionFactory(){
        Configuration configuration = new Configuration().addAnnotatedClass(Ad.class).addAnnotatedClass(Car.class).addAnnotatedClass(Image.class).addAnnotatedClass(Phone.class).addAnnotatedClass(User.class);
        return  configuration.buildSessionFactory();
    }



    public int save(Ad ad){
        Configuration configuration = new Configuration().addAnnotatedClass(Ad.class).addAnnotatedClass(Car.class)
                                                         .addAnnotatedClass(Image.class).addAnnotatedClass(Phone.class)
                                                         .addAnnotatedClass(User.class);
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(ad);
        session.getTransaction().commit();
        sessionFactory.close();
        return ad.getId();
    }

    public void update(Ad ad){
        sessionFactory = buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(ad);
        session.getTransaction().commit();
        sessionFactory.close();
    }

    public void remove(int id){
        sessionFactory = buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(id);
        session.getTransaction().commit();
        sessionFactory.close();
    }

    public Ad get(int id){
        sessionFactory = buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Ad res = session.get(Ad.class, id);
        session.getTransaction().commit();
        sessionFactory.close();
        return res;
    }

    public List<Ad> getAllAds(){
        sessionFactory = buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Ad> entities = session.createQuery("FROM com.shop.carshop.model.Ad").getResultList();
        System.out.println(entities);
        session.getTransaction().commit();
        sessionFactory.close();
        return entities;
    }

}
