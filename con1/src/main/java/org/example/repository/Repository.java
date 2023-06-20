package org.example.repository;

import org.example.Main;
import org.example.model.Card;
import org.example.model.Model;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static org.example.Main.sessionFactory;

public class Repository {

    public int save(Model value, Session session){
        session.save(value);
        int id = value.getId();
        return id;
    }

    public void remove(int id, Session session){}

    public Model get(int id, Session session){
        return new Model() {
            @Override
            public int getId() {
                return 0;
            }
        };
    }

}
