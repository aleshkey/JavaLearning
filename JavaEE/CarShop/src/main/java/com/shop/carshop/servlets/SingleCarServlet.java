package com.shop.carshop.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shop.carshop.DAO.CarDAO;
import com.shop.carshop.DAO.ImageDAO;
import com.shop.carshop.DAO.PhoneDAO;
import com.shop.carshop.DAO.UserDAO;
import com.shop.carshop.models.Car;
import com.shop.carshop.utils.Util;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SingleCarServlet", value = "/SingleCarServlet")
public class SingleCarServlet extends HttpServlet {
    CarDAO carDAO = new CarDAO();
    ImageDAO imageDAO = new ImageDAO();
    UserDAO userDAO = new UserDAO();
    PhoneDAO phoneDAO = new PhoneDAO();

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter pw = response.getWriter();
        pw.write(gson.toJson(carDAO.findCarByID(Util.getIDFromURL(request.getRequestURI()))));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int carId = Util.getIDFromURL(request.getRequestURI());
        Car car = carDAO.findCarByID(carId);
        JSONObject object = Util.readJSON(request);
        if (object.containsKey("mark"))
            car.setMark(String.valueOf(object.get("mark")));
        if (object.containsKey("model"))
            car.setModel(String.valueOf(object.get("model")));
        if (object.containsKey("year_of_release"))
            car.setYearOfRelease((Integer) object.get("year_of_release"));
        carDAO.updateCar(car);
        response.sendRedirect("/car-shop/cars/"+car.getID());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int carId = Util.getIDFromURL(req.getRequestURI());
        carDAO.deleteCar(carId);
        imageDAO.deleteByOwnerID(carId);
        resp.sendRedirect("/car-shop/cars");
    }
}
