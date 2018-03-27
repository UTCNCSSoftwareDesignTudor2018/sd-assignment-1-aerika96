package dataAccess.DatabaseAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import com.mysql.jdbc.Statement;

import dataAccess.realWorldEntities.Exams;

public class ExamDAO extends DatabaseAccessObject<Exams> {
	
	private static final String examInsertStatement = "INSERT INTO exams (grade,enrollments_id,year)"
			+ " VALUES (?,?,?)";
	
	 public void insertExam(int eId, int year) {
		 Connection connection = null;
		 PreparedStatement statement = null;
		 ResultSet result = null;
		 try {
			 connection = ConnectionFactory.getConnection();
			 statement=connection.prepareStatement(examInsertStatement);
			 statement.setInt(1,0);
			 statement.setInt(2,eId);
			 statement.setInt(3,year);
			 statement.executeUpdate(); 
		}catch(SQLException e){
				LOGGER.log(Level.WARNING,"ExamDAO:insertExam"+e.getMessage());
		}finally{
			ConnectionFactory.close(result);
			ConnectionFactory.close((Statement)statement);
			ConnectionFactory.close(connection);
		}
		 
	 }
	 
	 public void updateExam(int nGrade, int id) {
		 Connection connection = null;
		 PreparedStatement statement = null;
		 ResultSet result = null;
		 String update = new SqlQuery("Exams").createUpdateStatement("grade","idexams");
		 try {
			 connection = ConnectionFactory.getConnection();
			 statement=connection.prepareStatement(update);
			 statement.setInt(1,nGrade);
			 statement.setInt(2,id);
			 statement.executeUpdate(); 
		}catch(SQLException e){
				LOGGER.log(Level.WARNING,"ExamDAO:updateGrade"+e.getMessage());
		}finally{
			ConnectionFactory.close(result);
			ConnectionFactory.close((Statement)statement);
			ConnectionFactory.close(connection);
		}
	 }

}
