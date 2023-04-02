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

public class PhoneDAO {

    private Connection connection;

    private List<Phone> fromResultSetToList(ResultSet rs) throws SQLException {
        List<Phone> res = new ArrayList<>();
        while (rs.next()){
            Phone buff = new Phone();
            buff.setNumber(rs.getString("number"));
            buff.setId(rs.getInt("id"));
            buff.setOwnerID(rs.getInt("owner_id"));
            res.add(buff);
        }
        return res;
    }

    public PhoneDAO() {
        connection = Util.createConnection();
    }

    public void addPhone(Phone phone){
        if (!exist(phone)) {
            try {
                var ps = connection.prepareStatement("insert into phone_db (number, owner_id) values (?,?)");
                ps.setString(1, phone.getNumber());
                ps.setInt(2, phone.getOwnerID());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<Phone> getPhonesByOwnerId(int ownerId){
        try {
            var ps= connection.prepareStatement("select * from phone_db where owner_id = ?");
            ps.setInt(1, ownerId);
            var rs = ps.executeQuery();
            return fromResultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteByID(int id){
        try {
            var ps = connection.prepareStatement("delete from phone_db where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteByOwnerId(int ownerId){
        try {
            var ps = connection.prepareStatement("delete * from phone_db where oner_id = ?");
            ps.setInt(1, ownerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean exist(Phone phone){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("select number from phone_db where number = ?");
            ps.setString(1, phone.getNumber());
            var rs = ps.executeQuery();
            if (rs.next())
                return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
