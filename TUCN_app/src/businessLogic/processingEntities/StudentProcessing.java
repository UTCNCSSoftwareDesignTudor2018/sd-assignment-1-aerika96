package businessLogic.processingEntities;

import businessLogic.Validators.GroupValidator;
import businessLogic.Validators.IdValidator;
import businessLogic.Validators.Validator;
import dataAccess.DatabaseAccess.DatabaseAccessObject;
import dataAccess.DatabaseAccess.StudentDAO;
import dataAccess.realWorldEntities.Students;

public class StudentProcessing {
	DatabaseAccessObject<Students> dao = new StudentDAO();
	
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
