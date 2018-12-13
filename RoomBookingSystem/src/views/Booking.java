package views;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.toedter.calendar.JDateChooser;

import requestResponses.Timeline;

public class Booking extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel label;
	private JLabel logo;
	private JPanel header;
	private JButton btnLogout;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton blt1;
	private JButton blt2;
	private JButton blt3;
	private JButton blt4;
	private JButton blt5;
	private JButton blt6;
	private JButton blt7;
	private JButton blt8;
	private JButton blt9;
	private JButton blt10;
	private JButton bsac;
	private JButton bcpLab1;
	private JButton bcpLab2;
	private JButton bbeLab;
	private JRadioButton rdbtnSmall;
	private JRadioButton rdbtnLarge;
	private JCheckBox chckbxAc;
	private JCheckBox chckbxWhiteBoard;
	private JLabel lblFacilitiesRequired;
	private JLabel lblDate;
	private JPanel outerPanel;
	private JTextField LT;
	private  JTextField bookingDate;
	private JLabel lblBookedslots_1;
	private JLabel lblBookedslots_2;
	private JLabel lblBookedslots_3;
	private JLabel lblBookedslots_4;
	private JCheckBox chckbxAc2;
	
	private  ArrayList<Timeline> timeline;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Booking frame = new Booking();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		
