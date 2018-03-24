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

import dataAccess.realWorldEntities.Teachers;

public class TeacherDAO extends DatabaseAccessObject<Teachers> {

	
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
			List<Teachers> result= new ArrayList<Teachers>(createObjects(resultSet));
			if(result.isEmpty()){
				throw new NoSuchElementException("No elements found with the user ID "+ uId);
			}
			return result.get(0).getIdteacher();
		}catch(SQLException e){
			LOGGER.log(Level.WARNING,"StudentDAO:findByUserId "+e.getMessage());
		}finally{
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close((Statement)statement);
			ConnectionFactory.close(connection);
		}
		return -1;
	}
}
