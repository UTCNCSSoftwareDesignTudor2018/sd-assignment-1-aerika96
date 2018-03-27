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
import presentation.views.TeacherStartPage;

public class Controller {
	
	private int userId;
	private int studentId;
	private int teacherId;
	
	private LogInView login;
	private StudentController studentController;
	private TeacherController teacherController;
	
	public Controller(StudentController studContr, TeacherController tchCont) {
		login = new LogInView();
		login.addLoginListener(new LoginListener());
		studentController=studContr;
		teacherController=tchCont;
		
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
					login.setMainFrameFalse();
					studentController.setStudent(userId,studentId);
				}
				else {
					teacherId = id;
					login.setMainFrameFalse();
					teacherController.setTeacher(userId,teacherId);
				}
			}catch(IllegalArgumentException e) {
				 login.showErrorMessage(e.getMessage());
			}catch(NoSuchElementException e) {
				 login.showErrorMessage("Password or username incorrect");
			}
		}
		
	}

}
