package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project.models.Employee;
import com.revature.project.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    ObjectMapper mapper = new ObjectMapper();
    EmployeeService es = new EmployeeService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("[LOG] - EmployeeServlet received a request at " + LocalDateTime.now());
        System.out.println("[LOG] - Request URI: " + req.getRequestURI());
        System.out.println("[LOG] - Request Method: " + req.getMethod());
        System.out.println("[LOG] - Request Header, example: " + req.getHeader("example"));

        resp.setStatus(204);
        resp.setHeader("Content-type", "text/plain");
        resp.setHeader("example-response-header", "some-example-value");
        resp.getWriter().write("This is the response payload");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("[LOG] - EmployeeServlet received a request at " + LocalDateTime.now());
        Employee newEmployee = mapper.readValue(req.getInputStream(), Employee.class);

        es.login(newEmployee.getUsername(), newEmployee.getPassword());

        System.out.println(newEmployee);

        resp.setStatus(204);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}