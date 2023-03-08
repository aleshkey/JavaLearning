package com.shop.carshop.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shop.carshop.DAO.CarDAO;
import com.shop.carshop.DAO.ImageDAO;
import com.shop.carshop.models.Image;
import com.shop.carshop.utils.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SingleCarServlet", value = "/SingleCarServlet")
public class SingleImageServlet extends HttpServlet {

    private ImageDAO imageDAO = new ImageDAO();
    private CarDAO carDAO = new CarDAO();

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Image image = new Image();
        PrintWriter pw = resp.getWriter();
        pw.write(gson.toJson(imageDAO.getImageByID(Util.getIDFromURL(req.getRequestURI()))));
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int imageID = Util.getIDFromURL(req.getRequestURI());
        Image image = imageDAO.getImageByID(imageID);
        int carID = image.getCarId();
        System.out.println(carID);
        imageDAO.deleteById(imageID);
        carDAO.updateDate(carID);
        System.out.println(carID);
        resp.sendRedirect("/car-shop/cars/"+carID);
    }
}
