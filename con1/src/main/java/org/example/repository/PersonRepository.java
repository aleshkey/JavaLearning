package org.example.repository;

import org.example.model.Model;
import org.example.model.Person;

import static org.example.Main.session;

public class PersonRepository extends Repository{
    @Override
    public Model get(int id) {
        return session.get(Person.class, id);
    }

    @Override
    public void remove(int id){
        Person person = session.get(Person.class, id);
        session.remove(person);
    }
}
