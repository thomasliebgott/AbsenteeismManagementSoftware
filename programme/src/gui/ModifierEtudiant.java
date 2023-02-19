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

public class ModifierEtudiant {

	private JFrame frame;
	private JTextField nomTextField;
	private JTextField prenomTextField;
	private JTextField mailTextField;
	private int idEtudiant = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifierEtudiant window = new ModifierEtudiant();
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
	public ModifierEtudiant(int idEtudiant) {
		this.idEtudiant = idEtudiant;
		initialize();
	}
	
	public ModifierEtudiant() {
		initialize();
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(25, 25, 112));
		frame.setBounds(100, 100, 728, 572);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		EtudiantDAO etudiantDAO = new EtudiantDAO();
		Etudiant etudiant = etudiantDAO.get(idEtudiant);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(0, 0, 722, 82);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(95, 158, 160));
		panel_3.setBounds(0, 0, 722, 82);
		panel.add(panel_3);
		
		Label label_1 = new Label("MODIFIER UN ETUDIANT");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Dialog", Font.BOLD, 20));
		label_1.setBackground(new Color(95, 158, 160));
		label_1.setAlignment(Label.CENTER);
		label_1.setBounds(22, 20, 247, 33);
		panel_3.add(label_1);
		
		JLabel lblEsigelec = new JLabel(" ESIGELEC ");
		lblEsigelec.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblEsigelec.setForeground(Color.RED);
		lblEsigelec.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 38));
		lblEsigelec.setBounds(516, 20, 182, 47);
		panel_3.add(lblEsigelec);
		
		Label nomLabel = new Label("Nom");
		nomLabel.setForeground(Color.WHITE);
		nomLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		nomLabel.setBounds(64, 103, 147, 27);
		frame.getContentPane().add(nomLabel);
		
		Label prenomLabel = new Label("Pr\u00E9nom");
		prenomLabel.setForeground(new Color(255, 255, 255));
		prenomLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		prenomLabel.setBounds(64, 146, 147, 27);
		frame.getContentPane().add(prenomLabel);
		
		Label mailLabel = new Label("Adresse mail");
		mailLabel.setForeground(new Color(255, 255, 255));
		mailLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		mailLabel.setBounds(64, 195, 147, 27);
		frame.getContentPane().add(mailLabel);
		
		Label filiereLabel = new Label("Filiere");
		filiereLabel.setForeground(new Color(255, 255, 255));
		filiereLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		filiereLabel.setBounds(64, 228, 147, 34);
		frame.getContentPane().add(filiereLabel);
		
		Label groupeLabel = new Label("Groupe");
		groupeLabel.setForeground(new Color(255, 255, 255));
		groupeLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		groupeLabel.setBounds(64, 268, 147, 34);
		frame.getContentPane().add(groupeLabel);
		
		Label labelEtudiant = new Label("Identifiant Etudiant");
		labelEtudiant.setForeground(new Color(255, 255, 255));
		labelEtudiant.setFont(new Font("Dialog", Font.BOLD, 16));
		labelEtudiant.setBounds(64, 363, 170, 34);
		frame.getContentPane().add(labelEtudiant);
		
		Label labelUtilisateur = new Label("Identifiant Utilisateur");
		labelUtilisateur.setForeground(new Color(255, 255, 255));
		labelUtilisateur.setFont(new Font("Dialog", Font.BOLD, 16));
		labelUtilisateur.setBounds(64, 308, 170, 34);
		frame.getContentPane().add(labelUtilisateur);
		
		nomTextField = new JTextField();
		nomTextField.setText(etudiant.getNom());
		nomTextField.setBounds(263, 103, 235, 27);
		frame.getContentPane().add(nomTextField);
		nomTextField.setColumns(10);
		frame.setResizable(false);
		
		prenomTextField = new JTextField();
		prenomTextField.setText(etudiant.getPrenom());
		prenomTextField.setColumns(10);
		prenomTextField.setBounds(263, 146, 235, 27);
		frame.getContentPane().add(prenomTextField);
		
		mailTextField = new JTextField();
		mailTextField.setText(etudiant.getAdresseMail());
		mailTextField.setColumns(10);
		mailTextField.setBounds(263, 195, 235, 27);
		frame.getContentPane().add(mailTextField);
		
		Label idEtudiantLabel = new Label(String.valueOf(idEtudiant));
		idEtudiantLabel.setForeground(new Color(255, 255, 255));
		idEtudiantLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		idEtudiantLabel.setBounds(263, 363, 189, 34);
		frame.getContentPane().add(idEtudiantLabel);
		
		JComboBox groupeBox = new JComboBox();
		groupeBox.setBounds(263, 273, 235, 29);
		frame.getContentPane().add(groupeBox);
		
		JComboBox filiereBox = new JComboBox();
		filiereBox.addItem("Classique");
		filiereBox.addItem("Apprentissage");
		filiereBox.setSelectedItem(etudiant.getFiliere());
		selectionnerGroupesDeLaFiliere(filiereBox, groupeBox);
		filiereBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectionnerGroupesDeLaFiliere(filiereBox, groupeBox);
			}
		});
		filiereBox.setBounds(263, 233, 235, 29);
		frame.getContentPane().add(filiereBox);
		
		Button confirmerBtn = new Button("Confirmer");
		confirmerBtn.setBackground(new Color(95, 158, 160));
		confirmerBtn.setForeground(Color.WHITE);
		
		confirmerBtn.setBounds(361, 413, 137, 36);
		frame.getContentPane().add(confirmerBtn);
		
		Button annulerBtn = new Button("Annuler");
		annulerBtn.setBackground(new Color(95, 158, 160));
		annulerBtn.setForeground(Color.WHITE);
		annulerBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				//frame = new GestionEtudiant().getFrame();
				//frame.setVisible(true);
			}
		});
		annulerBtn.setBounds(529, 413, 137, 36);
		frame.getContentPane().add(annulerBtn);
		
		JComboBox utilisateurBox = new JComboBox();
		ArrayList<Utilisateur> listeUtilisateur = new UtilisateurDAO().getList("Etudiant");
		for(int i=0; i<listeUtilisateur.size(); i++) {
			utilisateurBox.addItem(String.valueOf(listeUtilisateur.get(i).getIdUtilisateur()));
		}
		utilisateurBox.setSelectedItem(String.valueOf(etudiant.getIdEtudiant()));
		confirmerBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idEtudiant = Integer.parseInt(idEtudiantLabel.getText());
				int idUtilisateur = Integer.parseInt((String) utilisateurBox.getSelectedItem());
				String nom = nomTextField.getText();
				String prenom = prenomTextField.getText();
				int numerogroupe = Integer.parseInt((String) groupeBox.getSelectedItem());
				String mail = mailTextField.getText();
				String filiere = (String)filiereBox.getSelectedItem();
				int retour = etudiantDAO.update(new Etudiant(idEtudiant, nom, prenom, idUtilisateur, mail, numerogroupe, filiere));
				if(retour!=0) {
					JOptionPane.showMessageDialog(null, "Etudiant modifié dans la BDD");
					frame.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Erreur lors de la modification de l'étudiant dans la BDD");
				}
				
			}
		});
		utilisateurBox.setBounds(263, 313, 235, 29);
		frame.getContentPane().add(utilisateurBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 527, 697, -36);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(95, 158, 160));
		panel_2.setBounds(0, 481, 722, 56);
		frame.getContentPane().add(panel_2);
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
