package com.shop.carshop.DAO;

import com.shop.carshop.utils.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImageDAO {

    private Connection connection;

    public ImageDAO() {
        connection = Util.createConnection();
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
}
