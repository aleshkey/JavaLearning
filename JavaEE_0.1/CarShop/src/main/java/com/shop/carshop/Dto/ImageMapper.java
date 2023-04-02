package com.shop.carshop.Dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shop.carshop.model.Car;
import com.shop.carshop.model.Image;
import com.shop.carshop.Repository.ImageRepository;
import com.shop.carshop.service.ImageService;
import com.shop.carshop.utils.Util;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImageMapper {

    public static String getImageInJSON(HttpServletRequest request){
        int imageId = Util.getIDFromURL(request.getRequestURI());
        return ImageService.getFromRepository(imageId);
    }

    public static List<Image> fromJSONToImages(JSONArray array, Car car){
        Iterator<JSONObject> iterator = array.iterator();
        List<Image> images = new ArrayList<>();
        while (iterator.hasNext()) {
            JSONObject jsonObject = iterator.next();
            Image image = new Image();
            image.setCar(car);
            image.setUrl(String.valueOf(jsonObject.get("url")));
            images.add(image);
        }
        car.setImages(images);
        return images;
    }

    public static void removeImage(HttpServletRequest request){
        int imageId = Util.getIDFromURL(request.getRequestURI());
        ImageService.removeFromRepository(imageId);
    }

}
