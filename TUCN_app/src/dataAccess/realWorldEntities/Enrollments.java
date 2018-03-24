package dataAccess.realWorldEntities;

public class Enrollments {

	private int idenrollments;
	private int students_idstudent;
	private int courses_idcourses;
	
	public Enrollments() {
		
	}

	public int getIdenrollments() {
		return idenrollments;
	}

	public void setIdenrollments(int idenrollments) {
		this.idenrollments = idenrollments;
	}

	public int getStudents_idstudent() {
		return students_idstudent;
	}

	public void setStudents_idstudent(int students_idstudent) {
		this.students_idstudent = students_idstudent;
	}

	public int getCourses_idcourses() {
		return courses_idcourses;
	}

	public void setCourses_idcourses(int courses_idcourses) {
		this.courses_idcourses = courses_idcourses;
	}
}
