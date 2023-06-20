package org.example.repository;

import org.example.model.Card;
import org.example.model.Model;

import static org.example.Main.session;
public class CardRepository extends Repository{
    @Override
    public void remove(int id) {
        Card card = session.get(Card.class, id);
        session.remove(card);
    }

    @Override
    public Model get(int id) {
        return session.get(Card.class, id);
    }
}