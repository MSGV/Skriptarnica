package Prozori;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Zajednicko.HelperPrikaz;



public class Main_Menu extends JFrame {

	private JPanel contentPane;
	HelperPrikaz HelperPrikaz = new HelperPrikaz();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Menu frame = new Main_Menu();
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
	public Main_Menu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Legion\\Desktop\\Milan Buric fakultet\\MPAPP.png"));
		setFont(new Font("Times New Roman", Font.BOLD, 14));
		setTitle("Glavni Prozor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setBounds(0, 0, 774, 543);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnBooks = new JButton("Lista knjiga");
		btnBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Lista_Knjiga LK = new Lista_Knjiga();
				LK.setVisible(true);
			}
		});
		btnBooks.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnBooks.setBounds(288, 54, 154, 21);
		panel.add(btnBooks);
		
		JButton btnQuit = new JButton("Izlaz");
		btnQuit.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuit.setBounds(325, 512, 85, 21);
		panel.add(btnQuit);
		
		JButton btnNewButton = new JButton("Prodaje");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Prodaje PD = new Prodaje();
				PD.setVisible(true);
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.setBounds(288, 86, 154, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Prodaj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Prodaj PD = new Prodaj();
				PD.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_1.setBounds(288, 22, 153, 23);
		panel.add(btnNewButton_1);
	}
}
