package com.shop.carshop.Repository;

import com.shop.carshop.model.*;

import java.util.List;

import static com.shop.carshop.constants.Constants.session;


public class AdRepository implements Repository<Ad>{
    @Override
    public Class<Ad> getModelClass() {
        return null;
    }

    public List<Ad> getAllAds(){
        return (List<Ad>) session.createQuery("FROM com.shop.carshop.model.Ad").getResultList();
    }

}
