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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Zajednicko.Helper;
import Zajednicko.HelperPrikaz;
import java.awt.SystemColor;

public class Korpa extends JFrame {

	private JPanel contentPane;
	private JTable Korpa;
	private int idForDelete;
	DefaultTableModel model;
	Helper Helper = new Helper();
	HelperPrikaz HelperPrikaz = new HelperPrikaz();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Korpa frame = new Korpa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
public void PrikazPodataka() {
		
		String query = "SELECT k.ID_R, CONCAT_WS('',K.Naziv) as 'Naziv Rada', CONCAT_WS('',K.Cena) as 'Cena Rada', k.Kolicina, k.CenaTotal FROM Korpa k, Knjige K " + "WHERE k.ID_R = K.ID AND k.CenaTotal = K.Cena*k.Kolicina";
	
		HelperPrikaz.PopuniTabelu(query, Korpa);
	}

	/**
	 * Create the frame.
	 */
	public Korpa() {
		
		initComp();
		PrikazPodataka();
		
	}
	
	public void initComp() {
		setTitle("KORPA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1230, 598);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("NATRAG");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Lista_Knjiga LK = new Lista_Knjiga();
				LK.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(10, 525, 114, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("NATRAG NA GLAVNI MENI");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Main_Menu MM = new Main_Menu();
				MM.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_1.setBounds(918, 525, 286, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("PRODAJ");
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Kasa K = new Kasa();
				K.setVisible(true);
				
			}
		});
		btnNewButton_2.setBounds(1013, 11, 191, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("VRATI NATRAG");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				String query = "DELETE FROM Korpa WHERE ID_R = "+idForDelete+"";
                Connection connect = Helper.DBSetup();
                
                try {
					Statement stm = connect.createStatement();
					stm.execute(query);
					PrikazPodataka();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
                
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_3.setBounds(1013, 45, 191, 23);
		contentPane.add(btnNewButton_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 93, 1194, 421);
		contentPane.add(scrollPane);
		
		Korpa = new JTable();
		scrollPane.setViewportView(Korpa);
		
		JLabel lblSadrzajKorpe = new JLabel("SADRŽAJ KORPE:");
		lblSadrzajKorpe.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSadrzajKorpe.setBounds(10, 50, 157, 23);
		contentPane.add(lblSadrzajKorpe);
		
		ListSelectionModel rowSelectionModel = Korpa.getSelectionModel();

        rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
              public void valueChanged(ListSelectionEvent e) {

                ListSelectionModel lsm = (ListSelectionModel)e.getSource();

                if(lsm.isSelectionEmpty()) {
                    //JOptionPane.showMessageDialog(null, "No Selection");
                }
                else {
                    int selRow = Korpa.getSelectedRow();
                    idForDelete = Integer.parseInt(Korpa.getModel().getValueAt(selRow, 0).toString());
                    //JOptionPane.showMessageDialog(null,String.valueOf(idForDelete));
                }
              }

            });
	}
}
