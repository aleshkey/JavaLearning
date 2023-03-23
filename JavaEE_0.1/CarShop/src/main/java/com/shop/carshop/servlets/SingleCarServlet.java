package com.shop.carshop.servlets;

import com.shop.carshop.Dto.AdMapper;
import com.shop.carshop.model.Car;
import com.shop.carshop.utils.Util;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/car-shop/cars/{id}")
public class SingleCarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        JSONObject object = AdMapper.getInfoAboutCar(request);
        pw.write(String.valueOf(object));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int carId = Util.getIDFromURL(request.getRequestURI());
        AdMapper.updateCar(request);
        response.sendRedirect("/car-shop/cars/"+carId);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdMapper.removeCar(req);
        resp.sendRedirect("/car-shop/cars/pages/1");
    }
}
