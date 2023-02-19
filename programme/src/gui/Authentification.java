package gui;

import java.awt.EventQueue;


import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import dao.EnseignantDAO;
import dao.EtudiantDAO;
import dao.UtilisateurDAO;
import model.Utilisateur;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import javax.swing.JSeparator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import model.Seance;
import javax.swing.JPasswordField;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;

public class Authentification {

	private JFrame frame;
	private JTextField identifiant;
	private JPasswordField motDePasse;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification window = new Authentification();
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
	public Authentification() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ENTER) {
					System.out.println("Entrée appuyée");
				}
			}
		});
		frame.getContentPane().setBackground(new Color(95, 158, 160));
		frame.setBounds(100, 100, 1002, 613);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel titreLabel = new JLabel("SYSTEME DE GESTION DES ABSENCES");
		titreLabel.setForeground(new Color(255, 255, 255));
		titreLabel.setBackground(new Color(30, 144, 255));
		titreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titreLabel.setFont(new Font("Dialog", Font.BOLD, 22));
		titreLabel.setBounds(39, 156, 433, 81);
		frame.getContentPane().add(titreLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 120, 215)));
		panel_1.setBackground(new Color(25, 25, 112));
		panel_1.setBounds(514, 0, 482, 697);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel(" ");
		label.setBounds(177, 59, 146, 118);
		panel_1.add(label);
		label.setIcon(new ImageIcon("./src/images/log-in-button (1).png"));
		
		JLabel identifiantLabel = new JLabel("Identifiant:");
		identifiantLabel.setBounds(101, 205, 214, 41);
		panel_1.add(identifiantLabel);
		identifiantLabel.setHorizontalAlignment(SwingConstants.LEFT);
		identifiantLabel.setForeground(new Color(255, 255, 255));
		identifiantLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		identifiantLabel.setBackground(new Color(255, 255, 255));
		
		identifiant = new JTextField();
		identifiant.setFont(new Font("Tahoma", Font.BOLD, 15));
		identifiant.setCaretColor(new Color(255, 255, 255));
		identifiant.setDisabledTextColor(new Color(255, 255, 255));
		identifiant.setBounds(82, 246, 337, 26);
		panel_1.add(identifiant);
		identifiant.setForeground(new Color(255, 255, 255));
		identifiant.setOpaque(false);
		identifiant.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		identifiant.setColumns(10);
		
		JLabel motDePasseLabel = new JLabel("Mot De Passe:");
		motDePasseLabel.setBounds(101, 303, 344, 41);
		panel_1.add(motDePasseLabel);
		motDePasseLabel.setHorizontalAlignment(SwingConstants.LEFT);
		motDePasseLabel.setForeground(new Color(255, 255, 255));
		motDePasseLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		motDePasseLabel.setBackground(new Color(30, 144, 255));
		
		motDePasse = new JPasswordField();
		motDePasse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		motDePasse.setForeground(new Color(255, 255, 255));
		motDePasse.setDisabledTextColor(new Color(255, 255, 255));
		motDePasse.setCaretColor(new Color(255, 255, 255));
		motDePasse.setBounds(82, 356, 331, 26);
		panel_1.add(motDePasse);
		motDePasse.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		motDePasse.setOpaque(false);
		
		JButton connexionButton = new JButton("CONNEXION");
		connexionButton.setBounds(251, 441, 168, 47);
		panel_1.add(connexionButton);
		connexionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		connexionButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				
				connecter();
			}
		});
		connexionButton.setBackground(new Color(95, 158, 160));
		connexionButton.setForeground(new Color(255, 255, 255));
		connexionButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel label_1 = new JLabel(" ");
		label_1.setBounds(38, 237, 32, 42);
		panel_1.add(label_1);
		label_1.setIcon(new ImageIcon("./src/images/user (6).png"));
		
		JLabel label_3 = new JLabel(" ");
		label_3.setIcon(new ImageIcon("./src/images/key (1).png"));
		label_3.setBounds(38, 348, 56, 41);
		panel_1.add(label_3);
		
		JLabel label_2 = new JLabel(" ");
		label_2.setIcon(new ImageIcon("./src/images/schedule (3).png"));
		label_2.setBounds(186, 232, 141, 208);
		frame.getContentPane().add(label_2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 255)));
		panel.setBounds(53, 453, 386, 3);
		frame.getContentPane().add(panel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 255)));
		panel_2.setBounds(97, 469, 308, 3);
		frame.getContentPane().add(panel_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblNewLabel.setBounds(154, 89, 173, 55);
		frame.getContentPane().add(lblNewLabel);
		frame.setResizable(false);
		
		
		
	}

	public void connecter() {
		String password = motDePasse.getText();
		int username = Integer.parseInt(identifiant.getText());
		Utilisateur user = new UtilisateurDAO().get(username, password);
		//user = new Utilisateur(10, "Test", "Gestionnaire");
		if(user != null){
			if(user.getProfil().equals("Gestionnaire")) {
				frame.dispose();
				frame = new MenuGestionnaire().getFrame();
			    frame.setVisible(true);
			}else if(user.getProfil().equals("Etudiant")) {
				int grp = Integer.parseInt(EtudiantDAO.getNumeroGroupe(Integer.parseInt(identifiant.getText())));
				System.out.println(grp);
				frame.dispose();
				frame = new MenuEtudiant(grp).getFrame();
				frame.setVisible(true);
			}else if(user.getProfil().equals("Enseignant")) {
				int idEn = Integer.parseInt(EnseignantDAO.getIdEnseignant(Integer.parseInt(identifiant.getText())));
				System.out.println(idEn);
				frame.dispose();
				frame = new MenuEnseignant().getFrame();
				frame.setVisible(true);
			}else {
				System.out.println("Attention!!! Il y'a certainement une erreur dans la BDD");
			}
		}else{
			JOptionPane.showMessageDialog(null, "Invalid Login","Login error",JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
