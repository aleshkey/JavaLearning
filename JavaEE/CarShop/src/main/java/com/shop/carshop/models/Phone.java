package com.shop.carshop.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Phone {

    private String number;

    private int ownerID;

    private int id;

    public Phone(String number, int ownerID, int id) {
        this.number = number;
        this.ownerID = ownerID;
        this.id = id;
    }

    public Phone() {
    }
}
