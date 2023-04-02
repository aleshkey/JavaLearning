package com.shop.carshop.Dto;

import com.shop.carshop.model.*;
import com.shop.carshop.service.AdService;
import com.shop.carshop.utils.Util;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdMapper {
    public static void createAd(HttpServletRequest request){
        Ad ad = new Ad();
        JSONObject object = Util.readJSON(request);
        User user = UserMapper.fromJSONToUser(object);
        Car car = CarMapper.fromJSONToCar(object, ad);
        JSONArray array = (JSONArray) object.get("numbers");
        List<Phone> phones = PhoneMapper.fromJSONToPhones(array, user);
        array.clear();
        array =(JSONArray) object.get("images");
        List<Image> images = ImageMapper.fromJSONToImages(array, car);
        car.setUser(user);
        user.setCar(car);
        ad.setCar(car);
        AdService.setBothDatesAndSave(ad);
    }

    public static int getAd(HttpServletRequest request){
        JSONObject object = Util.readJSON(request);
        return Math.toIntExact((Long) object.get("add_id"));
    }

    public static String getPage(int page){
        StringBuilder res = new StringBuilder();
        for (var ad : AdService.getPage(page)){
            Car car = ad.getCar();
            res.append(CarMapper.fromCarToJSON(car)).append(" \n");
        }
        return res.toString();
    }

}
