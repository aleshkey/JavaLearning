package com.shop.carshop.service;

import com.shop.carshop.Repository.CarRepository;
import com.shop.carshop.model.Car;

public class CarService {
    private static final CarRepository carRepository = new CarRepository();

    public static void remove(int carId){
        carRepository.remove(carId);
    }

    public static Car getFromRepository(int carId){
        return carRepository.get(carId);
    }

    public static void update(Car car){
        AdService.setUpdateDateAndSave(car.getAd());
    }

}
