package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.CoursDAO;
import dao.EnseignantDAO;
import dao.GroupeDAO;
import dao.HoraireDAO;
import dao.SeanceDAO;
import dao.SessionDAO;
import model.CellRenderer;
import model.Cours;
import model.Enseignant;
import model.FonctionsDates;
import model.Groupe;
import model.Horaire;
import model.MyRenderer;
import model.Seance;
import model.Session;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class CreerPlanningSemaine {

	private JFrame frame;
	private JTable table;
	private SeanceDAO seanceDAO = new SeanceDAO();
	private HoraireDAO horaireDAO = new HoraireDAO();
	private GroupeDAO groupeDAO = new GroupeDAO();
	private SessionDAO sessionDAO = new SessionDAO();
	private EnseignantDAO enseignantDAO = new EnseignantDAO();
	private CoursDAO coursDAO = new CoursDAO();
	private ArrayList<Enseignant> listeEnseignant = enseignantDAO.getList();
	private ArrayList<Cours> listeCours;
	private ArrayList<Session> listeSession = sessionDAO.getList();
	private ArrayList<Groupe> listeGroupe = groupeDAO.getList();
	private int selectedRow = 0;
	private int selectedColumn = 0;
	//private int selectedIdHoraire = 1;
	private ArrayList<Seance> listeSeance = new ArrayList<Seance>();
	private int semaine = 1;
	private String[][] donneesAdditionnelles = new String[11][6];
	//private MultiLineTableCellRenderer renderer = new MultiLineTableCellRenderer();
	//private MultipleLines renderer = new MultipleLines();
	//private FonctionsDates fonctionsDates = new FonctionsDates();
	private int nombreSeance = 1;
	
	private String[] colonnes = new String[] {
			"Horaire", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"
		};
	private String[] typeSeance = new String[] {
			"TD", "TP", "Cour Magistral", "Examen", "Rattrapage", "Reliquat"
		};
	
	private ArrayList<Horaire> listeHoraire = horaireDAO.getList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreerPlanningSemaine window = new CreerPlanningSemaine();
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
	public CreerPlanningSemaine() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(95, 158, 160));
		frame.setBounds(100, 100, 1224, 660);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(95, 158, 160));
		panel.setBounds(0, 0, 1221, 81);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ajouter Planning Des Cours de la Semaine");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(26, 18, 376, 43);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel(" ");
		label.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		label.setBounds(1020, 16, 168, 50);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(25, 25, 112));
		panel_1.setBounds(0, 76, 1221, 209);
		frame.getContentPane().add(panel_1);
		
		JComboBox typeSeanceBox = new JComboBox();
		for(int i=1; i<typeSeance.length; i++) {
			typeSeanceBox.addItem(typeSeance[i]);
		}
		typeSeanceBox.setBounds(158, 21, 190, 22);
		panel_1.add(typeSeanceBox);
		
		JComboBox sessionBox = new JComboBox();
		for(int i=0; i<listeSession.size(); i++) {
			sessionBox.addItem(listeSession.get(i).getNumSession());
		}
		sessionBox.setBounds(509, 21, 103, 22);
		panel_1.add(sessionBox);
		
		JComboBox groupeBox = new JComboBox();
		for(int i=0; i<listeGroupe.size(); i++) {
			groupeBox.addItem(listeGroupe.get(i).getNumeroGroupe());
		}
		groupeBox.setBounds(775, 21, 96, 22);
		panel_1.add(groupeBox);
		
		JLabel nomPrenomEnseignant = new JLabel("Nom Pr\u00E9nom");
		JLabel nomCoursLabel = new JLabel("Nom Cours");
		JComboBox coursBox = new JComboBox();
		coursBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
				int idCours = (int)coursBox.getSelectedItem();
				Cours c = new CoursDAO().get(idCours);
				nomCoursLabel.setText(c.getNomCours());
				}
				catch(Exception exx){
					
				}
			}
		});
		
		JComboBox enseignantBox = new JComboBox();
		for(int i=0; i<listeEnseignant.size(); i++) {
			enseignantBox.addItem(listeEnseignant.get(i).getIdEnseignant());
		}
		
		int idEnseignant = (int)enseignantBox.getSelectedItem();
		Enseignant en = enseignantDAO.get(idEnseignant);
		
		nomPrenomEnseignant.setText(en.getNom() + " " + en.getPrenom());
		coursBox.removeAllItems();
		listeCours = CoursDAO.getList(idEnseignant);
		for(int i=0; i<listeCours.size(); i++) {
			coursBox.addItem(listeCours.get(i).getIdCours());
		}
		
		nomCoursLabel.setText(coursDAO.get((int)coursBox.getSelectedItem()).getNomCours());
		
		enseignantBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				coursBox.removeAllItems();
				Enseignant en = enseignantDAO.get((int)enseignantBox.getSelectedItem());
				nomPrenomEnseignant.setText(en.getNom() + " " + en.getPrenom());
				listeCours = CoursDAO.getList(idEnseignant);
				for(int i=0; i<listeCours.size(); i++) {
					coursBox.addItem(listeCours.get(i).getIdCours());
				}
			
			}
		});
		
		enseignantBox.setBounds(158, 92, 77, 22);
		panel_1.add(enseignantBox);
		
		
		coursBox.setBounds(509, 92, 103, 22);
		panel_1.add(coursBox);
		
		JLabel lblNewLabel_1 = new JLabel("Type de S\u00E9ance");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(45, 23, 103, 18);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Session");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(396, 23, 103, 18);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Cours");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setBounds(396, 96, 103, 18);
		panel_1.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Groupe");
		lblNewLabel_1_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2.setBounds(669, 25, 103, 18);
		panel_1.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Enseignant");
		lblNewLabel_1_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setBounds(45, 96, 103, 18);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		nomPrenomEnseignant.setForeground(new Color(255, 255, 255));
		nomPrenomEnseignant.setFont(new Font("Tahoma", Font.BOLD, 14));
		nomPrenomEnseignant.setHorizontalAlignment(SwingConstants.CENTER);
		nomPrenomEnseignant.setBounds(68, 125, 167, 36);
		panel_1.add(nomPrenomEnseignant);
		
		nomCoursLabel.setForeground(new Color(255, 255, 255));
		nomCoursLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nomCoursLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		nomCoursLabel.setBounds(432, 125, 167, 36);
		panel_1.add(nomCoursLabel);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Heure de d\u00E9but");
		lblNewLabel_1_1_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_2.setBounds(669, 96, 103, 18);
		panel_1.add(lblNewLabel_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Heure de fin");
		lblNewLabel_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_2_1.setBounds(669, 129, 103, 18);
		panel_1.add(lblNewLabel_1_1_1_2_1);
		
		JComboBox semaineBox = new JComboBox();
		semaineBox.addItem(semaine);
		semaineBox.setSelectedItem(semaine);
		semaineBox.setBounds(1026, 21, 96, 22);
		panel_1.add(semaineBox);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Semaine");
		lblNewLabel_1_1_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2_1.setBounds(913, 23, 103, 18);
		panel_1.add(lblNewLabel_1_1_2_1);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Jour");
		lblNewLabel_1_1_2_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2_1_1.setBounds(913, 94, 103, 18);
		panel_1.add(lblNewLabel_1_1_2_1_1);
		
		JComboBox jourBox = new JComboBox();
		for(int i=1; i<colonnes.length; i++) {
			jourBox.addItem(colonnes[i]);
		}
		jourBox.setBounds(1026, 92, 96, 22);
		panel_1.add(jourBox);
		
		JButton ajouterButton = new JButton("Ajouter");
		ajouterButton.setForeground(new Color(255, 255, 255));
		ajouterButton.setBackground(new Color(95, 158, 160));
		ajouterButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(selectedColumn!=0) {
					String typeSeance = (String)typeSeanceBox.getSelectedItem();
					int numSession = (int)sessionBox.getSelectedItem();
					int numeroGrp = (int) groupeBox.getSelectedItem();
					int idEnseignant = (int) enseignantBox.getSelectedItem();
					int idCours = (int) coursBox.getSelectedItem();
					int idHoraire = getIdHoraire(selectedRow);
					int idSemaine = 1;
					String journee = colonnes[selectedColumn];
					Seance seance = new Seance(nombreSeance, typeSeance, numSession, numeroGrp, idEnseignant, idCours, idHoraire, journee, idSemaine);
					nombreSeance++;
					ajouterSeanceTable(table, seance);
					listeSeance.add(seance);
				}
			}
		});
		
		ajouterButton.setBounds(961, 175, 89, 23);
		panel_1.add(ajouterButton);
		
		JLabel heureDebutLabel = new JLabel("8H00");
		heureDebutLabel.setForeground(new Color(255, 255, 255));
		heureDebutLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		heureDebutLabel.setHorizontalAlignment(SwingConstants.CENTER);
		heureDebutLabel.setBounds(775, 96, 103, 18);
		panel_1.add(heureDebutLabel);
		
		JLabel heureFinLabel = new JLabel("9H00");
		heureFinLabel.setForeground(new Color(255, 255, 255));
		heureFinLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		heureFinLabel.setHorizontalAlignment(SwingConstants.CENTER);
		heureFinLabel.setBounds(775, 125, 103, 18);
		panel_1.add(heureFinLabel);
		
		table = table = new JTable(){

            //Implement table cell tool tips.           
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);

                try {
                    //comment row, exclude heading
                    if(colIndex != 0){
                      //tip = getValueAt(rowIndex, colIndex).toString();
                    tip = donneesAdditionnelles[rowIndex][colIndex];
                    }
                } catch (RuntimeException e1) {
                    //catch null pointer exception if mouse is over an empty line
                }

                return tip;
            }
        };
		
		JButton btnMettreJour = new JButton("Mettre \u00E0 jour");
		btnMettreJour.setBackground(new Color(95, 158, 160));
		btnMettreJour.setForeground(new Color(255, 255, 255));
		btnMettreJour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//To do
				if(selectedColumn!=0 && table.getValueAt(selectedRow, selectedColumn)!=null) {
					String typeSeance = (String)typeSeanceBox.getSelectedItem();
					int numSession = (int)sessionBox.getSelectedItem();
					int numeroGrp = (int) groupeBox.getSelectedItem();
					int idEnseignant = (int) enseignantBox.getSelectedItem();
					int idCours = (int) coursBox.getSelectedItem();
					int idHoraire = getIdHoraire(selectedRow);
					int idSemaine = 1;
					String journee = colonnes[selectedColumn];
					Seance seance = new Seance(nombreSeance, typeSeance, numSession, numeroGrp, idEnseignant, idCours, idHoraire, journee, idSemaine);
					nombreSeance++;
					ajouterSeanceTable(table, seance);
					for(int i=0; i<listeSeance.size(); i++) {
						if(listeSeance.get(i).getJour().equals(seance.getJour()) && listeSeance.get(i).getIdHoraire() == seance.getIdHoraire()) {
							listeSeance.set(i, seance);
						}
					}
					//listeSeance.add(seance);
				}else {
					JOptionPane.showMessageDialog(null, "Rien à mettre à jour ici");
				}
			}
		});
		btnMettreJour.setBounds(830, 175, 121, 23);
		panel_1.add(btnMettreJour);
		
		JButton terminerBtn = new JButton("Terminer");
		terminerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listeSeance.size()<1) {
					JOptionPane.showMessageDialog(null, "Veuillez ajoutez au moins une séance");
				}else {
					int numSession = (int)sessionBox.getSelectedItem();
					Session session = sessionDAO.get(numSession);
					Date debutSession = session.getDateDebutSession();
					Date finSession = session.getDateFinSession();
					ArrayList<Date> listeDates = FonctionsDates.getListeDatesSession(debutSession, finSession);
					int idSemaine = 1;
					int weekYear = FonctionsDates.getNumeroSemaines(debutSession);
					//int nombreSemaines = FonctionsDates.getNombreSemaines(debutSession, finSession);
					int n = 0;
					for(int i=0; i<listeDates.size(); i++) {
						if(weekYear!=FonctionsDates.getNumeroSemaines(listeDates.get(i))) {
							weekYear = FonctionsDates.getNumeroSemaines(listeDates.get(i));
							idSemaine++;
						}
					for(int j=0; j<listeSeance.size(); j++) {
							if(FonctionsDates.getJourSemaine(listeDates.get(i)).equals(listeSeance.get(j).getJour())) {
								Seance s = listeSeance.get(j);
								s.setDateSeance(listeDates.get(i));
								s.setIdSemaine(idSemaine);
								int r = seanceDAO.add(s);
								if(r!=0) n++;
								
							}
							try {
							Thread.sleep(10);
						} catch (InterruptedException exxx) {
							// TODO Auto-generated catch block
							exxx.printStackTrace();
						}
						}
					}
						JOptionPane.showMessageDialog(null, "Planning créé");
						frame.dispose();
						frame = new MenuGestionnaire().getFrame();
						frame.setVisible(true);
					
				}
				
			}
		});
		terminerBtn.setForeground(new Color(255, 255, 255));
		terminerBtn.setBackground(new Color(95, 158, 160));
		terminerBtn.setBounds(1067, 175, 89, 23);
		panel_1.add(terminerBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 286, 1221, 281);
		frame.getContentPane().add(scrollPane);
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedRow = table.getSelectedRow();
				selectedColumn = table.getSelectedColumn();
				if(selectedColumn!=0) {
					Horaire df = horaireDAO.get(getIdHoraire(selectedRow));
					heureDebutLabel.setText(String.valueOf(df.getHeureDebut()) + "H00");
					heureFinLabel.setText(String.valueOf(df.getHeureFin()) + "H00");
					String jour = colonnes[selectedColumn];
					jourBox.setSelectedItem(jour);
				}
				
			}
		});
		table.setModel(new DefaultTableModel(
			getHoraireTab(),
			colonnes
		));
		//table.setRowHeight(table.getRowHeight() + 100);
		
		table.setDefaultRenderer(String.class, new MyRenderer());
		scrollPane.setViewportView(table);
		
		JButton btnRetour = new JButton("Retour ");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frame = new MenuGestionnaire().getFrame();
				frame.setVisible(true);
			}
		});
		btnRetour.setBackground(new Color(25, 25, 112));
		btnRetour.setForeground(new Color(255, 255, 255));
		btnRetour.setBounds(1076, 587, 97, 25);
		frame.getContentPane().add(btnRetour);
		frame.setResizable(false);
	}
	
	public Object[][] getHoraireTab() {
		Object[][] horairesTab = new Object[listeHoraire.size()][colonnes.length];
		for(int i=0; i<listeHoraire.size(); i++) {
			horairesTab[i][0] = String.valueOf(listeHoraire.get(i).getHeureDebut()) + "H00-" +String.valueOf(listeHoraire.get(i).getHeureFin()) + "H00";
		}
		return horairesTab;
	}
	
	public int getIdHoraire(int row) {
		return row + 1;
	}
	
	public void ajouterSeanceTable(JTable table, Seance seance) {
		int row = seance.getIdHoraire() - 1 ;
		int column = FonctionsDates.jourToInt(seance.getJour());
		int idEnseignant = seance.getIdEnseignant();
		int idCours = seance.getIdCours();
		Cours c = coursDAO.get(idCours);
		Enseignant prof = enseignantDAO.get(idEnseignant);
		//table.getColumnModel().getColumn(column).setCellRenderer(new MyRenderer());
		
		//System.out.println(row + " " + column);
		//table.setValueAt(seance.myToStringT(c.getNomCours(), prof.getNom() + " " + prof.getPrenom()), row, column);
		donneesAdditionnelles[row][column] = "Enseignant : " + prof.getNom() + " " + prof.getPrenom() + "    Date : " + seance.getDateSeance();
		table.setValueAt(seance.myToStringT(c.getNomCours()), row, column);
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
