package dataAccess.realWorldEntities;

public class Courses {

	private int idcourses;
	private String name;
	private int teachers_idteacher;
	
	public Courses(){
		
	}

	public int getIdcourses() {
		return idcourses;
	}

	public void setIdcourses(int idcourses) {
		this.idcourses = idcourses;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTeachers_idteacher() {
		return teachers_idteacher;
	}

	public void setTeachers_idteacher(int teachers_idteachers) {
		this.teachers_idteacher = teachers_idteachers;
	}
	
	
}
