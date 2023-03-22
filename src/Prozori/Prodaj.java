package Prozori;

import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Zajednicko.Helper;
import Zajednicko.HelperPrikaz;
import java.awt.SystemColor;

public class Prodaj extends JFrame {

	public JPanel contentPane;
	private JTextField txtNazivP;
	private int idForInsert;
	private int BrK;
	private int Cena;
	private int CenaTot;
	private JSpinner spinner;
	String NazivP;

	static Helper Helper = new Helper();
	HelperPrikaz HelperPrikaz = new HelperPrikaz();
	public JTable RadoviTable;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prodaj frame = new Prodaj();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Prodaj() {
		initComponents();
		PrikazPodataka();
	}
	
	public void PrikazPodataka() {
		
		String query = "SELECT ID, CONCAT_WS('',Naziv) as 'Naziv Knjige', CONCAT_WS('',Cena) as 'Cena Knjige' FROM Knjige";
	
		HelperPrikaz.PopuniTabelu(query, RadoviTable);
	}
	
	
	public void initComponents()
	{
		setTitle("KNJIGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1221, 644);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("NATRAG");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Main_Menu MM = new Main_Menu();
				MM.setVisible(true);
			}
		});
		
		
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.setBounds(10, 571, 121, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("KOLICINA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(372, 28, 100, 36);
		contentPane.add(lblNewLabel);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinner.setBounds(482, 28, 89, 36);
		contentPane.add(spinner);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 118, 1185, 422);
		contentPane.add(scrollPane);
		
		RadoviTable = new JTable();
		scrollPane.setViewportView(RadoviTable);
		ListSelectionModel rowSelectionModel = RadoviTable.getSelectionModel();

		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent e) {
		        			        
		        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		        		        
		        if(lsm.isSelectionEmpty()) {
		        	//JOptionPane.showMessageDialog(null, "No Selection");
		        }
		        else {
		        	int selRow = RadoviTable.getSelectedRow();
		        	NazivP = RadoviTable.getModel().getValueAt(selRow, 1).toString();
		        	idForInsert = Integer.parseInt(RadoviTable.getModel().getValueAt(selRow, 0).toString());
		        	Cena = Integer.parseInt(RadoviTable.getModel().getValueAt(selRow, 2).toString());
		        	//JOptionPane.showMessageDialog(null,String.valueOf(idForInsert));
		        	txtNazivP.setText(NazivP);
		        }
		      }

		    });
		
		
		
		JButton btnNewButton_1 = new JButton("DPDAJ U KORPU");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BrK = (Integer)spinner.getValue();
				CenaTot = Cena * BrK;
				
				Connection connect = Helper.DBSetup();
				
				String sql = "INSERT INTO Korpa (ID_R, Kolicina, CenaTotal ) VALUES ( "+idForInsert+", "+BrK+", "+CenaTot+")";
				
				try {
					Statement stm = connect.createStatement();
					
					stm.execute(sql);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
					JOptionPane.showMessageDialog(null,String.valueOf("Došlo je do greške pokušatjte ponovo."));
				}
				
				spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
				
				JOptionPane.showMessageDialog(null, "Proizvod je uspesno dodat u korpu!");
				
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_1.setBounds(911, 36, 253, 23);
		contentPane.add(btnNewButton_1);
		
		txtNazivP = new JTextField();
		txtNazivP.setEditable(false);
		txtNazivP.setBounds(10, 28, 352, 36);
		contentPane.add(txtNazivP);
		txtNazivP.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("KORPA");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Korpa K = new Korpa();
				K.setVisible(true);
				
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_2.setBounds(1045, 572, 150, 23);
		contentPane.add(btnNewButton_2);
	}
}
