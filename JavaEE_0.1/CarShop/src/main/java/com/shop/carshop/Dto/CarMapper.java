package com.shop.carshop.Dto;

import com.shop.carshop.model.Ad;
import com.shop.carshop.model.Car;
import com.shop.carshop.model.Condition;
import com.shop.carshop.service.CarService;
import com.shop.carshop.utils.Util;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;

public class CarMapper {

    public static JSONObject getInfoAboutCar(HttpServletRequest request){
        Car car =CarService.getFromRepository(Util.getIDFromURL(request.getRequestURI()));
        JSONObject object = new JSONObject();
        object.put("mark", car.getMark());
        object.put("model", car.getModel());
        object.put("yearOfRelease", car.getYearOfRelease());
        object.put("condition", car.getCondition());
        object.put("ownerName", car.getUser().getName());
        object.put("ownerPhones", car.getUser().getPhones());
        object.put("photosURL", car.getImages());
        object.put("creationDate", car.getAd().getDateOfCreation());
        return object;
    }

    public static JSONObject fromCarToJSON(Car car){
        JSONObject object = new JSONObject();
        object.put("ID", car.getAd().getId());
        object.put("year_of_release", car.getYearOfRelease());
        object.put("mark", car.getMark());
        object.put("model", car.getModel());
        object.put("condition", String.valueOf(car.getCondition()));
        object.put("ad_creation_date", car.getAd().getDateOfCreation());
        object.put("number_of_images", car.getImages().size());
        return object;
    }

    public static Car fromJSONToCar(JSONObject object, Ad ad){
        Car car = new Car();
        car.setAd(ad);
        car.setModel(String.valueOf(object.get("model")));
        car.setMark((String) object.get("mark"));
        car.setYearOfRelease(Math.toIntExact((Long)object.get("yearOfRelease")));
        car.setCondition(Condition.valueOf(String.valueOf(object.get("condition"))));
        return car;
    }

    public static void removeCar(HttpServletRequest request) {
        int carId = Util.getIDFromURL(request.getRequestURI());
        CarService.remove(carId);
    }

    public static void updateCar(HttpServletRequest request) {
        int carId = Util.getIDFromURL(request.getRequestURI());
        Car car = CarService.getFromRepository(carId);
        JSONObject object = Util.readJSON(request);
        if (object.containsKey("mark"))
            car.setMark(String.valueOf(object.get("mark")));
        if (object.containsKey("model"))
            car.setModel(String.valueOf(object.get("model")));
        if (object.containsKey("yearOfRelease"))  //year_of_release
            car.setYearOfRelease(Math.toIntExact((Long) object.get("yearOfRelease")));
        CarService.update(car);
    }

}
