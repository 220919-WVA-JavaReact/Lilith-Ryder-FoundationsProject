package com.revature.project.models;

import java.util.Objects;

public class Ticket {

    private int ticketNum;

    private int submitId;

    private String submitTime;

    private String amount;

    private String status;

    private String approveName;

    private String approveTime;

    private String description;

    public Ticket(int ticketNum, int submitId, String submitTime, String amount, String status, String approveName, String approveTime, String description) {
        this.ticketNum = ticketNum;
        this.submitId = submitId;
        this.submitTime = submitTime;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.approveName = approveName;
        this.approveTime = approveTime;
    }

    public Ticket(String amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public Ticket() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return ticketNum == ticket.ticketNum && submitId == ticket.submitId && submitTime.equals(ticket.submitTime) && amount.equals(ticket.amount) && description.equals(ticket.description) && status.equals(ticket.status) && Objects.equals(approveName, ticket.approveName) && Objects.equals(approveTime, ticket.approveTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketNum, submitId, submitTime, amount, status, approveName, approveTime, description);
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    public int getSubmitId() {
        return submitId;
    }

    public void setSubmitId(int submitId) {
        this.submitId = submitId;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    public String getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(String approveTime) {
        this.approveTime = approveTime;
    }

    // allows tickets to be printed as readable values during GetTicket method calls
    @Override
    public String toString() {
        return "Employee{" +
                "ticketnum=" + ticketNum +
                ", submitId='" + submitId + '\'' +
                ", submitTime='" + submitTime + '\'' +
                ", amount='" + amount + '\'' +
                ", status='" + status + '\'' +
                ", approveName='" + approveName + '\'' +
                ", approveTime='" + approveTime + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
