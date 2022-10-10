package com.revature.project;

import com.revature.project.models.Employee;
import com.revature.project.services.EmployeeService;
import com.revature.project.services.TicketService;

import java.util.Scanner;

public class App {

    //initializes services to be used during operations.
    public static EmployeeService es = new EmployeeService();

    public static TicketService ts = new TicketService();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {

            System.out.println("Enter 1 to Login. Enter 2 to register. Enter 'exit' at any time to close.");

            Scanner sc = new Scanner(System.in);

            String choice = sc.nextLine();

            Employee loggedInEmployee = null;

            if (choice.equals("1")) {
                loggedInEmployee = es.login();
            } else if (choice.equals("2")) {
                loggedInEmployee = es.register();
            } else if (choice.equals("exit")) {
                return;
            } else {
                System.out.println("Invalid Entry");
            }

            //verifies user logged in before allowing access to menu functions.

            while (loggedInEmployee != null) {
                System.out.println("Press 1 to create a new ticket, 2 to view current/past tickets, 3 to view all current employees.");
                String subchoice = sc.nextLine();

                switch (subchoice) {
                    case "1":
                        ts.createTicket(loggedInEmployee);
                        break;
                    case "2":
                        ts.getTicketByEmployee(loggedInEmployee);
                        break;
                    case "3":
                        es.getAllEmployees();
                        break;
                    case "exit":
                        return;
                    default:
                        System.out.println("Invalid Entry");

                }
            }
        }
    }
}