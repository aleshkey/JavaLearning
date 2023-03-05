package com.shop.carshop.models;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Image {

    private int id;

    private String url;

    private int car_id;

    public Image(int id, String url, int car_id) {
        this.id = id;
        this.url = url;
        this.car_id = car_id;
    }
}
