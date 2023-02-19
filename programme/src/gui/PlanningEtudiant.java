package gui;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import model.Cours;
import model.Enseignant;
import model.FonctionsDates;
import model.Horaire;
import model.Seance;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.CoursDAO;
import dao.EnseignantDAO;
import dao.HoraireDAO;
import dao.SeanceDAO;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class PlanningEtudiant {

	private JFrame frame;
	private String[] colonnes = new String[] {
			"Horaire", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"
		};
	private ArrayList<Seance> listeSeance;
	private JTable table;
	private EnseignantDAO enseignantDAO = new EnseignantDAO();
	private CoursDAO coursDAO = new CoursDAO();
	private SeanceDAO seanceDAO = new SeanceDAO();
	private int idGroupe = 1;
	private HoraireDAO horaireDAO = new HoraireDAO();
	private ArrayList<Horaire> listeHoraire = horaireDAO.getList();
	private String[][] donneesAdditionnelles = new String[11][6];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlanningEtudiant window = new PlanningEtudiant();
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
	public PlanningEtudiant() {
		initialize();
	}
	public PlanningEtudiant(int idGroupe) {
		this.idGroupe = idGroupe;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1038, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(95, 158, 160));
		panel.setBounds(0, 0, 1022, 79);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Votre Planning");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(44, 11, 341, 45);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 139));
		panel_1.setBounds(0, 80, 1022, 113);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		
		int nombreSemaines = seanceDAO.getNombreSemaine();
		for(int i=1; i<nombreSemaines; i++) {
			comboBox.addItem(i);
		}
		comboBox.setBounds(224, 33, 114, 22);
		panel_1.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Semaine");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(61, 31, 153, 22);
		panel_1.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 191, 1022, 319);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable(){

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
        
		table.setModel(new DefaultTableModel(
			getHoraireTab(),
			colonnes
		));
		int idSemaine = (int)comboBox.getSelectedItem();
		listeSeance = seanceDAO.getListSeanceEtudiant(idGroupe, idSemaine);
		for(int i=0; i<listeSeance.size(); i++) {
			/*try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			ajouterSeanceTable(table, listeSeance.get(i));
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idSemaine = (int)comboBox.getSelectedItem();
				listeSeance = seanceDAO.getListSeanceEtudiant(idGroupe, idSemaine);
				for(int i=0; i<listeSeance.size(); i++) {
					/*try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					ajouterSeanceTable(table, listeSeance.get(i));
				}
			}
		});
		scrollPane.setViewportView(table);
	}
	
	public Object[][] getHoraireTab() {
		Object[][] horairesTab = new Object[listeHoraire.size()][colonnes.length];
		for(int i=0; i<listeHoraire.size(); i++) {
			horairesTab[i][0] = String.valueOf(listeHoraire.get(i).getHeureDebut()) + "H00-" +String.valueOf(listeHoraire.get(i).getHeureFin()) + "H00";
		}
		return horairesTab;
	}
	
	public void ajouterSeanceTable(JTable table, Seance seance) {
		try{
		int row = seance.getIdHoraire() - 1 ;
		seance.setJour(FonctionsDates.getJourSemaine(seance.getDateSeance()));
		int column = FonctionsDates.jourToInt(seance.getJour());
		int idCours = seance.getIdCours();
		Cours c = coursDAO.get(idCours);
		Enseignant en = enseignantDAO.get(seance.getIdEnseignant());
		
		table.setValueAt(seance.myToStringT(c.getNomCours()), row, column);
		donneesAdditionnelles[row][column] = "Enseignant : " + en.getNom() + " " + en.getPrenom() + "    Date : " + seance.getDateSeance();
		//System.out.println(seance.getDateSeance() + "\t" + FonctionsDates.getJourSemaine(seance.getDateSeance()) + "\t" + seance.toString());
		}
		catch(Exception e) {
			
		}
	}
	public JFrame getFrame() {
		return frame;
	}
	
}
