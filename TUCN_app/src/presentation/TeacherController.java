package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import businessLogic.processingEntities.CourseProcessing;
import businessLogic.processingEntities.EnrollmentProcessing;
import businessLogic.processingEntities.ReportProcessing;
import businessLogic.processingEntities.StudentProcessing;
import businessLogic.processingEntities.UserProcessing;
import presentation.StudentController.StudentTable;
import presentation.views.TeacherStartPage;

public class TeacherController {
		
	int userId;
	int teacherId;
	String crs;
	TeacherStartPage teacher;
	
	void setTeacher(int uId,int tId) {
		userId = uId;
		teacherId = tId;
		teacher = new TeacherStartPage();
		UserProcessing userProc = new UserProcessing();
		CourseProcessing courseProc = new CourseProcessing();
		teacher.setNameData(userProc.userName(userId));
		teacher.setIdData(userProc.idNum(userId));
		teacher.setPersNumbData(userProc.persCode(userId));
		teacher.setAddressData(userProc.address(userId));
		teacher.setTaughtCourses(courseProc.allCoursesByTeacher(teacherId), new String[] {"Course"},new TeachersCourseTable());
		teacher.setFindToSearch();
		
		teacher.addNameListener(new NameListener());
		teacher.addIdNumberListener(new IdListener());
		teacher.addPersNumListener(new PersNumListener());
		teacher.addAddressListener(new AddressListener());
		teacher.addAddListener(new AddListener());
		teacher.addBackListener(new BackToCoursesListener());
		teacher.addSearchListener(new SearchListener());
		teacher.addBackToSearchListener(new BackToSearchListener());
		
	}
	
	class BackToSearchListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			teacher.setFindToSearch();
		}
		
	}
	
	class SearchListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			StudentProcessing studProc = new StudentProcessing();
			UserProcessing userProc = new UserProcessing();
			String studentId = teacher.getStudentId();
			try {

				int userId = studProc.getByStudentId(studentId).getUser_iduser();
				int group = studProc.getByStudentId(studentId).getGroup_();
				String name =userProc.userName(userId);
				String idNum = userProc.idNum(userId);
				String persNum=userProc.persCode(userId);
				String address = userProc.address(userId);
				teacher.setFindToDisplay(group+"", studentId, name, idNum, persNum, address);
				
			}catch(NoSuchElementException e) {
				teacher.showErrorMessage("No student with the entered ID");
			}
			
			
		}
		
	}
	
	class BackToCoursesListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			CourseProcessing courseProc = new CourseProcessing();
			teacher.setTaughtCourses(courseProc.allCoursesByTeacher(teacherId), new String[] {"Course"},new TeachersCourseTable());
		}
		
	}
	
	class TeachersCourseTable extends MouseAdapter{
		 @Override
		 public void mouseClicked(java.awt.event.MouseEvent evt) {
			ReportProcessing repProc= new ReportProcessing();
	        int row = teacher.taughtCourses.rowAtPoint(evt.getPoint());
	        int col = teacher.taughtCourses.columnAtPoint(evt.getPoint());
	        try {
	        if (row >= 0 && col >= 0) {
	        	String course = teacher.taughtCourses.getValueAt(row, 0).toString();
	        	crs= course;
	        	List<String> names = new ArrayList<>(repProc.namesForGrading(course));
	        	List<String> studIds = new ArrayList<>(repProc.studentIdsForGrading(course));
	        	Object[][]students = new Object[names.size()][2];
	        	for(int i=0; i<names.size(); i++) {
	        		students[i]= new Object[] {names.get(i),studIds.get(i)};
	        	}
	    		teacher.setTaughtStudents(students, new String[] {"Name","Student ID"},new TeachersStudentTable());
	        	
	       }
	       }catch(NullPointerException e) {
	    	   
	       }catch(NoSuchElementException e) {
	    	   teacher.showErrorMessage("No students taking this course");
	       }
	     }
	}
		
	class TeachersStudentTable extends MouseAdapter{
		 @Override
		 public void mouseClicked(java.awt.event.MouseEvent evt) {
			 ReportProcessing repProc= new ReportProcessing();
			 int row = teacher.taughtCourses.rowAtPoint(evt.getPoint());
		     int col = teacher.taughtCourses.columnAtPoint(evt.getPoint());
		     try {
			        if (row >= 0 && col >= 0) {
			        	String name = teacher.taughtCourses.getValueAt(row, 0).toString();
			        	String studId = teacher.taughtCourses.getValueAt(row, 1).toString();
			        	String[] choices = { "1", "2", "3", "4", "5", "6","7","8","9","10" };
					    String input = (String) JOptionPane.showInputDialog(null, "Give a grade to "+name,
					        "Give a grade", JOptionPane.QUESTION_MESSAGE, null, 
					        choices, 
					        choices[0]); 
			        	int grade = Integer.parseInt(input);
			        	repProc.giveGrades(crs, studId, grade);
			        	
			       }
			       }catch(NullPointerException e) {
			    	   
			       }catch(NumberFormatException e) {
			    	   teacher.showErrorMessage("Please enter a grade");
			       }catch(IllegalArgumentException e) {
			    	   teacher.showErrorMessage("Student already graded");
			       }catch(NoSuchElementException e) {
			    	   teacher.showErrorMessage("Student didn't take the exam");
			       }
			 
		 }
		 
	}   
	
	    
	
	class AddListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			CourseProcessing courseProc= new CourseProcessing();
			try {
				courseProc.addNewCourse(teacher.getCourse(), teacherId);
			}catch(IllegalArgumentException ex){
				teacher.showErrorMessage(ex.getMessage());
			}catch(NullPointerException ex) {
				teacher.showErrorMessage("No course name entered");
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
			teacher.setNameData(userProc.userName(userId));
			}catch(IllegalArgumentException ex) {
				teacher.showErrorMessage(ex.getMessage());
			}catch(NullPointerException exc) {
				
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
			teacher.setIdData(userProc.idNum(userId));
			}catch(IllegalArgumentException ex) {
				teacher.showErrorMessage(ex.getMessage());
			}catch(NullPointerException exc) {
				
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
			teacher.setPersNumbData(userProc.persCode(userId));
			}catch(IllegalArgumentException ex) {
				teacher.showErrorMessage(ex.getMessage());
			}catch(NullPointerException exc) {
				
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
			teacher.setAddressData(userProc.address(userId));
			}catch(IllegalArgumentException ex) {
				teacher.showErrorMessage(ex.getMessage());
			}catch(NullPointerException exc) {
				
			}
			
		}
		
	}
}
