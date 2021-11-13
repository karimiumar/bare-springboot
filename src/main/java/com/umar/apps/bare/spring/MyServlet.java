package com.umar.apps.bare.spring;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.getWriter().println("""
                <html>
                    <head>
                        <title>...Bare Spring Boot...</title>
                    </head>
                    <body>
                        <h2>YAY! Bare Spring Boot's Up...</h2>
                    </body>
                </html>
                """);
    }
}
