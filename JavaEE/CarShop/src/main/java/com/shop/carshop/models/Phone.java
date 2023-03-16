package com.shop.carshop.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.simple.JSONObject;

@Data
@NoArgsConstructor
public class Phone {

    private String number;

    private int ownerID;

    private int id;

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("number", number);
        return String.valueOf(object);
    }
}
