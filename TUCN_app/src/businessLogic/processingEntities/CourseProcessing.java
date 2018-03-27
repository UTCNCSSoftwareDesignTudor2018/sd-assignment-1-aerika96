package businessLogic.processingEntities;

import java.util.ArrayList;
import java.util.List;

import dataAccess.DatabaseAccess.CourseDAO;
import dataAccess.DatabaseAccess.DatabaseAccessObject;
import dataAccess.realWorldEntities.Courses;

public class CourseProcessing {

	
	public String [] allCourses() {
		DatabaseAccessObject dao = new CourseDAO();
		List<Courses> courses = ((CourseDAO)dao).selectAll();
		List<String> names = new ArrayList<String>();
		
		for(Courses src: courses) {
			names.add(src.getName());
		}
		return  names.toArray(new String[0]);
	}
	
	public int courseIdByName(String name) {

		DatabaseAccessObject<Courses> cDao = new CourseDAO();
		int cId = cDao.findByName(name).getIdcourses();
		return cId;
		
	}
}
