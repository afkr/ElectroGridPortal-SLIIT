package com.oop.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oop.bll.TicketBLL;
import com.oop.model.ReturnObject;
import com.oop.model.Ticket;
import com.oop.utils.Utilities;

/**
 * Servlet implementation class GetListOfSubmittedTickets
 */
@WebServlet("/GetListOfSubmittedTickets")
public class GetListOfSubmittedTickets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetListOfSubmittedTickets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		
		int loggedInUserId = Integer.parseInt(session.getAttribute(Utilities.SessionVariables.userId.toString()).toString()) ;
		
	ArrayList<Ticket> ticketList = new TicketBLL().GetListOfSubmittedTickets(loggedInUserId);
		
		ReturnObject obj = new ReturnObject();

		obj.Status = Utilities.resultStatus.success.toString();

		ObjectMapper mapper = new ObjectMapper();
		
		// 4. Set response type to JSON
		response.setContentType("application/json");		    
		
		mapper.writeValue(response.getOutputStream(), ticketList);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
