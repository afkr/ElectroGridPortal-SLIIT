package com.oop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.oop.model.Notice;
import com.oop.model.Ticket;
import com.oop.model.Ticket;
import com.oop.utils.DBConnection;
import com.oop.utils.Utilities;

public class TicketRepository {
	private static Connection connection;

	private static Statement statement;

	private PreparedStatement preparedStatement;
	
	public void CreateTicket(Ticket ticket) {
		try {
			connection = DBConnection.getDBConnection();
			/*
			 * Query is available in EmployeeQuery.xml file and use
			 * insert_employee key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement("insert into Ticket (TicketDescription, TicketStatus, SubmittedBy) values (?, ?, ?)");
			connection.setAutoCommit(false);
			
			preparedStatement.setString(Utilities.COLUMN_INDEX_ONE, ticket.getTicketDescription());
			preparedStatement.setString(Utilities.COLUMN_INDEX_TWO, Utilities.TicketStatus.pending.toString());
			preparedStatement.setInt(Utilities.COLUMN_INDEX_THREE, ticket.getSubmittedBy());

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
	
	
	public void EditTicket(Ticket ticket) {
		
		try {
			connection = DBConnection.getDBConnection();
			/*
			 * Query is available in EmployeeQuery.xml file and use
			 * insert_employee key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement("update Ticket as t set t.TicketSolution = ?, t.TicketStatus = ? where t.Id = ?");
			connection.setAutoCommit(false);
			
			preparedStatement.setString(Utilities.COLUMN_INDEX_ONE, ticket.getTicketSolution());
			preparedStatement.setString(Utilities.COLUMN_INDEX_TWO, ticket.getTicketStatus());
			preparedStatement.setInt(Utilities.COLUMN_INDEX_THREE, ticket.getId());

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
	public void DeleteTicket(int id) {
		try {
			connection = DBConnection.getDBConnection();
			/*
			 * Query is available in EmployeeQuery.xml file and use
			 * insert_employee key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement("delete from Ticket where Ticket.Id = ?");
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
	
	public ArrayList<Ticket> GetListOfSubmittedTickets(int userId){
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		try {
			connection = DBConnection.getDBConnection();


				preparedStatement = connection
						.prepareStatement("select * from Ticket where SubmittedBy=?");
				preparedStatement.setInt(Utilities.COLUMN_INDEX_ONE, userId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Ticket ticket = new Ticket();
				ticket.setId(resultSet.getInt(Utilities.COLUMN_INDEX_ONE));
				ticket.setTicketDescription(resultSet.getString(Utilities.COLUMN_INDEX_TWO));
				ticket.setTicketSolution(resultSet.getString(Utilities.COLUMN_INDEX_THREE));
				ticket.setTicketStatus(resultSet.getString(Utilities.COLUMN_INDEX_FOUR));

				ticketList.add(ticket);
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
		return ticketList;
	}
	
	public ArrayList<Ticket> GetListOfTickets(){
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		try {
			connection = DBConnection.getDBConnection();


				preparedStatement = connection
						.prepareStatement("select * from Ticket");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Ticket ticket = new Ticket();
				ticket.setId(resultSet.getInt(Utilities.COLUMN_INDEX_ONE));
				ticket.setTicketDescription(resultSet.getString(Utilities.COLUMN_INDEX_TWO));
				ticket.setTicketSolution(resultSet.getString(Utilities.COLUMN_INDEX_THREE));
				ticket.setTicketStatus(resultSet.getString(Utilities.COLUMN_INDEX_FOUR));

				ticketList.add(ticket);
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
		return ticketList;
	}
}
