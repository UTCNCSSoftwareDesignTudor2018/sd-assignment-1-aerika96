package presentation.views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class StudentStartPage implements View {
	
	

	JButton changeGroup;
	JButton changeStudId;
	JButton changeName;
	JButton changeIdentityNumber;
	JButton changePersonalNumber;
	JButton changeAddress;
	
	JLabel groupData;
	JLabel studentIdData;
	JLabel nameData;
	JLabel idData;
	JLabel persNumbData;
	JLabel addressData;
	
	JComboBox course;
	
	JTable takenCourses;
	
	private JFrame mainFrame = new JFrame("Student Page");
	
	public StudentStartPage() {
		changeGroup = new JButton("CHANGE");
		changeStudId = new JButton("CHANGE");
		changeName =  new JButton("CHANGE");
		changeIdentityNumber =new JButton("CHANGE");
		changePersonalNumber = new JButton("CHANGE");
		changeAddress = new JButton("CHANGE");
		
		groupData = new JLabel();
		studentIdData = new JLabel();
		nameData = new JLabel();
		idData = new JLabel();
		persNumbData = new JLabel();
		addressData = new JLabel();
		
		takenCourses= new JTable();
		
		JLabel studentProfile = new JLabel("Student Profile");
		JLabel personalProfile = new JLabel("Personal Profile");
		
		JPanel profile = new JPanel();
		JPanel student = new JPanel();
		student.add(new JLabel("Group: "));
		student.add(groupData);
		student.add(changeGroup);
		student.add(new JLabel("Student ID: "));
		student.add(studentIdData);
		student.add(changeStudId);
		student.setLayout(new GridLayout(0,3));
		JPanel personal = new JPanel();
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
		profile.add(student,BorderLayout.NORTH);
		profile.add(personal, BorderLayout.CENTER);
		
		JPanel courses = new JPanel();
		JPanel enrollment = new JPanel();
		course= new JComboBox();
		enrollment.add(new JLabel("Enroll to a course: "));
		enrollment.add(course);
		enrollment.setLayout(new GridLayout(0,2));
		courses.setLayout(new BorderLayout(50,50));
		courses.add(enrollment,BorderLayout.NORTH);
		courses.add(takenCourses,BorderLayout.CENTER);
		courses.setLayout(new GridLayout(0,1));
		
		
		JTabbedPane tabbedPane = new JTabbedPane();
		

		tabbedPane.addTab("Profile", profile);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		tabbedPane.addTab("Enrollment", courses);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		
		 mainFrame.setLayout(new BorderLayout());
		 mainFrame.add(tabbedPane,BorderLayout.CENTER);
		 mainFrame.pack();    
		 mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 mainFrame.setVisible(true);
		 mainFrame.setSize(800,450);
		
	}
	
	public void showErrorMessage (String errorMessage){
		  JOptionPane.showMessageDialog(mainFrame,errorMessage );
	}
	
	public void setGroupData(String nGroup) {
		this.groupData.setText(nGroup);;
	}

	public void setStudentIdData(String nStudId) {
		this.studentIdData.setText(nStudId);
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

	public void setTakenCourses(JTable takenCourses) {
		this.takenCourses = takenCourses;
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
	
	public void addGroupListener (ActionListener st){
		changeGroup.addActionListener(st);
	}
	
	public void addStudIdListener (ActionListener st){
		changeStudId.addActionListener(st);
	}
}
