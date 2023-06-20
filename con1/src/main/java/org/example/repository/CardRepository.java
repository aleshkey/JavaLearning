package org.example.repository;

import org.example.model.Card;
import org.example.model.Model;

public class CardRepository extends Repository{
    @Override
    public void remove(int id) {
        sessionFactory = buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Card card = session.get(Card.class, id);
        session.remove(card);

        session.getTransaction().commit();
        sessionFactory.close();
    }

    @Override
    public Model get(int id) {
        sessionFactory = buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Card card = session.get(Card.class, id);
        session.getTransaction().commit();
        sessionFactory.close();
        return card;
    }
}
