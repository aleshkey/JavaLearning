package com.shop.carshop.service;

import com.shop.carshop.Repository.AdRepository;
import com.shop.carshop.model.Ad;
import org.hibernate.Session;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.shop.carshop.constants.Constants.sessionFactory;
import static com.shop.carshop.constants.Constants.session;


public class AdService {

    private static final AdRepository adRepository = new AdRepository();

    private static final int SIZE = 20;

    private static String getCurrentDate() {
        return String.valueOf(ZonedDateTime.now()).split("T")[0];
    }

    public static Ad setBothDatesAndSave(Ad ad){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        ad.setDateOfCreation(getCurrentDate());
        ad.setDateOfLastUpdate(getCurrentDate());
        adRepository.save(ad);
        System.out.println(session.getTransaction().isActive());
        session.getTransaction().commit();
        System.out.println(session.getTransaction().isActive());
        //sessionFactory.close();
        return ad;
    }

    public static Ad setUpdateDateAndSave(Ad ad){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        ad.setDateOfLastUpdate(getCurrentDate());
        adRepository.save(ad);
        session.getTransaction().commit();
        //sessionFactory.close();
        return ad;
    }

    public static List<Ad> getPage(int page){
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
        //sessionFactory.close();
        return res;
    }
}
