package com.oop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.oop.model.Notice;
import com.oop.model.User;
import com.oop.model.Notice;
import com.oop.utils.DBConnection;
import com.oop.utils.Utilities;

public class NoticeRepository {
	private static Connection connection;

	private static Statement statement;

	private PreparedStatement preparedStatement;
	
	public void CreateNotice(Notice notice) {
		try {
			connection = DBConnection.getDBConnection();
			/*
			 * Query is available in EmployeeQuery.xml file and use
			 * insert_employee key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement("insert into Notice (NoticeDescription) values (?)");
			connection.setAutoCommit(false);
			
			preparedStatement.setString(Utilities.COLUMN_INDEX_ONE, notice.getNoticeDescription());

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
	
	
	public void EditNotice(Notice notice) {
		
		try {
			connection = DBConnection.getDBConnection();
			/*
			 * Query is available in EmployeeQuery.xml file and use
			 * insert_employee key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement("update Notice as n set n.NoticeDescription = ? where n.Id = ?");
			connection.setAutoCommit(false);
			
			preparedStatement.setString(Utilities.COLUMN_INDEX_ONE, notice.getNoticeDescription());
			preparedStatement.setInt(Utilities.COLUMN_INDEX_TWO, notice.getId());

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
	public void DeleteNotice(int id) {
		try {
			connection = DBConnection.getDBConnection();
			/*
			 * Query is available in EmployeeQuery.xml file and use
			 * insert_employee key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement("delete from Notice where Notice.Id = ?");
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
	
	public ArrayList<Notice> GetListOfNotices(){
		ArrayList<Notice> noticeList = new ArrayList<Notice>();
		try {
			connection = DBConnection.getDBConnection();


				preparedStatement = connection
						.prepareStatement("select * from Notice");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Notice notice = new Notice();
				notice.setId(resultSet.getInt(Utilities.COLUMN_INDEX_ONE));
				notice.setNoticeDescription(resultSet.getString(Utilities.COLUMN_INDEX_TWO));

				noticeList.add(notice);
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
		return noticeList;
	}

}
