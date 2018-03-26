package dataAccess.realWorldEntities;

public class Exams {
	
	private int idexams;
	private int grade;
	private int enrollments_id;
	private int enrollments_student;
	private int enrollments_course;
	private int year;
	
	
	public Exams() {
		
	}

	public int getIdexams() {
		return idexams;
	}

	public void setIdexams(int idexams) {
		this.idexams = idexams;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getEnrollments_id() {
		return enrollments_id;
	}

	public void setEnrollments_id(int enrollments_id) {
		this.enrollments_id = enrollments_id;
	}

	public int getEnrollments_student() {
		return enrollments_student;
	}

	public void setEnrollments_student(int enrollments_student) {
		this.enrollments_student = enrollments_student;
	}

	public int getEnrollments_course() {
		return enrollments_course;
	}

	public void setEnrollments_course(int enrollments_course) {
		this.enrollments_course = enrollments_course;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
