package businessLogic.processingEntities;

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
	
	public void enrollToExam(int sId, String course) {
		DatabaseAccessObject<Courses> cDao =  new CourseDAO();
		DatabaseAccessObject<Enrollments> eDao =  new EnrollmentDAO();
		DatabaseAccessObject<Exams> exDao =  new ExamDAO();
		int cId = cDao.findByName(course).getIdcourses();
		int eId = ((EnrollmentDAO)eDao).findByStudentAndCourse(sId, cId);
		((ExamDAO)exDao).insertExam(eId,sId, cId);
	}

}
