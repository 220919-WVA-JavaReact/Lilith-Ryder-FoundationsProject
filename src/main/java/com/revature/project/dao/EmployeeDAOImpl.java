package com.revature.project.dao;

import com.revature.project.models.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


// Methods in this class were ported to EmployeeDAOImplPostgres.java and are not currently implemented.
public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public Employee getByUsername(String username) {

        String line;
        String splitBy = ",";

        try {
            BufferedReader br = new BufferedReader(new FileReader("Employee.csv"));

            while ((line = br.readLine()) != null) {

                String[] info = line.split(splitBy);

                if (info[3].equals(username)){
                    return new Employee(info[1], info[2], info[3], info[4]);
                }

            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Employee createEmployee(String firstName, String lastName, String username, String password) {
        System.out.println("Called the createEmployee method");
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public Employee createEmployee(Employee newEmployee) {
        return null;
    }
}
