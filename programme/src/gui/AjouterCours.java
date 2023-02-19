package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import dao.EnseignantDAO;
import dao.EtudiantDAO;
import dao.GroupeDAO;
import dao.CoursDAO;
import model.Cours;
import model.Enseignant;
import model.Etudiant;
import model.Groupe;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class AjouterCours {

	private JFrame frame;
	private JTextField textNomCours;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterCours window = new AjouterCours();
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
	public AjouterCours() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 586);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textNomCours = new JTextField();
		textNomCours.setBounds(227, 148, 116, 22);
		panel.add(textNomCours);
		textNomCours.setColumns(10);
		
		JLabel lblNomCours = new JLabel("nom Cours :");
		lblNomCours.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNomCours.setForeground(new Color(255, 255, 255));
		lblNomCours.setBackground(new Color(255, 255, 255));
		lblNomCours.setBounds(28, 149, 142, 16);
		panel.add(lblNomCours);
		
		JLabel lblMasseHoraireCours = new JLabel("nombre d'heure de cours en Amphi :");
		lblMasseHoraireCours.setForeground(new Color(255, 255, 255));
		lblMasseHoraireCours.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMasseHoraireCours.setBounds(28, 218, 284, 16);
		panel.add(lblMasseHoraireCours);
		
		JLabel lblMasseHoraireCours_1 = new JLabel("nombre d'heure de cours en TD :");
		lblMasseHoraireCours_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMasseHoraireCours_1.setForeground(new Color(255, 255, 255));
		lblMasseHoraireCours_1.setBounds(29, 273, 266, 16);
		panel.add(lblMasseHoraireCours_1);
		
		JLabel lblNombreDheureDe = new JLabel("nombre d'heure de cours en TP :");
		lblNombreDheureDe.setForeground(new Color(255, 255, 255));
		lblNombreDheureDe.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNombreDheureDe.setBounds(28, 327, 284, 16);
		panel.add(lblNombreDheureDe);
		
		JLabel lblNombreDheureDexam = new JLabel("nombre d'heure d'Exam :");
		lblNombreDheureDexam.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNombreDheureDexam.setForeground(new Color(255, 255, 255));
		lblNombreDheureDexam.setBounds(28, 383, 267, 16);
		
		panel.add(lblNombreDheureDexam);

		JButton btnRetour = new JButton("retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				frame = new MenuGestionnaire().getFrame();
				frame.setVisible(true);
			}
		});
		btnRetour.setForeground(new Color(255, 255, 255));
		btnRetour.setBackground(new Color(95, 158, 160));
		btnRetour.setBounds(533, 442, 97, 25);
		panel.add(btnRetour);
		
		JSpinner spinnerA = new JSpinner();
		spinnerA.setBounds(324, 217, 30, 22);
		panel.add(spinnerA);
		
		JSpinner spinnerTD = new JSpinner();
		spinnerTD.setBounds(324, 270, 30, 22);
		panel.add(spinnerTD);
		
		JSpinner spinnerTP = new JSpinner();
		spinnerTP.setBounds(324, 324, 30, 22);
		panel.add(spinnerTP);
		
		JSpinner spinnerE = new JSpinner();
		spinnerE.setBounds(324, 380, 30, 22);
		panel.add(spinnerE);
		
		JLabel nomEnseignantLabel = new JLabel("New label");
		nomEnseignantLabel.setForeground(new Color(255, 255, 255));
		nomEnseignantLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nomEnseignantLabel.setBounds(426, 243, 359, 70);
		panel.add(nomEnseignantLabel);
		
		/**
		 * afficher la liste des enseignant dans la combobox 
		 */
		
		JComboBox comboEnseignant = new JComboBox();
		comboEnseignant.setBounds(426, 354, 266, 22);
		panel.add(comboEnseignant);
		selectionnerEnseignant(comboEnseignant);
		comboEnseignant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int idEnseignant = Integer.parseInt((String)comboEnseignant.getSelectedItem());
				nomEnseignantLabel.setText(getNomPrenomEnseignant(idEnseignant));
			}
		});
		
		JLabel lblListeEnseignant = new JLabel("liste enseignant :");
		lblListeEnseignant.setForeground(new Color(255, 255, 255));
		lblListeEnseignant.setFont(new Font("Dialog", Font.BOLD, 16));
		lblListeEnseignant.setBounds(428, 311, 167, 32);
		panel.add(lblListeEnseignant);

		/**
		 * permet d'ajouter un type de cours dans la bdd 
		 */
		
		JButton btnValider = new JButton("Valider");
		btnValider.setBackground(new Color(95, 158, 160));
		btnValider.setForeground(new Color(255, 255, 255));
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int idEnseignant = Integer.parseInt((String)comboEnseignant.getSelectedItem());
				String nomCours = textNomCours.getText();
				int masseHoraireAmphi = Integer.parseInt(spinnerA.getValue().toString());
				int masseHoraireTD = Integer.parseInt(spinnerTD.getValue().toString());
				int masseHoraireTP = Integer.parseInt(spinnerTP.getValue().toString());
				int masseHoraireExam = Integer.parseInt(spinnerE.getValue().toString());
				int	retour = new CoursDAO().add(new Cours(0,idEnseignant,nomCours,masseHoraireAmphi,masseHoraireTD,masseHoraireTP,masseHoraireExam));
				
				if(retour!= 0) {
					JOptionPane.showMessageDialog(null, "Cours enregistré dans la BDD");
				}else {
					JOptionPane.showMessageDialog(null, "Erreur lors de l'insertion de l'étudiant dans la BDD");
				}
		textNomCours.setText(null);
			}
		});
		
		btnValider.setBounds(663, 442, 97, 25);
		panel.add(btnValider);
		
		JLabel lblNomEnseignantSlctionn = new JLabel("nom enseignant s\u00E9l\u00E9ctionn\u00E9 : ");
		lblNomEnseignantSlctionn.setForeground(new Color(255, 255, 255));
		lblNomEnseignantSlctionn.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNomEnseignantSlctionn.setBounds(426, 210, 245, 33);
		panel.add(lblNomEnseignantSlctionn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(95, 158, 160));
		panel_1.setBounds(0, 0, 1006, 116);
		panel.add(panel_1);
		
		JLabel lblTraiterJustificatif = new JLabel("AJOUTER COURS");
		lblTraiterJustificatif.setHorizontalAlignment(SwingConstants.CENTER);
		lblTraiterJustificatif.setForeground(Color.WHITE);
		lblTraiterJustificatif.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTraiterJustificatif.setBackground(Color.WHITE);
		lblTraiterJustificatif.setBounds(34, 46, 237, 25);
		panel_1.add(lblTraiterJustificatif);
		
		JLabel lblEsigelec = new JLabel(" ");
		lblEsigelec.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblEsigelec.setForeground(Color.RED);
		lblEsigelec.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 38));
		lblEsigelec.setBounds(587, 35, 181, 42);
		panel_1.add(lblEsigelec);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(95, 158, 160));
		panel_2.setBounds(0, 505, 797, 46);
		panel.add(panel_2);
		frame.setResizable(false);
		
	}
	
	public void selectionnerEnseignant(JComboBox comboEnseignant) {
		ArrayList<Enseignant> listeEnseignant = new EnseignantDAO().getList();
		comboEnseignant.removeAllItems();
		for(int i=0; i<listeEnseignant.size(); i++) {
			comboEnseignant.addItem(String.valueOf(listeEnseignant.get(i).getIdEnseignant()));
			

		}
		
	}
	/**
	 * methode pour avoir le nom et prenom de l'enseignant en focntion de l'id
	 * @param idEnseignant
	 * @return
	 */
	public String getNomPrenomEnseignant(int idEnseignant){
		ArrayList<Enseignant> listeEnseignant = new EnseignantDAO().getList();
		String nomEnseignant = null;
		String prenomEnseignant = null;
		for(int i=0; i<listeEnseignant.size(); i++) {
			if(idEnseignant == listeEnseignant.get(i).getIdEnseignant()){
				
				nomEnseignant = listeEnseignant.get(i).getNom();
				prenomEnseignant = listeEnseignant.get(i).getPrenom();
			}
		
		}
		if(nomEnseignant != null && prenomEnseignant != null){
		return nomEnseignant + "     "+ prenomEnseignant;
	}	
		return null;
	}
	public JFrame getFrame() {
		return frame;
	}
}
