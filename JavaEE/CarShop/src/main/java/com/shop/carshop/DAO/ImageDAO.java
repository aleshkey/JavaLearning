package com.shop.carshop.DAO;

import com.shop.carshop.models.Image;
import com.shop.carshop.models.Phone;
import com.shop.carshop.utils.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageDAO {

    private Connection connection;

    public ImageDAO() {
        connection = Util.createConnection();
    }

    private Image fromResultSetToImage(ResultSet rs) {
        Image image = new Image();
        try {
            if (rs.next()) {
                image.setId(rs.getInt("id"));
                image.setCarId(rs.getInt("car_id"));
                image.setUrl(rs.getString("url"));
            }
            } catch(SQLException e){
                throw new RuntimeException(e);
            }
        return image;
    }

    private List<Image> fromResultSetToList(ResultSet rs) throws SQLException {
        List<Image> res = new ArrayList<>();
        while (rs.next()){
            Image image = new Image();
            image.setId(rs.getInt("id"));
            image.setCarId(rs.getInt("car_id"));
            image.setUrl(rs.getString("url"));
            res.add(image);
        }
        return res;
    }

    public List<Image> getImageByCarId(int carId){
        try {
            var ps= connection.prepareStatement("select * from image_db where car_id = ?");
            ps.setInt(1, carId);
            var rs = ps.executeQuery();
            return fromResultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Image getImageByID(int id){
        try {
            var ps= connection.prepareStatement("select * from image_db where id = ?");
            ps.setInt(1, id);
            var rs = ps.executeQuery();
            return fromResultSetToImage(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int id){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("delete from image_db where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteByOwnerID(int carId){
        try {
            var ps = connection.prepareStatement("delete * from image_db where car_id = ?");
            ps.setInt(1, carId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getNumberOfImagesOfTheCar(int car_id){

        return getImageByCarId(car_id).size();
    }

    public void addImage(Image image){
        if (!exist(image)) {
            try {
                var ps = connection.prepareStatement("insert into image_db (url, car_id) values (?,?)");
                ps.setString(1, image.getUrl());
                ps.setInt(2, image.getCarId());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean exist(Image image){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("select url from user_db where url = ?");
            ps.setString(1, image.getUrl());
            var rs = ps.executeQuery();
            if (rs.next())
                return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
