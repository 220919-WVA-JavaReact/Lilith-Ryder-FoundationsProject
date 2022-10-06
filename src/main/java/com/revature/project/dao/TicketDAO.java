package com.revature.project.dao;

import com.revature.project.models.Employee;
import com.revature.project.models.Ticket;

import java.util.List;

public interface TicketDAO {

    boolean createTicket(Integer employeeId, String amount, String description);

    boolean createTicket(Employee employee, String amount, String description);

    boolean createTicket(int amount, String description, String employee);

    boolean createTicket(String amount, String description, int employeeId);

    List<Ticket> getAllTickets();

    List<Ticket> getTicketByEmployee(int employeeId);

    boolean approveTicket(Ticket ticket);

    boolean denyTicket(Ticket ticket);



}
