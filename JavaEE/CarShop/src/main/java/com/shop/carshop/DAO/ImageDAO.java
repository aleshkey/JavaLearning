package com.shop.carshop.DAO;

import com.shop.carshop.utils.Util;

import java.sql.Connection;

public class ImageDAO {

    private Connection connection;

    public ImageDAO() {
        connection = Util.createConnection();
    }
}
