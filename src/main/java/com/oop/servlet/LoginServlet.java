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

import org.json.JSONObject;

import com.oop.bll.UserBLL;
import com.oop.model.ReturnObject;
import com.oop.model.User;
import com.oop.utils.Utilities;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class Login
 */


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		//JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
		//String username = data.get("username").getAsString();
		//String password = data.get("password").getAsString();
		
		BufferedReader br = 
				new BufferedReader(new InputStreamReader(request.getInputStream()));
				
				String json = "";
				if(br != null){
					json = br.readLine();
				}
				
				//JSONObject asd = new JSONObject();
				
				// 2. initiate jackson mapper
				ObjectMapper mapper = new ObjectMapper();
		    	
				// 3. Convert received JSON to Article
				User user = mapper.readValue(json, User.class);
				
				User isExist = new UserBLL().AuthenticateUser(user);
				
				ReturnObject obj = new ReturnObject();
				
				if(isExist.getId() == 0) {
					obj.Status = Utilities.resultStatus.warning.toString();
					obj.Message = Utilities.INVALID_CREDENTIALS;
				}
				
				else {
					HttpSession session = request.getSession();
					
					session.setAttribute(Utilities.SessionVariables.userId.toString(), isExist.getId());
					session.setAttribute(Utilities.SessionVariables.fullName.toString(), isExist.getFullName());
					session.setAttribute(Utilities.SessionVariables.email.toString(), isExist.getEmail());
					session.setAttribute(Utilities.SessionVariables.userType.toString(), isExist.getUserType());
					
					obj.Status = Utilities.resultStatus.success.toString();
				}

				// 4. Set response type to JSON
				response.setContentType("application/json");		    
				
				mapper.writeValue(response.getOutputStream(), obj);

	}

}
