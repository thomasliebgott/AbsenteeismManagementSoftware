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
import model.Enseignant;
import dao.EnseignantDAO;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;

public class GestionEnseignant {

	private JFrame frame;
	private String[][] donnee;
	EnseignantDAO enseignantDAO = new EnseignantDAO();
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
					GestionEnseignant window = new GestionEnseignant();
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
	public GestionEnseignant() {
		initialize();
	}
	
	public GestionEnseignant(String[][] donnee, String direction, String attribut) {
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
		//frame.setBounds(100, 100, 893, 493);
		frame.setBounds(100, 100, 893, 515);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/**
		 * Recup�ration des donn�es des �tudiants � afficher dans la table
		 * Creation des colonnes de la table
		 * Les donn�es sont ensuite mises dans un tableau � 2 dimension
		 * pour etre affich� proprement dans la table
		 */
		ArrayList<Enseignant> liste = enseignantDAO.getList("Id", "Croissant");
		String colonne[] = {"Id", "Nom", "Prenom", "Utilisateur", "T�l�phone"};
		if(this.donnee == null) {
			donnee = getListeEnseignantTrie(liste, colonne);
		}
		
		
		JLabel lblNewLabel = new JLabel("GESTION DES ENSEIGNANT");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setBounds(-23, 12, 352, 54);
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
		Deconnecter.setBounds(728, 448, 149, 29);
		frame.getContentPane().add(Deconnecter);
		
		
		JComboBox comboBox = new JComboBox();
		JComboBox croissant = new JComboBox();
		
		JPanel bandeDeco = new JPanel();
		bandeDeco.setBackground(new Color(25, 25, 112));
		bandeDeco.setBounds(0, 73, 887, 54);
		frame.getContentPane().add(bandeDeco);
		bandeDeco.setLayout(null);
		
		Button button = new Button("Ajouter");
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(119, 136, 153));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AjouterEnseignant ae = new AjouterEnseignant();
				ae.getFrame().setVisible(true);
			}
		});
		button.setBounds(10, 10, 113, 22);
		bandeDeco.add(button);
		
		/**
		 * Cr�ation et initialisation de la table
		 * avec les donn�es et les colonnes
		 */
		JTable table = new JTable(donnee, colonne) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		/**
		 * Listener permettant d'impl�menter les fonctionnalit�s de suppression et de modification
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if(modificationActive) {
					/**
					 *Elle prendra en param�tre l'id de l'etudiant s�lectionn�
					 *La fenetre sera similaire � celle qui permet l'ajout d'un etudiant
					 * 
					 */
					int id = Integer.parseInt((String)table.getValueAt(row, 0));
					ModifierEnseignant modifierEnseignant = new ModifierEnseignant(id);
					modifierEnseignant.getFrame().setVisible(true);
				
				}else if(suppressionActive) {
					
					/**
					 * On utilise l'id de l'etudiant pour le suprimer
					 * PLus tard - Demander si on veut vraiment supprimer cet etudiant/ Possibilit� de suppression multiple � envisager
					 * 
					 * 1 - Supprimer dans la BDD en se servant de la classe DAO
					 * 2 - Mettre � jour la JTable
					 */
					
					int id = Integer.parseInt((String)table.getValueAt(row, 0));
					enseignantDAO.delete(id);
					
					String sens = (String)croissant.getSelectedItem();
					String critere = (String)comboBox.getSelectedItem();
					
					ArrayList<Enseignant> liste = enseignantDAO.getList(critere, sens);
					updateTable(liste, colonne, table);
					
					
				}else {
					//Pour une �ventuelle modification directe des cellules de la JTable
					//Suivie d'une mise � jour de la BDD
					//Le pouvoir absolu du Gestionnaire
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color(211, 211, 211));
		scrollPane.setBounds(0, 160, 887, 282);
		frame.getContentPane().add(scrollPane);
		//frame.setResizable(false);
		
		
		for(String str : colonne) {
			comboBox.addItem(str);
		}
		
		
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
		 * Listener qui permet de mettre � jour
		 * la table en fonction des options d'affichages choisies
		 */
		
		croissant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sens = (String)croissant.getSelectedItem();
				String critere = (String)comboBox.getSelectedItem();
				
				ArrayList<Enseignant> liste = enseignantDAO.getList(critere, sens);
				updateTable(liste, colonne, table);
				
				/*
				frame.dispose();
				frame = new GestionEtudiant(donnees, sens, critere).getFrame();
				frame.setVisible(true);
				*/
			}
		});
		
		/**
		 * Listener qui permet de mettre � jour
		 * la table en fonction des options d'affichages choisies
		 */
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sens = (String)croissant.getSelectedItem();
				String critere = (String)comboBox.getSelectedItem();
				
				ArrayList<Enseignant> liste = enseignantDAO.getList(critere, sens);
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
		supprimer.setForeground(Color.WHITE);
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
		
		for(String str : colonne) {
			cherchezBox.addItem(str);
		}
		cherchezBox.setBounds(693, 10, 132, 22);
		panel.add(cherchezBox);
		
		searchFieldText = new JTextField();
		/**
		 * Ce listener permet de r�aliser des recherches aussitot
		 * qu'on entre des donn�es dans la barre de texte
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
					resultatRecherche = recherchezEnseignant(critere, recherche, colonne);
				else {
					critere = (String)comboBox.getSelectedItem();
					ArrayList<Enseignant> liste = enseignantDAO.getList(critere, sens);
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
					resultatRecherche = recherchezEnseignant(critere, recherche, colonne);
				else {
					critere = (String)comboBox.getSelectedItem();
					ArrayList<Enseignant> liste = enseignantDAO.getList(critere, sens);
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
				ArrayList<Enseignant> liste = enseignantDAO.getList(critere, sens);
				updateTable(liste, colonne, table);
				
			}
		});
		
		chercherBtn.setForeground(new Color(255, 255, 255));
		chercherBtn.setBackground(new Color(25, 25, 112));
		chercherBtn.setBounds(391, 10, 113, 22);
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
		retourBtn.setForeground(Color.WHITE);
		retourBtn.setBackground(new Color(25, 25, 112));
		retourBtn.setBounds(10, 448, 113, 22);
		frame.getContentPane().add(retourBtn);
		
		JLabel lblEsigelec = new JLabel(" ESIGELEC ");
		lblEsigelec.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblEsigelec.setForeground(Color.RED);
		lblEsigelec.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 38));
		lblEsigelec.setBounds(696, 11, 179, 47);
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
	 * M�thode pour acceder � la frame de la classe
	 * Utile pour changer de fenetre
	 * @return frame
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Mets les informations de la liste dans un tableau � 2 dimensions
	 * exploitable facilement par une JTable
	 * @param listeEtudiant
	 * @param colonne
	 * @return data
	 */
	public String[][] getListeEnseignantTrie(ArrayList<Enseignant> listeEnseignant, String[] colonne){
		String data[][] = new String[listeEnseignant.size()][colonne.length];
		for(int i=0; i<listeEnseignant.size(); i++) {
			data[i][0] = String.valueOf(listeEnseignant.get(i).getIdEnseignant());
			data[i][1] = listeEnseignant.get(i).getNom(); 
			data[i][2] = listeEnseignant.get(i).getPrenom();
			data[i][3] = String.valueOf(listeEnseignant.get(i).getIdUtilisateur());
			data[i][4] = String.valueOf(listeEnseignant.get(i).getNumTel());
			
		}
		return data;
	}
	
	/**
	 * Permet d'afficher les �tudiants de la liste
	 * Utilis� � des fins de test lors de l'impl�mentation du
	 * tri et de la recherche
	 * @param liste
	 */
	public void displayList(ArrayList<Enseignant> liste) {
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
			 * Pour l'image en croix le centre et pour la seconde en bas � gauche
			 * Les valeurs de Height et Length ont �t� d�termin� en fonction de la taille de chaque image
			 */
			if(position.equals("centre")) 
				point = new Point(icon.getWidth(frame)/10, icon.getHeight(frame)/10);
			else
				point = new Point(0, icon.getHeight(frame)/20);
			
			curseur = Toolkit.getDefaultToolkit().createCustomCursor(icon, point, "Personal Cursor");
		}catch(Exception e) {
			System.out.println("Erreur lors du changement de curseur\nVeuillez v�rifier si les images sont dans le dossier images");
			curseur = new Cursor(Cursor.DEFAULT_CURSOR);
		}
		
		return curseur;
	}
	
	/**
	 * M�thode de recherche d'un �tudiant dans la base de donn�e
	 * Il renvoie les r�sutats dans une table (JTable) exploitable par la suite
	 * @param critere
	 * @param recherche
	 * @param colonne
	 * @return t
	 */
	public JTable recherchezEnseignant(String critere, String recherche, String[] colonne) {
		boolean erreur = false;
		JTable t = null;
		if(critere.equals("Id") || critere.equals("Utilisateur")) {
			try {
				int id = Integer.parseInt(recherche);
			}catch(Exception exception) {
				JOptionPane.showMessageDialog(null, "Identifiant invalide","Entrez un entier",JOptionPane.ERROR_MESSAGE);
				erreur = true;
			}
		}
		
		if(!erreur) {
			ArrayList<Enseignant> liste = enseignantDAO.recherchez(critere, recherche);
			//displayList(liste);
			String donnees[][] = getListeEnseignantTrie(liste, colonne);
			t = new JTable(donnees, colonne);
		}
		
		return t;
	}
	
	/**
	 * Met � jour la table
	 * @param liste
	 * @param colonne
	 * @param table
	 */
	public void updateTable(ArrayList<Enseignant> liste, String[] colonne, JTable table) {
		String donnees[][] = getListeEnseignantTrie(liste, colonne);
		JTable t = new JTable(donnees, colonne);
		table.setModel(t.getModel());
	}
}
