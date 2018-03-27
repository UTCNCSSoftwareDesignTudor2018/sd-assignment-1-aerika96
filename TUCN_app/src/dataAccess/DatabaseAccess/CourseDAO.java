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

import dataAccess.realWorldEntities.Courses;
import dataAccess.realWorldEntities.Teachers;

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
	 
	 public List<Courses> selectAll() {
		 	Connection connection=null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			String query = new SqlQuery("Courses").createSelectAll();
			try{
				connection= ConnectionFactory.getConnection();
				statement= connection.prepareStatement(query);
				resultSet=statement.executeQuery();
				List<Courses> result= new ArrayList<Courses>(createObjects(resultSet));
				if(result.isEmpty()){
					throw new NoSuchElementException("No elements found");
				}
				return result;
			}catch(SQLException e){
				LOGGER.log(Level.WARNING,"StudentDAO:findByUserId "+e.getMessage());
				return null;
			}finally{
				ConnectionFactory.close(resultSet);
				ConnectionFactory.close((Statement)statement);
				ConnectionFactory.close(connection);
			}
		
	 }
	 

}
