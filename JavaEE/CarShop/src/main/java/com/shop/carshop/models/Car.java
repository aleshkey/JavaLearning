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

    private int engineCapacity;

    private Condition condition;

    private String ownerName;

    private String image;

    private List<String> phoneNumber;

    public Car(int ID, int yearOfRelease, String mark, String model, int engineCapacity, Condition condition, String ownerName, String image, List<String> phoneNumber) {
        this.ID = ID;
        this.yearOfRelease = yearOfRelease;
        this.mark = mark;
        this.model = model;
        this.engineCapacity = engineCapacity;
        this.condition = condition;
        this.ownerName = ownerName;
        this.image = image;
        this.phoneNumber = phoneNumber;
    }

    public Car(int ID, int yearOfRelease, String mark, String model, int engineCapacity, Condition condition, String ownerName, List<String> phoneNumber) {
        this.ID = ID;
        this.yearOfRelease = yearOfRelease;
        this.mark = mark;
        this.model = model;
        this.engineCapacity = engineCapacity;
        this.condition = condition;
        this.ownerName = ownerName;
        this.phoneNumber = phoneNumber;
    }

    public Car() {
    }
}

