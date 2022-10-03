package com.revature.project.dao;

import com.revature.project.models.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
}