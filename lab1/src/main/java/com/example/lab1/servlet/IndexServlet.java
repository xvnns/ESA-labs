package com.example.lab1.servlet;

import com.example.lab1.bean.MainBean;

import java.io.*;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "indexServlet", value = "/index")
public class IndexServlet extends HttpServlet {

    @EJB
    MainBean mainBean;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("orders", mainBean.getAllOrders());
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}