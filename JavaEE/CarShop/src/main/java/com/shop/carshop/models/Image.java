package com.shop.carshop.models;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.simple.JSONObject;

@Data
@NoArgsConstructor
public class Image {

    private int id;

    private String url;

    private int carId;

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("imageURL", url);

        return String.valueOf(object);
    }
}
