package org.example;


import org.example.model.Card;
import org.example.model.Person;
import org.example.repository.CardRepository;
import org.example.repository.PersonRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static Session session;
    public static SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory buildSessionFactory(){
        Configuration configuration = new Configuration().addAnnotatedClass(Card.class).addAnnotatedClass(Person.class);
        return  configuration.buildSessionFactory();
    }

    public static void main(String[] args){
        session = sessionFactory.getCurrentSession();
        Person person = new Person();
        Card card = new Card();
        card.setNumber("12345678");
        person.setName("Lesha");
        person.setValue(900);
        person.setCard(List.of(card));
        card.setOwner(person);
        PersonRepository pr = new PersonRepository();
        CardRepository cr = new CardRepository();
        session.beginTransaction();
        pr.save(person, session);
        System.out.println(pr.get(0, session));
        System.out.println(cr.get(person.getId(), session));
        session.getTransaction().commit();
        sessionFactory.close();

    }
}