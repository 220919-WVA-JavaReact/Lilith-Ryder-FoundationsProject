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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AuthServlet extends HttpServlet {
    private final ObjectMapper mapper;

    public AuthServlet(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employees = new ArrayList<>();
        EmployeeService es = new EmployeeService();

        employees.addAll(es.getAllEmployees());
        HashMap<String, Object> credentials = mapper.readValue(req.getInputStream(), HashMap.class);

        String providedUsername = (String) credentials.get("username");
        String providedPassword = (String) credentials.get("password");


        for (Employee employee: employees){
            if (providedUsername.equals(employee.getUsername()) && providedPassword.equals(employee.getPassword())){
                System.out.println("[LOG] - Found employee!");



                HttpSession session = req.getSession();
                session.setAttribute("auth-employee", employee);

                resp.setStatus(204);
                return;
            }
        }

        resp.setStatus(400);
        resp.setContentType("application/json");


        HashMap<String, Object> errorMessage = new HashMap<>();

        errorMessage.put("Status code", 400);
        errorMessage.put("Message", "No employee found with provided credentials");
        errorMessage.put("Timestamp", LocalDateTime.now().toString());

        resp.getWriter().write(mapper.writeValueAsString(errorMessage));


    }



    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);


        if(session != null){

            System.out.println(session.getAttribute("auth-employee"));
            session.invalidate();
        }

        resp.setStatus(204);
    }
}

