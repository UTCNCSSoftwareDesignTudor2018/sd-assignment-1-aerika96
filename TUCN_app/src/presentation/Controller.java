package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import businessLogic.processingEntities.UserProcessing;
import presentation.views.LogInView;
import presentation.views.StudentStartPage;
import presentation.views.View;

public class Controller {
	
	private int userId;
	private int studentId;
	private int teacherId;
	
	private View login;
	
	public Controller() {
		login = new LogInView();
		((LogInView) login).addLoginListener(new LoginListener());
		
	}
	
	class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				UserProcessing userProc =  new UserProcessing();
				userId = userProc.processLogin(((LogInView) login).getUserName(),((LogInView) login).getPassWord());
				int id = userProc.teacherOrStudent(userId);
				if(id<0) {
					studentId = -1*id;
					login = new StudentStartPage();
				}
				else {
					teacherId = id;
				}
			}catch(IllegalArgumentException e) {
				((LogInView) login).showErrorMessage(e.getMessage());
			}
		}
		
	}

}
