package com.revature.project.dao;

import com.revature.project.models.Employee;
import com.revature.project.models.Ticket;
import com.revature.project.util.ConnUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImpl implements TicketDAO {

    // codeblock for ticket creation
    @Override
    public boolean createTicket(int employeeId, String amount, String description) {
        System.out.println("Called the create ticket method!");

        Ticket ticket = new Ticket();

        try (Connection conn = ConnUtil.getConn()) {

            String sql = "INSERT INTO tickets (submitid, amount, description) VALUES (?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, employeeId);
            stmt.setString(2, amount);
            stmt.setString(3, description);


            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Unable to create ticket!");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean createTicket(Integer employeeId, String amount, String description) {
        return false;
    }

    @Override
    public boolean createTicket(Employee employee, String amount, String description) {
        return false;
    }

    @Override
    public boolean createTicket(String amount, String description, int employeeId) {
        return false;
    }

    // currently unimplemented method to return all tickets in database; admin function;

    @Override
    public List<Ticket> getAllTickets() {

        System.out.println("Retrieving employee tickets...");
        Connection conn = ConnUtil.getConn();
        List<Ticket> tickets = new ArrayList<>();

        try {
            String sql = "SELECT * FROM tickets";

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null) {

                while (rs.next()) {

                    int id = rs.getInt("ticketnum");
                    int submitId = rs.getInt("submitid");
                    String submitTime = rs.getString("submittime");
                    String amount = rs.getString("amount");
                    String status = rs.getString("status");
                    String approveName = rs.getString("approvename");
                    String approveTime = rs.getString("approvetime");
                    String description = rs.getString("description");

                    Ticket ticket = new Ticket(id, submitId, submitTime, amount, status, approveName, approveTime, description);
                    tickets.add(ticket);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }


    @Override
    public List<Ticket> getAllPending() {
        System.out.println("Retrieving employee tickets...");
        Connection conn = ConnUtil.getConn();
        List<Ticket> tickets = new ArrayList<>();

        try {
            String sql = "SELECT * FROM tickets WHERE status = Pending";

            PreparedStatement stmt = conn.prepareStatement(sql);


            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null) {

                while (rs.next()) {

                    int id = rs.getInt("ticketnum");
                    int submitId = rs.getInt("submitid");
                    String submitTime = rs.getString("submittime");
                    String amount = rs.getString("amount");
                    String status = rs.getString("status");
                    String approveName = rs.getString("approvename");
                    String approveTime = rs.getString("approvetime");
                    String description = rs.getString("description");

                    Ticket ticket = new Ticket(id, submitId, submitTime, amount, status, approveName, approveTime, description);
                    tickets.add(ticket);
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        } return tickets;
    }

    // codeblock for retrieving current user tickets from database
    @Override
    public List<Ticket> getTicketByEmployee(int employeeId) {
        System.out.println("Retrieving employee tickets...");
        Connection conn = ConnUtil.getConn();
        List<Ticket> tickets = new ArrayList<>();

        try {

            String sql = "SELECT * FROM tickets WHERE submitid = ? AND status = Pending";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, employeeId);

            ResultSet rs;


            if ((rs = stmt.executeQuery()) != null) {

                while (rs.next()) {

                    int id = rs.getInt("ticketnum");
                    int submitId = rs.getInt("submitid");
                    String submitTime = rs.getString("submittime");
                    String amount = rs.getString("amount");
                    String status = rs.getString("status");
                    String approveName = rs.getString("approvename");
                    String approveTime = rs.getString("approvetime");
                    String description = rs.getString("description");

                    Ticket ticket = new Ticket(id, submitId, submitTime, amount, status, approveName, approveTime, description);
                    tickets.add(ticket);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public List<Ticket> getPastTicketByEmployee(int employeeId) {
        System.out.println("Retrieving employee tickets...");
        Connection conn = ConnUtil.getConn();
        List<Ticket> tickets = new ArrayList<>();

        try {

            String sql = "SELECT * FROM tickets WHERE submitid = ? AND status <> Pending";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, employeeId);

            ResultSet rs;


            if ((rs = stmt.executeQuery()) != null) {

                while (rs.next()) {

                    int id = rs.getInt("ticketnum");
                    int submitId = rs.getInt("submitid");
                    String submitTime = rs.getString("submittime");
                    String amount = rs.getString("amount");
                    String status = rs.getString("status");
                    String approveName = rs.getString("approvename");
                    String approveTime = rs.getString("approvetime");
                    String description = rs.getString("description");

                    Ticket ticket = new Ticket(id, submitId, submitTime, amount, status, approveName, approveTime, description);
                    tickets.add(ticket);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public Ticket updateTicket(int ticketNum, String status, Employee employee) {

        Ticket ticket = new Ticket();


        try (Connection conn = ConnUtil.getConn()) {

            String sql = "UPDATE tickets SET status = ?, approveName = ?, approveTime = ? WHERE ticketNum = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, status);
            stmt.setString(2, employee.getUsername());
            stmt.setString(3, String.valueOf(LocalDateTime.now()));
            stmt.setInt(4, ticketNum);

            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null) {



                int recTicketNum = ticketNum;
                int submitId = rs.getInt("submitId");
                String submitTime = rs.getString("submitTime");
                String amount = rs.getString("amount");
                String approveName = employee.getUsername();
                String approveTime = String.valueOf(LocalDateTime.now());
                String description = rs.getString("description");

                ticket = new Ticket(recTicketNum, submitId, submitTime, amount, status, approveName, approveTime, description);

            }

        } catch (SQLException e) {
            System.out.println("Unable to retrieve tickets!");
            e.printStackTrace();
        }
        return ticket;
    }

}
