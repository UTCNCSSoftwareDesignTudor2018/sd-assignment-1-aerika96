package businessLogic.processingEntities;

import java.util.Date;
import java.util.NoSuchElementException;

import dataAccess.DatabaseAccess.CourseDAO;
import dataAccess.DatabaseAccess.DatabaseAccessObject;
import dataAccess.DatabaseAccess.EnrollmentDAO;
import dataAccess.DatabaseAccess.ExamDAO;
import dataAccess.realWorldEntities.Courses;
import dataAccess.realWorldEntities.Enrollments;
import dataAccess.realWorldEntities.Exams;

public class EnrollmentProcessing {
	
	public void enrollToCourse(int sId, String course) {
		DatabaseAccessObject<Courses> cDao =  new CourseDAO();
		DatabaseAccessObject<Enrollments> eDao =  new EnrollmentDAO();
		int cId = cDao.findByName(course).getIdcourses();
		((EnrollmentDAO)eDao).insertEnrollment(sId, cId);
	}
	
	@SuppressWarnings("deprecation")
	public void enrollToExam(int sId, String course) {
		DatabaseAccessObject<Courses> cDao =  new CourseDAO();
		DatabaseAccessObject<Enrollments> eDao =  new EnrollmentDAO();
		DatabaseAccessObject<Exams> exDao =  new ExamDAO();
		int cId = cDao.findByName(course).getIdcourses();
		int eId = ((EnrollmentDAO)eDao).findByStudentAndCourse(sId, cId);
		((ExamDAO)exDao).insertExam(eId,new Date().getYear()+1900);
	}

	public boolean enrollmentFound(int sId, int cId) {

		DatabaseAccessObject<Enrollments> eDao =  new EnrollmentDAO();
		try {
		int eId = ((EnrollmentDAO)eDao).findByStudentAndCourse(sId, cId);
		return true;
		}catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public int enrollmentId(int sId, int cId) {

		DatabaseAccessObject<Enrollments> eDao =  new EnrollmentDAO();
		try {
		int eId = ((EnrollmentDAO)eDao).findByStudentAndCourse(sId, cId);
		return eId;
		}catch (NoSuchElementException e) {
			return -1;
		}
	}
}
