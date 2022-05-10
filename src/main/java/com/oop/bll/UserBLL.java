package com.oop.bll;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.oop.model.ReturnObject;
import com.oop.model.User;
import com.oop.repository.UserRepository;
import com.oop.utils.Utilities;

public class UserBLL {
	public User AuthenticateUser(User user) {
		User isExist = new UserRepository().GetUserByUsernameAndPassword(user.getUsername(), user.getPassword());	
		return isExist;
	}
	
	public void CreateUser(User user) {
		new UserRepository().CreateUser(user);
	}
	
	public void EditUser(User user) {		
		new UserRepository().EditUser(user);
	}
	
	public void DeleteUser(int id) {
		new UserRepository().DeleteUser(id);	
	}
	
	public ArrayList<User> GetListOfUsers(){
		ArrayList<User> userList = new UserRepository().GetListOfUsers();
		return userList;
	}
}
