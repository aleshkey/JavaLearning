package org.example.repository;

import org.example.model.Card;
import org.example.model.Model;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Repository {
    protected SessionFactory sessionFactory;

    protected Session session;
    protected SessionFactory buildSessionFactory(){
        Configuration configuration = new Configuration().addAnnotatedClass(Card.class).addAnnotatedClass(Person.class);
        return  configuration.buildSessionFactory();
    }

    public int save(Model value){
        sessionFactory = buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(value);
        int id = value.getId();
        session.getTransaction().commit();
        sessionFactory.close();
        return id;
    }

    public void remove(int id){}

    public Model get(int id){
        return new Model() {
            @Override
            public int getId() {
                return 0;
            }
        };
    }

}
