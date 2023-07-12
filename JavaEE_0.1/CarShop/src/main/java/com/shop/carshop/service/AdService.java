package com.shop.carshop.service;

import com.shop.carshop.Repository.AdRepository;
import com.shop.carshop.model.Ad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.shop.carshop.constants.Constants.sessionFactory;
import static com.shop.carshop.constants.Constants.session;


public class AdService {

    private static final AdRepository adRepository = new AdRepository();

    private static final int SIZE = 20;

    private static Date getCurrentDate() {
        return new Date();
    }

    public static Ad setBothDatesAndSave(Ad ad){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        ad.setDateOfCreation(getCurrentDate());
        ad.setDateOfLastUpdate(getCurrentDate());
        adRepository.save(ad);
        session.getTransaction().commit();
        return ad;
    }

    public static Ad setUpdateDateAndSave(Ad ad){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        ad.setDateOfLastUpdate(getCurrentDate());
        adRepository.save(ad);
        System.out.println(1);
        session.getTransaction().commit();
        return ad;
    }

    public static List<Ad> getPage(int page){
        System.out.println(page);
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        page--;
        List<Ad> res = new ArrayList<>();
        var allAds = adRepository.getAllAds();
        if ((page+1)*SIZE <= allAds.size()) {
            for (int i = page * SIZE; i < (page + 1) * SIZE; i++) {
                res.add(allAds.get(i));
            }
        }
        else {
            for (int i = page * SIZE; i < allAds.size(); i++) {
                res.add(allAds.get(i));
            }
        }

        session.getTransaction().commit();
        return res;
    }
}
