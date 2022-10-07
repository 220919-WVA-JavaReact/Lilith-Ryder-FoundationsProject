package com.revature.project.services;

import com.revature.project.dao.TicketDAO;
import com.revature.project.dao.TicketDAOImpl;
import com.revature.project.models.Employee;
import com.revature.project.models.Ticket;

import java.util.List;
import java.util.Scanner;

public class TicketService {

    // Allows user input and implements Ticket DAO
    Scanner sc = new Scanner(System.in);

    TicketDAO td = new TicketDAOImpl();

    // creates a new ticket a persists to database
    public void createTicket(Employee employee){
        System.out.println("Enter reimbursement amount: ");
        String amount = sc.nextLine();

        System.out.println("Enter description: ");
        String description = sc.nextLine();

        int employeeId = employee.getEmployeeId();

        boolean successful = td.createTicket(employeeId, amount, description);

        if (successful){
            System.out.println("Ticket submitted successfully!");
        } else{
            System.out.println("Something went wrong");
        }
    }

    //allows current user to look at tickets they've submitted
    public void getTicketByEmployee(Employee employee){
        List<Ticket> ticketList = td.getTicketByEmployee(employee.getEmployeeId());

        for(Ticket ticket: ticketList){
            System.out.println(ticket);
        }
    }

}