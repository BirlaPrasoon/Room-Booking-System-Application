package views;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import requestResponses.RequestResponse;

import javax.swing.border.LineBorder;

public class Cancel extends JFrame {

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
	private JButton btnPreviousBookings;
	private JButton btnCancel1;
	private JButton btnCancel2;
	private JButton btnCancel3;
	private JButton btnCancel4;
	private JButton btnCancel7;
	private JButton btnCancel8;
	private JButton btnCancel6;
	private JButton btnCancel5;
	private JButton btnCancel9;
	private JButton btnCancel10;
	private JPanel CancelButtons;
	private static ArrayList<RequestResponse> requestsResponses;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
/*		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cancel frame = new Cancel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		
		getCancellable();
		
	}
	
	/**
	 * Create the frame.
	 */
	public Cancel() {
		getCancellable();
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
		
		JLabel lblBookingHistory = new JLabel("Booking Cancellation");
		lblBookingHistory.setFont(new Font("Serif", Font.BOLD, 30));
		lblBookingHistory.setBounds(780, 270, 360, 37);
		contentPane.add(lblBookingHistory);
		
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
			textField_14	.setText("Hi, User");
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
		
		btnPreviousBookings = new JButton("Previous Bookings");
		btnPreviousBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				History pb = new History();
				pb.setVisible(true);
				dispose();
			}
		});
		btnPreviousBookings.setFont(new Font("Serif", Font.BOLD, 14));
		btnPreviousBookings.setBackground(Color.WHITE);
		btnPreviousBookings.setBounds(220, 110, 179, 28);
		header.add(btnPreviousBookings);
		
		table = new JTable();
		table.setGridColor(new Color(0, 120, 215));
		table.setBorder(new LineBorder(new Color(0, 120, 215)));
		table.setRowHeight(50);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		
	    int size = requestsResponses.size()>10?10:requestsResponses.size();
	    Object[][] data =  new Object[size][4];
	    
	    for(int i = 0; i<size; i++) {
	    	data[i][0] = requestsResponses.get(i).getRoomId();
	    	data[i][1] = requestsResponses.get(i).getDate();
	    	data[i][2] = requestsResponses.get(i).getFrom_time() + "-" + requestsResponses.get(i).getTo_time();
	    	data[i][3] = requestsResponses.get(i).getStatus();
	    }
		
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"Room", "Date", "Timings", "Status"
			}
		));
		
		table.setFont(new Font("Serif", Font.BOLD, 14));
		table.setBounds(425, 357, 1000, 500);
		contentPane.add(table);
		
		categories = new JPanel();
		categories.setLayout(null);
		categories.setBackground(Color.WHITE);
		categories.setBounds(425, 330, 1000, 24);
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
		
		CancelButtons = new JPanel();
		CancelButtons.setBackground(Color.WHITE);
		CancelButtons.setBounds(1465, 369, 100, 475);
		contentPane.add(CancelButtons);
		CancelButtons.setLayout(null);
		
		btnCancel1 = new JButton("Cancel");
		btnCancel1.setBounds(0, 0, 100, 28);
		CancelButtons.add(btnCancel1);
		btnCancel1.setBorder(new LineBorder(new Color(0, 120, 215)));
		btnCancel1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancel1.setEnabled(false);
				btnCancel1.setText("Cancelled");
				cancel(0);
			}
		});
		btnCancel1.setFont(new Font("Serif", Font.BOLD, 14));
		btnCancel1.setForeground(Color.RED);
		btnCancel1.setBackground(Color.WHITE);
		
		btnCancel2 = new JButton("Cancel");
		btnCancel2.setBounds(0, 51, 100, 28);
		CancelButtons.add(btnCancel2);
		btnCancel2.setBorder(new LineBorder(new Color(0, 120, 215)));
		btnCancel2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancel2.setEnabled(false);
				btnCancel2.setText("Cancelled");
				cancel(1);
			}
		});
		btnCancel2.setFont(new Font("Serif", Font.BOLD, 14));
		btnCancel2.setForeground(Color.RED);
		btnCancel2.setBackground(Color.WHITE);
		
		btnCancel3 = new JButton("Cancel");
		btnCancel3.setBounds(0, 98, 100, 28);
		CancelButtons.add(btnCancel3);
		btnCancel3.setBorder(new LineBorder(new Color(0, 120, 215)));
		btnCancel3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancel3.setEnabled(false);
				btnCancel3.setText("Cancelled");
				cancel(2);
			}
		});
		btnCancel3.setFont(new Font("Serif", Font.BOLD, 14));
		btnCancel3.setForeground(Color.RED);
		btnCancel3.setBackground(Color.WHITE);
		
		btnCancel4 = new JButton("Cancel");
		btnCancel4.setBounds(0, 149, 100, 28);
		CancelButtons.add(btnCancel4);
		btnCancel4.setBorder(new LineBorder(new Color(0, 120, 215)));
		btnCancel4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancel4.setEnabled(false);
				btnCancel4.setText("Cancelled");
				cancel(3);
			}
		});
		btnCancel4.setFont(new Font("Serif", Font.BOLD, 14));
		btnCancel4.setForeground(Color.RED);
		btnCancel4.setBackground(Color.WHITE);
		
		btnCancel5 = new JButton("Cancel");
		btnCancel5.setBounds(0, 196, 100, 28);
		CancelButtons.add(btnCancel5);
		btnCancel5.setBorder(new LineBorder(new Color(0, 120, 215)));
		btnCancel5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancel5.setEnabled(false);
				btnCancel5.setText("Cancelled");
				cancel(4);
			}
		});
		btnCancel5.setFont(new Font("Serif", Font.BOLD, 14));
		btnCancel5.setForeground(Color.RED);
		btnCancel5.setBackground(Color.WHITE);
		
		btnCancel6 = new JButton("Cancel");
		btnCancel6.setBounds(0, 247, 100, 28);
		CancelButtons.add(btnCancel6);
		btnCancel6.setBorder(new LineBorder(new Color(0, 120, 215)));
		btnCancel6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancel6.setEnabled(false);
				btnCancel6.setText("Cancelled");
				cancel(5);
			}
		});
		btnCancel6.setFont(new Font("Serif", Font.BOLD, 14));
		btnCancel6.setForeground(Color.RED);
		btnCancel6.setBackground(Color.WHITE);
		
		btnCancel7 = new JButton("Cancel");
		btnCancel7.setBounds(0, 294, 100, 28);
		CancelButtons.add(btnCancel7);
		btnCancel7.setBorder(new LineBorder(new Color(0, 120, 215)));
		btnCancel7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancel7.setEnabled(false);
				btnCancel7.setText("Cancelled");
				cancel(6);
			}
		});
		btnCancel7.setFont(new Font("Serif", Font.BOLD, 14));
		btnCancel7.setForeground(Color.RED);
		btnCancel7.setBackground(Color.WHITE);
		
		btnCancel8 = new JButton("Cancel");
		btnCancel8.setBounds(0, 345, 100, 28);
		CancelButtons.add(btnCancel8);
		btnCancel8.setBorder(new LineBorder(new Color(0, 120, 215)));
		btnCancel8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancel8.setEnabled(false);
				btnCancel8.setText("Cancelled");
				cancel(7);
			}
		});
		btnCancel8.setFont(new Font("Serif", Font.BOLD, 14));
		btnCancel8.setForeground(Color.RED);
		btnCancel8.setBackground(Color.WHITE);
		
		btnCancel9 = new JButton("Cancel");
		btnCancel9.setBounds(0, 396, 100, 28);
		CancelButtons.add(btnCancel9);
		btnCancel9.setBorder(new LineBorder(new Color(0, 120, 215)));
		btnCancel9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancel9.setEnabled(false);
				btnCancel9.setText("Cancelled");
				cancel(8);
			}
		});
		btnCancel9.setFont(new Font("Serif", Font.BOLD, 14));
		btnCancel9.setForeground(Color.RED);
		btnCancel9.setBackground(Color.WHITE);
		
		btnCancel10 = new JButton("Cancel");
		btnCancel10.setBounds(0, 447, 100, 28);
		CancelButtons.add(btnCancel10);
		btnCancel10.setBorder(new LineBorder(new Color(0, 120, 215)));
		btnCancel10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancel10.setEnabled(false);
				btnCancel10.setText("Cancelled");
				cancel(9);
			}
		});
		btnCancel10.setFont(new Font("Serif", Font.BOLD, 14));
		btnCancel10.setForeground(Color.RED);
		btnCancel10.setBackground(Color.WHITE);
		
		//pack();
		logo.requestFocusInWindow();
	}
	
	
	
	public static void cancel(int index) {
		
		if(requestsResponses !=null) {
		
		String urlstring; 
		urlstring = "http://localhost:8080/cancellable?requestId="+ requestsResponses.get(index).getRequestId();
		 
		 try {
				URL url = new URL(urlstring);
			
			 URLConnection urlcon=url.openConnection();  
			 urlcon.setDoOutput(true);
			 OutputStreamWriter writer = new OutputStreamWriter(urlcon.getOutputStream());
			 
			 writer.write("data send by client");
			    writer.flush();
			    String line;
			    BufferedReader reader = new BufferedReader(new 
			                                     InputStreamReader(urlcon.getInputStream()));
			    
			    String response = "";
			    
			    while ((line = reader.readLine()) != null) {
			      System.out.println(line);
			      response +=line;
			    }
			    writer.close();
			    reader.close();
			    
			 if(!response.equals("Request status: cancelled")) {
				 System.out.println("Response: "+ response);
				 JOptionPane.showMessageDialog(null, "Server failed to cancel request", "Cancel Request Failed", JOptionPane.ERROR_MESSAGE);
			 }
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
		 }
	}
	
	public static void getCancellable() {
		
		
		String email;
		email = Login.requester.getEmail();
//		email = "16ucs136@lnmiit.ac.in";
		 
			 String urlstring = "http://localhost:8080/cancellable?email=16ucs136@lnmiit.ac.in";
			 urlstring = "http://localhost:8080/cancellable?email="+email;
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
				 
				 requestsResponses = new ArrayList<>();
				 for(int i =0; i<entries.size(); i++) {
					 System.out.println(entries.get(i));
					 requestsResponses.add(gson.fromJson(entries.get(i), RequestResponse.class));
				 }
				 
				 for(RequestResponse rre: requestsResponses)
					 System.out.println(rre.getStatus());
				 
			 }else {
				 System.out.println("Nothing sent by server");
				 JOptionPane.showMessageDialog(null, "No pending requests found!", "No Requests", JOptionPane.INFORMATION_MESSAGE);
				 return;
			 }
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
	}
}