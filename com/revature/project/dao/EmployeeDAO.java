package com.revature.project.dao;

import com.revature.project.models.Employee;

public interface EmployeeDAO {

    Employee getByUsername(String username);

}
