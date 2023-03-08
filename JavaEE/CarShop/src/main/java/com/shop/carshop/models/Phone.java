package com.shop.carshop.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

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

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("number", number);
        return String.valueOf(object);
    }
}
