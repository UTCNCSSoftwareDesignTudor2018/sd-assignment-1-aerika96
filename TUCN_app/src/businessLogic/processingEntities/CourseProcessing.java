package businessLogic.processingEntities;

import java.util.ArrayList;
import java.util.List;

import businessLogic.Validators.NameValidator;
import businessLogic.Validators.Validator;
import dataAccess.DatabaseAccess.CourseDAO;
import dataAccess.DatabaseAccess.DatabaseAccessObject;
import dataAccess.realWorldEntities.Courses;

public class CourseProcessing {

	
	public String [] allCourses() {
		DatabaseAccessObject<Courses> dao = new CourseDAO();
		List<Courses> courses = ((CourseDAO)dao).selectAll();
		List<String> names = new ArrayList<String>();
		
		for(Courses src: courses) {
			names.add(src.getName());
		}
		return  names.toArray(new String[0]);
	}
	
	public Object[][] allCoursesByTeacher(int teacherId) {
		DatabaseAccessObject<Courses> dao = new CourseDAO();
		List<Courses> courses = ((CourseDAO)dao).findAllBySpecificId("teachers_idteacher", teacherId);
		//List<String> names = new ArrayList<String>();
		Object[][]result = new Object[courses.size()][1];
		
		for(int i=0; i<courses.size(); i++) {
			result[i][0]= courses.get(i).getName();
			
		}
		return result; 
		
	}
	
	public int courseIdByName(String name) {

		DatabaseAccessObject<Courses> cDao = new CourseDAO();
		int cId = cDao.findByName(name).getIdcourses();
		return cId;
		
	}
	
	public void addNewCourse(String name, int teacherId){
		Validator<String> courseValidator= new NameValidator();
		DatabaseAccessObject<Courses> courseDao= new CourseDAO();
		if(courseValidator.validate(name)) {
			((CourseDAO)courseDao).insertCourse(name, teacherId);
		}
		else {
			throw new IllegalArgumentException();
		}
		
	}
}
