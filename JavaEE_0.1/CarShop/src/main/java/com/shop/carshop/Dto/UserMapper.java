package com.shop.carshop.Dto;

import com.shop.carshop.model.User;
import org.json.simple.JSONObject;

public class UserMapper {

    public static User fromJSONToUser(JSONObject object){
        User user = new User();
        if (object.containsKey("owner_id")){
            user.setId((Integer) object.get("owner_id"));
        }
        user.setName(String.valueOf(object.get("ownerName")));
        user.setEmail(String.valueOf(object.get("ownerEmail")));
        return user;
    }

}
