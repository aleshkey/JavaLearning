package com.shop.carshop.models;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private int ID;

    private String name;

    private String email;

    public User(int id, String name, String email) {
        this.ID = id;
        this.name = name;
        this.email = email;
    }

    public User() {
    }
}
