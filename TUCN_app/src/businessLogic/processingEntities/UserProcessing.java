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
	
	public String userName(int id) {
		try {	
		if(dao.findBySpecificId("iduser",id).getName() == null) {
			return new String ("Set name");
		}
		return dao.findBySpecificId("iduser",id).getName();
		}catch(NoSuchElementException e) {
			return new String ("Set name");
		}catch(NullPointerException e) {
			return new String("Set name");
		}
	}
	
	public String idNum(int id) {
		try {	
			if(dao.findBySpecificId("iduser",id).getIdnumber() == null) {
				return new String ("Set ID number");
			}
			return dao.findBySpecificId("iduser",id).getIdnumber();
			}catch(NoSuchElementException e) {
				return new String ("Set ID number");
			}catch(NullPointerException e) {
				return new String("Set ID number");
			}
	}
	
	public String persCode(int id) {
		try {	
			if(dao.findBySpecificId("iduser",id).getCnp() == null) {
				return new String ("Set personal numerical code");
			}
			return dao.findBySpecificId("iduser",id).getCnp();
			}catch(NoSuchElementException e) {
				return new String ("Set personal numerical code");
			}catch(NullPointerException e) {
				return new String("Set personal numerical code");
			}
	}
	
	public String address(int id) {
		try {	
			if(dao.findBySpecificId("iduser",id).getAddress() == null) {
				return new String ("Set address");
			}
			return dao.findBySpecificId("iduser",id).getAddress();
			}catch(NoSuchElementException e) {
				return new String ("Set address");
			}catch(NullPointerException e) {
				return new String("Set address");
			}
	}

	public int processLogin(String username, String password)throws NoSuchElementException {
		Validator<String> nameValidator = new NameValidator();
		if(!nameValidator.validate(username) || !nameValidator.validate(password)) {
			throw new IllegalArgumentException("Password or username not of the correct form");
		}
		if(((UserDAO)dao).findByUsername(username).getPassword().equals(password)) {
			return ((UserDAO)dao).findByUsername(username).getIduser();
		}
		throw new IllegalArgumentException("Password or username incorrect");
		
	}
	
	public int teacherOrStudent(int id) {
		DatabaseAccessObject<Students> sDao = new StudentDAO();
		DatabaseAccessObject<Teachers> tDao = new TeacherDAO();
		try {
			return -1*((StudentDAO)sDao).findByUserId(id);
		}catch(NoSuchElementException e){
			return ((TeacherDAO)tDao).findByUserId(id);
			
		}
	}
	public void changeName(String name, int id) {
		Validator<String> nameValidator = new NameValidator();
		if(!nameValidator.validate(name)) {
			throw new IllegalArgumentException("Identity card number not of the correct form");
		}
		((UserDAO)dao).updateName(name, id);
	}
	
	
	public void changeIdNumber(String idNum, int id) {
		Validator<String> idValidator = new IdValidator();
		if(!idValidator.validate(idNum)) {
			throw new IllegalArgumentException("Identity card number not of the correct form");
		}
		((UserDAO)dao).updateIdNum(idNum, id);
	}
	
	public void changeCNP(String cnp, int id) {
		Validator<String> cnpValidator = new CNPValidator();
		if(!cnpValidator.validate(cnp)) {
			throw new IllegalArgumentException("Personal numeric code not of the correct form");
		}
		((UserDAO)dao).updateCnp(cnp, id);
	}
	
	public void changeAddress(String address, int id) {
		Validator<String> addressValidator = new AddressValidator();
		if(!addressValidator.validate(address)) {
			throw new IllegalArgumentException("Address not of the correct form");
		}
		((UserDAO)dao).updateAddress(address, id);
	}
	
}
