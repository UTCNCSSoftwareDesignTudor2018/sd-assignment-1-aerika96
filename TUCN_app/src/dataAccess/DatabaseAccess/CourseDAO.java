package dataAccess.DatabaseAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import com.mysql.jdbc.Statement;

import dataAccess.realWorldEntities.Courses;

public class CourseDAO extends DatabaseAccessObject<Courses> {
	private static final String courseInsertStatement = "INSERT INTO courses (name,teachers_idteacher)"
			+ " VALUES (?,?)";
	
	 public void insertCourse(String name, int id) {
		 Connection connection = null;
		 PreparedStatement statement = null;
		 ResultSet result = null;
		 try {
			 connection = ConnectionFactory.getConnection();
			 statement=connection.prepareStatement(courseInsertStatement);
			 statement.setString(1,name);
			 statement.setInt(2,id);
			 statement.executeUpdate(); 
		}catch(SQLException e){
				LOGGER.log(Level.WARNING,"CourseDAO:insertCourse"+e.getMessage());
		}finally{
			ConnectionFactory.close(result);
			ConnectionFactory.close((Statement)statement);
			ConnectionFactory.close(connection);
		}
		 
	 }

}
