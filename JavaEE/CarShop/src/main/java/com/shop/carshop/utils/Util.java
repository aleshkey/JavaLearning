package com.shop.carshop.utils;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.*;


public class Util {
    private static Connection connection;

    public static Connection createConnection(){
        if (connection == null) {
            Properties properties = new Properties();

            try {
                properties.load(new FileInputStream("D:\\lesha\\JavaLearning\\JavaEE\\CarShop\\src\\main\\resources\\application.properties"));
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
        return connection;
    }

    public static int getIDFromURL(String URL){
        Pattern integerPattern = Pattern.compile("-?\\d+");
        Matcher matcher = integerPattern.matcher(URL);

        List<Integer> integerList = new ArrayList<>();
        while (matcher.find()) {
            integerList.add(Integer.parseInt(matcher.group()));
        }

        return integerList.get(0);
    }

    public static JSONObject readJSON(HttpServletRequest request){
        String buffer ="";
        JSONParser parser = new JSONParser();
        try {
            BufferedReader br = request.getReader();
            while (br.ready()){
                buffer = buffer+br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONObject object = null;
        try {
            object =(JSONObject) parser.parse(buffer);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return object;
    }

    public static int getMaxId(String dataBaseName){
        Connection connection = createConnection();
        int res= -1;
        try {
            var ps = connection.prepareStatement("SELECT MAX(id) FROM "+dataBaseName);
            var rs = ps.executeQuery();
            if (rs.next()){
                res = rs.getInt(1);
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
