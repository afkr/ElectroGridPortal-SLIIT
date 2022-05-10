package com.oop.model;

public class Ticket {
	private int Id;
	private String TicketDescription;
	private String TicketSolution;
	private String TicketStatus;
	private int SubmittedBy;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getTicketDescription() {
		return TicketDescription;
	}
	public void setTicketDescription(String ticketDescription) {
		TicketDescription = ticketDescription;
	}
	public String getTicketSolution() {
		return TicketSolution;
	}
	public void setTicketSolution(String ticketSolution) {
		TicketSolution = ticketSolution;
	}
	public String getTicketStatus() {
		return TicketStatus;
	}
	public void setTicketStatus(String ticketStatus) {
		TicketStatus = ticketStatus;
	}
	public int getSubmittedBy() {
		return SubmittedBy;
	}
	public void setSubmittedBy(int submittedby) {
		SubmittedBy = submittedby;
	}
	
	
	
}
