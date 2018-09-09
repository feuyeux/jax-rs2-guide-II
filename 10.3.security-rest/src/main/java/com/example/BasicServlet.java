package com.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(asyncSupported = false, name = "basicServlet", urlPatterns = { "/basic" })
public class BasicServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        out.println("This is the Test Servlet");
    }
}
