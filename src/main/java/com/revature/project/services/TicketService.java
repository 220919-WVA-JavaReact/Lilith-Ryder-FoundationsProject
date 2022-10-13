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
    public void createTicket(Employee employee) {
        while (true) {

            System.out.println("Enter reimbursement amount: ");
            String amount = sc.nextLine();

            if (amount.equals("")) {
                System.out.println("amount cannot be blank!");
                return;
            } else if (Double.parseDouble(amount) <= 0) {
                System.out.println("amount must be a number greater than zero!");
                return;
            } else if (amount.equals("exit")) {

                return;
            }

            System.out.println("Enter description: ");
            String description = sc.nextLine();

            if (description.equals("")) {
                System.out.println("Description cannot be blank!");
                return;
            }

            int employeeId = employee.getEmployeeId();

            boolean successful = td.createTicket(employeeId, amount, description);

            if (successful) {
                System.out.println("Ticket submitted successfully!");
            } else {
                System.out.println("Something went wrong");
            }
        }
    }

    //allows current user to look at tickets they've submitted
    public void getTicketByEmployee(Employee employee) {
        List<Ticket> ticketList = td.getTicketByEmployee(employee.getEmployeeId());

        for (Ticket ticket : ticketList) {
            System.out.println(ticket);
        }
    }

    public void createTicket(int employeeId, Ticket newTicket) {


            if (newTicket.getAmount().equals("")) {
                System.out.println("amount cannot be blank!");
                return;
            } else if (Double.parseDouble(newTicket.getAmount()) <= 0) {
                System.out.println("amount must be a number greater than zero!");
                return;
            }

            if (newTicket.getDescription().equals("")) {
                System.out.println("Description cannot be blank!");
                return;
            }

            boolean successful = td.createTicket(employeeId, newTicket.getAmount(), newTicket.getDescription());

            if (successful) {
                System.out.println("Ticket submitted successfully!");
            } else {
                System.out.println("Something went wrong");
            }
        }

    }