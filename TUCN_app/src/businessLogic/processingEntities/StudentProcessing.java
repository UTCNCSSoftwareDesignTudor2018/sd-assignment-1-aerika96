package businessLogic.processingEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import businessLogic.Validators.GroupValidator;
import businessLogic.Validators.IdValidator;
import businessLogic.Validators.Validator;
import dataAccess.DatabaseAccess.CourseDAO;
import dataAccess.DatabaseAccess.DatabaseAccessObject;
import dataAccess.DatabaseAccess.EnrollmentDAO;
import dataAccess.DatabaseAccess.ExamDAO;
import dataAccess.DatabaseAccess.StudentDAO;
import dataAccess.realWorldEntities.Courses;
import dataAccess.realWorldEntities.Enrollments;
import dataAccess.realWorldEntities.Exams;
import dataAccess.realWorldEntities.Students;

public class StudentProcessing {
	DatabaseAccessObject<Students> dao = new StudentDAO();
	
	public String getGroup(int id) {
		try {
			return ""+dao.findBySpecificId("idstudent",id).getGroup_()+"";
			}catch(NoSuchElementException e) {
				return "Set group";
			}catch(NullPointerException e) {
				return "Set group";
			}
	}
	
	public String getStudentId(int id) {
		try {
			return ""+dao.findBySpecificId("idstudent",id).getStudentid()+"";
			}catch(NoSuchElementException e) {
				return "Set group";
			}catch(NullPointerException e) {
				return "Set group";
			}
	}
	
	public Students getByStudentId(String studentId) throws NoSuchElementException {

		DatabaseAccessObject<Students> dao = new StudentDAO();
		return ((StudentDAO)dao).findByStudentId(studentId);
		
	}
	
	public Object [][] getAllEnrolled(int id) {
		DatabaseAccessObject<Enrollments> enDao = new EnrollmentDAO();
		DatabaseAccessObject<Courses> crsDao = new CourseDAO();
		DatabaseAccessObject<Exams> exDao = new ExamDAO(); 
		List<Enrollments> enrolls = new ArrayList<>(enDao.findAllBySpecificId("students_idstudent", id));
		List<Integer> enrId= new ArrayList<>();
		List<Integer> cId = new ArrayList<>();
		List<String> courses = new ArrayList<>();
		List<String> status = new ArrayList<>();
		List<String> grading = new ArrayList<>();
		for(Enrollments src: enrolls) {
			enrId.add(src.getIdenrollments());
			cId.add(src.getCourses_idcourses());
		}
		for(Integer src: cId) {
			courses.add(((CourseDAO)crsDao).findBySpecificId("idcourses", src).getName());
		}
		for(Integer src: enrId) {
			try {

				if((exDao.findBySpecificId("enrollments_id", src)).getGrade()==0) {
					grading.add("Not graded");
				}
				else {
					grading.add(""+(exDao.findBySpecificId("enrollments_id", src)).getGrade());
				}
				status.add("Exam Taken");
			}catch(NoSuchElementException e) {
				status.add("Exam Not Taken");
				grading.add("Not graded");
			}
		}
		Object[][] result = new Object[courses.size()][3];
		for(int i=0; i<courses.size(); i++) {
			Object[] line= new Object[] {courses.get(i),status.get(i),grading.get(i)};
			result[i]=line;
			
		}
		return result;
		
		
	}
	
	public void changeStudentId(String sId, int id) {
		Validator<String> idValidator = new IdValidator();
		if(!idValidator.validate(sId)) {
			throw new IllegalArgumentException("Student id not of the correct form");
		}
		((StudentDAO)dao).updateStudId(sId, id);
	}
	
	public void changeGroup(int group, int id) {
		Validator<Integer> idValidator = new GroupValidator();
		if(!idValidator.validate(group)) {
			throw new IllegalArgumentException("Group not of the correct form");
		}
		((StudentDAO)dao).updateGroup(group, id);
	}
	

}
