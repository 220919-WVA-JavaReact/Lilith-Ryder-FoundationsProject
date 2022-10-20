package com.revature.project.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project.models.Employee;
import com.revature.project.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;



public class EmployeeServlet extends HttpServlet {
    ObjectMapper mapper = new ObjectMapper();
    EmployeeService es = new EmployeeService();

    public EmployeeServlet(ObjectMapper mapper) {
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("[LOG] - EmployeeServlet received a request at " + LocalDateTime.now());

        resp.setStatus(204);
        resp.setHeader("Content-type", "text/plain");
        resp.setHeader("example-response-header", "some-example-value");
        resp.getWriter().write("This is the response payload");

        es.getAllEmployees();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("[LOG] - EmployeeServlet received a request at " + LocalDateTime.now());
        Employee newEmployee = mapper.readValue(req.getInputStream(), Employee.class);

        HttpSession session = req.getSession();
        //session.setAttribute("Auth-employee", newEmployee);

        switch (req.getHeader("Type")) {
            case "Login":
                Employee employee = es.login(newEmployee.getUsername(), newEmployee.getPassword());

                if (employee.getAdmin()) {
                    session.setAttribute("Auth-employee", employee);
                    session.setAttribute("Admin", true);
                    System.out.println("[LOG] - Logged in as manager role.");
                    resp.getWriter().write("Logged in as manager role.");
                    resp.setStatus(204);
                    return;
                } else if (!employee.getAdmin()){
                    session.setAttribute("Auth-employee", employee);
                    session.setAttribute("Admin", false);
                    System.out.println("[LOG] - Logged in as employee role.");
                    resp.getWriter().write("Logged in as employee role.");
                    resp.setStatus(204);
                    return;
                } else {
                    resp.setStatus(403);
                    resp.getWriter().write("invalid login attempt");
                    return;
                }
            case "Register":
                        es.register(newEmployee);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[LOG] - EmployeeServlet received a request at " + LocalDateTime.now());
        Employee newEmployee = mapper.readValue(req.getInputStream(), Employee.class);


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[LOG] - EmployeeServlet received a request at " + LocalDateTime.now());
        HttpSession session = req.getSession(false);

        if(session != null){

            System.out.println("Successfully logged out.");
            session.invalidate();
        }

        resp.setStatus(204);
    }
}
