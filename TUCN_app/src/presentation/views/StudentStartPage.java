package presentation.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

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
	JPanel courses;
	
	JComboBox<String> course;
	
	public JTable takenCourses;
	
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
		student.add(studentProfile);
		student.add(new JLabel());
		student.add(new JLabel());
		student.add(new JLabel("Group: "));
		student.add(groupData);
		student.add(changeGroup);
		student.add(new JLabel("Student ID: "));
		student.add(studentIdData);
		student.add(changeStudId);
		student.setLayout(new GridLayout(0,3));
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
		profile.add(student,BorderLayout.NORTH);
		profile.add(personal, BorderLayout.CENTER);
		
		 courses = new JPanel();
		JPanel enrollment = new JPanel();
		JPanel all = new JPanel();
		course= new JComboBox<>();
		enrollment.add(new JLabel("Enroll to a course: "));
		enrollment.add(course);
		enrollment.setLayout(new GridLayout(0,2));
		all.setLayout(new BorderLayout(50,50));
		all.add(enrollment,BorderLayout.NORTH);
		all.add(courses,BorderLayout.CENTER);
		
		
		JTabbedPane tabbedPane = new JTabbedPane();
		

		tabbedPane.addTab("Profile", profile);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		tabbedPane.addTab("Enrollment", all);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		
		 mainFrame.setLayout(new BorderLayout());
		 mainFrame.add(tabbedPane,BorderLayout.CENTER);
		 mainFrame.pack();    
		 mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 mainFrame.setVisible(true);
		 mainFrame.setSize(800,450);
		
	}
	
	
	public String getCourse() {
		String value = (String)course.getSelectedItem();
		return value;
	}


	public void setCourse(String [] crs) {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(crs );
		this.course.setModel(model);
	}

	public void showErrorMessage (String errorMessage){
		  JOptionPane.showMessageDialog(mainFrame,errorMessage );
	}
	
	public void setGroupData(String nGroup) {
		this.groupData.setText(nGroup);
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

	public void setTakenCourses(Object[][] data,MouseAdapter mouse) {
		JTable taken = new JTable(data,new String[]{"Course","Staus","Grade"}) ;
		this.courses.removeAll();
		takenCourses =taken;
		takenCourses.setPreferredScrollableViewportSize(new Dimension(500,200));
		JScrollPane pane= new JScrollPane(takenCourses,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setSize(new Dimension(100,100));
		this.courses.add(pane);
		takenCourses.addMouseListener(mouse);
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
	
	public void addGroupListener (ActionListener st){
		changeGroup.addActionListener(st);
	}
	
	public void addStudIdListener (ActionListener st){
		changeStudId.addActionListener(st);
	}
	
	public void addCourseListener(ActionListener st) {
		course.addActionListener(st);
	}
}
