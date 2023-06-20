package org.example.repository;

import org.example.model.Model;
import org.example.model.Person;
import org.hibernate.Session;

public class PersonRepository extends Repository{
    @Override
    public Model get(int id, Session session) {
        return session.get(Person.class, id);
    }

    @Override
    public void remove(int id, Session session){
        Person person = session.get(Person.class, id);
        session.remove(person);
    }
}
