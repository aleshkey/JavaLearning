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

    private String getCurrentDate() {
        return String.valueOf(ZonedDateTime.now()).split("T")[0];
    }

    public CarDAO() {
        connection = Util.createConnection();
    }

    private Car setInfoFromResultSet(ResultSet rs) {
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

    public List<Car> getAllCars() {
        List<Car> res = new ArrayList<>();
        try {
            ResultSet rs = connection.prepareStatement("select * from car_db order by ad_creation_date DESC").executeQuery();
            while (rs.next()) {
                res.add(setInfoFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public List<Car> getPageOfCars(int numberOfPage) {
        List<Car> res = new ArrayList<>();
        numberOfPage--;
        try {
            int page = 20;
            if (getAllCars().size() < 20 * numberOfPage + 20) {
                page = getAllCars().size() - 20 * numberOfPage;
            }

            var ps = connection.prepareStatement("select * from car_db ORDER by ad_creation_date LIMIT ? offset ? ");
            ps.setInt(2, 20 * numberOfPage);
            ps.setInt(1, page);
            var rs = ps.executeQuery();
            while (rs.next()) {
                res.add(setInfoFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }


    public Car findCarByID(int id) {
        try {
            var ps = connection.prepareStatement("select * from car_db where id = ?");
            ps.setInt(1, id);
            var rs = ps.executeQuery();
            if (rs.next()) {
                return setInfoFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void addCar(Car car) {
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


    public void deleteCar(int id) {

        try {

            ImageDAO imageDAO = new ImageDAO();
             imageDAO.deleteByOwnerID(id);
            var ps = connection.prepareStatement("delete from car_db where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateCar(Car car) {
        try {
            var ps = connection.prepareStatement("update car_db set mark = ?, model = ?, year_of_release=?, ad_change_date=? where id = ?");
            ps.setString(1, car.getMark());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getYearOfRelease());
            ps.setString(4, getCurrentDate());
            ps.setInt(5, car.getID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateDate(int carId) {
        try {
            var ps = connection.prepareStatement("update car_db set ad_change_date=? where id = ?");
            ps.setString(1, getCurrentDate());
            ps.setInt(2, carId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDateOfCreation(int carId) {
        try {
            var ps = connection.prepareStatement("select * from car_db where id = ?");
            ps.setInt(1, carId);
            var rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("ad_creation_date");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getChangeDate(int carId) {
        try {
            var ps = connection.prepareStatement("select * from car_db where id = ?");
            ps.setInt(1, carId);
            var rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("ad_change_date");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
