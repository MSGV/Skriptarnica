package Prozori;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	static Zajednicko.Helper Helper = new Zajednicko.Helper();
		
	
	private JPanel contentPane;
	private JTextField txtKorisnickoime;
	private JPasswordField txtLozinka;
	public int ID;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					Login  CW =  (Login) Helper.CenterWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void initComponents() {

		setBackground(new Color(255, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 589);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Wlcome = new JLabel("Dobrodošli");
		Wlcome.setFont(new Font("Tahoma", Font.BOLD, 20));
		Wlcome.setForeground(Color.DARK_GRAY);
		Wlcome.setHorizontalAlignment(SwingConstants.CENTER);
		Wlcome.setBounds(45, 11, 354, 32);
		contentPane.add(Wlcome);
		
		JLabel Username = new JLabel("Korisničko ime:");
		Username.setBackground(new Color(128, 0, 0));
		Username.setFont(new Font("Tahoma", Font.BOLD, 14));
		Username.setHorizontalAlignment(SwingConstants.CENTER);
		Username.setBounds(57, 367, 135, 17);
		contentPane.add(Username);
		
		JLabel Password = new JLabel("Šifra:");
		Password.setFont(new Font("Tahoma", Font.BOLD, 14));
		Password.setHorizontalAlignment(SwingConstants.CENTER);
		Password.setBounds(57, 412, 135, 17);
		contentPane.add(Password);
		
		txtLozinka = new JPasswordField();
		txtLozinka.setBounds(202, 411, 189, 18);
		contentPane.add(txtLozinka);
		
		txtKorisnickoime = new JTextField();
		txtKorisnickoime.setBounds(202, 366, 189, 18);
		contentPane.add(txtKorisnickoime);
		txtKorisnickoime.setColumns(10);
		
				
		
		JButton btnLogin = new JButton("ULOGUJ SE");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					 
				Connection connect = Helper.DBSetup();
				
				String username = txtKorisnickoime.getText();
			      char[] password = txtLozinka.getPassword();
			      String pw = "";
			      for(int i = 0; i < password.length; i++ ) {
			    	  pw+=password[i];
			      }
				
			      String sql = "SELECT * FROM admins WHERE Username = '"+username+"' AND Password = '"+pw+"'";
			      
				try{			      				      				      
				      Statement stm = connect.createStatement();

				      ResultSet rs = stm.executeQuery(sql);			     
					     
					  if(rs.next()) {	
					    	 
						  	ID = rs.getInt("ID");
						     
					    	dispose();
					    	Main_Menu MM = new Main_Menu();
					    	MM.setVisible(true);
					    	//Main_Menu MMU = (Main_Menu) Helper.CenterWindow(MM);
					    	 
				    	  }
				    	  else {
				    		  JOptionPane.showMessageDialog(null,String.valueOf("ERROR!!! \nWrong Username or Password."));					    	 
				    	  }

					     connect.close();
				    }
					catch(Exception eis){ System.out.println(e);}
				 
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setBounds(149, 481, 147, 25);
		contentPane.add(btnLogin);
		
		JButton btnRegister = new JButton("REGISTRACIJA");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
		    	Register RegPage = new Register();
		    	RegPage.setVisible(true);
		    	//Register CW = (Register) Helper.CenterWindow(RegPage);
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRegister.setBounds(149, 517, 147, 23);
		contentPane.add(btnRegister);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Legion\\Desktop\\lib11.jpg"));
		lblNewLabel.setBounds(10, 41, 431, 314);
		contentPane.add(lblNewLabel);
		
		
	
	}
	
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Legion\\Desktop\\lib1.jpg"));
		setForeground(Color.BLACK);
		setTitle("Login");
		setResizable(false);
		initComponents();
		
	}
}