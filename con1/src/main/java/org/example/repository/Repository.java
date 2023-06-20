package org.example.repository;

import org.example.model.Model;


import static org.example.Main.session;

public class Repository {

    public int save(Model value){
        session.save(value);
        int id = value.getId();
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
