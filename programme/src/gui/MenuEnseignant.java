package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.ImageIcon;

public class MenuEnseignant {

	private JFrame frame;
	private int idEn;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuEnseignant window = new MenuEnseignant();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public MenuEnseignant() {
		initialize();
	}
	public MenuEnseignant(int idEn) {
		this.idEn = idEn ;
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 728, 553);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnConsulterSonPlanning = new JButton("consulter son planning ");
		btnConsulterSonPlanning.setBackground(new Color(95, 158, 160));
		btnConsulterSonPlanning.setForeground(Color.WHITE);
		btnConsulterSonPlanning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				System.out.println(idEn);
//				frame.dispose();
//				frame = new PlanningEnseignant(idEn).getFrame();
//				frame.setVisible(true);
			}
		});
		
		btnConsulterSonPlanning.setBounds(104, 222, 201, 25);
		panel.add(btnConsulterSonPlanning);
		
		JButton btnNewButton = new JButton("Faire Appel");
		btnNewButton.setBackground(new Color(95, 158, 160));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frame = new Appel().getFrame();
				frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(284, 344, 201, 25);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Conuslter Absence d'un \u00E9l\u00E8ve");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBackground(new Color(95, 158, 160));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBounds(454, 222, 201, 25);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(95, 158, 160));
		panel_1.setBounds(0, 0, 722, 136);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEsigelec = new JLabel(" ESIGELEC ");
		lblEsigelec.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblEsigelec.setForeground(new Color(128, 0, 0));
		lblEsigelec.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 38));
		lblEsigelec.setBounds(525, 39, 172, 47);
		panel_1.add(lblEsigelec);
		
		JLabel lblBonjour = new JLabel("Bonjour ");
		lblBonjour.setForeground(Color.WHITE);
		lblBonjour.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblBonjour.setBounds(26, 36, 99, 62);
		panel_1.add(lblBonjour);
		
		JLabel labelNomPrenomEnseignant = new JLabel(" ");
		labelNomPrenomEnseignant.setFont(new Font("Arial", Font.PLAIN, 18));
		labelNomPrenomEnseignant.setBounds(142, 62, 148, 16);
		panel_1.add(labelNomPrenomEnseignant);
		
		JLabel label = new JLabel(" ");
		label.setIcon(new ImageIcon("./src/images/calendar.png"));
		label.setBounds(26, 188, 93, 70);
		panel.add(label);
		
		JLabel label_1 = new JLabel(" ");
		label_1.setIcon(new ImageIcon("./src/images/shapes-and-symbols (1).png"));
		label_1.setBounds(186, 329, 72, 55);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel(" ");
		label_2.setIcon(new ImageIcon("./src/images/calendar (1).png"));
		label_2.setBounds(360, 199, 82, 70);
		panel.add(label_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(95, 158, 160));
		panel_2.setBounds(0, 465, 722, 54);
		panel.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(25, 25, 112));
		panel_3.setBounds(0, 135, 722, 340);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("deconnexion");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frame = new Authentification().getFrame();
				frame.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(543, 272, 109, 25);
		panel_3.add(btnNewButton_2);
		btnNewButton_2.setBackground(new Color(95, 158, 160));
		btnNewButton_2.setForeground(Color.WHITE);
		frame.setResizable(false);
	}

	public JFrame getFrame() {
		return frame;
	}
}
