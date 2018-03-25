package businessLogic.processingEntities;

import java.util.ArrayList;
import java.util.List;

import businessLogic.Validators.NameValidator;
import businessLogic.Validators.Validator;
import dataAccess.DatabaseAccess.CourseDAO;
import dataAccess.DatabaseAccess.DatabaseAccessObject;
import dataAccess.realWorldEntities.Courses;

public class TeacherProcessing {
	
	public void addNewCourse(String name, int id) {
		DatabaseAccessObject<Courses> dao = new CourseDAO();
		Validator<String> nameValidator= new NameValidator();
		if(!nameValidator.validate(name)) {
			throw new IllegalArgumentException("Course name not of the correct form");
		}
		((CourseDAO)dao).insertCourse(name, id);		
	}
	
	public List<String> allCourses(int id){
		DatabaseAccessObject<Courses> dao = new CourseDAO();
		List<Courses> crs= new ArrayList<>(dao.findAllBySpecificId("teachers_idteacher", id));
		List<String> crsNames = new ArrayList<>();
		int length = crs.size();
		for(Courses src:crs) {
			crsNames.add(src.getName());
		}
		return crsNames;
	}

}
