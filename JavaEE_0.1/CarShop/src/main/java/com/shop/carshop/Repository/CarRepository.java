package com.shop.carshop.Repository;

import com.shop.carshop.model.*;

public class CarRepository implements Repository<Car>{
    @Override
    public Class<Car> getModelClass() {
        return Car.class;
    }

}
