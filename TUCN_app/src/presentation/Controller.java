package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import businessLogic.processingEntities.CourseProcessing;
import businessLogic.processingEntities.EnrollmentProcessing;
import businessLogic.processingEntities.StudentProcessing;
import businessLogic.processingEntities.UserProcessing;
import presentation.views.LogInView;
import presentation.views.StudentStartPage;

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
		CourseProcessing courseProc = new CourseProcessing();
		student.setGroupData(studProc.getGroup(studentId));
		student.setStudentIdData(studProc.getStudentId(studentId));
		student.setNameData(userProc.userName(userId));
		student.setIdData(userProc.idNum(userId));
		student.setPersNumbData(userProc.persCode(userId));
		student.setAddressData(userProc.address(userId));
		student.setCourse(courseProc.allCourses());
		try {
		student.setTakenCourses(studProc.getAllEnrolled(studentId), new StudentTable());
		}catch(NoSuchElementException e) {
			
		}
		
		student.addGroupListener(new GroupListener());
		student.addStudIdListener(new StudIdListener());
		student.addNameListener(new NameListener());
		student.addIdNumberListener(new IdListener());
		student.addPersNumListener(new PersNumListener());
		student.addAddressListener(new AddressListener());
		student.addCourseListener(new CourseListener());
		
	}
	void setTeacher() {
		
	}
	void setLogin() {
		
	}
	class StudentTable extends MouseAdapter{
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	StudentProcessing studProc =  new StudentProcessing();
				 EnrollmentProcessing enProc =  new EnrollmentProcessing();
				 CourseProcessing courseProc =  new CourseProcessing();
		        int row = student.takenCourses.rowAtPoint(evt.getPoint());
		        int col = student.takenCourses.columnAtPoint(evt.getPoint());
		        if (row >= 0 && col >= 0) {
		        	String course = student.takenCourses.getValueAt(row, 0).toString();
		        	String status = student.takenCourses.getValueAt(row,1).toString();
		        	if(status.equals("Exam Taken")) {
		        		student.showErrorMessage("Exam already taken");
		        	}
		        	else {
		        		String[] choices = { "Yes","No" };
		   			 String input = (String) JOptionPane.showInputDialog(null, "Do you want to take the "+course+ " exam?",
		   			        "Choose option", JOptionPane.QUESTION_MESSAGE, null, 
		   			        choices,
		   			        choices[0]);
		   			 try {
		   			 if(input.equals("Yes")) {
		   				 enProc.enrollToExam(studentId, course);
		 	        	 student.setTakenCourses(studProc.getAllEnrolled(studentId), new StudentTable());
		   			 }
		   			 }catch(NullPointerException e) {
		   				 student.showErrorMessage("Maybe next time");
		   			 }catch(NoSuchElementException e) {
		   				 
		   			 }
		        	
		        	}
		        }
	 }
	}
		    
		
	
	
	
	class CourseListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

	    	StudentProcessing studProc =  new StudentProcessing();
			 EnrollmentProcessing enProc =  new EnrollmentProcessing();
			 CourseProcessing courseProc =  new CourseProcessing();
			 String[] choices = { "Yes","No" };
			 String input = (String) JOptionPane.showInputDialog(null, "Do you want to take the "+student.getCourse()+ " course?",
			        "Choose option", JOptionPane.QUESTION_MESSAGE, null, 
			        choices,
			        choices[0]);
			 try {
			 if(input.equals("Yes")) {
				 
				 if(enProc.enrollmentFound(studentId, courseProc.courseIdByName(student.getCourse()))) {
					 student.showErrorMessage("Course already taken");
				 }
				 else {
					 enProc.enrollToCourse(studentId, student.getCourse());
					 try {
	 	        	 student.setTakenCourses(studProc.getAllEnrolled(studentId), new StudentTable());
					 }catch(NoSuchElementException e) {
						 
					 }
				 }
			 }
			 }catch(NullPointerException e) {
				 
			 }
			
		}
		
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
