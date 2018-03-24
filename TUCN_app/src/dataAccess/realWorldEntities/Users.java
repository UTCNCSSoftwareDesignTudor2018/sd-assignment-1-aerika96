package dataAccess.realWorldEntities;

public class Users {
	private int iduser;
	private String username;
	private String password;
	private String name;
	private String idnumber;
	private String cnp;
	private String address;
	
	public Users() {
		username = new String("");
		password = new String("");
		name = new String("");
		idnumber =  new String("");
		cnp = new String("");
		address =  new String("");
	}

	public Users(String nUsername, String nPassword) {
		this.username =  nUsername;
		this.password = nPassword;
	}
	/*public Users() {
		
	}*/

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
