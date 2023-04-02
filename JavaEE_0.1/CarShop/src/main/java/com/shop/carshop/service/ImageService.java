package com.shop.carshop.service;

import com.shop.carshop.Repository.ImageRepository;
import com.shop.carshop.model.Image;
import org.json.simple.JSONObject;

public class ImageService {

    private static final ImageRepository imageRepository = new ImageRepository();

    public static String getFromRepository(int imageId){
        Image image = imageRepository.get(imageId);
        JSONObject object = new JSONObject();
        object.put("id", image.getId());
        object.put("url", image.getUrl());
        object.put("ad_id", image.getCar().getAd().getId());
        return object.toString();
    }

    public static void removeFromRepository(int imageId){
        Image image = imageRepository.get(imageId);
        imageRepository.remove(image);
    }
}
