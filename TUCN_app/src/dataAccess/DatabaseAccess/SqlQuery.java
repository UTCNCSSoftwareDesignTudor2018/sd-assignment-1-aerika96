package dataAccess.DatabaseAccess;

public class SqlQuery {
	
	private String table;
	//private String field;
	
	public SqlQuery(String nTable) {
		table = nTable;
		//field = nField;
	}

	public String createSelectAll(){
		StringBuilder query = new StringBuilder();
		query.append("SELECT ");
		query.append(" * ");
		query.append(" FROM ");
		query.append(table);

		return query.toString();
	}
	
	
	public String createSelectStatement(String field){
		StringBuilder query = new StringBuilder();
		query.append("SELECT ");
		query.append(" * ");
		query.append(" FROM ");
		query.append(table);
		query.append(" WHERE " + field+ " =?");
		
		return query.toString();
	}
	
	public String createDoubleSelectStatement(String field1, String field2){
		StringBuilder query = new StringBuilder();
		query.append("SELECT ");
		query.append(" * ");
		query.append(" FROM ");
		query.append(table);
		query.append(" WHERE " + field1+ " =?");
		query.append(" AND "+field2+" =?");
		
		return query.toString();
	}
	
	public String createDeleteStatement(String field){
		StringBuilder delete = new StringBuilder();
		delete.append("DELETE");
		delete.append(" FROM ");
		delete.append(table);
		delete.append(" WHERE " + field + " =?");
		
		return delete.toString();
	}
	
	
	public String createUpdateStatement(String field){
		StringBuilder update = new StringBuilder();
		update.append("UPDATE ");
		update.append(table);
		update.append(" SET "+ field +" = ?");
		update.append(" WHERE id = ?");
		return update.toString();
	}
	
	
}
