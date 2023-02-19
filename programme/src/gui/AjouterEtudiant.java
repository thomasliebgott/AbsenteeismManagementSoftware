package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Label;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JComboBox;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import model.Etudiant;
import model.Groupe;
import model.Utilisateur;
import dao.EtudiantDAO;
import dao.GroupeDAO;
import dao.UtilisateurDAO;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class AjouterEtudiant {

	private JFrame frame;
	private JTextField nomTextField;
	private JTextField prenomTextField;
	private JTextField mailTextField;

	/**
	 * Launch the application.
	 * @param args 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterEtudiant window = new AjouterEtudiant();
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
	public AjouterEtudiant() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 687, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(0, 0, 680, 82);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(95, 158, 160));
		panel_3.setBounds(0, 0, 680, 82);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		Label label = new Label("AJOUTER UN ETUDIANT");
		label.setBounds(22, 20, 247, 33);
		panel_3.add(label);
		label.setBackground(new Color(95, 158, 160));
		label.setForeground(new Color(255, 255, 255));
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Dialog", Font.BOLD, 20));
		
		JLabel lblEsigelec = new JLabel(" ESIGELEC ");
		lblEsigelec.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblEsigelec.setForeground(Color.RED);
		lblEsigelec.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 38));
		lblEsigelec.setBounds(486, 20, 182, 47);
		panel_3.add(lblEsigelec);
		
		Label nomLabel = new Label("Nom");
		nomLabel.setForeground(new Color(255, 255, 255));
		nomLabel.setBackground(new Color(25, 25, 112));
		nomLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		nomLabel.setBounds(64, 103, 147, 27);
		frame.getContentPane().add(nomLabel);
		
		Label prenomLabel = new Label("Pr\u00E9nom");
		prenomLabel.setForeground(new Color(255, 255, 255));
		prenomLabel.setBackground(new Color(25, 25, 112));
		prenomLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		prenomLabel.setBounds(64, 146, 147, 27);
		frame.getContentPane().add(prenomLabel);
		
		Label mailLabel = new Label("Adresse mail");
		mailLabel.setForeground(new Color(255, 255, 255));
		mailLabel.setBackground(new Color(25, 25, 112));
		mailLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		mailLabel.setBounds(64, 195, 147, 27);
		frame.getContentPane().add(mailLabel);
		
		Label filiereLabel = new Label("Filiere");
		filiereLabel.setForeground(new Color(255, 255, 255));
		filiereLabel.setBackground(new Color(25, 25, 112));
		filiereLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		filiereLabel.setBounds(64, 228, 147, 34);
		frame.getContentPane().add(filiereLabel);
		
		Label groupeLabel = new Label("Groupe");
		groupeLabel.setForeground(new Color(255, 255, 255));
		groupeLabel.setBackground(new Color(25, 25, 112));
		groupeLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		groupeLabel.setBounds(64, 268, 147, 34);
		frame.getContentPane().add(groupeLabel);
		
		Label labelEtudiant = new Label("Identifiant Etudiant");
		labelEtudiant.setForeground(new Color(255, 255, 255));
		labelEtudiant.setBackground(new Color(25, 25, 112));
		labelEtudiant.setFont(new Font("Dialog", Font.BOLD, 16));
		labelEtudiant.setBounds(64, 363, 147, 34);
		frame.getContentPane().add(labelEtudiant);
		
		Label labelUtilisateur = new Label("Identifiant Utilisateur");
		labelUtilisateur.setForeground(new Color(255, 255, 255));
		labelUtilisateur.setBackground(new Color(25, 25, 112));
		labelUtilisateur.setFont(new Font("Dialog", Font.BOLD, 16));
		labelUtilisateur.setBounds(64, 308, 163, 34);
		frame.getContentPane().add(labelUtilisateur);
		
		nomTextField = new JTextField();
		nomTextField.setBounds(263, 103, 189, 27);
		frame.getContentPane().add(nomTextField);
		nomTextField.setColumns(10);
		
		prenomTextField = new JTextField();
		prenomTextField.setColumns(10);
		prenomTextField.setBounds(263, 146, 189, 27);
		frame.getContentPane().add(prenomTextField);
		
		mailTextField = new JTextField();
		mailTextField.setColumns(10);
		mailTextField.setBounds(263, 195, 189, 27);
		frame.getContentPane().add(mailTextField);
		
		Label idEtudiantLabel = new Label(String.valueOf(new EtudiantDAO().selectCurrentSequenceValue()));
		idEtudiantLabel.setForeground(new Color(255, 255, 255));
		idEtudiantLabel.setBackground(new Color(25, 25, 112));
		idEtudiantLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		idEtudiantLabel.setBounds(263, 363, 189, 34);
		frame.getContentPane().add(idEtudiantLabel);
		
		JComboBox groupeBox = new JComboBox();
		groupeBox.setBounds(263, 273, 189, 29);
		frame.getContentPane().add(groupeBox);
		
		JComboBox filiereBox = new JComboBox();
		filiereBox.addItem("Classique");
		filiereBox.addItem("Apprentissage");
		selectionnerGroupesDeLaFiliere(filiereBox, groupeBox);
		filiereBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectionnerGroupesDeLaFiliere(filiereBox, groupeBox);
			}
		});
		
		filiereBox.setBounds(263, 233, 189, 29);
		frame.getContentPane().add(filiereBox);
		
		JComboBox utilisateurBox = new JComboBox();
		ArrayList<Utilisateur> listeUtilisateur = new UtilisateurDAO().getList("Etudiant");
		for(int i=0; i<listeUtilisateur.size(); i++) {
			utilisateurBox.addItem(String.valueOf(listeUtilisateur.get(i).getIdUtilisateur()));
		}
		
		utilisateurBox.setBounds(263, 313, 189, 29);
		frame.getContentPane().add(utilisateurBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(25, 25, 112));
		panel_1.setBounds(0, 72, 680, 441);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 456, 657, -39);
		panel_1.add(panel_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.textHighlight);
		panel_4.setBounds(0, 430, 680, -26);
		panel_1.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(95, 158, 160));
		panel_5.setBounds(0, 395, 680, 46);
		panel_1.add(panel_5);
		
		Button annulerBtn = new Button("Annuler");
		annulerBtn.setBounds(505, 342, 137, 36);
		panel_1.add(annulerBtn);
		annulerBtn.setBackground(new Color(95, 158, 160));
		annulerBtn.setForeground(new Color(255, 255, 255));
		
		Button confirmerBtn = new Button("Confirmer");
		confirmerBtn.setBounds(345, 342, 137, 36);
		panel_1.add(confirmerBtn);
		confirmerBtn.setBackground(new Color(95, 158, 160));
		confirmerBtn.setForeground(new Color(255, 255, 255));
		
		confirmerBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idUtilisateur = Integer.parseInt((String) utilisateurBox.getSelectedItem());
				String nom = nomTextField.getText();
				String prenom = prenomTextField.getText();
				int numerogroupe = Integer.parseInt((String) groupeBox.getSelectedItem());
				String mail = mailTextField.getText();
				String filiere = (String)filiereBox.getSelectedItem();
				int retour = new EtudiantDAO().add(new Etudiant(0, nom, prenom, idUtilisateur, mail, numerogroupe, filiere));
				if(retour!= 0) {
					JOptionPane.showMessageDialog(null, "Etudiant enregistré dans la BDD");
					idEtudiantLabel.setText(String.valueOf(new EtudiantDAO().selectCurrentSequenceValue()));
				}else {
					JOptionPane.showMessageDialog(null, "Erreur lors de l'insertion de l'étudiant dans la BDD");
				}
				prenomTextField.setText(null);
				nomTextField.setText(null);
				mailTextField.setText(null);
			}
		});
		annulerBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				//frame = new GestionEtudiant().getFrame();
				//frame.setVisible(true);
			}
		});
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				frame.dispose();
			}
		});
		frame.setResizable(false);
	}
	
	public void selectionnerGroupesDeLaFiliere(JComboBox filiereBox, JComboBox groupeBox) {
		String filiere = (String)filiereBox.getSelectedItem();
		ArrayList<Groupe> listeGroupe = new GroupeDAO().getList(filiere);
		groupeBox.removeAllItems();
		for(int i=0; i<listeGroupe.size(); i++) {
			groupeBox.addItem(String.valueOf(listeGroupe.get(i).getNumeroGroupe()));
		}
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
