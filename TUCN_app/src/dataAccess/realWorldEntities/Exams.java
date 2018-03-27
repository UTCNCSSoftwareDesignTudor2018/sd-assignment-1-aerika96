package dataAccess.realWorldEntities;

public class Exams {
	
	private int idexams;
	private int grade;
	private int enrollments_id;
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

	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
