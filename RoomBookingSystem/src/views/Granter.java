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
import java.util.Vector;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class Granter extends JFrame {

	private JPanel contentPane;
	private JLabel lblRequests;
	private JLabel lblRoom;
	private JLabel lblDate;
	private JLabel lblTime;
	private JLabel lblapprovedecline;
	private JPanel header;
	private JButton button;
	private JLabel label;
	private JLabel logo;
	private JTextField textField_16;
	private JPanel categories;
	private JButton Approve1;
	private JButton Decline1;
	private JButton Approve2;
	private JButton Decline2;
	private JButton Approve3;
	private JButton Decline3;
	private JButton Approve4;
	private JButton Decline4;
	private JButton Approve5;
	private JButton Decline5;
	private JButton Approve6;
	private JButton Decline6;
	private JButton Approve7;
	private JButton Decline7;
	private JButton Approve8;
	private JButton Decline8;
	private JButton Approve9;
	private JButton Decline9;
	private JButton Approve10;
	private JButton Decline10;
	private JButton btnRefresh;
	private JTable table;

	static Object[][] data;
	static ArrayList<RequestResponse> requestsResponses;
	private JLabel lblAc;
	private JLabel lblMic;
	private JLabel lblProjector;
	private JPanel buttons;
	
	DefaultTableModel model;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		updateStatus(0, "denied");
		
	}

	/**
	 * Create the frame.
	 */
	
	public Granter() {
		getRequests();
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
		
		lblRequests = new JLabel("Requests");
		lblRequests.setFont(new Font("Serif", Font.BOLD, 30));
		lblRequests.setBounds(883, 270, 154, 37);
		contentPane.add(lblRequests);
		
		categories = new JPanel();
		categories.setBackground(Color.WHITE);
		categories.setBounds(285, 326, 1420, 24);
		contentPane.add(categories);
		categories.setLayout(null);
		
		lblRoom = new JLabel("Room");
		lblRoom.setBounds(52, 3, 45, 18);
		categories.add(lblRoom);
		lblRoom.setFont(new Font("Serif", Font.BOLD, 14));
		
		lblDate = new JLabel("Date");
		lblDate.setBounds(232, 3, 36, 18);
		categories.add(lblDate);
		lblDate.setFont(new Font("Serif", Font.BOLD, 14));
		
		lblTime = new JLabel("Time");
		lblTime.setBounds(430, 3, 39, 18);
		categories.add(lblTime);
		lblTime.setFont(new Font("Serif", Font.BOLD, 14));
		
		lblapprovedecline = new JLabel("Approve/Decline");
		lblapprovedecline.setBounds(1270, 3, 128, 18);
		categories.add(lblapprovedecline);
		lblapprovedecline.setFont(new Font("Serif", Font.BOLD, 14));
		
		JLabel lblPurpose = new JLabel("Purpose");
		lblPurpose.setFont(new Font("Serif", Font.BOLD, 14));
		lblPurpose.setBounds(768, 5, 64, 18);
		categories.add(lblPurpose);
		
		lblAc = new JLabel("AC");
		lblAc.setFont(new Font("Serif", Font.BOLD, 14));
		lblAc.setBounds(1064, 5, 22, 18);
		categories.add(lblAc);
		
		lblMic = new JLabel("Mic");
		lblMic.setFont(new Font("Serif", Font.BOLD, 14));
		lblMic.setBounds(1110, 5, 30, 18);
		categories.add(lblMic);
		
		lblProjector = new JLabel("Projector");
		lblProjector.setFont(new Font("Serif", Font.BOLD, 14));
		lblProjector.setBounds(1155, 5, 72, 18);
		categories.add(lblProjector);
		
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
		
		label = new JLabel("Room Booking System");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Serif", Font.BOLD, 35));
		label.setBounds(50, 15, 438, 42);
		header.add(label);
		
		logo = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("LNMIIT.jpg"));
		logo.setIcon(img);
		logo.setBounds(1640, 8, 200, 92);
		header.add(logo);
		
		textField_16 = new JTextField();
		if(Login.requester !=null){
			textField_16.setText("Hi, " + Login.requester.getName());
		}else
			textField_16.setText("Hi, User");
		textField_16.setForeground(Color.WHITE);
		textField_16.setFont(new Font("Serif", Font.PLAIN, 14));
		textField_16.setEditable(false);
		textField_16.setColumns(10);
		textField_16.setBorder(null);
		textField_16.setBackground(new Color(0, 120, 215));
		textField_16.setBounds(1640, 115, 120, 17);
		header.add(textField_16);
		
		buttons = new JPanel();
		buttons.setBackground(Color.WHITE);
		buttons.setBounds(1527, 362, 178, 475);
		contentPane.add(buttons);
		buttons.setLayout(null);
		
		Approve1 = new JButton("Approve");
		Approve1.setBounds(0, 0, 91, 25);
		buttons.add(Approve1);
		Approve1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Approve1.setBackground(Color.GREEN);
				Decline1.setForeground(Color.RED);
				Decline1.setBackground(Color.WHITE);
				updateStatus(0,"approved");
			}
		});
		Approve1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Approve1.setFont(new Font("Serif", Font.BOLD, 12));
		Approve1.setForeground(Color.GREEN);
		Approve1.setBackground(Color.WHITE);
		
		Decline1 = new JButton("Decline");
		Decline1.setBounds(92, 0, 86, 25);
		buttons.add(Decline1);
		Decline1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Decline1.setBackground(Color.RED);
				Approve1.setForeground(Color.GREEN);
				Approve1.setBackground(Color.WHITE);
				updateStatus(0,"denied");
			}
		});
		Decline1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Decline1.setFont(new Font("Serif", Font.BOLD, 12));
		Decline1.setForeground(Color.RED);
		Decline1.setBackground(Color.WHITE);
		
		Approve2 = new JButton("Approve");
		Approve2.setBounds(0, 50, 91, 25);
		buttons.add(Approve2);
		Approve2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Approve2.setBackground(Color.GREEN);
				Decline2.setForeground(Color.RED);
				Decline2.setBackground(Color.WHITE);
				updateStatus(1,"approved");
			}
		});
		Approve2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Approve2.setFont(new Font("Serif", Font.BOLD, 12));
		Approve2.setForeground(Color.GREEN);
		Approve2.setBackground(Color.WHITE);
		
		Decline2 = new JButton("Decline");
		Decline2.setBounds(92, 50, 86, 25);
		buttons.add(Decline2);
		Decline2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Decline2.setBackground(Color.RED);
				Approve2.setForeground(Color.GREEN);
				Approve2.setBackground(Color.WHITE);
				updateStatus(1,"denied");
			}
		});
		Decline2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Decline2.setFont(new Font("Serif", Font.BOLD, 12));
		Decline2.setForeground(Color.RED);
		Decline2.setBackground(Color.WHITE);
		
		Approve3 = new JButton("Approve");
		Approve3.setBounds(0, 100, 91, 25);
		buttons.add(Approve3);
		Approve3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Approve3.setBackground(Color.GREEN);
				Decline3.setForeground(Color.RED);
				Decline3.setBackground(Color.WHITE);
				updateStatus(2,"approved");
			}
		});
		Approve3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Approve3.setFont(new Font("Serif", Font.BOLD, 12));
		Approve3.setForeground(Color.GREEN);
		Approve3.setBackground(Color.WHITE);
		
		Decline3 = new JButton("Decline");
		Decline3.setBounds(92, 100, 86, 25);
		buttons.add(Decline3);
		Decline3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Decline3.setBackground(Color.RED);
				Approve3.setForeground(Color.GREEN);
				Approve3.setBackground(Color.WHITE);
				updateStatus(2,"denied");
			}
		});
		Decline3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Decline3.setFont(new Font("Serif", Font.BOLD, 12));
		Decline3.setForeground(Color.RED);
		Decline3.setBackground(Color.WHITE);
		
		Approve4 = new JButton("Approve");
		Approve4.setBounds(0, 150, 91, 25);
		buttons.add(Approve4);
		Approve4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Approve4.setBackground(Color.GREEN);
				Decline4.setForeground(Color.RED);
				Decline4.setBackground(Color.WHITE);
				updateStatus(3,"approved");
			}
		});
		Approve4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Approve4.setFont(new Font("Serif", Font.BOLD, 12));
		Approve4.setForeground(Color.GREEN);
		Approve4.setBackground(Color.WHITE);
		
		Decline4 = new JButton("Decline");
		Decline4.setBounds(92, 150, 86, 25);
		buttons.add(Decline4);
		Decline4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Decline4.setBackground(Color.RED);
				Approve4.setForeground(Color.GREEN);
				Approve4.setBackground(Color.WHITE);
				updateStatus(3,"denied");
			}
		});
		Decline4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Decline4.setFont(new Font("Serif", Font.BOLD, 12));
		Decline4.setForeground(Color.RED);
		Decline4.setBackground(Color.WHITE);
		
		Approve5 = new JButton("Approve");
		Approve5.setBounds(0, 200, 91, 25);
		buttons.add(Approve5);
		Approve5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Approve5.setBackground(Color.GREEN);
				Decline5.setForeground(Color.RED);
				Decline5.setBackground(Color.WHITE);
				updateStatus(4,"approved");
			}
		});
		Approve5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Approve5.setFont(new Font("Serif", Font.BOLD, 12));
		Approve5.setForeground(Color.GREEN);
		Approve5.setBackground(Color.WHITE);
		
		Decline5 = new JButton("Decline");
		Decline5.setBounds(92, 200, 86, 25);
		buttons.add(Decline5);
		Decline5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Decline5.setBackground(Color.RED);
				Approve5.setForeground(Color.GREEN);
				Approve5.setBackground(Color.WHITE);
				updateStatus(4,"denied");
			}
		});
		Decline5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Decline5.setFont(new Font("Serif", Font.BOLD, 12));
		Decline5.setForeground(Color.RED);
		Decline5.setBackground(Color.WHITE);
		
		Approve6 = new JButton("Approve");
		Approve6.setBounds(0, 250, 91, 25);
		buttons.add(Approve6);
		Approve6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Approve6.setBackground(Color.GREEN);
				Decline6.setForeground(Color.RED);
				Decline6.setBackground(Color.WHITE);
				updateStatus(5,"approved");
			}
		});
		Approve6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Approve6.setFont(new Font("Serif", Font.BOLD, 12));
		Approve6.setForeground(Color.GREEN);
		Approve6.setBackground(Color.WHITE);
		
		Decline6 = new JButton("Decline");
		Decline6.setBounds(92, 250, 86, 25);
		buttons.add(Decline6);
		Decline6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Decline6.setBackground(Color.RED);
				Approve6.setForeground(Color.GREEN);
				Approve6.setBackground(Color.WHITE);
				updateStatus(5,"denied");
			}
		});
		Decline6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Decline6.setFont(new Font("Serif", Font.BOLD, 12));
		Decline6.setForeground(Color.RED);
		Decline6.setBackground(Color.WHITE);
		
		Approve7 = new JButton("Approve");
		Approve7.setBounds(0, 300, 91, 25);
		buttons.add(Approve7);
		Approve7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Approve7.setBackground(Color.GREEN);
				Decline7.setForeground(Color.RED);
				Decline7.setBackground(Color.WHITE);
				updateStatus(6,"approved");
			}
		});
		Approve7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Approve7.setFont(new Font("Serif", Font.BOLD, 12));
		Approve7.setForeground(Color.GREEN);
		Approve7.setBackground(Color.WHITE);
		
		Decline7 = new JButton("Decline");
		Decline7.setBounds(92, 300, 86, 25);
		buttons.add(Decline7);
		Decline7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Decline7.setBackground(Color.RED);
				Approve7.setForeground(Color.GREEN);
				Approve7.setBackground(Color.WHITE);
				updateStatus(6,"denied");
			}
		});
		Decline7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Decline7.setFont(new Font("Serif", Font.BOLD, 12));
		Decline7.setForeground(Color.RED);
		Decline7.setBackground(Color.WHITE);
		
		Approve8 = new JButton("Approve");
		Approve8.setBounds(0, 350, 91, 25);
		buttons.add(Approve8);
		Approve8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Approve8.setBackground(Color.GREEN);
				Decline8.setForeground(Color.RED);
				Decline8.setBackground(Color.WHITE);
				updateStatus(7,"approved");
			}
		});
		Approve8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Approve8.setFont(new Font("Serif", Font.BOLD, 12));
		Approve8.setForeground(Color.GREEN);
		Approve8.setBackground(Color.WHITE);
		
		Decline8 = new JButton("Decline");
		Decline8.setBounds(92, 350, 86, 25);
		buttons.add(Decline8);
		Decline8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Decline8.setBackground(Color.RED);
				Approve8.setForeground(Color.GREEN);
				Approve8.setBackground(Color.WHITE);
				updateStatus(7,"denied");
			}
		});
		Decline8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Decline8.setFont(new Font("Serif", Font.BOLD, 12));
		Decline8.setForeground(Color.RED);
		Decline8.setBackground(Color.WHITE);
		
		Approve9 = new JButton("Approve");
		Approve9.setBounds(0, 400, 91, 25);
		buttons.add(Approve9);
		Approve9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Approve9.setBackground(Color.GREEN);
				Decline9.setForeground(Color.RED);
				Decline9.setBackground(Color.WHITE);
				updateStatus(8,"approved");
			}
		});
		Approve9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Approve9.setFont(new Font("Serif", Font.BOLD, 12));
		Approve9.setForeground(Color.GREEN);
		Approve9.setBackground(Color.WHITE);
		
		Decline9 = new JButton("Decline");
		Decline9.setBounds(92, 400, 86, 25);
		buttons.add(Decline9);
		Decline9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Decline9.setBackground(Color.RED);
				Approve9.setForeground(Color.GREEN);
				Approve9.setBackground(Color.WHITE);
				updateStatus(8,"denied");
			}
		});
		Decline9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Decline9.setFont(new Font("Serif", Font.BOLD, 12));
		Decline9.setForeground(Color.RED);
		Decline9.setBackground(Color.WHITE);
		
		Approve10 = new JButton("Approve");
		Approve10.setBounds(0, 450, 91, 25);
		buttons.add(Approve10);
		Approve10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Approve10.setBackground(Color.GREEN);
				Decline10.setForeground(Color.RED);
				Decline10.setBackground(Color.WHITE);
				updateStatus(9,"approved");
			}
		});
		Approve10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Approve10.setFont(new Font("Serif", Font.BOLD, 12));
		Approve10.setForeground(Color.GREEN);
		Approve10.setBackground(Color.WHITE);
		
		Decline10 = new JButton("Decline");
		Decline10.setBounds(92, 450, 86, 25);
		buttons.add(Decline10);
		Decline10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Decline10.setBackground(Color.RED);
				Approve10.setForeground(Color.GREEN);
				Approve10.setBackground(Color.WHITE);
				updateStatus(9,"denied");
			}
		});
		Decline10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Decline10.setFont(new Font("Serif", Font.BOLD, 12));
		Decline10.setForeground(Color.RED);
		Decline10.setBackground(Color.WHITE);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRefresh.setBackground(new Color(0, 120, 215));
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setFont(new Font("Serif", Font.BOLD, 14));
		btnRefresh.setBounds(285, 874, 100, 40);
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getRequests();
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
		});
		contentPane.add(btnRefresh);
		
		table = new JTable();
		table.setGridColor(new Color(0, 120, 215));
		table.setBorder(new LineBorder(new Color(0, 120, 215)));
		table.setRowHeight(50);
		
	    model = buildDataTable();
		
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(0).setMinWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setMinWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setMinWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(500);
		table.getColumnModel().getColumn(3).setMinWidth(500);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setMinWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setMinWidth(50);
		table.getColumnModel().getColumn(6).setPreferredWidth(80);
		table.getColumnModel().getColumn(6).setMinWidth(80);		
		table.setFont(new Font("Serif", Font.PLAIN, 14));
		table.setBounds(285, 360, 1230, 500);
		contentPane.add(table);
		
		updateButtons(model.getDataVector());
		//pack();
		logo.requestFocusInWindow();
	}

	private DefaultTableModel buildDataTable() {
		if(requestsResponses!=null) {
		int size = requestsResponses.size()>10?10:requestsResponses.size();
	    data =  new Object[size][7];
	    
	    for(int i = 0; i<size; i++) {
	    	data[i][0] = requestsResponses.get(i).getRoomId();
	    	data[i][1] = requestsResponses.get(i).getDate();
	    	data[i][2] = requestsResponses.get(i).getFrom_time() + "-" + requestsResponses.get(i).getTo_time();
	    	data[i][3] = requestsResponses.get(i).getPurpose();
	    	data[i][4] = requestsResponses.get(i).getAc();
	    	data[i][5] = requestsResponses.get(i).getMic();
	    	data[i][6] = requestsResponses.get(i).getProjector();
	    }
		}
		if(data == null) {
			Object[][] data = new Object[1][7];
			
	    	data[0][0] = null;
	    	data[0][1] = null;
	    	data[0][2] = null;
	    	data[0][3] = null;
	    	data[0][4] = null;
	    	data[0][5] = null;
	    	data[0][6] = null;
		}
		DefaultTableModel model = new DefaultTableModel(
				data,
				new String[] {
					"Room", "Date", "Time", "Purpose", "Ac", "Mic", "Projector"
				}
			);
		return model;
	}
	
	public static void updateStatus(int i, String status) {
		RequestResponse r =  requestsResponses.get(i);
		
		System.out.println("Our status is : ");
		
		String urlstring;
		 urlstring = "http://localhost:8080/setStatus?"
		 		+"&requestId=" + r.getRequestId()
		 		+"&roomId=" + "LT2"
		 		+ "&status=" +status;
		 
//		 http://localhost:8080/setStatus?requestId=2&roomId=LT2&status=denied
		 
		 try {
				URL url = new URL(urlstring);
			
			 URLConnection urlcon=url.openConnection();  
			 
			
			    String line;
			    BufferedReader reader = new BufferedReader(new 
			                                     InputStreamReader(urlcon.getInputStream()));
			    
			    String response = "";
			    System.out.println(urlstring);
			    
			    while ((line = reader.readLine()) != null) {
			      System.out.println(line);
			      response +=line;
			    }
			    
		 if(!response.equals("Request status: " + status)) {
			 System.out.println("Response: "+ response);
//			 JOptionPane.showMessageDialog(null, "Server failed to update status", "Update status Failed", JOptionPane.ERROR_MESSAGE);
		 }
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
	}
	
	private void updateButtons(Vector dataVector) {
		// TODO Auto-generated method stub
		
		int size = dataVector.size();
		
		if(size>=10) {
			return;
		}else {
			if(size<11 && size<10) {
				Approve10.hide();
				Decline10.hide();
			}
			if(size<11 && size<9) {
				Approve9.hide();
				Decline9.hide();
			}
			if(size<11 && size<8) {
				Approve8.hide();
				Decline8.hide();
			}
			if(size<11 && size<7) {
				Approve7.hide();
				Decline7.hide();
			}
			if(size<11 && size<6) {
				Approve6.hide();
				Decline6.hide();
			}
			if(size<11 && size<5) {
				Approve5.hide();
				Decline5.hide();
			}
			if(size<11 && size<4) {
				Approve4.hide();
				Decline4.hide();
			}
			if(size<11 && size<3) {
				Approve3.hide();
				Decline3.hide();
			}
			if(size<11 && size<2) {
				Approve2.hide();
				Decline2.hide();
			}
			if(size<11 && size<1) {
				Approve1.hide();
				Decline1.hide();
			}
		}
	}
		
	private  void getRequests() {
			 
			String email;
			email = Login.requester.getEmail();
//			email = "16ucs136@lnmiit.ac.in";
			 
				 String urlstring;
				 urlstring = "http://localhost:8080/granter?granterId="+email;
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