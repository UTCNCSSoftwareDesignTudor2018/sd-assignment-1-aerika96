package dataAccess.realWorldEntities;

public class Courses {

	private int idcourses;
	private String name;
	private int teachers_idteachers;
	
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

	public int getTeachers_idteachers() {
		return teachers_idteachers;
	}

	public void setTeachers_idteachers(int teachers_idteachers) {
		this.teachers_idteachers = teachers_idteachers;
	}
	
	
}
