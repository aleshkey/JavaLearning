package com.shop.carshop.Repository;

import com.shop.carshop.model.Model;

import static com.shop.carshop.constants.Constants.session;


public interface Repository <T extends Model>{
    default int save(T model){
        session.saveOrUpdate(model);
        int id = model.getId();
        return id;
    }

    Class<T> getModelClass();

    default void remove (int id){
        T model = session.get(getModelClass(), id);
        session.remove(model);
    }

    default T get(int id){
        T model = session.get(getModelClass(), id);
        return model;
    }
}
