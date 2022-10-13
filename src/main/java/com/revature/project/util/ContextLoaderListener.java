package com.revature.project.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project.dao.TicketDAO;
import com.revature.project.dao.TicketDAOImpl;
import com.revature.servlets.AuthServlet;
import com.revature.servlets.EmployeeServlet;
import com.revature.servlets.TicketServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import java.time.LocalDateTime;

public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("[LOG] - The servlet context was initialized at " + LocalDateTime.now());

        ObjectMapper mapper = new ObjectMapper();
        TicketDAO td = new TicketDAOImpl();
        EmployeeServlet employeeServlet = new EmployeeServlet(mapper);
        TicketServlet ticketServlet = new TicketServlet(mapper, td);
        AuthServlet authServlet = new AuthServlet(mapper);


        ServletContext context = sce.getServletContext();
        ServletRegistration.Dynamic registeredServlet = context.addServlet("EmployeeServlet", employeeServlet);

        registeredServlet.addMapping("/employees");
        registeredServlet.setLoadOnStartup(3);
        registeredServlet.setInitParameter("employee-servlet-key", "employee-servlet-value");
        registeredServlet.setInitParameter("another-param", "another-value");

        context.addServlet("TicketServlet", ticketServlet).addMapping("/tickets/*");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[LOG] - The servlet context was destroyed at " + LocalDateTime.now());
    }
}
