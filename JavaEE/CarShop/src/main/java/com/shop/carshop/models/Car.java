package com.shop.carshop.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
public class Car {
    private int ID;

    private int yearOfRelease;

    private String mark;

    private String model;

    private Condition condition;

    private int ownerID;
}

