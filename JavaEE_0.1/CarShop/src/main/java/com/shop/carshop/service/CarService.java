package com.shop.carshop.service;

import com.shop.carshop.Repository.AdRepository;
import com.shop.carshop.Repository.CarRepository;
import com.shop.carshop.model.Car;

import static com.shop.carshop.constants.Constants.sessionFactory;
import static com.shop.carshop.constants.Constants.session;


public class CarService {
    private static final CarRepository carRepository = new CarRepository();
    private static final AdRepository adRepository = new AdRepository();

    public static void remove(int carId){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        adRepository.remove(carId);
        session.getTransaction().commit();
    }

    public static Car getFromRepository(int carId)
    {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Car car = carRepository.get(carId);
        System.out.println(car.getUser().getPhones());
        System.out.println(car.getImages());
        session.getTransaction().commit();
        return car;
    }

    public static void update(Car car)
    {
        AdService.setUpdateDateAndSave(car.getAd());
    }

}
