package com.shop.carshop.service;

import com.shop.carshop.Repository.CarRepository;
import com.shop.carshop.model.Car;
import org.hibernate.Session;

import static com.shop.carshop.constants.Constants.sessionFactory;

public class CarService {
    private static final CarRepository carRepository = new CarRepository();

    public static void remove(int carId){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        carRepository.remove(carId);
        session.getTransaction().commit();
        //sessionFactory.close();
    }

    public static Car getFromRepository(int carId)
    {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Car car = carRepository.get(carId);
        session.getTransaction().commit();
        //sessionFactory.close();
        return car;
    }

    public static void update(Car car)
    {
        AdService.setUpdateDateAndSave(car.getAd());
    }

}
