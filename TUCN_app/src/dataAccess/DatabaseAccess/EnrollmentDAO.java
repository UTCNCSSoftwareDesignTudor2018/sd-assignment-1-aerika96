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

import dataAccess.realWorldEntities.Enrollments;
import dataAccess.realWorldEntities.Students;

public class EnrollmentDAO extends DatabaseAccessObject<Enrollments> {

	private static final String enrollmentInsertStatement = "INSERT INTO enrollments (students_idstudent, courses_idcourses)"
			+ " VALUES (?,?)";
	
	 public void insertEnrollment(int sId, int cId) {
		 Connection connection = null;
		 PreparedStatement statement = null;
		 ResultSet result = null;
		 try {
			 connection = ConnectionFactory.getConnection();
			 statement=connection.prepareStatement(enrollmentInsertStatement);
			 statement.setInt(1,sId);
			 statement.setInt(2,cId);
			 statement.executeUpdate(); 
		}catch(SQLException e){
				LOGGER.log(Level.WARNING,"EnrollmentDAO:insertEnrollment"+e.getMessage());
		}finally{
			ConnectionFactory.close(result);
			ConnectionFactory.close((Statement)statement);
			ConnectionFactory.close(connection);
		}
		 
	 }
	 
	 public int findByStudentAndCourse(int sId, int cId) {
		 Connection connection=null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			String query = new SqlQuery(type.getSimpleName()).createDoubleSelectStatement("students_idstudent","courses_idcourses");
			try{
				connection= ConnectionFactory.getConnection();
				statement= connection.prepareStatement(query);
				statement.setInt(1, sId);
				statement.setInt(2, cId);
				resultSet=statement.executeQuery();
				List<Enrollments> result= new ArrayList<Enrollments>(createObjects(resultSet));
				if(result.isEmpty()){
					throw new NoSuchElementException("No elements found with the student id and course id ");
				}
				return result.get(0).getIdenrollments();
			}catch(SQLException e){
				LOGGER.log(Level.WARNING,"EnrollmentsDAO:findByStudentAndCourse "+e.getMessage());
			}finally{
				ConnectionFactory.close(resultSet);
				ConnectionFactory.close((Statement)statement);
				ConnectionFactory.close(connection);
			}
			return -1;

	 }
	
}
