package com.revature.project.dao;

import com.revature.project.models.Ticket;

import java.util.List;

public interface TicketDAO {

    Ticket createTicket(String amount, String description);

    List<Ticket> getAllTickets();

    List<Ticket> getTicketByEmployee(int employeeId);

    boolean approveTicket(Ticket ticket);

    boolean denyTicket(Ticket ticket);


}
