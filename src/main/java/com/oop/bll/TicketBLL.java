package com.oop.bll;

import java.util.ArrayList;

import com.oop.model.Ticket;
import com.oop.model.User;
import com.oop.repository.TicketRepository;
import com.oop.repository.UserRepository;

public class TicketBLL {
	public void CreateTicket(Ticket ticket) {
		new TicketRepository().CreateTicket(ticket);
	}
	
	public void EditTicket(Ticket ticket) {
		new TicketRepository().EditTicket(ticket);
	}
	
	public void DeleteTicket(int id) {
		new TicketRepository().DeleteTicket(id);
	}
	
	public ArrayList<Ticket> GetListOfTickets(){
		ArrayList<Ticket> ticketList = new TicketRepository().GetListOfTickets();
		return ticketList;
	}
	
	public ArrayList<Ticket> GetListOfSubmittedTickets(int userId){
		ArrayList<Ticket> ticketList = new TicketRepository().GetListOfSubmittedTickets(userId);
		return ticketList;
	}
}

