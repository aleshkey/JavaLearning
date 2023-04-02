package com.shop.carshop.Dto;

import com.shop.carshop.model.Phone;
import com.shop.carshop.model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PhoneMapper {

    public static List<Phone> fromJSONToPhones(JSONArray array, User user){
        Iterator<JSONObject> iterator = array.iterator();
        List<Phone> phones = new ArrayList<>();
        while (iterator.hasNext()) {
            JSONObject jsonObject = iterator.next();
            System.out.println(jsonObject);
            Phone phone = new Phone();
            phone.setUser(user);
            phone.setNumber(String.valueOf(jsonObject.get("number")));
            phones.add(phone);
        }
        user.setPhones(phones);
        return phones;
    }

}
