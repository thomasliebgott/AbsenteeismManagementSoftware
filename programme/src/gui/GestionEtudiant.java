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

import dao.EtudiantDAO;

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
import model.Etudiant;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GestionEtudiant {

	private JFrame frame;
	private String[][] donnee;
	EtudiantDAO etudiantDAO = new EtudiantDAO();
	private String direction;
	private String attribut;
	private boolean suppressionActive = false;
	private boolean modificationActive = false;
	private JTextField searchFieldText;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionEtudiant window = new GestionEtudiant();
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
	public GestionEtudiant() {
		initialize();
	}
	
	public GestionEtudiant(String[][] donnee, String direction, String attribut) {
		this.donnee = donnee;
		this.direction = direction;
		this.attribut = attribut;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(95, 158, 160));
		frame.setBounds(100, 100, 893, 515);
		//frame.setBounds(100, 100, 893, 493);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/**
		 * Recupération des données des étudiants à afficher dans la table
		 * Creation des colonnes de la table
		 * Les données sont ensuite mises dans un tableau à 2 dimension
		 * pour etre affiché proprement dans la table
		 */
		ArrayList<Etudiant> liste = etudiantDAO.getList("Id", "Croissant");
		String colonne[] = {"ID", "ID Utilisateur","Adresse mail", "Nom", "Prénom", "Groupe", "Fillière"};
		if(this.donnee == null) {
			donnee = getListeEtudiantTrie(liste, colonne);
		}

		JLabel lblNewLabel = new JLabel("GESTION DES ETUDIANTS");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setBounds(-19, 6, 352, 54);
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
		Deconnecter.setBounds(728, 443, 149, 29);
		frame.getContentPane().add(Deconnecter);
		
		JComboBox comboBox = new JComboBox();
		JComboBox croissant = new JComboBox();
		
		JPanel bandeDeco = new JPanel();
		bandeDeco.setBackground(new Color(25, 25, 112));
		bandeDeco.setBounds(0, 73, 887, 54);
		frame.getContentPane().add(bandeDeco);
		bandeDeco.setLayout(null);
		
		Button button = new Button("Ajouter");
		button.setForeground(new Color(255, 255, 255));
		button.setBackground(new Color(119, 136, 153));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AjouterEtudiant aj = new AjouterEtudiant();
				aj.getFrame().setVisible(true);
			}
		});
		button.setBounds(10, 10, 113, 22);
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
					 *Elle prendra en paramètre l'id de l'etudiant sélectionné
					 *La fenetre sera similaire à celle qui permet l'ajout d'un etudiant
					 * 
					 */
					int id = Integer.parseInt((String)table.getValueAt(row, 0));
					ModifierEtudiant modifierEtudiant = new ModifierEtudiant(id);
					modifierEtudiant.getFrame().setVisible(true);
					
				}else if(suppressionActive) {
					
					/**
					 * On utilise l'id de l'etudiant pour le suprimer
					 * PLus tard - Demander si on veut vraiment supprimer cet etudiant/ Possibilité de suppression multiple à envisager
					 * 
					 * 1 - Supprimer dans la BDD en se servant de la classe DAO
					 * 2 - Mettre à jour la JTable
					 */
					
					int id = Integer.parseInt((String)table.getValueAt(row, 0));
					etudiantDAO.delete(id);
					
					String sens = (String)croissant.getSelectedItem();
					String critere = (String)comboBox.getSelectedItem();
					
					ArrayList<Etudiant> liste = etudiantDAO.getList(critere, sens);
					updateTable(liste, colonne, table);
					
					
				}else {
					//Pour une éventuelle modification directe des cellules de la JTable
					//Suivie d'une mise à jour de la BDD
					//Le pouvoir absolu du Gestionnaire
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color(211, 211, 211));
		scrollPane.setBounds(0, 160, 887, 277);
		frame.getContentPane().add(scrollPane);
		//frame.setResizable(false);
		
		comboBox.addItem("Id");
		comboBox.addItem("Groupe");
		comboBox.addItem("Filiere");
		comboBox.addItem("Nom");
		comboBox.addItem("Prenom");
		comboBox.addItem("Email");
		comboBox.addItem("Utilisateur");
		
		if(attribut!=null) {
			comboBox.setSelectedItem(attribut);
		}
		comboBox.setBounds(541, 10, 132, 22);
		bandeDeco.add(comboBox);
		
		
		croissant.addItem("Croissant");
		croissant.addItem("Decroissant");
		if(direction!=null) {
			croissant.setSelectedItem(direction);
		}
		
		/**
		 * Listener qui permet de mettre à jour
		 * la table en fonction des options d'affichages choisies
		 */
		
		croissant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sens = (String)croissant.getSelectedItem();
				String critere = (String)comboBox.getSelectedItem();
				
				ArrayList<Etudiant> liste = etudiantDAO.getList(critere, sens);
				updateTable(liste, colonne, table);
				
				/*
				frame.dispose();
				frame = new GestionEtudiant(donnees, sens, critere).getFrame();
				frame.setVisible(true);
				*/
			}
		});
		
		/**
		 * Listener qui permet de mettre à jour
		 * la table en fonction des options d'affichages choisies
		 */
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sens = (String)croissant.getSelectedItem();
				String critere = (String)comboBox.getSelectedItem();
				
				ArrayList<Etudiant> liste = etudiantDAO.getList(critere, sens);
				updateTable(liste, colonne, table);
				/*
				frame.dispose();
				frame = new GestionEtudiant(donnees, sens, critere).getFrame();
				frame.setVisible(true);
				*/
			}
		});
		
		croissant.setBounds(697, 10, 99, 22);
		bandeDeco.add(croissant);
		
		Button supprimer = new Button("Supprimer");
		supprimer.setBackground(new Color(119, 136, 153));
		supprimer.setForeground(new Color(255, 255, 255));
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
		
		supprimer.setBounds(150, 10, 113, 22);
		bandeDeco.add(supprimer);
		
		
		Button modifier = new Button("Modifier");
		modifier.setForeground(new Color(255, 255, 255));
		modifier.setBackground(new Color(119, 136, 153));
		modifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
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
		modifier.setBounds(292, 10, 113, 22);
		bandeDeco.add(modifier);
		
		JLabel trierLabel = new JLabel("Trier Par");
		trierLabel.setForeground(new Color(255, 255, 255));
		trierLabel.setHorizontalAlignment(SwingConstants.CENTER);
		trierLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		trierLabel.setBounds(443, 10, 88, 22);
		bandeDeco.add(trierLabel);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(119, 136, 153));
		panel.setBounds(0, 120, 887, 44);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox cherchezBox = new JComboBox();
		
		cherchezBox.addItem("Id");
		cherchezBox.addItem("Groupe");
		cherchezBox.addItem("Filiere");
		cherchezBox.addItem("Nom");
		cherchezBox.addItem("Prenom");
		cherchezBox.addItem("Email");
		cherchezBox.addItem("Utilisateur");
		cherchezBox.setBounds(693, 10, 132, 22);
		panel.add(cherchezBox);
		frame.setResizable(false);
		
		searchFieldText = new JTextField();
		/**
		 * Ce listener permet de réaliser des recherches aussitot
		 * qu'on entre des données dans la barre de texte
		 */
		searchFieldText.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				//String recherche = searchFieldText.getText();
				//System.out.println(recherche);
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				String recherche = searchFieldText.getText();
				String critere = (String)cherchezBox.getSelectedItem();
				String sens = (String)croissant.getSelectedItem();
				JTable resultatRecherche = null;
				if(!recherche.equals(""))
					resultatRecherche = recherchezEtudiant(critere, recherche, colonne);
				else {
					critere = (String)comboBox.getSelectedItem();
					ArrayList<Etudiant> liste = etudiantDAO.getList(critere, sens);
					updateTable(liste, colonne, table);
				}
				if(resultatRecherche != null) {
					table.setModel(resultatRecherche.getModel());
				}
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				String recherche = searchFieldText.getText();
				String critere = (String)cherchezBox.getSelectedItem();
				String sens = (String)croissant.getSelectedItem();
				JTable resultatRecherche = null;
				if(!recherche.equals(""))
					resultatRecherche = recherchezEtudiant(critere, recherche, colonne);
				else {
					critere = (String)comboBox.getSelectedItem();
					ArrayList<Etudiant> liste = etudiantDAO.getList(critere, sens);
					updateTable(liste, colonne, table);
				}
				if(resultatRecherche != null) {
					table.setModel(resultatRecherche.getModel());
				}
				
			}
		});
		
		searchFieldText.setBounds(128, 11, 222, 20);
		panel.add(searchFieldText);
		searchFieldText.setColumns(10);
		
		Button chercherBtn = new Button("Annuler");
		
		/**
		 * Ce listener permet de vider la barre de recherche
		 */
		
		chercherBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchFieldText.setText(null);
				String sens = (String)croissant.getSelectedItem();
				String critere = (String)comboBox.getSelectedItem();
				ArrayList<Etudiant> liste = etudiantDAO.getList(critere, sens);
				updateTable(liste, colonne, table);
			}
		});
		
		chercherBtn.setForeground(new Color(255, 255, 255));
		chercherBtn.setBackground(new Color(25, 25, 112));
		chercherBtn.setBounds(380, 9, 113, 22);
		panel.add(chercherBtn);
		
		JLabel CritereDeRechercheLabel = new JLabel("Critere de Recherche");
		CritereDeRechercheLabel.setForeground(new Color(255, 255, 255));
		CritereDeRechercheLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CritereDeRechercheLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		CritereDeRechercheLabel.setBounds(532, 11, 151, 22);
		panel.add(CritereDeRechercheLabel);
		
		JLabel lblRecherchez = new JLabel("Recherchez");
		lblRecherchez.setBackground(Color.CYAN);
		lblRecherchez.setForeground(Color.WHITE);
		lblRecherchez.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecherchez.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRecherchez.setBounds(10, 10, 116, 22);
		panel.add(lblRecherchez);
		
		Button retourBtn = new Button("Retour");
		retourBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frame = new MenuGestionnaire().getFrame();
				frame.setVisible(true);
			}
		});
		retourBtn.setBackground(new Color(25, 25, 112));
		retourBtn.setForeground(new Color(255, 255, 255));
		retourBtn.setBounds(10, 450, 113, 22);
		frame.getContentPane().add(retourBtn);
		
		JLabel lblEsigelec = new JLabel(" ESIGELEC ");
		lblEsigelec.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblEsigelec.setForeground(Color.RED);
		lblEsigelec.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 38));
		lblEsigelec.setBounds(696, 13, 181, 47);
		frame.getContentPane().add(lblEsigelec);
		frame.setResizable(false);
		
		/*
		DefaultTableModel tableModel =new DefaultTableModel(colonne, 5) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable table =  new JTable(tableModel);
		*/
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
	public String[][] getListeEtudiantTrie(ArrayList<Etudiant> listeEtudiant, String[] colonne){
		String data[][] = new String[listeEtudiant.size()][colonne.length];
		for(int i=0; i<listeEtudiant.size(); i++) {
			data[i][0] = String.valueOf(listeEtudiant.get(i).getIdEtudiant());
			data[i][1] = String.valueOf(listeEtudiant.get(i).getIdUtilisateur());
			data[i][2] = listeEtudiant.get(i).getAdresseMail();
			data[i][3] = listeEtudiant.get(i).getNom();
			data[i][4] = listeEtudiant.get(i).getPrenom();
			data[i][5] = String.valueOf(listeEtudiant.get(i).getIdGroupe());
			data[i][6] = listeEtudiant.get(i).getFiliere();
		}
		return data;
	}
	
	/**
	 * Permet d'afficher les étudiants de la liste
	 * Utilisé à des fins de test lors de l'implémentation du
	 * tri et de la recherche
	 * @param liste
	 */
	public void displayList(ArrayList<Etudiant> liste) {
		for(int i=0; i<liste.size(); i++) {
			liste.get(i).afficher();
		}
		System.out.println("Fin\n");
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
			iconPath = "images//CloseDeleteBlackIcon.png";
			position = "centre";
		}else {
			iconPath = "images//EditBlackPencilIcon.png";
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
	
	/**
	 * Méthode de recherche d'un étudiant dans la base de donnée
	 * Il renvoie les résutats dans une table (JTable) exploitable par la suite
	 * @param critere
	 * @param recherche
	 * @param colonne
	 * @return t
	 */
	public JTable recherchezEtudiant(String critere, String recherche, String[] colonne) {
		boolean erreur = false;
		JTable t = null;
		if(critere.equals("Groupe") || critere.equals("Id") || critere.equals("Utilisateur")) {
			try {
				int id = Integer.parseInt(recherche);
			}catch(Exception exception) {
				JOptionPane.showMessageDialog(null, "Identifiant invalide","Entrez un entier",JOptionPane.ERROR_MESSAGE);
				erreur = true;
			}
		}
		
		if(!erreur) {
			ArrayList<Etudiant> liste = etudiantDAO.recherchez(critere, recherche);
			displayList(liste);
			String donnees[][] = getListeEtudiantTrie(liste, colonne);
			t = new JTable(donnees, colonne);
		}
		
		return t;
	}
	
	/**
	 * Met à jour la table
	 * @param liste
	 * @param colonne
	 * @param table
	 */
	public void updateTable(ArrayList<Etudiant> liste, String[] colonne, JTable table) {
		String donnees[][] = getListeEtudiantTrie(liste, colonne);
		JTable t = new JTable(donnees, colonne);
		table.setModel(t.getModel());
	}
}
