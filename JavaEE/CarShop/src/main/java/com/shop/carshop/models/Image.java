package com.shop.carshop.models;


import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

@Setter
@Getter
public class Image {

    private int id;

    private String url;

    private int carId;

    public Image(int id, String url, int car_id) {
        this.id = id;
        this.url = url;
        this.carId = car_id;
    }

    public Image() {

    }

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("imageURL", url);

        return String.valueOf(object);
    }
}
