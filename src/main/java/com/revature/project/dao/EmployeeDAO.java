package com.revature.project.dao;

import com.revature.project.models.Employee;

import java.util.List;

public interface EmployeeDAO {

    Employee getByUsername(String username);

    Employee createEmployee(String firstName, String lastName, String username, String password);

    List<Employee> getAllEmployees();
}
