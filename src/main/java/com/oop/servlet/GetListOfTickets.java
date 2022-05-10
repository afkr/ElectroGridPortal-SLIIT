package com.oop.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oop.bll.NoticeBLL;
import com.oop.bll.TicketBLL;
import com.oop.model.Notice;
import com.oop.model.ReturnObject;
import com.oop.model.Ticket;
import com.oop.utils.Utilities;

/**
 * Servlet implementation class GetListOfTickets
 */
@WebServlet("/GetListOfTickets")
public class GetListOfTickets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetListOfTickets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<Ticket> ticketList = new TicketBLL().GetListOfTickets();
		
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
