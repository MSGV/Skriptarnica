package Prozori;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Zajednicko.Helper;
import Zajednicko.HelperPrikaz;

public class Lista_Knjiga extends JFrame {

	private JPanel contentPane;
	private JTextField naziv;
	private JTextField sifra;
	private JTextField kolicina;
	private JTextField cena;
	private int ID;
	
	static Helper Helper = new Helper();
	HelperPrikaz HelperP = new HelperPrikaz();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lista_Knjiga frame = new Lista_Knjiga();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Lista_Knjiga() {
		initComponents();
		Prikaz();
	}
	
	public void Prikaz() {
		String query = "SELECT * FROM Knjige";
		HelperP.PopuniTabelu(query, table);
	}
	
		private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Legion\\Desktop\\Milan Buric fakultet\\LOB.png"));
		setTitle("Lista Knjiga");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1167, 718);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setBounds(0, 0, 1151, 681);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Sifra");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_2.setBounds(36, 99, 75, 13);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Kolicina");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(36, 124, 75, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Cena");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_3.setBounds(36, 149, 75, 13);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Naziv");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_4.setBounds(36, 73, 75, 13);
		panel.add(lblNewLabel_4);
		
		naziv = new JTextField();
		naziv.setBounds(146, 73, 186, 19);
		panel.add(naziv);
		naziv.setColumns(10);
		
		sifra = new JTextField();
		sifra.setBounds(146, 99, 186, 19);
		panel.add(sifra);
		sifra.setColumns(10);
		
		kolicina = new JTextField();
		kolicina.setBounds(146, 124, 186, 19);
		panel.add(kolicina);
		kolicina.setColumns(10);
		
		cena = new JTextField();
		cena.setBounds(146, 149, 186, 19);
		panel.add(cena);
		cena.setColumns(10);

		
		JButton btnNewButton = new JButton("NATRAG");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Main_Menu MM = new Main_Menu();
				MM.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.setBounds(10, 650, 146, 21);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("DODAJ");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
if(naziv.getText().equals("") || sifra.getText().equals("") || kolicina.getText().equals("") || cena.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Please Enter the necessary Information!");
				}else { 
					
					String Naziv = naziv.getText();
					String Sifra = sifra.getText();
					String Kolicina = kolicina.getText();
					String Cena = cena.getText();
					
					naziv.setText("");
					kolicina.setText("");
					sifra.setText("");
					cena.setText("");
					
					Connection conn = Helper.DBSetup();
					String query = "INSERT INTO Knjige(Naziv, Sifra, Kolicina, Cena) VALUES ('"+Naziv+"', '"+Integer.valueOf(Sifra)+"', '"+Integer.valueOf(Kolicina)+"', '"+Integer.valueOf(Cena)+"')";
					
					try {
						java.sql.Statement stm = conn.createStatement();
						stm.execute(query);
						Prikaz();
					}
					catch(Exception be){
							be.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "Informacije su uspesno dodate!");
				}
			}
		});
		btnNewButton_1.setBounds(10, 280, 145, 21);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("IZMENI");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Naziv = naziv.getText();
				String Sifra = sifra.getText();
				String Kolicina = kolicina.getText();
				String Cena = cena.getText();
				
				Connection connect = Helper.DBSetup();
				
				String sql = "UPDATE Knjige SET Naziv = '"+Naziv+"', Sifra = '"+Integer.valueOf(Sifra)+"', Kolicina = '"+Integer.valueOf(Kolicina)+"'"
						+ "Cena = '"+Integer.valueOf(Cena)+"' WHERE ID = '"+ID+"'";
						
		
					try { 
						Statement stm = connect.createStatement();
				    	stm.execute(sql);
				    
				    	Prikaz();
				    
						connect.close();
				    
	
					}   
					catch(SQLException ex) {
						System.out.println(ex.getMessage());
					}
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_2.setBounds(186, 280, 145, 21);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("OBRISI");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
Connection connect = Helper.DBSetup();
				
				String sql = "DELETE * FROM Knjige WHERE ID = '"+ID+"'";
				
				try { 
					Statement stm = connect.createStatement();
			    	stm.execute(sql);
			    
			    	Prikaz();
			    
					connect.close();
			    

				}   
				catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}		
				
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_3.setBounds(10, 324, 145, 21);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("OCISTI");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				naziv.setText("");
				sifra.setText("");
				kolicina.setText("");
				cena.setText("");
				JOptionPane.showMessageDialog(null, "Informacije su uspesno dodate!");
			}
		});
		btnNewButton_4.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_4.setBounds(187, 324, 145, 21);
		panel.add(btnNewButton_4);
		
		JLabel lblNewLabel_5 = new JLabel("LISTA KNJIGA");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_5.setBounds(10, 0, 266, 60);
		panel.add(lblNewLabel_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(354, 24, 761, 632);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		ListSelectionModel rowSelectionModel = table.getSelectionModel();

		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent e) {
		        			        
		        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		        		        
		        if(lsm.isSelectionEmpty()) {
		        	//JOptionPane.showMessageDialog(null, "No Selection");
		        }
		        else {
		        	int selRow = table.getSelectedRow();
		        	String Naziv = table.getModel().getValueAt(selRow, 1).toString();
		        	String Sifra = table.getModel().getValueAt(selRow, 2).toString();
		        	String Kolicina = table.getModel().getValueAt(selRow, 3).toString();
		        	String Cena = table.getModel().getValueAt(selRow, 4).toString();
		        	int idForEdit = Integer.parseInt(table.getModel().getValueAt(selRow, 0).toString());
		        	//Phone email addres ptt
		        	naziv.setText(Naziv);
		        	kolicina.setText(Sifra);
		        	sifra.setText(Kolicina);
					cena.setText(Cena);
					ID = idForEdit;
		        	//String Surname = Integer.parseInt(table.getModel().getValueAt(selRow, 1).toString());
		        	JOptionPane.showMessageDialog(null,String.valueOf(idForEdit));
		        	//txtNazivPredstave.setText(NazivP);
		        }
		      }

		    });
	}
}
