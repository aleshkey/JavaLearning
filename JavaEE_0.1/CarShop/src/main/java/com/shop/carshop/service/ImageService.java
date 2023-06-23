package com.shop.carshop.service;

import com.shop.carshop.Repository.ImageRepository;
import com.shop.carshop.model.Image;
import org.hibernate.Session;
import org.json.simple.JSONObject;

import static com.shop.carshop.constants.Constants.sessionFactory;


public class ImageService {

    private static final ImageRepository imageRepository = new ImageRepository();

    public static String getFromRepository(int imageId){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Image image = imageRepository.get(imageId);
        JSONObject object = new JSONObject();
        object.put("id", image.getId());
        object.put("url", image.getUrl());
        object.put("ad_id", image.getCar().getAd().getId());
        session.getTransaction().commit();
        //sessionFactory.close();
        return object.toString();
    }

    public static void removeFromRepository(int imageId){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Image image = imageRepository.get(imageId);
        imageRepository.remove(image.getId());
        session.getTransaction().commit();
        //sessionFactory.close();
    }
}