//		getTimeline();
	}

	/**
	 * Create the frame.
	 */
	public Booking() {
		
		getTimeline();
		
		setTitle("Room Booking System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0,screen.width,screen.height);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 120, 215));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		header = new JPanel();
		header.setSize(1920, 150);
		header.setLayout(null);
		header.setBackground(new Color(0, 120, 215));
		contentPane.add(header, BorderLayout.NORTH);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
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
		btnLogout.setBackground(Color.WHITE);
		btnLogout.setForeground(Color.BLACK);
		btnLogout.setFont(new Font("Serif", Font.BOLD, 14));
		btnLogout.setBounds(1752, 110, 88, 28);
		header.add(btnLogout);
		
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
		
		textField = new JTextField();
		if(Login.requester !=null){
			textField.setText("Hi, " + Login.requester.getName());
		}else
			textField.setText("Hi, User");
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Serif", Font.PLAIN, 14));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBackground(new Color(0, 120, 215));
		textField.setBounds(1640, 115, 120, 17);
		header.add(textField);
		
		JButton btnHome = new JButton("Previous Bookings");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				History pb = new History();
				pb.setVisible(true);
				dispose();
			}
		});
		btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHome.setFont(new Font("Serif", Font.BOLD, 14));
		btnHome.setBackground(Color.WHITE);
		btnHome.setBounds(50, 110, 179, 28);
		header.add(btnHome);
		
		JPanel RoomSelection = new JPanel();
		RoomSelection.setBackground(Color.WHITE);
		RoomSelection.setBounds(0, 150, 959, 930);
		contentPane.add(RoomSelection);
		RoomSelection.setLayout(null);

		
		JLabel lblRoomType = new JLabel("Room Type:");
		lblRoomType.setFont(new Font("Serif", Font.BOLD, 30));
		lblRoomType.setBounds(50, 150, 197, 37);
		RoomSelection.add(lblRoomType);
		
		rdbtnSmall = new JRadioButton("Small");
		rdbtnSmall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxWhiteBoard.isSelected()) {
					blt1.setEnabled(false);
					blt1.setBackground(Color.BLACK);
					blt2.setEnabled(false);
					blt2.setBackground(Color.BLACK);
					blt3.setEnabled(false);
					blt3.setBackground(Color.BLACK);
					blt4.setEnabled(false);
					blt4.setBackground(Color.BLACK);
					blt5.setEnabled(false);
					blt5.setBackground(Color.BLACK);
					blt6.setEnabled(false);
					blt6.setBackground(Color.BLACK);
					blt7.setEnabled(false);
					blt7.setBackground(Color.BLACK);
					blt8.setEnabled(false);
					blt8.setBackground(Color.BLACK);
					blt9.setEnabled(false);
					blt9.setBackground(Color.BLACK);
					blt10.setEnabled(false);
					blt10.setBackground(Color.BLACK);
					bsac.setEnabled(false);
					bsac.setBackground(Color.BLACK);
					bcpLab1.setEnabled(false);
					bcpLab1.setBackground(Color.BLACK);
					bcpLab2.setEnabled(false);
					bcpLab2.setBackground(Color.BLACK);
					bbeLab.setEnabled(false);
					bbeLab.setBackground(Color.BLACK);
				}
				else {
					if(chckbxAc.isSelected()) {
						blt1.setEnabled(false);
						blt1.setBackground(Color.BLACK);
						blt2.setEnabled(false);
						blt2.setBackground(Color.BLACK);
						blt3.setEnabled(false);
						blt3.setBackground(Color.BLACK);
						blt4.setEnabled(false);
						blt4.setBackground(Color.BLACK);
						blt5.setEnabled(true);
						blt5.setBackground(new Color(0, 120, 215));
						blt6.setEnabled(false);
						blt6.setBackground(Color.BLACK);
						blt7.setEnabled(false);
						blt7.setBackground(Color.BLACK);
						blt8.setEnabled(false);
						blt8.setBackground(Color.BLACK);
						blt9.setEnabled(false);
						blt9.setBackground(Color.BLACK);
						blt10.setEnabled(false);
						blt10.setBackground(Color.BLACK);
						bsac.setEnabled(false);
						bsac.setBackground(Color.BLACK);
						bcpLab1.setEnabled(false);
						bcpLab1.setBackground(Color.BLACK);
						bcpLab2.setEnabled(false);
						bcpLab2.setBackground(Color.BLACK);
						bbeLab.setEnabled(false);
						bbeLab.setBackground(Color.BLACK);
					}
					else {
						blt1.setEnabled(false);
						blt1.setBackground(Color.BLACK);
						blt2.setEnabled(false);
						blt2.setBackground(Color.BLACK);
						blt3.setEnabled(true);
						blt3.setBackground(new Color(0, 120, 215));
						blt4.setEnabled(true);
						blt4.setBackground(new Color(0, 120, 215));
						blt5.setEnabled(true);
						blt5.setBackground(new Color(0, 120, 215));
						blt6.setEnabled(true);
						blt6.setBackground(new Color(0, 120, 215));
						blt7.setEnabled(true);
						blt7.setBackground(new Color(0, 120, 215));
						blt8.setEnabled(true);
						blt8.setBackground(new Color(0, 120, 215));
						blt9.setEnabled(false);
						blt9.setBackground(Color.BLACK);
						blt10.setEnabled(false);
						blt10.setBackground(Color.BLACK);
						bsac.setEnabled(false);
						bsac.setBackground(Color.BLACK);
						bcpLab1.setEnabled(false);
						bcpLab1.setBackground(Color.BLACK);
						bcpLab2.setEnabled(false);
						bcpLab2.setBackground(Color.BLACK);
						bbeLab.setEnabled(false);
						bbeLab.setBackground(Color.BLACK);
					}
				}
			}
		});
		rdbtnSmall.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnSmall.setFont(new Font("Serif", Font.BOLD, 20));
		buttonGroup.add(rdbtnSmall);
		rdbtnSmall.setBackground(Color.WHITE);
		rdbtnSmall.setBounds(50, 199, 89, 32);
		RoomSelection.add(rdbtnSmall);
		
		rdbtnLarge = new JRadioButton("Large");
		rdbtnLarge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxAc.isSelected()) {
					blt1.setEnabled(false);
					blt1.setBackground(Color.BLACK);
					blt2.setEnabled(false);
					blt2.setBackground(Color.BLACK);
					blt3.setEnabled(false);
					blt3.setBackground(Color.BLACK);
					blt4.setEnabled(false);
					blt4.setBackground(Color.BLACK);
					blt5.setEnabled(false);
					blt5.setBackground(Color.BLACK);
					blt6.setEnabled(false);
					blt6.setBackground(Color.BLACK);
					blt7.setEnabled(false);
					blt7.setBackground(Color.BLACK);
					blt8.setEnabled(false);
					blt8.setBackground(Color.BLACK);
					blt9.setEnabled(false);
					blt9.setBackground(Color.BLACK);
					blt10.setEnabled(false);
					blt10.setBackground(Color.BLACK);
					bsac.setEnabled(false);
					bsac.setBackground(Color.BLACK);
					bcpLab1.setEnabled(true);
					bcpLab1.setBackground(new Color(0, 120, 215));
					bcpLab2.setEnabled(true);
					bcpLab2.setBackground(new Color(0, 120, 215));
					bbeLab.setEnabled(true);
					bbeLab.setBackground(new Color(0, 120, 215));
				}
				else {
					if(chckbxWhiteBoard.isSelected()) {
						blt1.setEnabled(false);
						blt1.setBackground(Color.BLACK);
						blt2.setEnabled(false);
						blt2.setBackground(Color.BLACK);
						blt3.setEnabled(false);
						blt3.setBackground(Color.BLACK);
						blt4.setEnabled(false);
						blt4.setBackground(Color.BLACK);
						blt5.setEnabled(false);
						blt5.setBackground(Color.BLACK);
						blt6.setEnabled(false);
						blt6.setBackground(Color.BLACK);
						blt7.setEnabled(false);
						blt7.setBackground(Color.BLACK);
						blt8.setEnabled(false);
						blt8.setBackground(Color.BLACK);
						blt9.setEnabled(false);
						blt9.setBackground(Color.BLACK);
						blt10.setEnabled(true);
						blt10.setBackground(new Color(0, 120, 215));
						bsac.setEnabled(false);
						bsac.setBackground(Color.BLACK);
						bcpLab1.setEnabled(true);
						bcpLab1.setBackground(new Color(0, 120, 215));
						bcpLab2.setEnabled(true);
						bcpLab2.setBackground(new Color(0, 120, 215));
						bbeLab.setEnabled(true);
						bbeLab.setBackground(new Color(0, 120, 215));
					}
					else {
						blt1.setEnabled(true);
						blt1.setBackground(new Color(0, 120, 215));
						blt2.setEnabled(true);
						blt2.setBackground(new Color(0, 120, 215));
						blt3.setEnabled(false);
						blt3.setBackground(Color.BLACK);
						blt4.setEnabled(false);
						blt4.setBackground(Color.BLACK);
						blt5.setEnabled(false);
						blt5.setBackground(Color.BLACK);
						blt6.setEnabled(false);
						blt6.setBackground(Color.BLACK);
						blt7.setEnabled(false);
						blt7.setBackground(Color.BLACK);
						blt8.setEnabled(false);
						blt8.setBackground(Color.BLACK);
						blt9.setEnabled(true);
						blt9.setBackground(new Color(0, 120, 215));
						blt10.setEnabled(true);
						blt10.setBackground(new Color(0, 120, 215));
						bsac.setEnabled(true);
						bsac.setBackground(new Color(0, 120, 215));
						bcpLab1.setEnabled(true);
						bcpLab1.setBackground(new Color(0, 120, 215));
						bcpLab2.setEnabled(true);
						bcpLab2.setBackground(new Color(0, 120, 215));
						bbeLab.setEnabled(true);
						bbeLab.setBackground(new Color(0, 120, 215));
					}
				}
			}
		});
		rdbtnLarge.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnLarge.setFont(new Font("Serif", Font.BOLD, 20));
		buttonGroup.add(rdbtnLarge);
		rdbtnLarge.setBackground(Color.WHITE);
		rdbtnLarge.setBounds(50, 239, 90, 32);
		RoomSelection.add(rdbtnLarge);
		
		chckbxAc = new JCheckBox("AC");
		chckbxAc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxAc.isSelected()) {
					if(rdbtnSmall.isSelected()) {
						if(chckbxWhiteBoard.isSelected()) {
							blt1.setEnabled(false);
							blt1.setBackground(Color.BLACK);
							blt2.setEnabled(false);
							blt2.setBackground(Color.BLACK);
							blt3.setEnabled(false);
							blt3.setBackground(Color.BLACK);
							blt4.setEnabled(false);
							blt4.setBackground(Color.BLACK);
							blt5.setEnabled(false);
							blt5.setBackground(Color.BLACK);
							blt6.setEnabled(false);
							blt6.setBackground(Color.BLACK);
							blt7.setEnabled(false);
							blt7.setBackground(Color.BLACK);
							blt8.setEnabled(false);
							blt8.setBackground(Color.BLACK);
							blt9.setEnabled(false);
							blt9.setBackground(Color.BLACK);
							blt10.setEnabled(false);
							blt10.setBackground(Color.BLACK);
							bsac.setEnabled(false);
							bsac.setBackground(Color.BLACK);
							bcpLab1.setEnabled(false);
							bcpLab1.setBackground(Color.BLACK);
							bcpLab2.setEnabled(false);
							bcpLab2.setBackground(Color.BLACK);
							bbeLab.setEnabled(false);
							bbeLab.setBackground(Color.BLACK);
						}
						else {
							blt1.setEnabled(false);
							blt1.setBackground(Color.BLACK);
							blt2.setEnabled(false);
							blt2.setBackground(Color.BLACK);
							blt3.setEnabled(false);
							blt3.setBackground(Color.BLACK);
							blt4.setEnabled(false);
							blt4.setBackground(Color.BLACK);
							blt5.setEnabled(true);
							blt5.setBackground(new Color(0, 120, 215));
							blt6.setEnabled(false);
							blt6.setBackground(Color.BLACK);
							blt7.setEnabled(false);
							blt7.setBackground(Color.BLACK);
							blt8.setEnabled(false);
							blt8.setBackground(Color.BLACK);
							blt9.setEnabled(false);
							blt9.setBackground(Color.BLACK);
							blt10.setEnabled(false);
							blt10.setBackground(Color.BLACK);
							bsac.setEnabled(false);
							bsac.setBackground(Color.BLACK);
							bcpLab1.setEnabled(false);
							bcpLab1.setBackground(Color.BLACK);
							bcpLab2.setEnabled(false);
							bcpLab2.setBackground(Color.BLACK);
							bbeLab.setEnabled(false);
							bbeLab.setBackground(Color.BLACK);
						}
					}
					else if(rdbtnLarge.isSelected()) {
						blt1.setEnabled(false);
						blt1.setBackground(Color.BLACK);
						blt2.setEnabled(false);
						blt2.setBackground(Color.BLACK);
						blt3.setEnabled(false);
						blt3.setBackground(Color.BLACK);
						blt4.setEnabled(false);
						blt4.setBackground(Color.BLACK);
						blt5.setEnabled(false);
						blt5.setBackground(Color.BLACK);
						blt6.setEnabled(false);
						blt6.setBackground(Color.BLACK);
						blt7.setEnabled(false);
						blt7.setBackground(Color.BLACK);
						blt8.setEnabled(false);
						blt8.setBackground(Color.BLACK);
						blt9.setEnabled(false);
						blt9.setBackground(Color.BLACK);
						blt10.setEnabled(false);
						blt10.setBackground(Color.BLACK);
						bsac.setEnabled(false);
						bsac.setBackground(Color.BLACK);
						bcpLab1.setEnabled(true);
						bcpLab1.setBackground(new Color(0, 120, 215));
						bcpLab2.setEnabled(true);
						bcpLab2.setBackground(new Color(0, 120, 215));
						bbeLab.setEnabled(true);
						bbeLab.setBackground(new Color(0, 120, 215));
					}
					else {
						if(chckbxWhiteBoard.isSelected()) {
							blt1.setEnabled(false);
							blt1.setBackground(Color.BLACK);
							blt2.setEnabled(false);
							blt2.setBackground(Color.BLACK);
							blt3.setEnabled(false);
							blt3.setBackground(Color.BLACK);
							blt4.setEnabled(false);
							blt4.setBackground(Color.BLACK);
							blt5.setEnabled(false);
							blt5.setBackground(Color.BLACK);
							blt6.setEnabled(false);
							blt6.setBackground(Color.BLACK);
							blt7.setEnabled(false);
							blt7.setBackground(Color.BLACK);
							blt8.setEnabled(false);
							blt8.setBackground(Color.BLACK);
							blt9.setEnabled(false);
							blt9.setBackground(Color.BLACK);
							blt10.setEnabled(false);
							blt10.setBackground(Color.BLACK);
							bsac.setEnabled(false);
							bsac.setBackground(Color.BLACK);
							bcpLab1.setEnabled(true);
							bcpLab1.setBackground(new Color(0, 120, 215));
							bcpLab2.setEnabled(true);
							bcpLab2.setBackground(new Color(0, 120, 215));
							bbeLab.setEnabled(true);
							bbeLab.setBackground(new Color(0, 120, 215));
							rdbtnLarge.setSelected(true);
						}
						else {
							blt1.setEnabled(false);
							blt1.setBackground(Color.BLACK);
							blt2.setEnabled(false);
							blt2.setBackground(Color.BLACK);
							blt3.setEnabled(false);
							blt3.setBackground(Color.BLACK);
							blt4.setEnabled(false);
							blt4.setBackground(Color.BLACK);
							blt5.setEnabled(true);
							blt5.setBackground(new Color(0, 120, 215));
							blt6.setEnabled(false);
							blt6.setBackground(Color.BLACK);
							blt7.setEnabled(false);
							blt7.setBackground(Color.BLACK);
							blt8.setEnabled(false);
							blt8.setBackground(Color.BLACK);
							blt9.setEnabled(false);
							blt9.setBackground(Color.BLACK);
							blt10.setEnabled(false);
							blt10.setBackground(Color.BLACK);
							bsac.setEnabled(false);
							bsac.setBackground(Color.BLACK);
							bcpLab1.setEnabled(true);
							bcpLab1.setBackground(new Color(0, 120, 215));
							bcpLab2.setEnabled(true);
							bcpLab2.setBackground(new Color(0, 120, 215));
							bbeLab.setEnabled(true);
							bbeLab.setBackground(new Color(0, 120, 215));
						}
					}
				}
				else {
					if(rdbtnSmall.isSelected()) {
						if(chckbxWhiteBoard.isSelected()) {
							blt1.setEnabled(false);
							blt1.setBackground(Color.BLACK);
							blt2.setEnabled(false);
							blt2.setBackground(Color.BLACK);
							blt3.setEnabled(false);
							blt3.setBackground(Color.BLACK);
							blt4.setEnabled(false);
							blt4.setBackground(Color.BLACK);
							blt5.setEnabled(false);
							blt5.setBackground(Color.BLACK);
							blt6.setEnabled(false);
							blt6.setBackground(Color.BLACK);
							blt7.setEnabled(false);
							blt7.setBackground(Color.BLACK);
							blt8.setEnabled(false);
							blt8.setBackground(Color.BLACK);
							blt9.setEnabled(false);
							blt9.setBackground(Color.BLACK);
							blt10.setEnabled(false);
							blt10.setBackground(Color.BLACK);
							bsac.setEnabled(false);
							bsac.setBackground(Color.BLACK);
							bcpLab1.setEnabled(false);
							bcpLab1.setBackground(Color.BLACK);
							bcpLab2.setEnabled(false);
							bcpLab2.setBackground(Color.BLACK);
							bbeLab.setEnabled(false);
							bbeLab.setBackground(Color.BLACK);
						}
						else {
							blt1.setEnabled(false);
							blt1.setBackground(Color.BLACK);
							blt2.setEnabled(false);
							blt2.setBackground(Color.BLACK);
							blt3.setEnabled(true);
							blt3.setBackground(new Color(0, 120, 215));
							blt4.setEnabled(true);
							blt4.setBackground(new Color(0, 120, 215));
							blt5.setEnabled(true);
							blt5.setBackground(new Color(0, 120, 215));
							blt6.setEnabled(true);
							blt6.setBackground(new Color(0, 120, 215));
							blt7.setEnabled(true);
							blt7.setBackground(new Color(0, 120, 215));
							blt8.setEnabled(true);
							blt8.setBackground(new Color(0, 120, 215));
							blt9.setEnabled(false);
							blt9.setBackground(Color.BLACK);
							blt10.setEnabled(false);
							blt10.setBackground(Color.BLACK);
							bsac.setEnabled(false);
							bsac.setBackground(Color.BLACK);
							bcpLab1.setEnabled(false);
							bcpLab1.setBackground(Color.BLACK);
							bcpLab2.setEnabled(false);
							bcpLab2.setBackground(Color.BLACK);
							bbeLab.setEnabled(false);
							bbeLab.setBackground(Color.BLACK);
						}
					}
					else if(rdbtnLarge.isSelected()) {
						if(chckbxWhiteBoard.isSelected()) {
							blt1.setEnabled(false);
							blt1.setBackground(Color.BLACK);
							blt2.setEnabled(false);
							blt2.setBackground(Color.BLACK);
							blt3.setEnabled(false);
							blt3.setBackground(Color.BLACK);
							blt4.setEnabled(false);
							blt4.setBackground(Color.BLACK);
							blt5.setEnabled(false);
							blt5.setBackground(Color.BLACK);
							blt6.setEnabled(false);
							blt6.setBackground(Color.BLACK);
							blt7.setEnabled(false);
							blt7.setBackground(Color.BLACK);
							blt8.setEnabled(false);
							blt8.setBackground(Color.BLACK);
							blt9.setEnabled(false);
							blt9.setBackground(Color.BLACK);
							blt10.setEnabled(true);
							blt10.setBackground(new Color(0, 120, 215));
							bsac.setEnabled(false);
							bsac.setBackground(Color.BLACK);
							bcpLab1.setEnabled(true);
							bcpLab1.setBackground(new Color(0, 120, 215));
							bcpLab2.setEnabled(true);
							bcpLab2.setBackground(new Color(0, 120, 215));
							bbeLab.setEnabled(true);
							bbeLab.setBackground(new Color(0, 120, 215));
						}
						else {
							blt1.setEnabled(true);
							blt1.setBackground(new Color(0, 120, 215));
							blt2.setEnabled(true);
							blt2.setBackground(new Color(0, 120, 215));
							blt3.setEnabled(false);
							blt3.setBackground(Color.BLACK);
							blt4.setEnabled(false);
							blt4.setBackground(Color.BLACK);
							blt5.setEnabled(false);
							blt5.setBackground(Color.BLACK);
							blt6.setEnabled(false);
							blt6.setBackground(Color.BLACK);
							blt7.setEnabled(false);
							blt7.setBackground(Color.BLACK);
							blt8.setEnabled(false);
							blt8.setBackground(Color.BLACK);
							blt9.setEnabled(true);
							blt9.setBackground(new Color(0, 120, 215));
							blt10.setEnabled(true);
							blt10.setBackground(new Color(0, 120, 215));
							bsac.setEnabled(true);
							bsac.setBackground(new Color(0, 120, 215));
							bcpLab1.setEnabled(true);
							bcpLab1.setBackground(new Color(0, 120, 215));
							bcpLab2.setEnabled(true);
							bcpLab2.setBackground(new Color(0, 120, 215));
							bbeLab.setEnabled(true);
							bbeLab.setBackground(new Color(0, 120, 215));
						}
					}
					else {
						if(!chckbxWhiteBoard.isSelected()) {
							blt1.setEnabled(true);
							blt1.setBackground(new Color(0, 120, 215));
							blt2.setEnabled(true);
							blt2.setBackground(new Color(0, 120, 215));
							blt3.setEnabled(true);
							blt3.setBackground(new Color(0, 120, 215));
							blt4.setEnabled(true);
							blt4.setBackground(new Color(0, 120, 215));
							blt5.setEnabled(true);
							blt5.setBackground(new Color(0, 120, 215));
							blt6.setEnabled(true);
							blt6.setBackground(new Color(0, 120, 215));
							blt7.setEnabled(true);
							blt7.setBackground(new Color(0, 120, 215));
							blt8.setEnabled(true);
							blt8.setBackground(new Color(0, 120, 215));
							blt9.setEnabled(true);
							blt9.setBackground(new Color(0, 120, 215));
							blt10.setEnabled(true);
							blt10.setBackground(new Color(0, 120, 215));
							bsac.setEnabled(true);
							bsac.setBackground(new Color(0, 120, 215));
							bcpLab1.setEnabled(true);
							bcpLab1.setBackground(new Color(0, 120, 215));
							bcpLab2.setEnabled(true);
							bcpLab2.setBackground(new Color(0, 120, 215));
							bbeLab.setEnabled(true);
							bbeLab.setBackground(new Color(0, 120, 215));
						}
					}
				}
			}
		});
		
		lblFacilitiesRequired = new JLabel("Facilities Required:");
		lblFacilitiesRequired.setFont(new Font("Serif", Font.BOLD, 30));
		lblFacilitiesRequired.setBounds(50, 304, 327, 37);
		RoomSelection.add(lblFacilitiesRequired);
		chckbxAc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxAc.setFont(new Font("Serif", Font.BOLD, 20));
		chckbxAc.setBackground(Color.WHITE);
		chckbxAc.setBounds(50, 349, 57, 32);
		RoomSelection.add(chckbxAc);
		
		chckbxWhiteBoard = new JCheckBox("White Board");
		chckbxWhiteBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxWhiteBoard.isSelected()) {
					if(rdbtnSmall.isSelected()) {
						blt1.setEnabled(false);
						blt1.setBackground(Color.BLACK);
						blt2.setEnabled(false);
						blt2.setBackground(Color.BLACK);
						blt3.setEnabled(false);
						blt3.setBackground(Color.BLACK);
						blt4.setEnabled(false);
						blt4.setBackground(Color.BLACK);
						blt5.setEnabled(false);
						blt5.setBackground(Color.BLACK);
						blt6.setEnabled(false);
						blt6.setBackground(Color.BLACK);
						blt7.setEnabled(false);
						blt7.setBackground(Color.BLACK);
						blt8.setEnabled(false);
						blt8.setBackground(Color.BLACK);
						blt9.setEnabled(false);
						blt9.setBackground(Color.BLACK);
						blt10.setEnabled(false);
						blt10.setBackground(Color.BLACK);
						bsac.setEnabled(false);
						bsac.setBackground(Color.BLACK);
						bcpLab1.setEnabled(false);
						bcpLab1.setBackground(Color.BLACK);
						bcpLab2.setEnabled(false);
						bcpLab2.setBackground(Color.BLACK);
						bbeLab.setEnabled(false);
						bbeLab.setBackground(Color.BLACK);
					}
					else {
						if(chckbxAc.isSelected()) {
							blt1.setEnabled(false);
							blt1.setBackground(Color.BLACK);
							blt2.setEnabled(false);
							blt2.setBackground(Color.BLACK);
							blt3.setEnabled(false);
							blt3.setBackground(Color.BLACK);
							blt4.setEnabled(false);
							blt4.setBackground(Color.BLACK);
							blt5.setEnabled(false);
							blt5.setBackground(Color.BLACK);
							blt6.setEnabled(false);
							blt6.setBackground(Color.BLACK);
							blt7.setEnabled(false);
							blt7.setBackground(Color.BLACK);
							blt8.setEnabled(false);
							blt8.setBackground(Color.BLACK);
							blt9.setEnabled(false);
							blt9.setBackground(Color.BLACK);
							blt10.setEnabled(false);
							blt10.setBackground(Color.BLACK);
							bsac.setEnabled(false);
							bsac.setBackground(Color.BLACK);
							bcpLab1.setEnabled(true);
							bcpLab1.setBackground(new Color(0, 120, 215));
							bcpLab2.setEnabled(true);
							bcpLab2.setBackground(new Color(0, 120, 215));
							bbeLab.setEnabled(true);
							bbeLab.setBackground(new Color(0, 120, 215));
						}
						else {
							blt1.setEnabled(false);
							blt1.setBackground(Color.BLACK);
							blt2.setEnabled(false);
							blt2.setBackground(Color.BLACK);
							blt3.setEnabled(false);
							blt3.setBackground(Color.BLACK);
							blt4.setEnabled(false);
							blt4.setBackground(Color.BLACK);
							blt5.setEnabled(false);
							blt5.setBackground(Color.BLACK);
							blt6.setEnabled(false);
							blt6.setBackground(Color.BLACK);
							blt7.setEnabled(false);
							blt7.setBackground(Color.BLACK);
							blt8.setEnabled(false);
							blt8.setBackground(Color.BLACK);
							blt9.setEnabled(false);
							blt9.setBackground(Color.BLACK);
							blt10.setEnabled(true);
							blt10.setBackground(new Color(0, 120, 215));
							bsac.setEnabled(false);
							bsac.setBackground(Color.BLACK);
							bcpLab1.setEnabled(true);
							bcpLab1.setBackground(new Color(0, 120, 215));
							bcpLab2.setEnabled(true);
							bcpLab2.setBackground(new Color(0, 120, 215));
							bbeLab.setEnabled(true);
							bbeLab.setBackground(new Color(0, 120, 215));
						}
						if(!rdbtnLarge.isSelected()) {
							rdbtnLarge.setSelected(true);
						}
					}
				}
				else {
					if(chckbxAc.isSelected()) {
						if(rdbtnSmall.isSelected()) {
							blt1.setEnabled(false);
							blt1.setBackground(Color.BLACK);
							blt2.setEnabled(false);
							blt2.setBackground(Color.BLACK);
							blt3.setEnabled(false);
							blt3.setBackground(Color.BLACK);
							blt4.setEnabled(false);
							blt4.setBackground(Color.BLACK);
							blt5.setEnabled(true);
							blt5.setBackground(new Color(0, 120, 215));
							blt6.setEnabled(false);
							blt6.setBackground(Color.BLACK);
							blt7.setEnabled(false);
							blt7.setBackground(Color.BLACK);
							blt8.setEnabled(false);
							blt8.setBackground(Color.BLACK);
							blt9.setEnabled(false);
							blt9.setBackground(Color.BLACK);
							blt10.setEnabled(false);
							blt10.setBackground(Color.BLACK);
							bsac.setEnabled(false);
							bsac.setBackground(Color.BLACK);
							bcpLab1.setEnabled(false);
							bcpLab1.setBackground(Color.BLACK);
							bcpLab2.setEnabled(false);
							bcpLab2.setBackground(Color.BLACK);
							bbeLab.setEnabled(false);
							bbeLab.setBackground(Color.BLACK);
						}
						else if(rdbtnLarge.isSelected()) {
							blt1.setEnabled(false);
							blt1.setBackground(Color.BLACK);
							blt2.setEnabled(false);
							blt2.setBackground(Color.BLACK);
							blt3.setEnabled(false);
							blt3.setBackground(Color.BLACK);
							blt4.setEnabled(false);
							blt4.setBackground(Color.BLACK);
							blt5.setEnabled(false);
							blt5.setBackground(Color.BLACK);
							blt6.setEnabled(false);
							blt6.setBackground(Color.BLACK);
							blt7.setEnabled(false);
							blt7.setBackground(Color.BLACK);
							blt8.setEnabled(false);
							blt8.setBackground(Color.BLACK);
							blt9.setEnabled(false);
							blt9.setBackground(Color.BLACK);
							blt10.setEnabled(false);
							blt10.setBackground(Color.BLACK);
							bsac.setEnabled(false);
							bsac.setBackground(Color.BLACK);
							bcpLab1.setEnabled(true);
							bcpLab1.setBackground(new Color(0, 120, 215));
							bcpLab2.setEnabled(true);
							bcpLab2.setBackground(new Color(0, 120, 215));
							bbeLab.setEnabled(true);
							bbeLab.setBackground(new Color(0, 120, 215));
						}
						else {
							blt1.setEnabled(false);
							blt1.setBackground(Color.BLACK);
							blt2.setEnabled(false);
							blt2.setBackground(Color.BLACK);
							blt3.setEnabled(false);
							blt3.setBackground(Color.BLACK);
							blt4.setEnabled(false);
							blt4.setBackground(Color.BLACK);
							blt5.setEnabled(true);
							blt5.setBackground(new Color(0, 120, 215));
							blt6.setEnabled(false);
							blt6.setBackground(Color.BLACK);
							blt7.setEnabled(false);
							blt7.setBackground(Color.BLACK);
							blt8.setEnabled(false);
							blt8.setBackground(Color.BLACK);
							blt9.setEnabled(false);
							blt9.setBackground(Color.BLACK);
							blt10.setEnabled(false);
							blt10.setBackground(Color.BLACK);
							bsac.setEnabled(false);
							bsac.setBackground(Color.BLACK);
							bcpLab1.setEnabled(true);
							bcpLab1.setBackground(new Color(0, 120, 215));
							bcpLab2.setEnabled(true);
							bcpLab2.setBackground(new Color(0, 120, 215));
							bbeLab.setEnabled(true);
							bbeLab.setBackground(new Color(0, 120, 215));
						}
					}
					else {
						if(rdbtnSmall.isSelected()) {
							blt1.setEnabled(false);
							blt1.setBackground(Color.BLACK);
							blt2.setEnabled(false);
							blt2.setBackground(Color.BLACK);
							blt3.setEnabled(true);
							blt3.setBackground(new Color(0, 120, 215));
							blt4.setEnabled(true);
							blt4.setBackground(new Color(0, 120, 215));
							blt5.setEnabled(true);
							blt5.setBackground(new Color(0, 120, 215));
							blt6.setEnabled(true);
							blt6.setBackground(new Color(0, 120, 215));
							blt7.setEnabled(true);
							blt7.setBackground(new Color(0, 120, 215));
							blt8.setEnabled(true);
							blt8.setBackground(new Color(0, 120, 215));
							blt9.setEnabled(false);
							blt9.setBackground(Color.BLACK);
							blt10.setEnabled(false);
							blt10.setBackground(Color.BLACK);
							bsac.setEnabled(false);
							bsac.setBackground(Color.BLACK);
							bcpLab1.setEnabled(false);
							bcpLab1.setBackground(Color.BLACK);
							bcpLab2.setEnabled(false);
							bcpLab2.setBackground(Color.BLACK);
							bbeLab.setEnabled(false);
							bbeLab.setBackground(Color.BLACK);
						}
						else {
							blt1.setEnabled(true);
							blt1.setBackground(new Color(0, 120, 215));
							blt2.setEnabled(true);
							blt2.setBackground(new Color(0, 120, 215));
							blt3.setEnabled(false);
							blt3.setBackground(Color.BLACK);
							blt4.setEnabled(false);
							blt4.setBackground(Color.BLACK);
							blt5.setEnabled(false);
							blt5.setBackground(Color.BLACK);
							blt6.setEnabled(false);
							blt6.setBackground(Color.BLACK);
							blt7.setEnabled(false);
							blt7.setBackground(Color.BLACK);
							blt8.setEnabled(false);
							blt8.setBackground(Color.BLACK);
							blt9.setEnabled(true);
							blt9.setBackground(new Color(0, 120, 215));
							blt10.setEnabled(true);
							blt10.setBackground(new Color(0, 120, 215));
							bsac.setEnabled(true);
							bsac.setBackground(new Color(0, 120, 215));
							bcpLab1.setEnabled(true);
							bcpLab1.setBackground(new Color(0, 120, 215));
							bcpLab2.setEnabled(true);
							bcpLab2.setBackground(new Color(0, 120, 215));
							bbeLab.setEnabled(true);
							bbeLab.setBackground(new Color(0, 120, 215));
						}
					}
				}
			}
		});
		chckbxWhiteBoard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxWhiteBoard.setFont(new Font("Serif", Font.BOLD, 20));
		chckbxWhiteBoard.setBackground(Color.WHITE);
		chckbxWhiteBoard.setBounds(50, 389, 167, 32);
		RoomSelection.add(chckbxWhiteBoard);
		
		lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Serif", Font.BOLD, 30));
		lblDate.setBounds(50, 456, 89, 37);
		RoomSelection.add(lblDate);
		
		JDateChooser date = new JDateChooser();
		date.setBounds(50, 501, 250, 32);
		RoomSelection.add(date);
		date.setBackground(Color.WHITE);
		date.setFont(new Font("Serif", Font.BOLD, 12));
		date.setDateFormatString("yyyy-MM-dd");
		
		Date curDate = new Date();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String cDate = sdf.format(curDate);
		
		try {
			date.setDate(sdf.parse(cDate));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		outerPanel = new JPanel();
		outerPanel.setBackground(Color.WHITE);
		outerPanel.setBounds(600, 160, 220, 265);
		RoomSelection.add(outerPanel);
		outerPanel.setLayout(null);
		
		blt1 = new JButton("LT1");
		blt1.setBounds(0, 0, 100, 25);
		outerPanel.add(blt1);
		blt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(date.getDate().getDate()==curDate.getDate()) {
					LT.setText("LT1");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(false);
				}
				else if(date.getDate().before(curDate)) {
					JOptionPane.showMessageDialog(null, "Invalid Date!", "Invalid Date", JOptionPane.ERROR_MESSAGE);
				}
				else {
					LT.setText("LT1");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(false);
				}
			}
		});
		blt1.setFont(new Font("Serif", Font.BOLD, 12));
		blt1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		blt1.setForeground(Color.WHITE);
		blt1.setBackground(new Color(0, 120, 215));
		
		blt2 = new JButton("LT2");
		blt2.setBounds(120, 0, 100, 25);
		outerPanel.add(blt2);
		blt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(date.getDate().getDate()==curDate.getDate()) {
					LT.setText("LT2");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(false);
				}
				else if(date.getDate().before(curDate)) {
					JOptionPane.showMessageDialog(null, "Invalid Date!", "Invalid Date", JOptionPane.ERROR_MESSAGE);
				}
				else {
					LT.setText("LT2");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(false);
				}
			}
		});
		blt2.setFont(new Font("Serif", Font.BOLD, 12));
		blt2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		blt2.setForeground(Color.WHITE);
		blt2.setBackground(new Color(0, 120, 215));
		
		blt3 = new JButton("LT3");
		blt3.setBounds(0, 40, 100, 25);
		outerPanel.add(blt3);
		blt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(date.getDate().getDate()==curDate.getDate()) {
					LT.setText("LT3");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(false);
				}
				else if(date.getDate().before(curDate)) {
					JOptionPane.showMessageDialog(null, "Invalid Date!", "Invalid Date", JOptionPane.ERROR_MESSAGE);
				}
				else {
					LT.setText("LT3");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(false);
				}
			}
		});
		blt3.setFont(new Font("Serif", Font.BOLD, 12));
		blt3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		blt3.setForeground(Color.WHITE);
		blt3.setBackground(new Color(0, 120, 215));
		
		blt4 = new JButton("LT4");
		blt4.setBounds(120, 40, 100, 25);
		outerPanel.add(blt4);
		blt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(date.getDate().getDate()==curDate.getDate()) {
					LT.setText("LT4");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(false);
				}
				else if(date.getDate().before(curDate)) {
					JOptionPane.showMessageDialog(null, "Invalid Date!", "Invalid Date", JOptionPane.ERROR_MESSAGE);
				}
				else {
					LT.setText("LT4");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(false);
				}
			}
		});
		blt4.setFont(new Font("Serif", Font.BOLD, 12));
		blt4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		blt4.setForeground(Color.WHITE);
		blt4.setBackground(new Color(0, 120, 215));
		
		blt5 = new JButton("LT5");
		blt5.setBounds(0, 80, 100, 25);
		outerPanel.add(blt5);
		blt5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(date.getDate().getDate()==curDate.getDate()) {
					LT.setText("LT5");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(true);
				}
				else if(date.getDate().before(curDate)) {
					JOptionPane.showMessageDialog(null, "Invalid Date!", "Invalid Date", JOptionPane.ERROR_MESSAGE);
				}
				else {
					LT.setText("LT5");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(true);
				}
			}
		});
		blt5.setFont(new Font("Serif", Font.BOLD, 12));
		blt5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		blt5.setForeground(Color.WHITE);
		blt5.setBackground(new Color(0, 120, 215));
		
		blt6 = new JButton("LT6");
		blt6.setBounds(120, 80, 100, 25);
		outerPanel.add(blt6);
		blt6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(date.getDate().getDate()==curDate.getDate()) {
					LT.setText("LT6");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(false);
				}
				else if(date.getDate().before(curDate)) {
					JOptionPane.showMessageDialog(null, "Invalid Date!", "Invalid Date", JOptionPane.ERROR_MESSAGE);
				}
				else {
					LT.setText("LT6");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(false);
				}
			}
		});
		blt6.setFont(new Font("Serif", Font.BOLD, 12));
		blt6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		blt6.setForeground(Color.WHITE);
		blt6.setBackground(new Color(0, 120, 215));
		
		blt7 = new JButton("LT7");
		blt7.setBounds(0, 120, 100, 25);
		outerPanel.add(blt7);
		blt7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(date.getDate().getDate()==curDate.getDate()) {
					LT.setText("LT7");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(false);
				}
				else if(date.getDate().before(curDate)) {
					JOptionPane.showMessageDialog(null, "Invalid Date!", "Invalid Date", JOptionPane.ERROR_MESSAGE);
				}
				else {
					LT.setText("LT7");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(false);
				}
			}
		});
		blt7.setFont(new Font("Serif", Font.BOLD, 12));
		blt7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		blt7.setForeground(Color.WHITE);
		blt7.setBackground(new Color(0, 120, 215));
		
		blt8 = new JButton("LT8");
		blt8.setBounds(120, 120, 100, 25);
		outerPanel.add(blt8);
		blt8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(date.getDate().getDate()==curDate.getDate()) {
					LT.setText("LT8");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(false);
				}
				else if(date.getDate().before(curDate)) {
					JOptionPane.showMessageDialog(null, "Invalid Date!", "Invalid Date", JOptionPane.ERROR_MESSAGE);
				}
				else {
					LT.setText("LT8");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(false);
				}
			}
		});
		blt8.setFont(new Font("Serif", Font.BOLD, 12));
		blt8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		blt8.setForeground(Color.WHITE);
		blt8.setBackground(new Color(0, 120, 215));
		
		blt9 = new JButton("LT9");
		blt9.setBounds(0, 160, 100, 25);
		outerPanel.add(blt9);
		blt9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(date.getDate().getDate()==curDate.getDate()) {
					LT.setText("LT9");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(false);
				}
				else if(date.getDate().before(curDate)) {
					JOptionPane.showMessageDialog(null, "Invalid Date!", "Invalid Date", JOptionPane.ERROR_MESSAGE);
				}
				else {
					LT.setText("LT9");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(false);
				}
			}
		});
		blt9.setFont(new Font("Serif", Font.BOLD, 12));
		blt9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		blt9.setForeground(Color.WHITE);
		blt9.setBackground(new Color(0, 120, 215));
		
		blt10 = new JButton("LT10");
		blt10.setBounds(120, 160, 100, 25);
		outerPanel.add(blt10);
		blt10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(date.getDate().getDate()==curDate.getDate()) {
					LT.setText("LT10");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(false);
				}
				else if(date.getDate().before(curDate)) {
					JOptionPane.showMessageDialog(null, "Invalid Date!", "Invalid Date", JOptionPane.ERROR_MESSAGE);
				}
				else {
					LT.setText("LT10");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(false);
				}
			}
		});
		blt10.setFont(new Font("Serif", Font.BOLD, 12));
		blt10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		blt10.setForeground(Color.WHITE);
		blt10.setBackground(new Color(0, 120, 215));
		
		bsac = new JButton("SAC");
		bsac.setBounds(0, 200, 100, 25);
		outerPanel.add(bsac);
		bsac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(date.getDate().getDate()==curDate.getDate()) {
					LT.setText("SAC");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(false);
				}
				else if(date.getDate().before(curDate)) {
					JOptionPane.showMessageDialog(null, "Invalid Date!", "Invalid Date", JOptionPane.ERROR_MESSAGE);
				}
				else {
					LT.setText("SAC");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(false);
				}
			}
		});
		bsac.setFont(new Font("Serif", Font.BOLD, 12));
		bsac.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bsac.setForeground(Color.WHITE);
		bsac.setBackground(new Color(0, 120, 215));
		
		bcpLab1 = new JButton("CP LAB1");
		bcpLab1.setBounds(120, 200, 100, 25);
		outerPanel.add(bcpLab1);
		bcpLab1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(date.getDate().getDate()==curDate.getDate()) {
					LT.setText("CP LAB1");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(true);
				}
				else if(date.getDate().before(curDate)) {
					JOptionPane.showMessageDialog(null, "Invalid Date!", "Invalid Date", JOptionPane.ERROR_MESSAGE);
				}
				else {
					LT.setText("CP LAB1");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(true);
				}
			}
		});
		bcpLab1.setFont(new Font("Serif", Font.BOLD, 12));
		bcpLab1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bcpLab1.setForeground(Color.WHITE);
		bcpLab1.setBackground(new Color(0, 120, 215));
		
		bcpLab2 = new JButton("CP LAB2");
		bcpLab2.setBounds(0, 240, 100, 25);
		outerPanel.add(bcpLab2);
		bcpLab2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(date.getDate().getDate()==curDate.getDate()) {
					LT.setText("CP LAB2");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(true);
				}
				else if(date.getDate().before(curDate)) {
					JOptionPane.showMessageDialog(null, "Invalid Date!", "Invalid Date", JOptionPane.ERROR_MESSAGE);
				}
				else {
					LT.setText("CP LAB2");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(true);
				}
			}
		});
		bcpLab2.setFont(new Font("Serif", Font.BOLD, 12));
		bcpLab2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bcpLab2.setForeground(Color.WHITE);
		bcpLab2.setBackground(new Color(0, 120, 215));
		
		bbeLab = new JButton("B.E. LAB");
		bbeLab.setBounds(120, 240, 100, 25);
		outerPanel.add(bbeLab);
		bbeLab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(date.getDate().getDate()==curDate.getDate()) {
					LT.setText("B.E. LAB");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(true);
				}
				else if(date.getDate().before(curDate)) {
					JOptionPane.showMessageDialog(null, "Invalid Date!", "Invalid Date", JOptionPane.ERROR_MESSAGE);
				}
				else {
					LT.setText("B.E. LAB");
					bookingDate.setText(sdf.format(date.getDate()));
					getTimeline();
					setLabels();
					chckbxAc2.setEnabled(true);
				}
			}
		});
		bbeLab.setFont(new Font("Serif", Font.BOLD, 12));
		bbeLab.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bbeLab.setForeground(Color.WHITE);
		bbeLab.setBackground(new Color(0, 120, 215));
		
		
		JPanel Form = new JPanel();
		Form.setBackground(Color.WHITE);
		Form.setBounds(961, 150, 959, 930);
		contentPane.add(Form);
		Form.setLayout(null);
		
		JLabel lblNewBooking = new JLabel("New Booking");
		lblNewBooking.setForeground(new Color(0, 120, 215));
		lblNewBooking.setFont(new Font("Serif", Font.BOLD, 30));
		lblNewBooking.setBounds(50, 80, 222, 37);
		Form.add(lblNewBooking);
		
		JLabel lblRoom = new JLabel("Room:");
		lblRoom.setFont(new Font("Serif", Font.BOLD, 25));
		lblRoom.setBounds(50, 150, 90, 30);
		Form.add(lblRoom);
		
		JLabel lblTimeSlot = new JLabel("Time Slot:");
		lblTimeSlot.setFont(new Font("Serif", Font.BOLD, 25));
		lblTimeSlot.setBounds(50, 250, 146, 30);
		Form.add(lblTimeSlot);
		
		JLabel lblFrom = new JLabel("From:");
		lblFrom.setFont(new Font("Serif", Font.BOLD, 25));
		lblFrom.setBounds(250, 250, 83, 30);
		Form.add(lblFrom);
		
		JLabel lblTo = new JLabel("To:");
		lblTo.setFont(new Font("Serif", Font.BOLD, 25));
		lblTo.setBounds(460, 250, 45, 30);
		Form.add(lblTo);
		
		JComboBox timeFrom = new JComboBox();
		timeFrom.setBorder(new LineBorder(new Color(0, 120, 215)));
		timeFrom.setBackground(Color.WHITE);
		timeFrom.setModel(new DefaultComboBoxModel(new String[] {"-Select-", "8:00AM", "9:00AM", "10:00AM", "11:00AM", "12:00PM", "1:00PM", "2:00PM", "3:00PM", "4:00PM", "5:00PM", "6:00PM", "6:30PM", "7:00PM", "7:30PM", "8:00PM", "8:30PM", "9:00PM", "9:30PM", "10:00PM"}));
		timeFrom.setBounds(350, 250, 92, 26);
		Form.add(timeFrom);
		
		JComboBox timeTo = new JComboBox();
		timeTo.setBorder(new LineBorder(new Color(0, 120, 215)));
		timeTo.setBackground(Color.WHITE);
		timeTo.setModel(new DefaultComboBoxModel(new String[] {"-Select-", "9:00AM", "10:00AM", "11:00AM", "12:00PM", "1:00PM", "2:00PM", "3:00PM", "4:00PM", "5:00PM", "6:00PM", "6:30PM", "7:00PM", "7:30PM", "8:00PM", "8:30PM", "9:00PM", "9:30PM", "10:00PM", "10:30PM"}));
		timeTo.setBounds(510, 250, 92, 26);
		Form.add(timeTo);
		
		JLabel lblPurpose = new JLabel("Purpose:");
		lblPurpose.setFont(new Font("Serif", Font.BOLD, 25));
		lblPurpose.setBounds(50, 300, 123, 30);
		Form.add(lblPurpose);
		
		JTextArea txtrPurpose = new JTextArea();
		txtrPurpose.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(txtrPurpose.getText().equals("Please write the purpose of booking.")){
			        txtrPurpose.setText("");
			    }
			}
			public void focusLost(FocusEvent e) {
				if(txtrPurpose.getText().equals("")){
			        txtrPurpose.setText("Please write the purpose of booking.");
			    }
			}
		});
		txtrPurpose.setBorder(new LineBorder(new Color(0, 120, 215)));
		txtrPurpose.setFont(new Font("Serif", Font.PLAIN, 14));
		txtrPurpose.setBackground(Color.WHITE);
		txtrPurpose.setText("Please write the purpose of booking.");
		txtrPurpose.setBounds(250, 300, 500, 80);
		Form.add(txtrPurpose);
		
		JLabel lblInternalRequirements = new JLabel("Internal Requirements:");
		lblInternalRequirements.setFont(new Font("Serif", Font.BOLD, 25));
		lblInternalRequirements.setBounds(50, 400, 330, 30);
		Form.add(lblInternalRequirements);
		
		chckbxAc2 = new JCheckBox("AC");
		chckbxAc2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxAc2.setFont(new Font("Serif", Font.BOLD, 20));
		chckbxAc2.setBackground(Color.WHITE);
		chckbxAc2.setBounds(50, 430, 57, 32);
		Form.add(chckbxAc2);
		
		JCheckBox chckbxMic = new JCheckBox("Mic");
		chckbxMic.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxMic.setFont(new Font("Serif", Font.BOLD, 20));
		chckbxMic.setBackground(Color.WHITE);
		chckbxMic.setBounds(150, 430, 67, 32);
		Form.add(chckbxMic);
		
		JCheckBox chckbxProjector = new JCheckBox("Projector");
		chckbxProjector.setFont(new Font("Serif", Font.BOLD, 20));
		chckbxProjector.setBackground(Color.WHITE);
		chckbxProjector.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxProjector.setBounds(250, 430, 129, 32);
		Form.add(chckbxProjector);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//JOptionPane.showMessageDialog(null, "Submitted Successfully!");
				if(timeFrom.getSelectedIndex()>timeTo.getSelectedIndex()) {
					JOptionPane.showMessageDialog(null, "invalid timeslot", "Invalid", JOptionPane.ERROR_MESSAGE);
				}
				else if(LT.getText().compareTo("")==0 || bookingDate.getText().compareTo("")==0 || txtrPurpose.getText().compareTo("")==0 || txtrPurpose.getText().compareTo("Please write the purpose of booking.")==0 || timeFrom.getSelectedIndex()==0 || timeTo.getSelectedIndex()==0) {

					JOptionPane.showMessageDialog(null, "Please fill the form correctly.", "Incomplete Form", JOptionPane.ERROR_MESSAGE);
				}
				else {
					
					
					String purpose = txtrPurpose.getText();
					String from_time = timeFrom.getSelectedItem().toString();
					String to_time = timeTo.getSelectedItem().toString();
					int mic = chckbxMic.isSelected()?1:0;
					int ac = chckbxAc.isSelected()?1:0;
					int projector = chckbxProjector.isSelected()?1:0;
					String roomId = LT.getText();
					String date = bookingDate.getText();
					String email = Login.requester.getEmail();
					String granterId = "";
					
//					if(roomId.compareTo("LT5")==0)
//						granterId = "granter2@lnmiit.ac.in";
//					else if(roomId.compareTo("SAC")==0)
//						granterId = "granter3@lnmiit.ac.in";
//					else if(roomId.compareTo("CP LAB1")==0 || roomId.compareTo("CP LAB2")==0)
//						granterId = "granter4@lnmiit.ac.in";
//					else if(roomId.compareTo("B.E. LAB")==0)
//						granterId = "granter5@lnmiit.ac.in";
//					else
					
						granterId = "16ucs138@lnmiit.ac.in";

					String urlstring;
//					 urlstring = "http://localhost:8080/submitRequest?";
//					 urlstring += "purpose=Welcome";
//					 urlstring += "&from_time="+ from_time;
//					 urlstring += "&to_time="+ to_time;
//					 urlstring += "&mic="+ mic;
//					 urlstring += "&ac="+ ac;
//					 urlstring += "&projector="+ projector;
//					 urlstring += "&roomId="+ roomId;
//					 urlstring += "&date="+date;
//					 urlstring += "&email="+ email;
//					 urlstring += "&cleaning=0";
//					 urlstring += "&granterId="+ granterId;
					 
//					 System.out.println(urlstring);
					 
					 
					 urlstring = "http://localhost:8080/submitRequest?"
					 		+ "purpose=Welcome&"
					 		+ "from_time=8000AM&"
					 		+ "to_time=1100AM"
					 		+ "&mic=0&ac=0&"
					 		+ "projector=0"
					 		+ "&roomId="+roomId
					 		+ "&date="+ date
					 		+ "&email=16ucs136@lnmiit.ac.in"
					 		+ "&cleaning=0&granterId=granter1@lnmiit.ac.in";
//					 
					 try {
							URL url = new URL(urlstring);
						
						 URLConnection urlcon=url.openConnection();  
						 InputStream stream=urlcon.getInputStream();  
						 
						 BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
						 
						 String json = reader.readLine();
						 
						 System.out.println(json);
						
					 
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					
					History rs = new History();
					rs.setVisible(true);
					dispose();
				}
			}
		});
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.setFont(new Font("Serif", Font.BOLD, 20));
		btnSubmit.setBackground(new Color(0, 120, 215));
		btnSubmit.setBounds(50, 500, 115, 34);
		Form.add(btnSubmit);
		
		LT = new JTextField();
		LT.setFont(new Font("Serif", Font.PLAIN, 14));
		LT.setBorder(new LineBorder(new Color(0, 120, 215)));
		LT.setHorizontalAlignment(SwingConstants.CENTER);
		LT.setBackground(Color.WHITE);
		LT.setEditable(false);
		LT.setBounds(250, 160, 132, 19);
		Form.add(LT);
		LT.setColumns(10);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Serif", Font.BOLD, 25));
		lblDate.setBounds(50, 200, 75, 30);
		Form.add(lblDate);
		
		bookingDate = new JTextField();
		bookingDate.setHorizontalAlignment(SwingConstants.CENTER);
		bookingDate.setFont(new Font("Serif", Font.PLAIN, 14));
		bookingDate.setBorder(new LineBorder(new Color(0, 120, 215)));
		bookingDate.setBackground(Color.WHITE);
		bookingDate.setEditable(false);
		bookingDate.setBounds(250, 210, 132, 19);
		Form.add(bookingDate);
		bookingDate.setColumns(10);
		

		
		JLabel lblBookedslots = new JLabel("BookedSlots:");
		lblBookedslots.setFont(new Font("Serif", Font.BOLD, 18));
		lblBookedslots.setBounds(50, 600, 132, 22);
		Form.add(lblBookedslots);

		lblBookedslots_1 = new JLabel("");
		lblBookedslots_1.setFont(new Font("Serif", Font.BOLD, 14));
		lblBookedslots_1.setBounds(50, 650, 950, 20);
		Form.add(lblBookedslots_1);
			
		lblBookedslots_2 = new JLabel("");
		lblBookedslots_2.setFont(new Font("Serif", Font.BOLD, 14));
		lblBookedslots_2.setBounds(50, 700, 950, 20);
		Form.add(lblBookedslots_2);
			
		lblBookedslots_3 = new JLabel("");
		lblBookedslots_3.setFont(new Font("Serif", Font.BOLD, 14));
		lblBookedslots_3.setBounds(50, 750, 950, 20);
		Form.add(lblBookedslots_3);
				
		lblBookedslots_4 = new JLabel("");
		lblBookedslots_4.setFont(new Font("Serif", Font.BOLD, 14));
		lblBookedslots_4.setBounds(50, 800, 950, 20);
		Form.add(lblBookedslots_4);
		
		setLabels();
		pack();
		logo.requestFocusInWindow();
		
		
	}
	
	public void getTimeline() {
		

		if(LT!=null) {
		
		String roomId = LT.getText();
		String date = bookingDate.getText();
		String urlstring = "http://localhost:8080/timeline?roomId="+roomId +"&date=" + date;
		
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
			 
			 timeline = new ArrayList<>();
			 for(int i =0; i<entries.size(); i++) {
				 System.out.println(entries.get(i));
				 timeline.add(gson.fromJson(entries.get(i), Timeline.class));
			 }
			 
			 for(Timeline rre: timeline)
				 System.out.println(rre.getRoomId()  + " : " + rre.getTimeslot());
			
			 
		 }else {
			 System.out.println("Nothing sent by server");
//			 JOptionPane.showMessageDialog(null, "No pending requests found!", "No Requests", JOptionPane.INFORMATION_MESSAGE);
			 return;
		 }
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		}
	}
	
	public void setLabels() {
		
		String s = "";
		
		if(timeline!=null) {
		
		if(timeline.size()<4) {
			for(int i=0; i<timeline.size();i++) {
				s = s + timeline.get(i).getTimeslot() + " ";
			}
			lblBookedslots_1.setText(s);
		}
		else if(timeline.size()<8) {
			for(int i=0; i<4; i++) {
				s = s + timeline.get(i).getTimeslot() + " ";
			}
			lblBookedslots_1.setText(s);
			s="";
			for(int i=4; i<timeline.size(); i++) {
				s = s + timeline.get(i).getTimeslot() + " ";
			}
			lblBookedslots_2.setText(s);
		}
		else if(timeline.size()<12) {
			for(int i=0; i<4; i++) {
				s = s +  timeline.get(i).getTimeslot() + " ";
			}
			lblBookedslots_1.setText(s);
			s="";
			for(int i=4; i<8; i++) {
				s = s +  timeline.get(i).getTimeslot() + " ";
			}
			lblBookedslots_2.setText(s);
			s="";
			for(int i=0; i<timeline.size(); i++) {
				s = s +  timeline.get(i).getTimeslot() + " ";
			}
			lblBookedslots_3.setText(s);
		}
		else {
			for(int i=0; i<4; i++) {
				s = s +  timeline.get(i).getTimeslot() + " ";
			}
			lblBookedslots_1.setText(s);
			s="";
			for(int i=4; i<8; i++) {
				s = s +  timeline.get(i).getTimeslot() + " ";
			}
			lblBookedslots_2.setText(s);
			s="";
			for(int i=8; i<8; i++) {
				s = s +  timeline.get(i).getTimeslot() + " ";
			}
			lblBookedslots_3.setText(s);
			s="";
			for(int i=12; i<timeline.size(); i++) {
				s = s +  timeline.get(i).getTimeslot() + " ";
			}
			lblBookedslots_4.setText(s);
		}
		}
	}
}
