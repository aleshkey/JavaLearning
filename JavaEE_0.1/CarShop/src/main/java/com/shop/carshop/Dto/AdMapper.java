package com.shop.carshop.Dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shop.carshop.Repository.AdRepository;
import com.shop.carshop.model.*;
import com.shop.carshop.utils.Util;
import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdMapper {



    private static final AdRepository adRepository = new AdRepository();

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void createAd(HttpServletRequest request){

        JSONObject object = Util.readJSON(request);
        User user = new User();
        user.setName(String.valueOf(object.get("ownerName")));
        user.setEmail(String.valueOf(object.get("ownerEmail")));
        Car car = new Car();
        car.setModel(String.valueOf(object.get("model")));
        car.setMark((String) object.get("mark"));
        car.setYearOfRelease(Math.toIntExact((Long)object.get("yearOfRelease")));
        car.setCondition(Condition.valueOf(String.valueOf(object.get("condition"))));
        JSONArray array = (JSONArray) object.get("numbers");
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
        array.clear();

        array =(JSONArray) object.get("images");
        iterator = array.iterator();
        List<Image> images = new ArrayList<>();
        while (iterator.hasNext()) {
            JSONObject jsonObject = iterator.next();
            Image image = new Image();
            image.setCar(car);
            image.setUrl(String.valueOf(jsonObject.get("url")));
            images.add(image);
        }
        user.setPhones(phones);
        car.setUser(user);
        car.setImages(images);

        adRepository.save(car);

    }

    public static int getAd(HttpServletRequest request){
        JSONObject object = Util.readJSON(request);
        return (int)object.get("add_id");
    }

    public static JSONObject fromCarToJSON(Car car){
        JSONObject object = new JSONObject();
        object.put("ID", car.getAd());
        object.put("year_of_release", car.getYearOfRelease());
        object.put("mark", car.getMark());
        object.put("model", car.getModel());
        object.put("condition", String.valueOf(car.getCondition()));
        object.put("ad_creation_date", car.getAd().getDateOfCreation());
        object.put("number_of_images", car.getImages().size());
        return object;
    }

    public static JSONObject getInfoAboutCar(HttpServletRequest request){
        Car car = (Car) adRepository.get(Car.class, Util.getIDFromURL(request.getRequestURI()));
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

    public static void updateCar(HttpServletRequest request){
        int carId = Util.getIDFromURL(request.getRequestURI());
        Car car = (Car) adRepository.get(Car.class, carId);
        JSONObject object = Util.readJSON(request);
        if (object.containsKey("mark"))
            car.setMark(String.valueOf(object.get("mark")));
        if (object.containsKey("model"))
            car.setModel(String.valueOf(object.get("model")));
        if (object.containsKey("year_of_release"))
            car.setYearOfRelease((Integer) object.get("year_of_release"));
        adRepository.update(car);
    }

    public static void removeCar(HttpServletRequest request) {
        int carId = Util.getIDFromURL(request.getRequestURI());
        adRepository.remove(carId);
    }

    public static String getImageInJSON(HttpServletRequest request){
        int imageId = Util.getIDFromURL(request.getRequestURI());
        Image image = (Image) adRepository.get(Image.class, imageId);
        return gson.toJson(image);
    }

    public static void removeImage(HttpServletRequest request){
        int imageId = Util.getIDFromURL(request.getRequestURI());
        Image image = (Image) adRepository.get(Image.class, imageId);
        adRepository.remove(image);
    }
}
