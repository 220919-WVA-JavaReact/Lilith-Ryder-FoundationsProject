package com.revature.project.dao;

import com.revature.project.models.Employee;
import com.revature.project.util.ConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImplPostgres implements EmployeeDAO {

    // codeblock to verify username and password input match database
    @Override
    public Employee getByUsername(String username) {

        Employee employee = new Employee();

        try (Connection conn = ConnUtil.getConn()) {

            String sql = "SELECT * FROM employees WHERE username = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, username);

            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null) {

                rs.next();

                int id = rs.getInt("employeeId");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String recUsername = rs.getString("username");
                String password = rs.getString("password");
                boolean admin = rs.getBoolean("admin");
                employee = new Employee(id, firstname, lastname, username, password, admin);
            }
        } catch (SQLException e) {
            System.out.println("Unable to retrieve employees!");
            e.printStackTrace();
        }
        return employee;
    }

    //codeblock for registering a new employee and persisting to database
    @Override
    public Employee createEmployee(String firstName, String lastName, String username, String password) {

        Employee employee = new Employee();

        try (Connection conn = ConnUtil.getConn()) {

            String sql = "INSERT INTO employees (firstname, lastname, username, password) VALUES (?,?,?,?) RETURNING *";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, username);
            stmt.setString(4, password);


            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null) {

                rs.next();
                rs.next();

                int id = rs.getInt("employeeId");
                String receivedFirst = rs.getString("firstname");
                String receivedLast = rs.getString("lastname");
                String receivedUsername = rs.getString("username");
                String receivedPassword = rs.getString("password");

                employee = new Employee(id, receivedFirst, receivedLast, receivedUsername, receivedPassword);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't register user to the database");
        }
        return employee;
    }

    // returns full list of current employees including password.
    // later iterations of program will provide this info to administrators only.
    public List<Employee> getAllEmployees() {
        Connection conn = ConnUtil.getConn();
        List<Employee> employees = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM employees";

            ResultSet rs = stmt.executeQuery(sql);


            while (rs.next()) {
                int id = rs.getInt("employeeId");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Boolean admin = rs.getBoolean("admin");

                Employee worker = new Employee(id, firstname, lastname, username, password, admin);
                employees.add(worker);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Employee createEmployee(Employee newEmployee) {

        String firstName = newEmployee.getFirstName();
        String lastName = newEmployee.getLastName();
        String username = newEmployee.getUsername();
        String password = newEmployee.getPassword();


        try (Connection conn = ConnUtil.getConn()) {

            String sql = "INSERT INTO employees (firstname, lastname, username, password) VALUES (?,?,?,?) RETURNING *";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, username);
            stmt.setString(4, password);


            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null) {

                rs.next();

                int id = rs.getInt("employeeId");
                String receivedFirst = rs.getString("firstname");
                String receivedLast = rs.getString("lastname");
                String receivedUsername = rs.getString("username");
                String receivedPassword = rs.getString("password");

                newEmployee = new Employee(id, receivedFirst, receivedLast, receivedUsername, receivedPassword);
                System.out.println(receivedFirst + " " + receivedLast + " has registered successfully!");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't register user to the database");
        }
        return newEmployee;
    }
}

