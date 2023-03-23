package com.shop.carshop.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shop.carshop.Dto.AdMapper;
import com.shop.carshop.model.Image;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.write(AdMapper.getImageInJSON(req));
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdMapper.removeImage(req);
        resp.sendRedirect("/car-shop/cars/page/1");
    }

}
