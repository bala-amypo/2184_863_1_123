package com.example.demo.config;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<Servlet> helloServlet() {
        return new ServletRegistrationBean<>(new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                resp.setContentType("text/plain");
                resp.getWriter().write("Hello from simple servlet");
            }
        }, "/hello-servlet");
    }
}
