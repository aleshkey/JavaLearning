package org.example;


import org.example.model.Card;
import org.example.model.Person;
import org.example.repository.CardRepository;
import org.example.repository.PersonRepository;

import java.util.List;

public class Main {


    public static void main(String[] args){

        Person person = new Person();
        Card card = new Card();
        card.setNumber("12345678");
        person.setName("Lesha");
        person.setValue(900);
        person.setCard(List.of(card));
        card.setOwner(person);
        PersonRepository pr = new PersonRepository();
        CardRepository cr = new CardRepository();
        pr.save(person);
        System.out.println(pr.get(0));
        System.out.println(cr.get(person.getId()));

    }
}