package com.revature.project.servlets;

import com.fasterxml.jackson.core.JsonParser;
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
import javax.servlet.http.HttpSession;
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
        HttpSession session = req.getSession();
        Employee employee= (Employee) session.getAttribute("Auth-employee");
        Boolean admin = (Boolean) session.getAttribute("Admin");
        if (admin == true && req.getHeader("Request").equals("Pending")) {
            ts.getAllPending();
            System.out.println("Getting all pending tickets!");
        } else if (admin == true && req.getHeader("Request").equals("All")) {
            ts.getAllTickets();
        } else if(admin == false && req.getHeader("Request").equals("Pending")) {
            ts.getTicketByEmployee(employee);
        } else if (!admin && req.getHeader("Request").equals("All")) {
            ts.getPastTicketByEmployee(employee);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Ticket newTicket = mapper.readValue(req.getInputStream(), Ticket.class);
        HttpSession session = req.getSession();
        Employee employee= (Employee)session.getAttribute("Auth-employee");
        ts.createTicket(employee.getEmployeeId(), newTicket);
        resp.setStatus(204);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Ticket newTicket = mapper.readValue(req.getInputStream(), Ticket.class);
        HttpSession session = req.getSession();
        Employee employee = (Employee)session.getAttribute("Auth-employee");
        boolean admin = (Boolean)session.getAttribute("Admin");

        if (!admin) {
            resp.getWriter().write("Only managers can approve or deny tickets.");
            System.out.println("Only managers can approve or deny tickets.");
        } else if (admin) {
                ts.updateTicket(newTicket.getTicketNum(), newTicket.getStatus(), employee);
                resp.getWriter().write("Ticket status has been updated to " + newTicket.getStatus());
            System.out.println("Ticket status has been updated to " + newTicket.getStatus());
                resp.setStatus(201);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }


}
