package com.oop.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
 * Servlet implementation class DeleteNotice
 */
@WebServlet("/DeleteNotice")
public class DeleteNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteNotice() {
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
		    	
				Notice notice = mapper.readValue(json, Notice.class);
				
				new NoticeBLL().DeleteNotice(notice.getId());
				
				ReturnObject obj = new ReturnObject();
				
				obj.Status = Utilities.resultStatus.success.toString();

				// 4. Set response type to JSON
				response.setContentType("application/json");		    
				
				mapper.writeValue(response.getOutputStream(), obj);
	}

}
