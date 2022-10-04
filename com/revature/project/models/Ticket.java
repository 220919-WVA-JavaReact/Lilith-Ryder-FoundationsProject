package com.revature.project.models;

import java.util.Objects;

public class Ticket {

    private int ticketNum;

    private String submitId;

    private String submitTime;

    private String amount;

    private String status;

    private String approveName;

    private String approveTime;

    public Ticket(int ticketNum, String submitName, String submitTime, String amount, String status, String approveName, String approveTime) {
        this.ticketNum = ticketNum;
        this.submitId = submitName;
        this.submitTime = submitTime;
        this.amount = amount;
        this.status = status;
        this.approveName = approveName;
        this.approveTime = approveTime;
    }

    public Ticket(String submitName, String submitTime, String amount) {
        this.submitId = submitName;
        this.submitTime = submitTime;
        this.amount = amount;
    }

    public Ticket() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return ticketNum == ticket.ticketNum && submitId.equals(ticket.submitId) && submitTime.equals(ticket.submitTime) && amount.equals(ticket.amount) && status.equals(ticket.status) && Objects.equals(approveName, ticket.approveName) && Objects.equals(approveTime, ticket.approveTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketNum, submitId, submitTime, amount, status, approveName, approveTime);
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    public String getSubmitName() {
        return submitId;
    }

    public void setSubmitName(String submitName) {
        this.submitId = submitName;
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
}
