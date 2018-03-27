package presentation;

import java.lang.reflect.ParameterizedType;
import java.util.Date;

public class Main {
	
	public static void main(String[] args) {
		StudentController studCont = new StudentController();
		TeacherController teachCont = new TeacherController();
		Controller cont= new Controller(studCont,teachCont);
	}

}
