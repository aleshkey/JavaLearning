package com.shop.carshop.DAO;

import com.shop.carshop.models.Car;
import com.shop.carshop.models.Condition;
import com.shop.carshop.models.User;
import com.shop.carshop.utils.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection connection;

    public UserDAO() {
        connection = Util.createConnection();
    }


    private User setInfoFromResultSet(ResultSet rs){
        User user = new User();
        try {
            user.setID(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setName(rs.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public User findUserById(int id){
        try {
            var ps = connection.prepareStatement("select * from user_db where id = ?");
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

    public void createUser(User user){
        try {
            var ps = connection.prepareStatement("insert into user_db (name, email) values (?, ?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
