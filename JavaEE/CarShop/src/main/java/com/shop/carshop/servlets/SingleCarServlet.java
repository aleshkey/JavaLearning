package com.shop.carshop.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shop.carshop.DAO.CarDAO;
import com.shop.carshop.models.Car;
import com.shop.carshop.utils.Util;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SingleCarServlet", value = "/SingleCarServlet")
public class SingleCarServlet extends HttpServlet {
    CarDAO carDAO = new CarDAO();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter pw = response.getWriter();
        pw.write(gson.toJson(carDAO.findCarByID(Util.getIDFromURL(request.getRequestURI()))));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
