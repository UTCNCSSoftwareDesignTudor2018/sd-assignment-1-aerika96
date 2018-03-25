package dataAccess.DatabaseAccess;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;

public class DatabaseAccessObject<T> {
	
	protected static final Logger LOGGER = Logger.getLogger(DatabaseAccessObject.class.getName());
	
	public final Class<T> type;
	
	@SuppressWarnings("unchecked")
	
	
	public DatabaseAccessObject(){
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];	
	}
	
	
	public Class<T> getType() {
		return type;
	}
		
	public List<T> createObjects(ResultSet resultSet){
		List<T> list= new ArrayList<T>();
		try{
			while(resultSet.next()){
				T instance = type.newInstance();
				for(Field field: type.getDeclaredFields()){
					Object value = resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(),type);
					Method method= propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		}catch(InstantiationException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(IllegalAccessException e){
			e.printStackTrace();
		} catch (java.beans.IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * creating a list of all the objects - executing the selectAll query
	 * creating connection
	 * statement - prepared query
	 * resultSet- result after executing the query
	 * @return
	 */
	public List<T> findALL(){
		Connection connection=null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = new SqlQuery(type.getSimpleName()).createSelectAll();
		try{
			connection= ConnectionFactory.getConnection();
			statement= connection.prepareStatement(query);
			resultSet=statement.executeQuery();
			return createObjects(resultSet);
		}catch(SQLException e){
			LOGGER.log(Level.WARNING,type.getName() + "DAO:findAll "+e.getMessage());
		}finally{
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close((Statement)statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	
	public T findById(int id){
		Connection connection=null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = new SqlQuery(type.getSimpleName()).createSelectStatement("id");
		try{
			connection= ConnectionFactory.getConnection();
			statement= connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet=statement.executeQuery();
			List<T> result= new ArrayList<T>(createObjects(resultSet));
			if(result.isEmpty()){
				throw new NoSuchElementException("No elements found with the id "+id);
			}
			return result.get(0);
		}catch(SQLException e){
			LOGGER.log(Level.WARNING,type.getName() + "DAO:findById "+e.getMessage());
		}finally{
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close((Statement)statement);
			ConnectionFactory.close(connection);
		}
		return null;
	
	}
	
	
	public List<T> findAllBySpecificId(String idName, int id){
		Connection connection=null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query =  new SqlQuery(type.getSimpleName()).createSelectStatement(idName);
		try{
			connection= ConnectionFactory.getConnection();
			statement= connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet=statement.executeQuery();
			List<T> result = new ArrayList<T>(createObjects(resultSet));
			if(result.isEmpty()){
				throw new NoSuchElementException("No elements found with the "+idName+" "+id);
			}
			return result;
		}catch(SQLException e){
			LOGGER.log(Level.WARNING,type.getName() + "DAO:findBySpecificId "+e.getMessage());
		}finally{
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close((Statement)statement);
			ConnectionFactory.close(connection);
		}
		return null;
		
	}
	 
	
	
	public T findByName(String name){
		Connection connection=null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query =  new SqlQuery(type.getSimpleName()).createSelectStatement("name");
		try{
			connection= ConnectionFactory.getConnection();
			statement= connection.prepareStatement(query);
			statement.setString(1, name);
			resultSet=statement.executeQuery();
			List<T> result = new ArrayList<T>(createObjects(resultSet));
			if(result.isEmpty()){
				throw new NoSuchElementException("No elements found with the name "+name);
			}
			return result.get(0);
		}catch(SQLException e){
			LOGGER.log(Level.WARNING,type.getName() + "DAO:findById "+e.getMessage());
		}finally{
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close((Statement)statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	
	
	public void deleteById(int id){
		 Connection connection=null;
		 PreparedStatement statement=null;
		 ResultSet result=null;
		 String delete = new SqlQuery(type.getSimpleName()).createDeleteStatement("id");
		 try{
			 connection= ConnectionFactory.getConnection();
				statement= connection.prepareStatement(delete);
				statement.setInt(1, id);
				statement.executeUpdate();
		 }catch(SQLException e){
				LOGGER.log(Level.WARNING,type.getName() + "DAO:deleteById "+e.getMessage());
		}finally{
			ConnectionFactory.close(result);
			ConnectionFactory.close((Statement)statement);
			ConnectionFactory.close(connection);
		} 
	 }
	 
	 
	 
	

}
