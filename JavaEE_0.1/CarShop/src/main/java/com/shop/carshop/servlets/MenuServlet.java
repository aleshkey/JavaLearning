package com.shop.carshop.servlets;

import com.shop.carshop.Dto.AdMapper;
import com.shop.carshop.model.*;
import com.shop.carshop.utils.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MenuServlet", value = "/MenuServlet")
public class MenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        int page = Util.getIDFromURL(request.getRequestURI());
        PrintWriter pw = response.getWriter();
        String string = AdMapper.getPage(page);
        pw.write(string);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdMapper.createAd(request);
        response.sendRedirect("/car-shop/cars/pages/1");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/car-shop/cars/"+AdMapper.getAd(req));
    }
}
