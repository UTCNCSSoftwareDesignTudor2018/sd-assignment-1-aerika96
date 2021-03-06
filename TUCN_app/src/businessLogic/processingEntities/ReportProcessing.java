package businessLogic.processingEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import dataAccess.DatabaseAccess.CourseDAO;
import dataAccess.DatabaseAccess.DatabaseAccessObject;
import dataAccess.DatabaseAccess.EnrollmentDAO;
import dataAccess.DatabaseAccess.ExamDAO;
import dataAccess.DatabaseAccess.StudentDAO;
import dataAccess.DatabaseAccess.UserDAO;
import dataAccess.realWorldEntities.Courses;
import dataAccess.realWorldEntities.Enrollments;
import dataAccess.realWorldEntities.Exams;
import dataAccess.realWorldEntities.Students;
import dataAccess.realWorldEntities.Users;

public class ReportProcessing {

	public List<String> namesForGrading(String courseName) {
		DatabaseAccessObject<Courses> cDao = new CourseDAO();
		DatabaseAccessObject<Enrollments> eDao = new EnrollmentDAO();
		DatabaseAccessObject<Students> sDao = new StudentDAO();
		DatabaseAccessObject<Users> uDao = new UserDAO();
		
		int cId = cDao.findByName(courseName).getIdcourses();
		List<Enrollments> enrollm =  new ArrayList<>(eDao.findAllBySpecificId("courses_idcourses", cId));
		List<Integer> studentIds = new ArrayList<>();
		for(Enrollments src:enrollm) {
			studentIds.add(src.getStudents_idstudent());
		}
		
		List<Integer> userIds = new ArrayList<>();
		for (Integer src: studentIds) {
			userIds.add(sDao.findBySpecificId("idstudent",src).getUser_iduser());
		}
		
		List<String> users = new ArrayList<>();
		for(Integer src:userIds) {
			users.add(uDao.findBySpecificId("iduser",src).getName());
		}
		return users;
	}
	
	public List<String> studentIdsForGrading(String courseName){
		DatabaseAccessObject<Courses> cDao = new CourseDAO();
		DatabaseAccessObject<Enrollments> eDao = new EnrollmentDAO();
		DatabaseAccessObject<Students> sDao = new StudentDAO();
		DatabaseAccessObject<Users> uDao = new UserDAO();
		
		int cId = cDao.findByName(courseName).getIdcourses();
		List<Enrollments> enrollm =  new ArrayList<>(eDao.findAllBySpecificId("courses_idcourses", cId));
		List<Integer> studentIds = new ArrayList<>();
		for(Enrollments src:enrollm) {
			studentIds.add(src.getStudents_idstudent());
		}
		
		List<String> sIds = new ArrayList<>();
		for (Integer src: studentIds) {
			sIds.add(sDao.findBySpecificId("idstudent",src).getStudentid());
		}
		
		return sIds;
	}
	
	public void giveGrades(String courseName, String studentId, int grade) throws NoSuchElementException {
		DatabaseAccessObject<Students> sDao = new StudentDAO();
		DatabaseAccessObject<Courses> cDao = new CourseDAO();
		DatabaseAccessObject<Enrollments> eDao =  new EnrollmentDAO(); 
		DatabaseAccessObject<Exams> exDao = new ExamDAO();
		
		
		int sId = (((StudentDAO)sDao).findByStudentId(studentId)).getIdstudent();
		int cId = cDao.findByName(courseName).getIdcourses();
		int eId = ((EnrollmentDAO)eDao).findByStudentAndCourse(sId, cId);
		Exams exam = exDao.findAllBySpecificId("enrollments_id", eId).get(0);
		if(exam.getGrade()==0) {
			((ExamDAO)exDao).updateExam(grade, exam.getIdexams());
			
		}
		else {
			throw new IllegalArgumentException();
		}
		
		
		
	}
}

