package com.shop.carshop.DAO;

import com.shop.carshop.models.Car;
import com.shop.carshop.models.Condition;

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
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("D:\\java\\JavaEE\\CarShop\\src\\main\\resources\\application.properties"));
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(properties.getProperty("URL"), properties.getProperty("USER"), properties.getProperty("PASSWORD"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Car setInfoFromResultSet(ResultSet rs){
        Car car = new Car();
        try {
            car.setID(rs.getInt("id"));
            car.setCondition(Condition.valueOf(rs.getString("condition")));
            car.setMark(rs.getString("mark"));
            car.setEngineCapacity(rs.getInt("engine_capacity"));
            car.setYearOfRelease(rs.getInt("year_of_release"));
            car.setImage(rs.getString("image"));
            car.setOwnerName(rs.getString("owner_name"));
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
            ResultSet rs  = connection.prepareStatement("select from car_db where id = ?", id).executeQuery();
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
            var ps = connection.prepareStatement("insert into car_db (mark, model, year_of_release, engine_capacity, condition, owner_name, ad_creation_date, ad_cange_date) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, car.getMark());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getYearOfRelease());
            ps.setInt(4, car.getEngineCapacity());
            ps.setString(5, String.valueOf(car.getCondition()));
            ps.setString(6, car.getOwnerName());
            ps.setString(7, car.getImage());
            ps.setString(8, getCurrentDate());
            ps.setString(9, getCurrentDate());
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
