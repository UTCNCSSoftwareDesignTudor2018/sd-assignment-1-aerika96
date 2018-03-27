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
	
	JLabel groupData;
	JLabel profIdData;
	JLabel nameData;
	JLabel idData;
	JLabel persNumbData;
	JLabel addressData;
	JPanel courses;
	
	JTextField course;
	
	public JTable taughtCourses;
	
	private JFrame mainFrame = new JFrame("Student Page");
	
	public TeacherStartPage() {
		changeName =  new JButton("CHANGE");
		changeIdentityNumber =new JButton("CHANGE");
		changePersonalNumber = new JButton("CHANGE");
		changeAddress = new JButton("CHANGE");
		addCourse =  new JButton("ADD");
		
		groupData = new JLabel();
		profIdData = new JLabel();
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
		
		
		JTabbedPane tabbedPane = new JTabbedPane();
		

		tabbedPane.addTab("Profile", profile);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		tabbedPane.addTab("Courses and grading", all);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		
		 mainFrame.setLayout(new BorderLayout());
		 mainFrame.add(tabbedPane,BorderLayout.CENTER);
		 mainFrame.pack();    
		 mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 mainFrame.setVisible(true);
		 mainFrame.setSize(800,450);
		
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

	public void setTaughtCourses(Object[][] data,MouseAdapter mouse) {
		JTable taught = new JTable(data,new String[]{"Course"}) ;
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
	
}
