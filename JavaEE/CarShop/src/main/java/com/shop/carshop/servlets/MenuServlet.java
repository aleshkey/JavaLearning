package com.shop.carshop.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shop.carshop.DAO.CarDAO;
import com.shop.carshop.DAO.ImageDAO;
import com.shop.carshop.DAO.PhoneDAO;
import com.shop.carshop.DAO.UserDAO;
import com.shop.carshop.models.*;
import com.shop.carshop.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@WebServlet(name = "MenuServlet", value = "/MenuServlet")
public class MenuServlet extends HttpServlet {


    private CarDAO carDAO = new CarDAO();
    private UserDAO userDAO = new UserDAO();
    private PhoneDAO phoneDAO = new PhoneDAO();
    private ImageDAO imageDAO = new ImageDAO();

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        int page = Util.getIDFromURL(request.getRequestURI());
        PrintWriter pw = response.getWriter();
        pw.write(gson.toJson(carDAO.getPageOfCars(page)));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject object = Util.readJSON(request);
        User user = new User();
        user.setName(String.valueOf(object.get("ownerName")));
        user.setEmail(String.valueOf(object.get("ownerEmail")));
        int userID = 0;
        if (!userDAO.exist(user)) {
            userDAO.createUser(user);
            userID= Util.getMaxId("user_db");
        }
        else {
            userID = userDAO.findUserByEmail(user.getEmail()).getID();
        }
        Car car = new Car();
        car.setModel(String.valueOf(object.get("model")));
        car.setMark((String) object.get("mark"));
        car.setYearOfRelease(Math.toIntExact((Long)object.get("yearOfRelease")));
        car.setCondition(Condition.valueOf(String.valueOf(object.get("condition"))));
        car.setOwnerID(userID);
        carDAO.addCar(car);
        int carID = Util.getMaxId("car_db");

        JSONArray array = (JSONArray) object.get("numbers");
        Iterator<JSONObject> iterator = array.iterator();
        while (iterator.hasNext()) {
            JSONObject jsonObject = iterator.next();
            System.out.println(jsonObject);
            Phone phone = new Phone();
            phone.setOwnerID(userID);
            phone.setNumber(String.valueOf(jsonObject.get("number")));
            phoneDAO.addPhone(phone);
        }
        array.clear();

        array =(JSONArray) object.get("images");
        iterator = array.iterator();
        while (iterator.hasNext()) {
            JSONObject jsonObject = iterator.next();
            Image image = new Image();
            image.setCarId(carID);
            image.setUrl(String.valueOf(jsonObject.get("url")));
            imageDAO.addImage(image);
        }
        response.sendRedirect("/car-shop/cars/page/1");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject object = Util.readJSON(req);
        if (object.containsKey("car_id")){
            resp.sendRedirect("/car-shop/car/"+object.get("car_id").toString());
        }
    }
}
