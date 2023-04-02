package com.shop.carshop.service;

import com.shop.carshop.Repository.AdRepository;
import com.shop.carshop.model.Ad;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdService {

    private static final AdRepository adRepository = new AdRepository();

    private static final int SIZE = 20;

    private static String getCurrentDate() {
        return String.valueOf(ZonedDateTime.now()).split("T")[0];
    }

    public static Ad setBothDatesAndSave(Ad ad){
        ad.setDateOfCreation(getCurrentDate());
        ad.setDateOfLastUpdate(getCurrentDate());
        adRepository.save(ad);
        return ad;
    }

    public static Ad setUpdateDateAndSave(Ad ad){
        ad.setDateOfLastUpdate(getCurrentDate());
        adRepository.save(ad);
        return ad;
    }

    public static List<Ad> getPage(int page){
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
        return res;
    }
}
