package com.shop.carshop.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shop.carshop.Dto.AdMapper;
import com.shop.carshop.Repository.AdRepository;
import com.shop.carshop.model.*;
import com.shop.carshop.utils.Util;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

@WebServlet(name = "MenuServlet", value = "/MenuServlet")
public class MenuServlet extends HttpServlet {
    private final AdRepository adRepository = new AdRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        int page = Util.getIDFromURL(request.getRequestURI());
        PrintWriter pw = response.getWriter();
        for (var ad : adRepository.getAllAds()){
            Car car = ad.getCar();
            pw.write(String.valueOf(AdMapper.fromCarToJSON(car)));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdMapper.createAd(request);
        response.sendRedirect("/car-shop/cars/page/1");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/car-shop/cars/"+AdMapper.getAd(req));
    }
}
