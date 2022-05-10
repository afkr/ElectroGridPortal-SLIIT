package com.oop.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oop.bll.TicketBLL;
import com.oop.bll.UserBLL;
import com.oop.model.ReturnObject;
import com.oop.model.Ticket;
import com.oop.model.User;
import com.oop.utils.Utilities;

/**
 * Servlet implementation class CreateTicket
 */
@WebServlet("/CreateTicket")
public class CreateTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = 
				new BufferedReader(new InputStreamReader(request.getInputStream()));
				
				String json = "";
				if(br != null){
					json = br.readLine();
				}
				
				ObjectMapper mapper = new ObjectMapper();
		    	
				Ticket ticket = mapper.readValue(json, Ticket.class);
				
				HttpSession session = request.getSession();
				
				ticket.setSubmittedBy(Integer.parseInt(session.getAttribute(Utilities.SessionVariables.userId.toString()).toString()));
				
				new TicketBLL().CreateTicket(ticket);
				
				ReturnObject obj = new ReturnObject();
				
				obj.Status = Utilities.resultStatus.success.toString();

				// 4. Set response type to JSON
				response.setContentType("application/json");		    
				
				mapper.writeValue(response.getOutputStream(), obj);
	}

}
