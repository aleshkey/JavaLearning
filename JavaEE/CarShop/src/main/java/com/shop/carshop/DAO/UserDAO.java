package com.shop.carshop.DAO;

import com.shop.carshop.utils.Util;

import java.sql.Connection;

public class UserDAO {

    private Connection connection;

    public UserDAO() {
        connection = Util.createConnection();
    }


}
