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
import javax.swing.JDesktopPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class MenuGestionnaire {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuGestionnaire window = new MenuGestionnaire();
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
	public MenuGestionnaire() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 987, 596);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton quotasBtn = new JButton("Param\u00E9trer les quotas d'absences");
		quotasBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frame = new AjouterQuota().getFrame();
				frame.setVisible(true);
			}
		});
		quotasBtn.setForeground(Color.WHITE);
		quotasBtn.setBackground(new Color(95, 158, 160));
		quotasBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		quotasBtn.setBounds(641, 378, 255, 23);
		frame.getContentPane().add(quotasBtn);
		
		JButton typesAbsenceBtn = new JButton("D\u00E9finir La Session");
		typesAbsenceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				frame = new DefinirSession().getFrame();
				frame.setVisible(true);
				
			}
		});
		typesAbsenceBtn.setBackground(new Color(95, 158, 160));
		typesAbsenceBtn.setForeground(Color.WHITE);
		typesAbsenceBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		typesAbsenceBtn.setBounds(641, 414, 255, 23);
		frame.getContentPane().add(typesAbsenceBtn);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(95, 158, 160));
		panel_2.setBounds(0, 522, 984, 43);
		frame.getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(95, 158, 160));
		panel_3.setBounds(0, 0, 984, 137);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblEsigelec = new JLabel(" ESIGELEC ");
		lblEsigelec.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblEsigelec.setBounds(776, 39, 181, 47);
		panel_3.add(lblEsigelec);
		lblEsigelec.setForeground(new Color(255, 0, 0));
		lblEsigelec.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 38));
		
		JLabel lblBonjourGestionnaire = new JLabel("Bonjour Gestionnaire");
		lblBonjourGestionnaire.setForeground(Color.WHITE);
		lblBonjourGestionnaire.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblBonjourGestionnaire.setBounds(26, 36, 263, 62);
		panel_3.add(lblBonjourGestionnaire);
		
		JLabel labelNomPrenomEnseignant = new JLabel(" ");
		labelNomPrenomEnseignant.setFont(new Font("Arial", Font.PLAIN, 18));
		labelNomPrenomEnseignant.setBounds(146, 62, 148, 16);
		panel_3.add(labelNomPrenomEnseignant);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		panel.setBounds(0, 129, 984, 405);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel(" ");
		label.setBounds(535, 26, 94, 87);
		panel.add(label);
		label.setIcon(new ImageIcon("./src/images/accessibility.png"));
		
		JButton changerMdpBtn = new JButton("Gestion Utilisateurs");
		changerMdpBtn.setBounds(641, 58, 255, 23);
		panel.add(changerMdpBtn);
		changerMdpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				frame = new GestionUtilisateur().getFrame();
				frame.setVisible(true);
				
			}
		});
		changerMdpBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		changerMdpBtn.setForeground(new Color(255, 255, 255));
		changerMdpBtn.setBackground(new Color(95, 158, 160));
		
		JButton gererEtudiantBtn = new JButton("Gestion des Etudiants");
		gererEtudiantBtn.setBounds(641, 168, 255, 23);
		panel.add(gererEtudiantBtn);
		gererEtudiantBtn.setBackground(new Color(95, 158, 160));
		gererEtudiantBtn.setForeground(Color.WHITE);
		
		JButton gererEnseignantBtn = new JButton("Gestion des Enseignants");
		gererEnseignantBtn.setBounds(641, 132, 255, 23);
		panel.add(gererEnseignantBtn);
		gererEnseignantBtn.setBackground(new Color(95, 158, 160));
		gererEnseignantBtn.setForeground(Color.WHITE);
		
		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setBounds(535, 126, 65, 89);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("./src/images/user (7).png"));
		
		JLabel label_4 = new JLabel(" ");
		label_4.setBounds(534, 242, 125, 89);
		panel.add(label_4);
		label_4.setIcon(new ImageIcon("./src/images/calendar (1).png"));
		
		JButton deconnexionBtn = new JButton("D\u00E9connexion");
		deconnexionBtn.setBounds(797, 357, 155, 23);
		panel.add(deconnexionBtn);
		deconnexionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		deconnexionBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				frame = new Authentification().getFrame();
				frame.setVisible(true);
			}
		});
		deconnexionBtn.setForeground(new Color(255, 255, 255));
		deconnexionBtn.setBackground(new Color(95, 158, 160));
		
		JButton btnNewButton = new JButton("D\u00E9finir planning ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frame = new CreerPlanningSemaine().getFrame();
				frame.setVisible(true);
			}
		});
		btnNewButton.setBackground(new Color(95, 158, 160));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(641, 319, 255, 25);
		panel.add(btnNewButton);
		
		JButton gererCoursBtn = new JButton("Gestion des Cours");
		gererCoursBtn.setBounds(142, 70, 246, 43);
		panel.add(gererCoursBtn);
		gererCoursBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				frame = new AjouterCours().getFrame();
				frame.setVisible(true);
			}
		});
		gererCoursBtn.setBackground(new Color(95, 158, 160));
		gererCoursBtn.setForeground(Color.WHITE);
		gererCoursBtn.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				frame.dispose();
//				frame = new AjouterCours().getFrame();
//				frame.setVisible(true);
//			}
		});
		gererCoursBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel label_1 = new JLabel(" ");
		label_1.setBounds(43, 59, 94, 64);
		panel.add(label_1);
		label_1.setIcon(new ImageIcon("./src/images/calendar.png"));
		
		JButton gererGroupeBtn = new JButton("Gestion des Groupes");
		gererGroupeBtn.setBounds(142, 159, 246, 41);
		panel.add(gererGroupeBtn);
		gererGroupeBtn.setBackground(new Color(95, 158, 160));
		gererGroupeBtn.setForeground(Color.WHITE);
		
		JLabel label_2 = new JLabel(" ");
		label_2.setBounds(43, 141, 65, 77);
		panel.add(label_2);
		label_2.setIcon(new ImageIcon("./src/images/men.png"));
		
		JLabel label_3 = new JLabel(" ");
		label_3.setBounds(36, 249, 72, 75);
		panel.add(label_3);
		label_3.setIcon(new ImageIcon("./src/images/shapes-and-symbols (2).png"));
		
		JButton btnNewButton_1 = new JButton("Gestion Sanction");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frame = new GestionSanctions().getFrame();
				frame.setVisible(true);
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(95, 158, 160));
		btnNewButton_1.setBounds(142, 269, 246, 35);
		panel.add(btnNewButton_1);
		gererGroupeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				frame = new GestionGroupe().getFrame();
				frame.setVisible(true);
			}
		});
		gererEnseignantBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		gererEnseignantBtn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				frame = new GestionEnseignant().getFrame();
				frame.setVisible(true);
			}
		});
		gererEtudiantBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				frame =  new GestionEtudiant().getFrame();
				frame.setVisible(true);
			}
		});
	}
	
	public JFrame getFrame() {
		return frame;
	}
}