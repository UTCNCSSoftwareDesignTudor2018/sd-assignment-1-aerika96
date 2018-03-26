package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import businessLogic.processingEntities.StudentProcessing;
import businessLogic.processingEntities.UserProcessing;
import presentation.views.LogInView;
import presentation.views.StudentStartPage;
import presentation.views.View;

public class Controller {
	
	private int userId;
	private int studentId;
	private int teacherId;
	
	private LogInView login;
	private StudentStartPage student;
	
	public Controller() {
		login = new LogInView();
		login.addLoginListener(new LoginListener());
		
	}
	void setStudent() {

		student = new StudentStartPage();
		login.setMainFrameFalse();
		StudentProcessing studProc = new StudentProcessing();
		UserProcessing userProc = new UserProcessing();
		student.setGroupData(studProc.getGroup(studentId));
		student.setStudentIdData(studProc.getStudentId(studentId));
		student.setNameData(userProc.userName(userId));
		student.setIdData(userProc.idNum(userId));
		student.setPersNumbData(userProc.persCode(userId));
		student.setAddressData(userProc.address(userId));
		
		student.addGroupListener(new GroupListener());
		student.addStudIdListener(new StudIdListener());
		student.addNameListener(new NameListener());
		student.addIdNumberListener(new IdListener());
		student.addPersNumListener(new PersNumListener());
		student.addAddressListener(new AddressListener());
		
	}
	void setTeacher() {
		
	}
	void setLogin() {
		
	}
	
	class GroupListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			StudentProcessing studProc=  new StudentProcessing();
			String input = JOptionPane.showInputDialog("Enter new group:");
			try {
			int gr = Integer.parseInt(input);
			studProc.changeGroup(gr,studentId); 
			student.setGroupData(studProc.getGroup(studentId));
			}catch(IllegalArgumentException e) {
				student.showErrorMessage(e.getMessage());
			}
			
		}
		
	}
	
	class StudIdListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			StudentProcessing studProc=  new StudentProcessing();
			String input = JOptionPane.showInputDialog("Enter new student ID:");
			try {
			studProc.changeStudentId(input,studentId); 
			student.setStudentIdData(studProc.getStudentId(studentId));
			}catch(IllegalArgumentException ex) {
				student.showErrorMessage(ex.getMessage());
			}	
			
		}
		
	}
	
	class NameListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			UserProcessing userProc =  new UserProcessing();
			String input = JOptionPane.showInputDialog("Enter your name:");
			try {
			userProc.changeName(input, userId);; 
			student.setNameData(userProc.userName(userId));
			}catch(IllegalArgumentException ex) {
				student.showErrorMessage(ex.getMessage());
			}
			
		}
		
	}
	
	class IdListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			UserProcessing userProc =  new UserProcessing();
			String input = JOptionPane.showInputDialog("Enter your ID number:");
			try {
			userProc.changeIdNumber(input, userId);; 
			student.setIdData(userProc.idNum(userId));
			}catch(IllegalArgumentException ex) {
				student.showErrorMessage(ex.getMessage());
			}
			
		}
		
	}
	class PersNumListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			UserProcessing userProc =  new UserProcessing();
			String input = JOptionPane.showInputDialog("Enter your personal numerical code:");
			try {
			userProc.changeCNP(input, userId);; 
			student.setPersNumbData(userProc.persCode(userId));
			}catch(IllegalArgumentException ex) {
				student.showErrorMessage(ex.getMessage());
			}
			
		}
		
	}
	
	class AddressListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			UserProcessing userProc =  new UserProcessing();
			String input = JOptionPane.showInputDialog("Enter your address:");
			try {
			userProc.changeAddress(input, userId);; 
			student.setAddressData(userProc.address(userId));
			}catch(IllegalArgumentException ex) {
				student.showErrorMessage(ex.getMessage());
			}
			
		}
		
	}
	class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				UserProcessing userProc =  new UserProcessing();
				userId = userProc.processLogin(login.getUserName(), login.getPassWord());
				int id = userProc.teacherOrStudent(userId);
				if(id<0) {
					studentId = -1*id;
					setStudent();
				}
				else {
					teacherId = id;
				}
			}catch(IllegalArgumentException e) {
				 login.showErrorMessage(e.getMessage());
			}catch(NoSuchElementException e) {
				 login.showErrorMessage("Password or username incorrect");
			}
		}
		
	}

}
