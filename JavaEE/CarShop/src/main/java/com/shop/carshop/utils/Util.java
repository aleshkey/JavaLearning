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
import java.util.Properties;
import java.util.regex.*;


public class Util {
    public static Connection createConnection(){
        Connection connection;
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
        return connection;
    }

    public static int getIDFromURL(String URL){
        Pattern pat=Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
        Matcher matcher=pat.matcher(URL);
        if (matcher.find()){
            return Integer.parseInt(matcher.group());
        }
        return -1;
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
}
