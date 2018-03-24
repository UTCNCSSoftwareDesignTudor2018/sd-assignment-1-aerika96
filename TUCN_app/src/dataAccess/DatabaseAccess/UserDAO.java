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

import dataAccess.realWorldEntities.Users;

public class UserDAO extends DatabaseAccessObject<Users>{
	
	public Users findByUsername(String username) {
		Connection connection=null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = new SqlQuery(type.getSimpleName()).createSelectStatement("username");
		try{
			connection= ConnectionFactory.getConnection();
			statement= connection.prepareStatement(query);
			statement.setString(1, username);
			resultSet=statement.executeQuery();
			List<Users> result= new ArrayList<Users>(createObjects(resultSet));
			if(result.isEmpty()){
				throw new NoSuchElementException("No elements found with the username "+ username);
			}
			return result.get(0);
		}catch(SQLException e){
			LOGGER.log(Level.WARNING,"UserDAO:findByUsername "+e.getMessage());
		}finally{
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close((Statement)statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	
	 public void updateName(String name, int id){
			Connection connection=null;
			PreparedStatement statement=null;
			ResultSet result= null;
			String update=new SqlQuery("Users").createUpdateStatement("name");
			try{
				connection=ConnectionFactory.getConnection();
				statement=connection.prepareStatement(update);
				statement.setString(1, name);
				statement.setInt(2, id);
				statement.executeUpdate();
			}catch(SQLException e){
				LOGGER.log(Level.WARNING,"UsersDAO:updateName "+e.getMessage());
			}finally{
			ConnectionFactory.close(result);
			ConnectionFactory.close((Statement)statement);
			ConnectionFactory.close(connection);
			}
	}
	 
	 public void updateIdNum(String nId, int id) {
		 	Connection connection=null;
			PreparedStatement statement=null;
			ResultSet result= null;
			String update=new SqlQuery("Users").createUpdateStatement("idnumber");
			try{
				connection=ConnectionFactory.getConnection();
				statement=connection.prepareStatement(update);
				statement.setString(1, nId);
				statement.setInt(2, id);
				statement.executeUpdate();
			}catch(SQLException e){
				LOGGER.log(Level.WARNING,"UsersDAO:updateIdNum "+e.getMessage());
			}finally{
			ConnectionFactory.close(result);
			ConnectionFactory.close((Statement)statement);
			ConnectionFactory.close(connection);
			}
	 }
	
	 public void updateCnp(String nCnp, int id){
		 Connection connection = null;
		 PreparedStatement statement = null;
		 ResultSet result = null;
		 String update = new SqlQuery("Users").createUpdateStatement("cnp");
		 try {
			 connection = ConnectionFactory.getConnection();
			 statement=connection.prepareStatement(update);
			 statement.setString(1,nCnp);
			 statement.setInt(2,id);
			 statement.executeUpdate(); 
		}catch(SQLException e){
				LOGGER.log(Level.WARNING,"UsersDAO:updateCnp"+e.getMessage());
		}finally{
			ConnectionFactory.close(result);
			ConnectionFactory.close((Statement)statement);
			ConnectionFactory.close(connection);
		}
	 }
	 
	 public void updateAddress(String nAddress, int id) {
		 Connection connection = null;
		 PreparedStatement statement = null;
		 ResultSet result = null;
		 String update = new SqlQuery("Users").createUpdateStatement("address");
		 try {
			 connection = ConnectionFactory.getConnection();
			 statement=connection.prepareStatement(update);
			 statement.setString(1,nAddress);
			 statement.setInt(2,id);
			 statement.executeUpdate(); 
		}catch(SQLException e){
				LOGGER.log(Level.WARNING,"UsersDAO:updateAddress"+e.getMessage());
		}finally{
			ConnectionFactory.close(result);
			ConnectionFactory.close((Statement)statement);
			ConnectionFactory.close(connection);
		}
		 
	 }
	

}
