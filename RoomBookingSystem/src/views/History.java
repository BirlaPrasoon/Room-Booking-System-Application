package views;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import javax.swing.border.LineBorder;

public class History extends JFrame {

	private JPanel contentPane;
	private JPanel header;
	private JButton button;
	private JLabel lblRoomBookingSystem;
	private JLabel logo;
	private JTextField textField_14;
	private JButton btnNewBooking;
	private JTable table;
	private JPanel categories;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	static Object[][] data;
	ArrayList<RequestResponse> history;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					History frame = new History();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
//		getHistory();
	}

	/**
	 * Create the frame.
	 */
	public History() {
		
		getHistory();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setup();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public void setup() {
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
		
		JLabel lblBookingHistory2 = new JLabel("Booking History");
		lblBookingHistory2.setFont(new Font("Serif", Font.BOLD, 30));
		lblBookingHistory2.setBounds(822, 270, 273, 37);
		contentPane.add(lblBookingHistory2);
		
		JButton btnHome = new JButton("Book a Room");
		btnHome.setFont(new Font("Serif", Font.BOLD, 14));
		btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHome.setBackground(new Color(0, 120, 215));
		btnHome.setForeground(Color.WHITE);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Booking book = new Booking();
				book.setVisible(true);
				dispose();
			}
		});
		btnHome.setBounds(460, 900, 138, 28);
		contentPane.add(btnHome);
		
		header = new JPanel();
		header.setLayout(null);
		header.setBackground(new Color(0, 120, 215));
		header.setBounds(0, 0, 1920, 150);
		contentPane.add(header);
		
		button = new JButton("Logout");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog.setDefaultLookAndFeelDecorated(true);
				int YesorNo = JOptionPane.showConfirmDialog(null, "Do you really want to Logout?", "Logout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(YesorNo == JOptionPane.YES_OPTION) {
					
					Writer w;
					try {
						w = new FileWriter("output.txt");
						w.write("");
				        w.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					Login login = new Login();
					login.setVisible(true);
					dispose();
					JOptionPane.showMessageDialog(null, "Logged Out Successfully");
				}
			}
		});
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Serif", Font.BOLD, 14));
		button.setBackground(Color.WHITE);
		button.setBounds(1752, 110, 88, 28);
		header.add(button);
		
		lblRoomBookingSystem = new JLabel("Room Booking System");
		lblRoomBookingSystem.setForeground(Color.WHITE);
		lblRoomBookingSystem.setFont(new Font("Serif", Font.BOLD, 35));
		lblRoomBookingSystem.setBounds(50, 15, 438, 42);
		header.add(lblRoomBookingSystem);
		
		logo = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("LNMIIT.jpg"));
		logo.setIcon(img);
		logo.setBounds(1640, 8, 200, 92);
		header.add(logo);
		
		textField_14 = new JTextField();
		if(Login.requester !=null){
			textField_14.setText("Hi, " + Login.requester.getName());
		}else
			textField_14.setText("Hi, User");
		textField_14.setForeground(Color.WHITE);
		textField_14.setFont(new Font("Serif", Font.PLAIN, 14));
		textField_14.setEditable(false);
		textField_14.setColumns(10);
		textField_14.setBorder(null);
		textField_14.setBackground(new Color(0, 120, 215));
		textField_14.setBounds(1640, 115, 120, 17);
		header.add(textField_14);
		
		btnNewBooking = new JButton("New Booking");
		btnNewBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Booking book = new Booking();
				book.setVisible(true);
				dispose();
			}
		});
		btnNewBooking.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewBooking.setFont(new Font("Serif", Font.BOLD, 14));
		btnNewBooking.setBackground(Color.WHITE);
		btnNewBooking.setBounds(50, 110, 138, 28);
		header.add(btnNewBooking);
		
		JButton btnCancel = new JButton("Cancel Booking");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cancel cancel = new Cancel();
				cancel.setVisible(true);
				dispose();
			}
		});
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setFont(new Font("Serif", Font.BOLD, 14));
		btnCancel.setBounds(220, 110, 157, 28);
		header.add(btnCancel);
		
		table = new JTable();
		table.setGridColor(new Color(0, 120, 215));
		table.setBorder(new LineBorder(new Color(0, 120, 215)));
		table.setRowHeight(50);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		
	    int size = history.size()>10?10:history.size();
	    data =  new Object[size][4];
	    
	    for(int i = 0; i<size; i++) {
	    	data[i][0] = history.get(i).getRoomId();
	    	data[i][1] = history.get(i).getDate();
	    	data[i][2] = history.get(i).getFrom_time() + "-" + history.get(i).getTo_time();
	    	data[i][3] = history.get(i).getStatus();
	    }
		
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"Room", "Date", "Timings", "Status"
			}
		));
		table.setFont(new Font("Serif", Font.BOLD, 14));
		table.setBounds(460, 357, 1000, 500);
		contentPane.add(table);
		
		categories = new JPanel();
		categories.setLayout(null);
		categories.setBackground(Color.WHITE);
		categories.setBounds(460, 330, 1000, 24);
		contentPane.add(categories);
		
		label = new JLabel("Room");
		label.setFont(new Font("Serif", Font.BOLD, 16));
		label.setBounds(97, 3, 52, 19);
		categories.add(label);
		
		label_1 = new JLabel("Date");
		label_1.setFont(new Font("Serif", Font.BOLD, 16));
		label_1.setBounds(357, 3, 41, 19);
		categories.add(label_1);
		
		label_2 = new JLabel("Time");
		label_2.setFont(new Font("Serif", Font.BOLD, 16));
		label_2.setBounds(605, 0, 45, 19);
		categories.add(label_2);
		
		label_3 = new JLabel("Status");
		label_3.setFont(new Font("Serif", Font.BOLD, 16));
		label_3.setBounds(850, 3, 57, 19);
		categories.add(label_3);
		
		//pack();
		logo.requestFocusInWindow();
	}

	private  void getHistory() {
		 
		String email = Login.requester.getEmail();
		//email = "16ucs136@lnmiit.ac.in";
		 
			 String urlstring;
			 urlstring = "http://localhost:8080/requesterhistory?email="+email;
			 URL url;
			try {
				url = new URL(urlstring);
			
			 URLConnection urlcon=url.openConnection();  
			 InputStream stream=urlcon.getInputStream();  
			 
			 BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			 
			 String json = reader.readLine();
			 if(!json.equals("[]")) {
				 System.out.println(json);
				 
				 JsonArray entries = (JsonArray) new JsonParser().parse(json);
			     
				 System.out.println(entries);
			        Gson gson = new GsonBuilder().create();
				 
				 history = new ArrayList<>();
				 for(int i =0; i<entries.size(); i++) {
					 System.out.println(entries.get(i));
					 history.add(gson.fromJson(entries.get(i), RequestResponse.class));
				 }
				 
				 for(RequestResponse rre: history)
					 System.out.println(rre.getStatus());
				 
			 }else {
				 System.out.println("Nothing sent by server");
				 JOptionPane.showMessageDialog(null, "No previous bookings found!", "No History", JOptionPane.INFORMATION_MESSAGE);
				 return;
			 }
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
		
		
	}
}