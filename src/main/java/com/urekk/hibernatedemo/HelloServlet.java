package com.urekk.hibernatedemo;

import java.io.*;
import java.util.List;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        UserDAO dao = new UserDAO();
        User user = dao.findOneByEmail("admin@gmail.com");
        System.out.println("lay user tu database");
        out.println(user);
    }

    public void destroy() {
    }
}