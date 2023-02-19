package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class MenuEtudiant {

	private JFrame frame;
	private int grp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuEtudiant window = new MenuEtudiant();
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
	public MenuEtudiant() {
		initialize();
	}
	public MenuEtudiant(int grp){
		this.grp=grp;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 738, 569);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnConsulterOnPlanning = new JButton("Consulter Son Planning ");
		btnConsulterOnPlanning.setBackground(new Color(95, 158, 160));
		btnConsulterOnPlanning.setForeground(Color.WHITE);
		btnConsulterOnPlanning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				
				frame.dispose();
				frame = new PlanningEtudiant(grp).getFrame();
				frame.setVisible(true);
				}
				catch(Exception ex){
					frame.dispose();
					frame = new MenuEtudiant(grp).getFrame();
					frame.setVisible(true);
					JOptionPane.showMessageDialog(null, "vous n'avez pas de planning a consulter veuillez contacter votre gestionnaire");
				}
			}
		});
		btnConsulterOnPlanning.setBounds(96, 237, 239, 25);
		panel.add(btnConsulterOnPlanning);
		
		JButton btnConsulterLaListe = new JButton("Consulter La Liste Des Absences");
		btnConsulterLaListe.setForeground(Color.WHITE);
		btnConsulterLaListe.setBackground(new Color(95, 158, 160));
		btnConsulterLaListe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frame = new ConsulterAbsence().getFrame();
				frame.setVisible(true);
			}
		});
		btnConsulterLaListe.setBounds(96, 374, 239, 25);
		panel.add(btnConsulterLaListe);
		
		JButton btnDposerUnJustificatif = new JButton("D\u00E9poser un justificatif  ");
		btnDposerUnJustificatif.setBackground(new Color(95, 158, 160));
		btnDposerUnJustificatif.setForeground(Color.WHITE);
		btnDposerUnJustificatif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				frame = new DeposerJustificatif().getFrame();
				frame.setVisible(true);
			}
		});
		btnDposerUnJustificatif.setBounds(435, 237, 239, 25);
		panel.add(btnDposerUnJustificatif);
		
		JButton btnAnticiperAbsence = new JButton("Anticiper absence");
		btnAnticiperAbsence.setBackground(new Color(95, 158, 160));
		btnAnticiperAbsence.setForeground(Color.WHITE);
		btnAnticiperAbsence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frame = new AnticiperAbsence().getFrame();
				frame.setVisible(true);
			}
		});
		btnAnticiperAbsence.setBounds(435, 374, 239, 25);
		panel.add(btnAnticiperAbsence);
		
		JButton btnNewButton = new JButton("deconnexion");
		btnNewButton.setBackground(new Color(95, 158, 160));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frame = new Authentification().getFrame();
				frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(529, 441, 122, 25);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(95, 158, 160));
		panel_1.setBounds(0, 0, 744, 147);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEsigelec = new JLabel(" ESIGELEC ");
		lblEsigelec.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblEsigelec.setBackground(new Color(255, 0, 0));
		lblEsigelec.setBounds(530, 34, 170, 47);
		panel_1.add(lblEsigelec);
		lblEsigelec.setForeground(new Color(255, 0, 0));
		lblEsigelec.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 38));
		
		JLabel lblBonjour = new JLabel("Bonjour");
		lblBonjour.setForeground(UIManager.getColor("MenuBar.highlight"));
		lblBonjour.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblBonjour.setBounds(29, 20, 99, 62);
		panel_1.add(lblBonjour);
		
		JLabel labelNomPrenomEtudiant = new JLabel(" ");
		labelNomPrenomEtudiant.setFont(new Font("Arial", Font.PLAIN, 18));
		labelNomPrenomEtudiant.setBounds(140, 45, 148, 16);
		panel_1.add(labelNomPrenomEtudiant);
		
		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setIcon(new ImageIcon("./src/images/calendar (1).png"));
		lblNewLabel.setBounds(22, 346, 72, 80);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel(" ");
		label.setIcon(new ImageIcon("./src/images/calendar.png"));
		label.setBounds(22, 215, 72, 68);
		panel.add(label);
		
		JLabel label_1 = new JLabel(" ");
		label_1.setIcon(new ImageIcon("./src/images/wall-clock.png"));
		label_1.setBounds(358, 350, 72, 72);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel(" ");
		label_2.setIcon(new ImageIcon("./src/images/shapes-and-symbols (1).png"));
		label_2.setBounds(358, 205, 72, 89);
		panel.add(label_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(95, 158, 160));
		panel_2.setBounds(0, 479, 744, 60);
		panel.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(25, 25, 112));
		panel_3.setBounds(0, 143, 744, 343);
		panel.add(panel_3);
		panel_3.setLayout(null);
		frame.setResizable(false);
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
