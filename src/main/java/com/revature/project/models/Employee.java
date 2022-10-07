package com.revature.project.models;

import java.util.Objects;

public class Employee {

    private int employeeId;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private Boolean admin;

    public Employee(int employeeId, String firstName, String lastName, String username, String password, Boolean admin) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public Employee(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, String username, String password) {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

// allows employee list items to be printed during GetEmployee method calls.
    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", first='" + firstName + '\'' +
                ", last='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId && firstName.equals(employee.firstName) && lastName.equals(employee.lastName) && username.equals(employee.username) && password.equals(employee.password) && admin.equals(employee.admin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, firstName, lastName, username, password, admin);
    }
}
