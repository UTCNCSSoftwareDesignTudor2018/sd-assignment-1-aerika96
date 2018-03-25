package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import businessLogic.processingEntities.UserProcessing;
import presentation.views.LogInView;

public class Controller {
	
	private int userId;
	private int studentId;
	private int teacherId;
	
	private LogInView login;
	
	public Controller() {
		login = new LogInView();
		login.addLoginListener(new LoginListener());
		
	}
	
	class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				UserProcessing userProc =  new UserProcessing();
				userId = userProc.processLogin(login.getUserName(),login.getPassWord());
				int id = userProc.teacherOrStudent(userId);
				if(id<0) {
					studentId = -1*id;
				}
				else {
					teacherId = id;
				}
			}catch(IllegalArgumentException e) {
				login.showErrorMessage(e.getMessage());
			}
		}
		
	}

}
