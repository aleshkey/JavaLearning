package org.example.repository;

import org.example.model.Card;
import org.example.model.Model;
import org.hibernate.Session;

public class CardRepository extends Repository{
    @Override
    public void remove(int id, Session session) {
        Card card = session.get(Card.class, id);
        session.remove(card);
    }

    @Override
    public Model get(int id, Session session) {
        return session.get(Card.class, id);
    }
}
