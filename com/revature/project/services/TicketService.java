package com.revature.project.services;

import com.revature.project.dao.TicketDAO;
import com.revature.project.dao.TicketDAOImpl;

import java.util.Scanner;

public class TicketService {

    Scanner sc = new Scanner(System.in);

    TicketDAO ed = new TicketDAOImpl();

    public void createTicket(){
        System.out.println("Enter reimbursement amount: ");
        String amount = sc.nextLine();

        System.out.println("Enter description: ");
        String description = sc.nextLine();
    }

}
