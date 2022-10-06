package com.revature.project.services;

import com.revature.project.dao.EmployeeDAO;
import com.revature.project.dao.EmployeeDAOImplPostgres;
import com.revature.project.models.Employee;

import java.util.List;
import java.util.Scanner;

public class EmployeeService {
    EmployeeDAO ed = new EmployeeDAOImplPostgres();
    Scanner sc = new Scanner(System.in);
    public Employee login(){

        System.out.println("Please enter your username: ");
        String username = sc.nextLine();
        System.out.println("Please enter your password: ");
        String password = sc.nextLine();

        Employee employee = ed.getByUsername(username);


        if(employee.getPassword().equals(password)){
            System.out.println("Login successful!");
            System.out.println(employee);

            return employee;
        } else{
            System.out.println("Invalid login attempt.");
            return null;
        }

    }

    public Employee register() {
        System.out.println("Please enter your First Name");
        String firstName = sc.nextLine();
        System.out.println("Please enter your Last Name");
        String lastName = sc.nextLine();
        System.out.println("Please enter your username");
        String username = sc.nextLine();
        System.out.println("Please enter your password");
        String password = sc.nextLine();

        Employee employee = ed.createEmployee(firstName,lastName,username,password);

        return employee;
    }

    public void getAllEmployees(){
        System.out.println("Return all employees in database.");

        List<Employee> employeeList = ed.getAllEmployees();

        for(Employee employee : employeeList){
            System.out.println(employee);
        }
    }


}
