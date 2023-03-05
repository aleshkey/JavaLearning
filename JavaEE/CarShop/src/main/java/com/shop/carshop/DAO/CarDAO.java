package com.shop.carshop.DAO;

import com.shop.carshop.models.Car;
import com.shop.carshop.models.Condition;
import com.shop.carshop.utils.Util;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarDAO {

    private Connection connection;

    private String getCurrentDate(){
        return String.valueOf(ZonedDateTime.now()).split("T")[0];
    }

    public CarDAO() {
        connection = Util.createConnection();
    }

    private Car setInfoFromResultSet(ResultSet rs){
        Car car = new Car();
        try {
            car.setID(rs.getInt("id"));
            car.setCondition(Condition.valueOf(rs.getString("condition")));
            car.setMark(rs.getString("mark"));
            car.setModel(rs.getString("model"));
            car.setOwnerID(rs.getInt("owner_id"));
            car.setYearOfRelease(rs.getInt("year_of_release"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return car;
    }

    public List<Car> getAllCars(){
        List <Car> res = new ArrayList<>();
        try {
            ResultSet rs  = connection.prepareStatement("select * from car_db").executeQuery();
            while (rs.next()){
                res.add(setInfoFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public List<Car> getPageOfCars(int numberOfPage){
        List <Car> res = new ArrayList<>();
        try {
            ResultSet rs  = connection.prepareStatement("select * from car_db limit "+20*numberOfPage+", 20").executeQuery();
            while (rs.next()){
                res.add(setInfoFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }


    public Car findCarByID(int id){
        try {
            var ps = connection.prepareStatement("select * from car_db where id = ?");
            ps.setInt(1, id);
            var rs = ps.executeQuery();
            if (rs.next()){
                return setInfoFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void addCar(Car car){
        try {
            var ps = connection.prepareStatement("insert into car_db (mark, model, year_of_release, condition, owner_id, ad_creation_date, ad_change_date) values (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, car.getMark());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getYearOfRelease());
            ps.setString(4, String.valueOf(car.getCondition()));
            ps.setInt(5, car.getOwnerID());
            ps.setString(6, getCurrentDate());
            ps.setString(7, getCurrentDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void removeCar(int id){

        try {
            var ps = connection.prepareStatement("delete from car_db where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeImage(int id){
        try {
            int carId = connection.prepareStatement("select from image_db where id = "+id).executeQuery().getInt("car_id");
            var updatePS = connection.prepareStatement("update car_db ad_change_date = ? where car_id = ?");
            updatePS.setString(1, getCurrentDate());
            updatePS.setInt(2, carId);
            updatePS.executeUpdate();

            var ps = connection.prepareStatement("delete from image_db where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateImage(int id, String URL){
        try {
            var updatePS = connection.prepareStatement("update image_db image_url = ? where id = ?");
            updatePS.setString(1, URL);
            updatePS.setInt(2, id);
            updatePS.executeUpdate();

            int carId = connection.prepareStatement("select from image_db where id = "+id).executeQuery().getInt("car_id");
            var updateDatePS = connection.prepareStatement("update car_db ad_change_date = ? where car_id = ?");
            updateDatePS.setString(1, getCurrentDate());
            updateDatePS.setInt(2, carId);
            updateDatePS.executeUpdate();
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addImage(String url, int car_id){
        try {
            var ps = connection.prepareStatement("insert into image_db (image_url, car_id) values (?, ?)");
            ps.setString(1, url);
            ps.setInt(2, car_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
