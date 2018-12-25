package views;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import requestResponses.Requester;

public class Login extends JFrame {

	public static Requester requester;
	
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
//		Writer w;
//		try {
//			w = new FileWriter("output.txt");
//			 w.write("");
//		        w.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
       
	}

	private static boolean loggedIn() {
		String s = "";
		 try {  
	            Reader reader = new FileReader("output.txt");  
	            int data = reader.read();  
	            
	            while (data != -1) {  
	                System.out.print((char) data);
	                s+=(char) data;
	                data = reader.read();  
	            }  
	            reader.close();  
	        } catch (Exception ex) {  
	            System.out.println(ex.getMessage());  
	        }  
		 if(s.isEmpty() || s.equals(""))
			 return false;
		 else {
			 System.out.println(s);
			 Gson gson = new GsonBuilder().create();
			 Requester r = gson.fromJson(s, Requester.class);
			 requester = r;
			 return true;
		 }
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		
		if(loggedIn()) {
			
			if(isGranter(requester.getEmail())) {
				Granter grant = new Granter();
				grant.setVisible(true);
				dispose();	
			}
			else {
			Booking book = new Booking();
			book.setVisible(true);
			dispose();
			}
		}else {
		
		setTitle("Room Booking System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0,screen.width,screen.height);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 120, 215));
		panel.setBounds(0, 0, screen.width/2,screen.height);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblRoom = new JLabel("Room");
		lblRoom.setForeground(Color.WHITE);
		lblRoom.setFont(new Font("Serif", Font.BOLD, 60));
		lblRoom.setBounds(390, 275, 193, 72);
		panel.add(lblRoom);
		
		JLabel lblBooking = new JLabel("Booking System");
		lblBooking.setForeground(Color.WHITE);
		lblBooking.setFont(new Font("Serif", Font.BOLD, 60));
		lblBooking.setBounds(210, 375, 544, 72);
		panel.add(lblBooking);
		
		JLabel login_logo = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("login.png"));
		login_logo.setIcon(img);
		login_logo.setBounds(224, 518, 512, 512);
		panel.add(login_logo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(screen.width/2, 0, screen.width/2, screen.height);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblUsername = new JLabel("UserName:");
		lblUsername.setBounds(200, 310, 186, 37);
		panel_1.add(lblUsername);
		lblUsername.setFont(new Font("Serif", Font.BOLD, 30));
		
		txtUsername = new JTextField();
		txtUsername.setBounds(200, 370, 500, 50);
		panel_1.add(txtUsername);
		txtUsername.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtUsername.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(txtUsername.getText().equals("Username")){
			        txtUsername.setText("");
			    }
			}
			public void focusLost(FocusEvent e) {
				if(txtUsername.getText().equals("")){
			        txtUsername.setText("Username");
			    }
			}
		});
		txtUsername.setForeground(Color.GRAY);
		txtUsername.setText("Username");
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(200, 460, 170, 37);
		panel_1.add(lblPassword);
		lblPassword.setFont(new Font("Serif", Font.BOLD, 30));
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(200, 520, 500, 50);
		panel_1.add(pwdPassword);
		pwdPassword.setFont(new Font("Dialog", Font.PLAIN, 13));
		pwdPassword.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				char[] pwd = pwdPassword.getPassword();
				String p = String.valueOf(pwd);
				if(p.equals("Password"))
					pwdPassword.setText("");
			}
			public void focusLost(FocusEvent e) {
				char[] pwd = pwdPassword.getPassword();
				String p = String.valueOf(pwd);
				if(p.equals(""))
				pwdPassword.setText("Password");
			}
		});
		pwdPassword.setForeground(Color.GRAY);
		pwdPassword.setText("Password");
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = txtUsername.getText();
				
				String email = txtUsername.getText();
				 String password = pwdPassword.getText();
				 
				 email = email + "@lnmiit.ac.in";
				 user = email;
				 
					 String urlstring = "http://localhost:8080/login?email="+ email +"&password=" + password;
					 URL url;
					try {
						url = new URL(urlstring);
					
					 URLConnection urlcon=url.openConnection();  
					 InputStream stream=urlcon.getInputStream();  
					 
					 BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
					 
					 String json = reader.readLine();
					 if(!json.equals("Invalid Credentials")) {
						 System.out.println(json);
						 
					        Writer w = new FileWriter("output.txt");
					        w.write(json);
					        w.close();
					        
					        Gson gson = new GsonBuilder().create();
							Requester r = gson.fromJson(json, Requester.class);
							requester = r;
							 
						 if(isGranter(user)) {
								Granter grant = new Granter();
								grant.setVisible(true);
								dispose();
							}
							else {
								Booking book = new Booking();
								book.setVisible(true);
								dispose();
							}
						 
					 }else {
						 System.out.println("Invalid Credentials");
						 JOptionPane.showMessageDialog(null, "Invalid Username or Password!", "Invalid Credentials", JOptionPane.ERROR_MESSAGE);
						 return;
					 }
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
				
				
			}
		});
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBounds(200, 610, 150, 50);
		panel_1.add(btnLogin);
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 14));
		btnLogin.setBackground(new Color(0, 120, 215));
		btnLogin.setForeground(Color.WHITE);
		
		JLabel logo = new JLabel("New");
		ImageIcon lnm = new ImageIcon(this.getClass().getResource("LNMIIT.jpg"));
		logo.setIcon(lnm);
		logo.setBounds(680, 8, 200, 92);
		panel_1.add(logo);
		
		pack();
		logo.requestFocusInWindow();
		}
	}
	
	public boolean isGranter(String user) {
		return user.compareTo("granter1@lnmiit.ac.in")==0 
				 || user.compareTo("granter2@lnmiit.ac.in")==0 
				 || user.compareTo("granter3@lnmiit.ac.in")==0 
				 || user.compareTo("granter4@lnmiit.ac.in")==0 
				 || user.compareTo("granter5@lnmiit.ac.in")==0 
				 || user.compareTo("granter3@lnmiit.ac.in")==0 
				 || user.compareTo("granter4@lnmiit.ac.in")==0
				 || user.compareTo("16ucs138@lnmiit.ac.in")==0;
	}
	
}
