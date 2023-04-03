package com.shop.carshop.servlets;

import com.shop.carshop.Dto.ImageMapper;

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
        pw.write(ImageMapper.getImageInJSON(req));
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ImageMapper.removeImage(req);
        resp.sendRedirect("/car-shop/cars/pages/1");
    }

}
