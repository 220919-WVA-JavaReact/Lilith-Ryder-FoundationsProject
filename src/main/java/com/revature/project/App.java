package com.revature.project;

import com.revature.project.models.Employee;
import com.revature.project.services.EmployeeService;
import com.revature.project.services.TicketService;

import java.util.Scanner;

public class App {

    public static EmployeeService es = new EmployeeService();

    public static TicketService ts = new TicketService();

    public static void main(String[] args) {
        System.out.println("Press 1 to Login. Press 2 to register");

        Scanner sc = new Scanner(System.in);

        String choice = sc.nextLine();

        Employee loggedInEmployee = null;

        if (choice.equals("1")) {
           loggedInEmployee = es.login();
        }
        else if (choice.equals("2")) {
            loggedInEmployee = es.register();
        }

        if (loggedInEmployee != null){
            System.out.println("Press 1 to create a new ticket, 2 to view current/past tickets, 3 to view all current employees.");
            String subchoice = sc.nextLine();

            switch (subchoice){
                case "1":
                    ts.createTicket(loggedInEmployee);
                    break;
                case "2":
                    ts.getTicketByEmployee(loggedInEmployee);
                    break;
                case "3":
                    es.getAllEmployees();
                    break;
                default:
                    System.out.println("Invalid Entry");

            }
        }
    }
}
