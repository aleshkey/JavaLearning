package com.shop.carshop.service;

import com.shop.carshop.Repository.CarRepository;
import com.shop.carshop.Repository.ImageRepository;
import com.shop.carshop.model.Car;
import com.shop.carshop.model.Image;
import org.json.simple.JSONObject;

import static com.shop.carshop.constants.Constants.sessionFactory;
import static com.shop.carshop.constants.Constants.session;


public class ImageService {

    private static final ImageRepository imageRepository = new ImageRepository();

    public static String getFromRepository(int imageId){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Image image = imageRepository.get(imageId);
        JSONObject object = new JSONObject();
        object.put("id", image.getId());
        object.put("url", image.getUrl());
        object.put("ad_id", image.getCar().getAd().getId());
        session.getTransaction().commit();
        return object.toString();
    }

    public static void removeFromRepository(int imageId){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        CarRepository cr = new CarRepository();
        Image image = imageRepository.get(imageId);
        Car car = image.getCar();
        car.getImages().remove(image);
        imageRepository.remove(imageId);
        cr.save(car);
        session.getTransaction().commit();
    }
}
