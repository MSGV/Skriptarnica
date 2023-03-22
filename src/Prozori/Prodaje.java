package Prozori;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Zajednicko.Helper;
import Zajednicko.HelperPrikaz;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.SystemColor;

public class Prodaje extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	
	static Helper Helper = new Helper();
	HelperPrikaz HelperP = new HelperPrikaz();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prodaje frame = new Prodaje();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
	public Prodaje() {
		initComponents();
		Prikaz();
	}
	
	public void Prikaz() {
		String query = "SELECT * FROM Prodaje";
		HelperP.PopuniTabelu(query, table);
	}
	public void initComponents() {
		setTitle("Prodaje");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1217, 677);
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
				Main_Menu MM = new Main_Menu();
				MM.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(10, 604, 109, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Dobrodošli na prozor registrovanih prodaja, ovde se nalaze sve ostvarene prodaje. ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 11, 480, 14);
		contentPane.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(458, 11, 733, 616);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
