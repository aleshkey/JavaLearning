package com.shop.carshop.utils;

import com.shop.carshop.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import org.hibernate.Session;


public class Util {

    private static Session session;

    public static Session getSession(){
        if (session == null) {
            Configuration configuration = new Configuration().addAnnotatedClass(Ad.class).addAnnotatedClass(Car.class).addAnnotatedClass(Image.class).addAnnotatedClass(Phone.class).addAnnotatedClass(User.class);

            SessionFactory sessionFactory = configuration.buildSessionFactory();
            session = sessionFactory.getCurrentSession();
            sessionFactory.close();
        }
        return session;
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

    public static JSONObject readJSON(HttpServletRequest request) {
        String buffer = "";
        JSONParser parser = new JSONParser();
        try {
            BufferedReader br = request.getReader();
            while (br.ready()) {
                buffer = buffer + br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONObject object = null;
        try {
            object = (JSONObject) parser.parse(buffer);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return object;
    }

    public static JSONObject readJSON(String str) {
        JSONParser parser = new JSONParser();
        JSONObject object = null;
        try {
            object = (JSONObject) parser.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return object;
    }


}
