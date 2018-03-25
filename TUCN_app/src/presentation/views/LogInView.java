package presentation.views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInView {

	private JButton logIn;
	
	private JTextField userName;
	private JPasswordField passWord;
	
	private JLabel user;
	private JLabel pass;
	
	private JPanel center;
	
	private JFrame mainFrame = new JFrame("Log In");
	
	public LogInView() {
		logIn = new JButton("LOG IN");
		userName = new JTextField();
		passWord = new JPasswordField(45);
		user = new JLabel("Username: ");
		pass = new JLabel("PassWord: ");
		
		center = new JPanel();
		center.add(user);
		center.add(userName);
		center.add(pass);
		center.add(passWord);
		center.add(logIn);
		center.setLayout(new GridLayout(0,2));
		
		 mainFrame.setLayout(new BorderLayout());
		 mainFrame.add(center,BorderLayout.CENTER);
		 mainFrame.pack();    
		 mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 mainFrame.setVisible(true);
		 mainFrame.setSize(800,450);
		
	}
	
	public void showErrorMessage (String errorMessage){
		  JOptionPane.showMessageDialog(mainFrame,errorMessage );
	}

	public String getUserName() {
		return userName.getText();
	}


	public String getPassWord() {
		return passWord.getPassword().toString();
	}

	public void addLoginListener (ActionListener st){
		logIn.addActionListener(st);
	}
	
	
}
