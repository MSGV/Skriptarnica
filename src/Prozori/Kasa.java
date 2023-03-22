package Prozori;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import Zajednicko.Helper;
import Zajednicko.HelperPrikaz;

public class Kasa extends JFrame {

	private JPanel contentPane;
	private JTable Kasa;
	Helper Helper = new Helper();
	HelperPrikaz HelperPrikaz = new HelperPrikaz();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kasa frame = new Kasa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
public void PrikazPodataka() {
		
		String query = "SELECT CONCAT_WS('',K.Naziv) as 'Naziv', CONCAT_WS('',K.Cena) as 'Cena', k.Kolicina, k.CenaTotal FROM Korpa k, Knjige K " + "WHERE k.ID_R = K.ID ";
	
		HelperPrikaz.PopuniTabelu(query, Kasa);
	}

	/**
	 * Create the frame.
	 */
	public Kasa() {
		setTitle("KASA");
		initComp();
		PrikazPodataka();
	}
	public void initComp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1242, 636);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("NATRAG");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Korpa K = new Korpa();
				K.setVisible(true);
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.setBounds(10, 563, 115, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 174, 1206, 380);
		contentPane.add(scrollPane);
		
		Kasa = new JTable();
		scrollPane.setViewportView(Kasa);
		
		JLabel lblProizvodiUKorpi = new JLabel("KNJIGE U KORPI");
		lblProizvodiUKorpi.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblProizvodiUKorpi.setBounds(10, 139, 173, 23);
		contentPane.add(lblProizvodiUKorpi);
		
		JButton btnNewButton_1 = new JButton("PRODAJ");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_1.setBounds(1027, 11, 189, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("NATRAG NA GLAVNI MENI");
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Main_Menu MM = new Main_Menu();
				MM.setVisible(true);
				
			}
		});
		btnNewButton_2.setBounds(856, 564, 360, 23);
		contentPane.add(btnNewButton_2);
	}
}
