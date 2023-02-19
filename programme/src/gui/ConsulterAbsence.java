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

import dao.AbsenceDAO;
import dao.CoursDAO;
import dao.EtudiantDAO;
import dao.SanctionDAO;
import dao.SeanceDAO;
import model.Absence;
import model.Etudiant;
import model.Sanction;
import model.Seance;

import javax.swing.JButton;

public class ConsulterAbsence {

	private JFrame frame;
	private String[][] donnee;
	private AbsenceDAO absenceDAO = new AbsenceDAO();
	private EtudiantDAO etudiantDAO = new EtudiantDAO();
	private int idEtudiant = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsulterAbsence window = new ConsulterAbsence();
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
	public ConsulterAbsence() {
		initialize();
	}
	
	public ConsulterAbsence(int idEtudiant) {
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
		 * Recupération des données des groupes à afficher dans la table
		 * Creation des colonnes de la table
		 * Les données sont ensuite mises dans un tableau à 2 dimension
		 * pour etre affiché proprement dans la table
		 */
		ArrayList<Absence> liste = absenceDAO.getList(idEtudiant);
		String colonne[] = {"Id Absence", "Etudiant", "Cours", "typeSeance","Date", "Hors quota ou non", "Justifie ou non"};
		
		if(this.donnee == null) {
			donnee = getTableauAbsence(liste, colonne);
		}
		
		
		JLabel lblNewLabel = new JLabel("CONSULTER ABSENCES");
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
		
		JButton btnNewButton = new JButton("Terminer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Retour à l'interface précédent
			}
		});
		btnNewButton.setBounds(702, 11, 89, 23);
		bandeDeco.add(btnNewButton);
		
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
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color(211, 211, 211));
		scrollPane.setBounds(0, 160, 880, 267);
		frame.getContentPane().add(scrollPane);
		
		
		
		
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
	public String[][] getTableauAbsence(ArrayList<Absence> listeAbsence, String[] colonne){
		String data[][] = new String[listeAbsence.size()][colonne.length];
		for(int i=0; i<listeAbsence.size(); i++) {
			try {
				data[i][0] = String.valueOf(listeAbsence.get(i).getIdAbsence());
				Etudiant etudiant = new EtudiantDAO().get(idEtudiant);
				data[i][1] = etudiant.getNom() + " " + etudiant.getPrenom();
				int idSeance = listeAbsence.get(i).getIdSeance();
				Seance s = new SeanceDAO().get(idSeance);
				int idCours = s.getIdCours();
				
				data[i][2] = s.myToStringT(new CoursDAO().get(idCours).getNomCours());
				data[i][3] = s.getTypeSeance();
				data[i][4] = s.getDateSeance().toString();
				data[i][5] = listeAbsence.get(i).getHorsQuota();
				data[i][6] = listeAbsence.get(i).getJustifieOuNon();
				
			}catch(Exception ex) {
				
			}
			
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

