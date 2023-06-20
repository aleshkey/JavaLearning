package org.example.repository;

import org.example.model.Model;
import org.example.model.Person;

public class PersonRepository extends Repository{
    @Override
    public Model get(int id) {
        sessionFactory = buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Person person = session.get(Person.class, id);
        session.getTransaction().commit();
        sessionFactory.close();
        return person;
    }

    @Override
    public void remove(int id){
        sessionFactory = buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Person person = session.get(Person.class, id);
        session.remove(person);

        session.getTransaction().commit();
        sessionFactory.close();
    }
}
