package com.shop.carshop.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Car {
    private int ID;

    private int yearOfRelease;

    private String mark;

    private String model;

    private Condition condition;

    private int ownerID;

    public Car(int ID, int yearOfRelease, String mark, String model, Condition condition, int ownerID) {
        this.ID = ID;
        this.yearOfRelease = yearOfRelease;
        this.mark = mark;
        this.model = model;
        this.condition = condition;
        this.ownerID = ownerID;
    }

    public Car() {
    }
}

