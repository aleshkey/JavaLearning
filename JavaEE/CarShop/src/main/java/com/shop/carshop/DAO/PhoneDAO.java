package com.shop.carshop.DAO;

import com.shop.carshop.utils.Util;

import java.sql.Connection;

public class PhoneDAO {

    private Connection connection;

    public PhoneDAO() {
        connection = Util.createConnection();
    }
}
