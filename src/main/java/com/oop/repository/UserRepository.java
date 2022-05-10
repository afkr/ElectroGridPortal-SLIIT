package com.oop.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.oop.model.User;

import com.oop.utils.DBConnection;
import com.oop.utils.Utilities;


public class UserRepository {
	private static Connection connection;

	private static Statement statement;

	private PreparedStatement preparedStatement;
	
	public User GetUserByUsernameAndPassword(String username, String password) {
		
		User existingUser = new User();
		try {
			connection = DBConnection.getDBConnection();
			/*
			 * Query is available in EmployeeQuery.xml file and use
			 * insert_employee key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement("select * from User where User.Username = ? and User.Password = ?");
			connection.setAutoCommit(false);
			
			preparedStatement.setString(Utilities.COLUMN_INDEX_ONE, username);
			preparedStatement.setString(Utilities.COLUMN_INDEX_TWO, password);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				existingUser.setId(resultSet.getInt(Utilities.COLUMN_INDEX_ONE));
				existingUser.setFullName(resultSet.getString(Utilities.COLUMN_INDEX_TWO));
				existingUser.setEmail(resultSet.getString(Utilities.COLUMN_INDEX_THREE));
				existingUser.setUserType(resultSet.getString(Utilities.COLUMN_INDEX_FOUR));
				existingUser.setUsername(resultSet.getString(Utilities.COLUMN_INDEX_FIVE));
				existingUser.setPassword(resultSet.getString(Utilities.COLUMN_INDEX_SIX));
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				
			}
		}
		
		return existingUser;
	}
	
	public void CreateUser(User user) {
		try {
			connection = DBConnection.getDBConnection();
			/*
			 * Query is available in EmployeeQuery.xml file and use
			 * insert_employee key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement("insert into User (FullName, Email, UserType, Username, Password) values (?, ?, ?, ?, ?)");
			connection.setAutoCommit(false);
			
			preparedStatement.setString(Utilities.COLUMN_INDEX_ONE, user.getFullName());
			preparedStatement.setString(Utilities.COLUMN_INDEX_TWO, user.getEmail());
			preparedStatement.setString(Utilities.COLUMN_INDEX_THREE, user.getUserType());
			preparedStatement.setString(Utilities.COLUMN_INDEX_FOUR, user.getUsername());
			preparedStatement.setString(Utilities.COLUMN_INDEX_FIVE, user.getPassword());

			preparedStatement.execute();
			connection.commit();

		} catch (SQLException | ClassNotFoundException e) {
			
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				
			}
		}
		
	}
	
	public void EditUser(User user) {
		
		try {
			connection = DBConnection.getDBConnection();
			/*
			 * Query is available in EmployeeQuery.xml file and use
			 * insert_employee key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement("update User as u set u.FullName = ?, u.Email = ?, u.UserType = ? where u.Id = ?");
			connection.setAutoCommit(false);
			
			preparedStatement.setString(Utilities.COLUMN_INDEX_ONE, user.getFullName());
			preparedStatement.setString(Utilities.COLUMN_INDEX_TWO, user.getEmail());
			preparedStatement.setString(Utilities.COLUMN_INDEX_THREE, user.getUserType());
			preparedStatement.setInt(Utilities.COLUMN_INDEX_FOUR, user.getId());

			preparedStatement.execute();
			connection.commit();

		} catch (SQLException | ClassNotFoundException e) {
			
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				
			}
		}
		
		
		
	}
	
	public void DeleteUser(int id) {
		try {
			connection = DBConnection.getDBConnection();
			/*
			 * Query is available in EmployeeQuery.xml file and use
			 * insert_employee key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement("delete from User where User.Id = ?");
			connection.setAutoCommit(false);
			
			preparedStatement.setInt(Utilities.COLUMN_INDEX_ONE, id);

			preparedStatement.execute();
			connection.commit();

		} catch (SQLException | ClassNotFoundException e) {
			
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				
			}
		}
		
	}
	
	public ArrayList<User> GetListOfUsers(){
		ArrayList<User> userList = new ArrayList<User>();
		try {
			connection = DBConnection.getDBConnection();


				preparedStatement = connection
						.prepareStatement("select * from User");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				User user = new User();
				
				user.setId(resultSet.getInt(Utilities.COLUMN_INDEX_ONE));
				user.setFullName(resultSet.getString(Utilities.COLUMN_INDEX_TWO));
				user.setEmail(resultSet.getString(Utilities.COLUMN_INDEX_THREE));
				user.setUserType(resultSet.getString(Utilities.COLUMN_INDEX_FOUR));
				
				userList.add(user);
			}

		} catch (SQLException | ClassNotFoundException e) {
			
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				
			}
		}
		return userList;
	}
}
