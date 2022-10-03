package com.revature.project.services;

import com.revature.project.dao.EmployeeDAO;
import com.revature.project.dao.EmployeeDAOImpl;
import com.revature.project.models.Employee;

public class EmployeeService {
    EmployeeDAO ed = new EmployeeDAOImpl();

    public void login(String username, String password){

        Employee worker = ed.getByUsername(username);

        if(worker.getPassword().equals(password)){
            System.out.println("Login successful!");
            System.out.println(worker);
        } else{
            System.out.println("Invalid login attempt.");
        }

    }
}
