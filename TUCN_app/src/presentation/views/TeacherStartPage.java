package presentation.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TeacherStartPage {

	
	JButton changeName;
	JButton changeIdentityNumber;
	JButton changePersonalNumber;
	JButton changeAddress;
	JButton addCourse;
	JButton backToCourse;
	JButton search;
	JButton backToSearch;
	
	
	JLabel nameData;
	JLabel idData;
	JLabel persNumbData;
	JLabel addressData;
	
	JLabel groupData;
	JLabel studIdData;
	JLabel studentName;
	JLabel studentIdNumber;
	JLabel studentPersNum;
	JLabel studentAddress;
	
	JPanel courses;
	JPanel findStudent;
	
	JTextField course;
	JTextField studentId;
	
	public JTable taughtCourses;
	
	private JFrame mainFrame = new JFrame("Student Page");
	
	public TeacherStartPage() {
		changeName =  new JButton("CHANGE");
		changeIdentityNumber =new JButton("CHANGE");
		changePersonalNumber = new JButton("CHANGE");
		changeAddress = new JButton("CHANGE");
		addCourse =  new JButton("ADD");
		backToCourse = new JButton("BACK");
		search = new JButton("SEARCH");
		backToSearch = new JButton("BACK");
		
		groupData = new JLabel();
		studIdData = new JLabel();
		studentName = new JLabel();
		studentIdNumber = new JLabel();
		studentPersNum = new JLabel();
		studentAddress = new JLabel();
		

		studentId = new JTextField();
		
		
		nameData = new JLabel();
		idData = new JLabel();
		persNumbData = new JLabel();
		addressData = new JLabel();
		
		taughtCourses= new JTable();
		
		JLabel personalProfile = new JLabel("Personal Profile");
		
		JPanel profile = new JPanel();
	
		JPanel personal = new JPanel();
		personal.add(personalProfile);
		personal.add(new JLabel());
		personal.add(new JLabel());
		personal.add(new JLabel("Name: "));
		personal.add(nameData);
		personal.add(changeName);
		personal.add(new JLabel("ID number: "));
		personal.add(idData);
		personal.add(changeIdentityNumber);
		personal.add(new JLabel("Personal numerical code: "));
		personal.add(persNumbData);
		personal.add(changePersonalNumber);
		personal.add(new JLabel("Address: "));
		personal.add(addressData);
		personal.add(changeAddress);
		personal.setLayout(new GridLayout(0,3));
		
		profile.setLayout(new BorderLayout(50,50));
		profile.add(personal, BorderLayout.CENTER);
		
		courses = new JPanel();
		JPanel courseCreation = new JPanel();
		JPanel all = new JPanel();
		course= new JTextField();
		courseCreation.add(new JLabel("Add a new course: "));
		courseCreation.add(course);
		courseCreation.add(addCourse);
		courseCreation.setLayout(new GridLayout(0,3));
		all.setLayout(new BorderLayout(50,50));
		all.add(courseCreation,BorderLayout.NORTH);
		all.add(courses,BorderLayout.CENTER);
		
		findStudent= new JPanel();
		
		JTabbedPane tabbedPane = new JTabbedPane();
		

		tabbedPane.addTab("Profile", profile);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		tabbedPane.addTab("Courses and grading", all);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		tabbedPane.addTab("Find student",findStudent);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_1);
		
		 mainFrame.setLayout(new BorderLayout());
		 mainFrame.add(tabbedPane,BorderLayout.CENTER);
		 mainFrame.pack();    
		 mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 mainFrame.setVisible(true);
		 mainFrame.setSize(800,450);
		
	}
	
	

	public String getStudentId() {
		return studentId.getText();
	}

	
	public void setFindToDisplay(String nGroup, String nStudId, String nName, String nId, String nPesNum, String nAddr) {
		this.findStudent.removeAll();
		findStudent.add(new JLabel("Group:")); 
		groupData.setText(nGroup);
		findStudent.add(groupData);
		findStudent.add(new JLabel());
		
		findStudent.add(new JLabel("Student ID:")); 
		studIdData.setText(nStudId);
		findStudent.add(studIdData);
		findStudent.add(new JLabel());
		
		findStudent.add(new JLabel("Name:"));
		studentName.setText(nName);
		findStudent.add(studentName);
		findStudent.add(new JLabel());
		
		findStudent.add(new JLabel("ID number:")); 
		studentIdNumber.setText(nName);
		findStudent.add(studentIdNumber);
		findStudent.add(new JLabel());
		
		findStudent.add(new JLabel("Personal numerical code:")); 
		studentPersNum.setText(nPesNum);
		findStudent.add(studentPersNum);
		findStudent.add(new JLabel());
		
		findStudent.add(new JLabel("Address:")); 
		studentAddress.setText(nAddr);
		findStudent.add(studentAddress);
		findStudent.add(new JLabel());
		
		findStudent.add(backToSearch);
		findStudent.add(new JLabel());
		findStudent.add(new JLabel());
		findStudent.setLayout(new GridLayout(0,3));
		this.findStudent.revalidate();
		this.findStudent.repaint();
		
	}
	

	public void setFindToSearch() {
		try {
		this.findStudent.removeAll();
		JPanel searchBar = new JPanel();
		searchBar.add(new JLabel("Student ID: "));
		searchBar.add(studentId);
		searchBar.add(search);
		searchBar.setLayout(new GridLayout(0,3));
		findStudent.setLayout(new BorderLayout(170,170));
		findStudent.add(searchBar, BorderLayout.CENTER);
		findStudent.add(new JPanel(), BorderLayout.NORTH);
		findStudent.add(new JPanel(), BorderLayout.SOUTH);
		findStudent.add(new JPanel(),BorderLayout.EAST);
		findStudent.add(new JPanel(),BorderLayout.WEST);
		this.findStudent.revalidate();
		this.findStudent.repaint();
		}catch(NullPointerException e) {
			JPanel searchBar = new JPanel();
			searchBar.add(new JLabel("Student ID: "));
			searchBar.add(studentId);
			searchBar.add(search);
			searchBar.setLayout(new GridLayout(0,3));
			findStudent.setLayout(new BorderLayout(170,170));
			findStudent.add(searchBar, BorderLayout.CENTER);
			findStudent.add(new JPanel(), BorderLayout.NORTH);
			findStudent.add(new JPanel(), BorderLayout.SOUTH);
			findStudent.add(new JPanel(),BorderLayout.EAST);
			findStudent.add(new JPanel(),BorderLayout.WEST);

			
		}
		
	}
	
	public String getCourse() {
		String value = course.getText();
		return value;
	}


	public void showErrorMessage (String errorMessage){
		  JOptionPane.showMessageDialog(mainFrame,errorMessage );
	}
	
	
	public void setNameData(String nName) {
		this.nameData.setText(nName);
	}


	public void setIdData(String nidData) {
		this.idData.setText(nidData);
	}

	public void setPersNumbData(String nPers) {
		this.persNumbData.setText(nPers);
	}

	public void setAddressData(String nAddress) {
		this.addressData.setText(nAddress);
	}

	public void setTaughtCourses(Object[][] data, String[] headers,MouseAdapter mouse) {
		JTable taught = new JTable(data,headers) ;
		this.courses.removeAll();
		taughtCourses =taught;
		taughtCourses.setPreferredScrollableViewportSize(new Dimension(500,200));
		JScrollPane pane= new JScrollPane(taughtCourses,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setSize(new Dimension(100,100));
		this.courses.add(pane);
		taughtCourses.addMouseListener(mouse);
		this.courses.revalidate();
		this.courses.repaint();
	}
	public void setTaughtStudents(Object[][] data, String[] headers,MouseAdapter mouse) {
		JTable taught = new JTable(data,headers) ;
		this.courses.removeAll();
		taughtCourses =taught;
		taughtCourses.setPreferredScrollableViewportSize(new Dimension(500,200));
		JScrollPane pane= new JScrollPane(taughtCourses,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setSize(new Dimension(100,100));
		this.courses.add(pane);
		this.courses.add(backToCourse);
		taughtCourses.addMouseListener(mouse);
		this.courses.revalidate();
		this.courses.repaint();
	}
	
	
	public void addNameListener (ActionListener st){
		changeName.addActionListener(st);
	}
	
	public void addIdNumberListener (ActionListener st){
		changeIdentityNumber.addActionListener(st);
	}
	
	public void addPersNumListener (ActionListener st){
		changePersonalNumber.addActionListener(st);
	}
	
	public void addAddressListener (ActionListener st){
		changeAddress.addActionListener(st);
	}
	
	public void addAddListener (ActionListener st){
		addCourse.addActionListener(st);
	}
	
	public void addBackListener(ActionListener st) {
		backToCourse.addActionListener(st);
	}
	
	public void addSearchListener(ActionListener st) {
		search.addActionListener(st);
	}
	
	public void addBackToSearchListener(ActionListener st) {
		backToSearch.addActionListener(st);
	}
	
}
