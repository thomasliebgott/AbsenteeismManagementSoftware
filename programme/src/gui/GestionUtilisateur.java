package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;


import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.Button;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import model.Utilisateur;
import dao.UtilisateurDAO;
import dao.EnseignantDAO;


import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;

public class GestionUtilisateur {

	private JFrame frame;
	private String[][] donnee;
	private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
	private boolean suppressionActive = false;
	private boolean modificationActive = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionUtilisateur window = new GestionUtilisateur();
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
	public GestionUtilisateur() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(95, 158, 160));
		frame.setBounds(100, 100, 893, 515);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/**
		 * Recupération des données des groupes à afficher dans la table
		 * Creation des colonnes de la table
		 * Les données sont ensuite mises dans un tableau à 2 dimension
		 * pour etre affiché proprement dans la table
		 */
		ArrayList<Utilisateur> liste = utilisateurDAO.getList();
		String colonne[] = {"Id", "Mot de Passe", "Profil"};
		
		if(this.donnee == null) {
			donnee = getTableauUtilisateur(liste, colonne);
		}
		
		
		JLabel lblNewLabel = new JLabel("GESTION DES UTILISATEUR");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setBounds(0, 13, 352, 54);
		frame.getContentPane().add(lblNewLabel);
		
		
		Button Deconnecter = new Button("D\u00E9connexion");
		Deconnecter.setForeground(new Color(255, 255, 255));
		Deconnecter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				frame = new Authentification().getFrame();
				frame.setVisible(true);
			}
		});
		Deconnecter.setBackground(new Color(25, 25, 112));
		Deconnecter.setBounds(716, 429, 149, 29);
		frame.getContentPane().add(Deconnecter);
		
		JPanel bandeDeco = new JPanel();
		bandeDeco.setBackground(new Color(25, 25, 112));
		bandeDeco.setBounds(0, 73, 875, 54);
		frame.getContentPane().add(bandeDeco);
		bandeDeco.setLayout(null);
		
		Button button = new Button("Ajouter");
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(119, 136, 153));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AjouterUtilisateur au = new AjouterUtilisateur();
				au.getFrame().setVisible(true);
			}
		});
		button.setBounds(46, 10, 113, 22);
		bandeDeco.add(button);
		
		/**
		 * Création et initialisation de la table
		 * avec les données et les colonnes
		 */
		JTable table = new JTable(donnee, colonne) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		/**
		 * Listener permettant d'implémenter les fonctionnalités de suppression et de modification
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if(modificationActive) {
					/**
					 *Elle prendra en paramètre l'id du groupe sélectionné
					 *La fenetre sera similaire à celle qui permet l'ajout d'un etudiant
					 * 
					 */
					int id = Integer.parseInt((String)table.getValueAt(row, 0));
					
					ModifierUtilisateur modifierUtilisateur = new ModifierUtilisateur(id);
					modifierUtilisateur.getFrame().setVisible(true);
				
				}else if(suppressionActive) {
					
					int id = Integer.parseInt((String)table.getValueAt(row, 0));
					Utilisateur us = utilisateurDAO.get(id);
					utilisateurDAO.delete(us);
					ArrayList<Utilisateur> listeUtilisateur = utilisateurDAO.getList();
					donnee = getTableauUtilisateur(listeUtilisateur, colonne);
					JTable t = new JTable(donnee, colonne);
					table.setModel(t.getModel());
					
				}else {
					//Pour une éventuelle modification directe des cellules de la JTable
					//Suivie d'une mise à jour de la BDD
					//Le pouvoir absolu du Gestionnaire
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color(211, 211, 211));
		scrollPane.setBounds(0, 160, 888, 256);
		frame.getContentPane().add(scrollPane);
		
		
		
		Button supprimer = new Button("Supprimer");
		supprimer.setBackground(new Color(119, 136, 153));
		supprimer.setForeground(Color.WHITE);
		supprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		/**
		 * Ce listener permet d'activer la fonction de suppression
		 * des etudiants dans la table
		 */
		supprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Cursor curseur;
				if(suppressionActive) {
					curseur = new Cursor(Cursor.DEFAULT_CURSOR);
					suppressionActive = false;
				}
				else{
					curseur = changeCursor("Supprimer");
					suppressionActive = true;
					modificationActive = false;
				}
				frame.setCursor(curseur);
			}
		});
		
		supprimer.setBounds(220, 10, 113, 22);
		bandeDeco.add(supprimer);
		
		
		Button modifier = new Button("Modifier");
		modifier.setForeground(Color.WHITE);
		modifier.setBackground(new Color(119, 136, 153));
		/**
		 * Ce listener permet d'activer la modification des etudiants de la table
		 */
		modifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Cursor curseur;
				if(modificationActive) {
					curseur = new Cursor(Cursor.DEFAULT_CURSOR);
					modificationActive = false;
				}
				else {
					curseur = changeCursor("Modifier");
					modificationActive = true;
					suppressionActive = false;
				}
				frame.setCursor(curseur);
			}
		});
		modifier.setBounds(382, 10, 113, 22);
		bandeDeco.add(modifier);
		
		Button actualiserBtn = new Button("Actualiser");
		actualiserBtn.setBackground(new Color(119, 136, 153));
		actualiserBtn.setForeground(Color.WHITE);
		actualiserBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ArrayList<Utilisateur> listeUtilisateur = utilisateurDAO.getList();
				donnee = getTableauUtilisateur(listeUtilisateur, colonne);
				JTable t = new JTable(donnee, colonne);
				table.setModel(t.getModel());
			}
		});
		actualiserBtn.setBounds(692, 10, 113, 22);
		bandeDeco.add(actualiserBtn);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(119, 136, 153));
		panel.setBounds(0, 120, 875, 44);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblListeDesGroupes = new JLabel("Liste des Utilisateurs");
		lblListeDesGroupes.setFont(new Font("Dialog", Font.BOLD, 20));
		lblListeDesGroupes.setHorizontalAlignment(SwingConstants.CENTER);
		lblListeDesGroupes.setForeground(Color.WHITE);
		lblListeDesGroupes.setBounds(-42, 13, 352, 22);
		panel.add(lblListeDesGroupes);
		
		Button retourBtn = new Button("Retour");
		retourBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				frame = new MenuGestionnaire().getFrame();
				frame.setVisible(true);
			}
		});
		retourBtn.setForeground(Color.WHITE);
		retourBtn.setBackground(new Color(25, 25, 112));
		retourBtn.setBounds(20, 429, 113, 22);
		frame.getContentPane().add(retourBtn);
		
		JLabel lblEsigelec = new JLabel(" ESIGELEC ");
		lblEsigelec.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblEsigelec.setForeground(Color.RED);
		lblEsigelec.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 38));
		lblEsigelec.setBounds(671, 13, 181, 47);
		frame.getContentPane().add(lblEsigelec);
		frame.setResizable(false);
		
		
		
	}
	
	/**
	 * Méthode pour acceder à la frame de la classe
	 * Utile pour changer de fenetre
	 * @return frame
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Mets les informations de la liste dans un tableau à 2 dimensions
	 * exploitable facilement par une JTable
	 * @param listeEtudiant
	 * @param colonne
	 * @return data
	 */
	public String[][] getTableauUtilisateur(ArrayList<Utilisateur> listeUtilisateur, String[] colonne){
		String data[][] = new String[listeUtilisateur.size()][colonne.length];
		for(int i=0; i<listeUtilisateur.size(); i++) {
			data[i][0] = String.valueOf(listeUtilisateur.get(i).getIdUtilisateur());
			data[i][1] = listeUtilisateur.get(i).getMdp();
			data[i][2] = listeUtilisateur.get(i).getProfil();			
		}
		return data;
	}

	
	/**
	 * Permet de changer l'apparence du curseur
	 * @param action
	 * @return
	 */
	public Cursor changeCursor(String action) {
		String iconPath;
		Cursor curseur;
		String position;
		if(action.equals("Supprimer")) {
			iconPath = "./src/images/CloseDeleteBlackIcon.png";
			position = "centre";
		}else {
			iconPath = "./src/images/EditBlackPencilIcon.png";
			position = "bord";
		}
		
		try {
			Image icon = ImageIO.read(new File(iconPath));
			Point point;
			/**
			 * La position du point de l'image ou le click du curseur est actif
			 * Pour l'image en croix le centre et pour la seconde en bas à gauche
			 * Les valeurs de Height et Length ont été déterminé en fonction de la taille de chaque image
			 */
			if(position.equals("centre")) 
				point = new Point(icon.getWidth(frame)/10, icon.getHeight(frame)/10);
			else
				point = new Point(0, icon.getHeight(frame)/20);
			
			curseur = Toolkit.getDefaultToolkit().createCustomCursor(icon, point, "Personal Cursor");
		}catch(Exception e) {
			System.out.println("Erreur lors du changement de curseur\nVeuillez vérifier si les images sont dans le dossier images");
			curseur = new Cursor(Cursor.DEFAULT_CURSOR);
		}
		
		return curseur;
	}
}
