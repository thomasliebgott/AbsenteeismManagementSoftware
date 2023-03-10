package gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import dao.EtudiantDAO;
import dao.SanctionDAO;
import model.Etudiant;
import model.Sanction;

public class GestionSanctions {

	private JFrame frame;
	private String[][] donnee;
	private SanctionDAO sanctionDAO = new SanctionDAO();
	private EtudiantDAO etudiantDAO = new EtudiantDAO();
	private boolean suppressionActive = false;
	private boolean modificationActive = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionSanctions window = new GestionSanctions();
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
	public GestionSanctions() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(95, 158, 160));
		frame.setBounds(100, 100, 888, 518);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/**
		 * Recup?ration des donn?es des groupes ? afficher dans la table
		 * Creation des colonnes de la table
		 * Les donn?es sont ensuite mises dans un tableau ? 2 dimension
		 * pour etre affich? proprement dans la table
		 */
		ArrayList<Sanction> liste = sanctionDAO.getList();
		String colonne[] = {"Id", "Id Etudiant", "Type de Sanction", "Nom et Prenom"};
		
		if(this.donnee == null) {
			donnee = getTableauSanction(liste, colonne);
		}
		
		
		JLabel lblNewLabel = new JLabel("GESTION DES SANCTIONS");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setBounds(-29, 13, 352, 54);
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
		Deconnecter.setBounds(711, 432, 149, 29);
		frame.getContentPane().add(Deconnecter);
		
		JPanel bandeDeco = new JPanel();
		bandeDeco.setBackground(new Color(25, 25, 112));
		bandeDeco.setBounds(0, 73, 890, 47);
		frame.getContentPane().add(bandeDeco);
		bandeDeco.setLayout(null);
		
		Button button = new Button("Ajouter");
		button.setForeground(new Color(255, 255, 255));
		button.setBackground(new Color(119, 136, 153));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AjouterSanction as = new AjouterSanction();
				as.getFrame().setVisible(true);
			}
		});
		button.setBounds(79, 10, 113, 22);
		bandeDeco.add(button);
		
		/**
		 * Cr?ation et initialisation de la table
		 * avec les donn?es et les colonnes
		 */
		JTable table = new JTable(donnee, colonne) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		/**
		 * Listener permettant d'impl?menter les fonctionnalit?s de suppression et de modification
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if(modificationActive) {
					/**
					 *Elle prendra en param?tre l'id du groupe s?lectionn?
					 *La fenetre sera similaire ? celle qui permet l'ajout d'un etudiant
					 * 
					 */
					int id = Integer.parseInt((String)table.getValueAt(row, 0));
					
					ModifierSanction modifierSanction = new ModifierSanction(id);
					modifierSanction.getFrame().setVisible(true);
				
				}else if(suppressionActive) {
					
					int id = Integer.parseInt((String)table.getValueAt(row, 0));
					sanctionDAO.delete(id);
					ArrayList<Sanction> listeSanction = sanctionDAO.getList();
					donnee = getTableauSanction(listeSanction, colonne);
					JTable t = new JTable(donnee, colonne);
					table.setModel(t.getModel());
					
				}else {
					//Pour une ?ventuelle modification directe des cellules de la JTable
					//Suivie d'une mise ? jour de la BDD
					//Le pouvoir absolu du Gestionnaire
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color(211, 211, 211));
		scrollPane.setBounds(0, 160, 880, 267);
		frame.getContentPane().add(scrollPane);
		
		
		
		Button supprimer = new Button("Supprimer");
		supprimer.setBackground(new Color(119, 136, 153));
		supprimer.setForeground(new Color(255, 255, 255));
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
		modifier.setForeground(new Color(255, 255, 255));
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
		actualiserBtn.setForeground(new Color(255, 255, 255));
		actualiserBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ArrayList<Sanction> listeSanction = sanctionDAO.getList();
				donnee = getTableauSanction(listeSanction, colonne);
				JTable t = new JTable(donnee, colonne);
				table.setModel(t.getModel());
			}
		});
		actualiserBtn.setBounds(652, 10, 113, 22);
		bandeDeco.add(actualiserBtn);
		
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(119, 136, 153));
		panel.setBounds(0, 113, 890, 61);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblListeDesGroupes = new JLabel("Liste des Sanctions");
		lblListeDesGroupes.setBackground(new Color(112, 128, 144));
		lblListeDesGroupes.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblListeDesGroupes.setHorizontalAlignment(SwingConstants.CENTER);
		lblListeDesGroupes.setForeground(Color.WHITE);
		lblListeDesGroupes.setBounds(-57, 13, 352, 22);
		panel.add(lblListeDesGroupes);
		
		Button retourBtn = new Button("Retour");
		retourBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frame = new MenuGestionnaire().getFrame();
				frame.setVisible(true);
			}
		});
		retourBtn.setForeground(new Color(255, 255, 255));
		retourBtn.setBackground(new Color(25, 25, 112));
		retourBtn.setBounds(10, 439, 113, 22);
		frame.getContentPane().add(retourBtn);
		
		JLabel lblEsigelec = new JLabel(" ESIGELEC ");
		lblEsigelec.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblEsigelec.setForeground(Color.RED);
		lblEsigelec.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 38));
		lblEsigelec.setBounds(664, 12, 179, 47);
		frame.getContentPane().add(lblEsigelec);
		frame.setResizable(false);
		
		
		
		
	}
	
	/**
	 * M?thode pour acceder ? la frame de la classe
	 * Utile pour changer de fenetre
	 * @return frame
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Mets les informations de la liste dans un tableau ? 2 dimensions
	 * exploitable facilement par une JTable
	 * @param listeEtudiant
	 * @param colonne
	 * @return data
	 */
	public String[][] getTableauSanction(ArrayList<Sanction> listeGroupe, String[] colonne){
		String data[][] = new String[listeGroupe.size()][colonne.length];
		for(int i=0; i<listeGroupe.size(); i++) {
			data[i][0] = String.valueOf(listeGroupe.get(i).getIdSanction());
			data[i][1] = String.valueOf(listeGroupe.get(i).getIdEtudiant());
			data[i][2] = listeGroupe.get(i).getTypeSanction();	
			int id = listeGroupe.get(i).getIdEtudiant();
			Etudiant et = etudiantDAO.get(id);
			data[i][3] = et.getNom() + " " + et.getPrenom();
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
			 * Pour l'image en croix le centre et pour la seconde en bas ? gauche
			 * Les valeurs de Height et Length ont ?t? d?termin? en fonction de la taille de chaque image
			 */
			if(position.equals("centre")) 
				point = new Point(icon.getWidth(frame)/10, icon.getHeight(frame)/10);
			else
				point = new Point(0, icon.getHeight(frame)/20);
			
			curseur = Toolkit.getDefaultToolkit().createCustomCursor(icon, point, "Personal Cursor");
		}catch(Exception e) {
			System.out.println("Erreur lors du changement de curseur\nVeuillez v?rifier si les images sont dans le dossier images");
			curseur = new Cursor(Cursor.DEFAULT_CURSOR);
		}
		
		return curseur;
	}
}

