package com.shop.carshop.Repository;

import com.shop.carshop.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CarRepository {

    private SessionFactory sessionFactory;

    private Session session;

    private SessionFactory buildSessionFactory(){
        Configuration configuration = new Configuration().addAnnotatedClass(Ad.class).addAnnotatedClass(Car.class).addAnnotatedClass(Image.class).addAnnotatedClass(Phone.class).addAnnotatedClass(User.class);
        return  configuration.buildSessionFactory();
    }

    public int save(Car car){
        sessionFactory = buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(car);
        int id = car.getAd().getId();
        session.getTransaction().commit();
        sessionFactory.close();
        return id;
    }

    public void remove(int id){
        sessionFactory = buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Car car = session.get(Car.class, id);
        System.out.println(car);
        session.remove(car.getAd());
        session.getTransaction().commit();
        sessionFactory.close();
    }

    public Car get(int id){
        sessionFactory = buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Car res = session.get(Car.class, id);
        System.out.println(res);
        session.getTransaction().commit();
        sessionFactory.close();
        return res;
    }
}
