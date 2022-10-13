package com.revature.project.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project.dao.TicketDAO;
import com.revature.project.models.Employee;
import com.revature.project.models.Ticket;
import com.revature.project.services.TicketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


public class TicketServlet extends HttpServlet {
    TicketService ts = new TicketService();
    ObjectMapper mapper = new ObjectMapper();

    public TicketServlet(ObjectMapper mapper, TicketDAO td) {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[LOG] - TicketServlet received a request at " + LocalDateTime.now());
        Employee current = mapper.readValue(req.getInputStream(), Employee.class);
        ts.getTicketByEmployee(current);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee current = mapper.readValue(req.getInputStream(), Employee.class);
        Ticket newTicket = mapper.readValue(req.getInputStream(), Ticket.class);
        ts.createTicket(current.getEmployeeId(), newTicket);
        resp.setStatus(204);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }


}
