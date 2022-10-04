package com.revature.project.dao;

import com.revature.project.models.Ticket;

import java.util.List;

public class TicketDAOImpl implements TicketDAO{


    @Override
    public Ticket createTicket(String amount, String description) {
        System.out.println("Called ticket creation method");
        return null;
    }

    @Override
    public List<Ticket> getAllTickets() {
        System.out.println("Called getAllTickets method");
        return null;
    }

    @Override
    public List<Ticket> getTicketByEmployee(int employeeId) {
        System.out.println("Called getTicketByEmployeeId method");
        return null;
    }

    @Override
    public boolean approveTicket(Ticket ticket) {
        System.out.println("Called the approveTicket method");
        return false;
    }

    @Override
    public boolean denyTicket(Ticket ticket) {
        System.out.println("Called the denyTicket method");
        return false;
    }
}
