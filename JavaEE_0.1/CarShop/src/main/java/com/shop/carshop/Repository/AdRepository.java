package com.shop.carshop.Repository;

import com.shop.carshop.model.*;
import com.shop.carshop.utils.Util;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.ZonedDateTime;
import java.util.List;

public class AdRepository {
    private final Session session;
    private final SessionFactory sessionFactory;
    public AdRepository() {
        Configuration configuration = new Configuration().addAnnotatedClass(Ad.class).addAnnotatedClass(Car.class).addAnnotatedClass(Image.class).addAnnotatedClass(Phone.class).addAnnotatedClass(User.class);

        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.getCurrentSession();

    }

    private String getCurrentDate() {
        return String.valueOf(ZonedDateTime.now()).split("T")[0];
    }

    public void save(Car car){
        session.beginTransaction();
        Ad ad = new Ad();
        ad.setCar(car);
        ad.setDateOfLastUpdate(getCurrentDate());
        ad.setDateOfCreation(getCurrentDate());
        session.save(ad);
        session.getTransaction().commit();
        sessionFactory.close();
    }


    public void update(Car car){
        session.beginTransaction();
        Ad ad = session.get(Ad.class, car);
        ad.setDateOfLastUpdate(getCurrentDate());
        ad.setCar(car);
        session.save(ad);
        session.getTransaction().commit();
        sessionFactory.close();
    }

    public void remove(Car car){
        session.beginTransaction();
        session.remove(car.getAd());
        session.getTransaction().commit();
        sessionFactory.close();
    }

    public void remove(Object o){
        session.beginTransaction();
        session.remove(o);
        session.getTransaction().commit();
        sessionFactory.close();
    }


    public void remove(int id){
        session.beginTransaction();
        session.remove(id);
        session.getTransaction().commit();
        sessionFactory.close();
    }

    public Object get(Class clazz, int id){
        session.beginTransaction();
        Object o = session.get(clazz, id);
        session.getTransaction().commit();
        sessionFactory.close();
        return o;
    }

    public List<Ad> getAllAds(){
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Ad> criteria = builder.createQuery(Ad.class);
        Root<Ad> root = criteria.from(Ad.class);
        criteria.select(root);
        List<Ad> entities = session.createQuery(criteria).getResultList();
        session.getTransaction().commit();
        sessionFactory.close();
        return entities;
    }

}
