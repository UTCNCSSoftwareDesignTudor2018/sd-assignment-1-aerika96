package dataAccess.DatabaseAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;

import com.mysql.jdbc.Statement;

import dataAccess.realWorldEntities.Students;

public class StudentDAO  extends DatabaseAccessObject<Students>{
	
	
	public int findByUserId(int uId) {
		Connection connection=null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = new SqlQuery(type.getSimpleName()).createSelectStatement("user_iduser");
		try{
			connection= ConnectionFactory.getConnection();
			statement= connection.prepareStatement(query);
			statement.setInt(1, uId);
			resultSet=statement.executeQuery();
			List<Students> result= new ArrayList<Students>(createObjects(resultSet));
			if(result.isEmpty()){
				throw new NoSuchElementException("No elements found with the user ID "+ uId);
			}
			System.out.println(result.get(0).getIdstudent());
			return result.get(0).getIdstudent();
		}catch(SQLException e){
			LOGGER.log(Level.WARNING,"StudentDAO:findByUserId "+e.getMessage());
			return -1;
		}finally{
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close((Statement)statement);
			ConnectionFactory.close(connection);
		}
	}
	
	public Students findByStudentId(String studentId) {
		Connection connection=null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = new SqlQuery(type.getSimpleName()).createSelectStatement("studentid");
		try{
			connection= ConnectionFactory.getConnection();
			statement= connection.prepareStatement(query);
			statement.setString(1, studentId);
			resultSet=statement.executeQuery();
			List<Students> result= new ArrayList<Students>(createObjects(resultSet));
			if(result.isEmpty()){
				throw new NoSuchElementException("No elements found with the student ID "+ studentId);
			}
			return result.get(0);
		}catch(SQLException e){
			LOGGER.log(Level.WARNING,"StudentDAO:findByStudentId "+e.getMessage());
			return null;
		}finally{
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close((Statement)statement);
			ConnectionFactory.close(connection);
		}
	}
	
	

	 public void updateStudId(String nSId, int id) {
		 Connection connection = null;
		 PreparedStatement statement = null;
		 ResultSet result = null;
		 String update = new SqlQuery("Students").createUpdateStatement("studentid","idstudent");
		 try {
			 connection = ConnectionFactory.getConnection();
			 statement=connection.prepareStatement(update);
			 statement.setString(1,nSId);
			 statement.setInt(2,id);
			 statement.executeUpdate(); 
		}catch(SQLException e){
				LOGGER.log(Level.WARNING,"StudentDAO:updateStudId"+e.getMessage());
		}finally{
			ConnectionFactory.close(result);
			ConnectionFactory.close((Statement)statement);
			ConnectionFactory.close(connection);
		}
		 
	 }
	 
	 public void updateGroup(int nGroup, int id) {
		 Connection connection = null;
		 PreparedStatement statement = null;
		 ResultSet result = null;
		 String update = new SqlQuery("Students").createUpdateStatement("group_","idstudent");
		 try {
			 connection = ConnectionFactory.getConnection();
			 statement=connection.prepareStatement(update);
			 statement.setInt(1,nGroup);
			 statement.setInt(2,id);
			 statement.executeUpdate(); 
		}catch(SQLException e){
				LOGGER.log(Level.WARNING,"StudentDAO:updateGroup"+e.getMessage());
		}finally{
			ConnectionFactory.close(result);
			ConnectionFactory.close((Statement)statement);
			ConnectionFactory.close(connection);
		}
		 
	 }
}
