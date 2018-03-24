package businessLogic.processingEntities;

import java.util.NoSuchElementException;

import businessLogic.Validators.AddressValidator;
import businessLogic.Validators.CNPValidator;
import businessLogic.Validators.IdValidator;
import businessLogic.Validators.NameValidator;
import businessLogic.Validators.Validator;
import dataAccess.DatabaseAccess.DatabaseAccessObject;
import dataAccess.DatabaseAccess.StudentDAO;
import dataAccess.DatabaseAccess.TeacherDAO;
import dataAccess.DatabaseAccess.UserDAO;
import dataAccess.realWorldEntities.Students;
import dataAccess.realWorldEntities.Teachers;
import dataAccess.realWorldEntities.Users;

public class UserProcessing {

	private DatabaseAccessObject<Users> dao = new UserDAO();

	private int processLogin(String username, String password) {
		Validator<String> nameValidator = new NameValidator();
		if(!nameValidator.validate(username) || !nameValidator.validate(password)) {
			throw new IllegalArgumentException("Password or username not of the correct form");
		}
		if(((UserDAO)dao).findByUsername(username).getPassword().equals(password)) {
			return ((UserDAO)dao).findByUsername(username).getIduser();
		}
		throw new IllegalArgumentException("Password or username incorrect");
		
	}
	
	private int teacherOrStudent(int id) {
		DatabaseAccessObject<Students> sDao = new StudentDAO();
		DatabaseAccessObject<Teachers> tDao = new TeacherDAO();
		try {
			return ((StudentDAO)sDao).findByUserId(id);
		}catch(NoSuchElementException e){
			return ((TeacherDAO)tDao).findByUserId(id);
			
		}
	}
	private void changeName(String name, int id) {
		Validator<String> nameValidator = new NameValidator();
		if(!nameValidator.validate(name)) {
			throw new IllegalArgumentException("Identity card number not of the correct form");
		}
		((UserDAO)dao).updateIdNum(name, id);
	}
	
	
	private void changeIdNumber(String idNum, int id) {
		Validator<String> idValidator = new IdValidator();
		if(!idValidator.validate(idNum)) {
			throw new IllegalArgumentException("Identity card number not of the correct form");
		}
		((UserDAO)dao).updateIdNum(idNum, id);
	}
	
	private void changeCNP(String cnp, int id) {
		Validator<String> cnpValidator = new CNPValidator();
		if(!cnpValidator.validate(cnp)) {
			throw new IllegalArgumentException("Personal numeric code not of the correct form");
		}
		((UserDAO)dao).updateCnp(cnp, id);
	}
	
	private void changeAddress(String address, int id) {
		Validator<String> addressValidator = new AddressValidator();
		if(!addressValidator.validate(address)) {
			throw new IllegalArgumentException("Address not of the correct form");
		}
		((UserDAO)dao).updateCnp(address, id);
	}
	
}
