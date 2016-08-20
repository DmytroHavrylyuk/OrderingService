package com.dikzz.service.preprocessors;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dikzz on 8/15/16.
 */
@WebServlet(urlPatterns = "/dikzz", asyncSupported = true)
public class MyServlet extends HttpServlet {

    public MyServlet() {
        super();
        System.out.println("construct" + Thread.currentThread().getName());
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init" + Thread.currentThread().getName());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.print("Hello");
        System.out.println("get " + Thread.currentThread().getName());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        System.out.println("service 1 " + Thread.currentThread().getName());
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println();
        super.service(req, res);
        System.out.println("service 2 " + Thread.currentThread().getName());
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("destroy " + Thread.currentThread().getName());
    }
}
