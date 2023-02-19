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

import model.Enseignant;
import model.Utilisateur;
import dao.EnseignantDAO;
import dao.UtilisateurDAO;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ModifierEnseignant {

	private JFrame frame;
	private JTextField nomTextField;
	private JTextField prenomTextField;
	private JTextField numTextField;
	private EnseignantDAO enseignantDAO = new EnseignantDAO();
	private int idEnseignant = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifierEnseignant window = new ModifierEnseignant();
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
	public ModifierEnseignant() {
		initialize();
	}
	
	public ModifierEnseignant(int idEnseignant) {
		this.idEnseignant = idEnseignant;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(25, 25, 112));
		frame.setBounds(100, 100, 686, 498);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Enseignant enseignant = enseignantDAO.get(idEnseignant);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(0, 0, 680, 82);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(95, 158, 160));
		panel_3.setBounds(0, 0, 705, 82);
		panel.add(panel_3);
		
		Label label_1 = new Label("MODIFIER GROUPE ");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Dialog", Font.BOLD, 20));
		label_1.setBackground(new Color(95, 158, 160));
		label_1.setAlignment(Label.CENTER);
		label_1.setBounds(10, 20, 247, 33);
		panel_3.add(label_1);
		
		JLabel lblEsigelec = new JLabel(" ESIGELEC ");
		lblEsigelec.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblEsigelec.setForeground(Color.RED);
		lblEsigelec.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 38));
		lblEsigelec.setBounds(482, 22, 180, 47);
		panel_3.add(lblEsigelec);
		
		Label nomLabel = new Label("Nom");
		nomLabel.setForeground(new Color(255, 255, 255));
		nomLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		nomLabel.setBounds(64, 103, 147, 27);
		frame.getContentPane().add(nomLabel);
		
		Label prenomLabel = new Label("Pr\u00E9nom");
		prenomLabel.setForeground(new Color(255, 255, 255));
		prenomLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		prenomLabel.setBounds(64, 146, 147, 27);
		frame.getContentPane().add(prenomLabel);
		
		Label numLabel = new Label("T\u00E9l\u00E9phone");
		numLabel.setForeground(new Color(255, 255, 255));
		numLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		numLabel.setBounds(64, 195, 147, 27);
		frame.getContentPane().add(numLabel);
		
		Label labelEnseignant = new Label("Identifiant Enseignant");
		labelEnseignant.setForeground(new Color(255, 255, 255));
		labelEnseignant.setFont(new Font("Dialog", Font.BOLD, 16));
		labelEnseignant.setBounds(64, 298, 173, 34);
		frame.getContentPane().add(labelEnseignant);
		
		Label labelUtilisateur = new Label("Identifiant Utilisateur");
		labelUtilisateur.setForeground(new Color(255, 255, 255));
		labelUtilisateur.setFont(new Font("Dialog", Font.BOLD, 16));
		labelUtilisateur.setBounds(64, 242, 161, 34);
		frame.getContentPane().add(labelUtilisateur);
		
		nomTextField = new JTextField();
		nomTextField.setBounds(263, 103, 189, 27);
		nomTextField.setText(enseignant.getNom());
		frame.getContentPane().add(nomTextField);
		nomTextField.setColumns(10);
		
		prenomTextField = new JTextField();
		prenomTextField.setColumns(10);
		prenomTextField.setText(enseignant.getPrenom());
		prenomTextField.setBounds(263, 146, 189, 27);
		frame.getContentPane().add(prenomTextField);
		frame.setResizable(false);
		
		numTextField = new JTextField();
		numTextField.setColumns(10);
		numTextField.setBounds(263, 195, 189, 27);
		numTextField.setText(String.valueOf(enseignant.getNumTel()));
		frame.getContentPane().add(numTextField);
		
		Label idEnseignantLabel = new Label(String.valueOf(enseignant.getIdEnseignant()));
		idEnseignantLabel.setForeground(new Color(255, 255, 255));
		idEnseignantLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		idEnseignantLabel.setBounds(263, 298, 189, 34);
		frame.getContentPane().add(idEnseignantLabel);
		
		Button confirmerBtn = new Button("Confirmer");
		confirmerBtn.setForeground(Color.WHITE);
		confirmerBtn.setBackground(new Color(95, 158, 160));
		
		confirmerBtn.setBounds(315, 354, 137, 36);
		frame.getContentPane().add(confirmerBtn);
		
		Button annulerBtn = new Button("Annuler");
		annulerBtn.setForeground(Color.WHITE);
		annulerBtn.setBackground(new Color(95, 158, 160));
		annulerBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				//frame = new GestionEtudiant().getFrame();
				//frame.setVisible(true);
			}
		});
		annulerBtn.setBounds(503, 354, 137, 36);
		frame.getContentPane().add(annulerBtn);
		
		JComboBox utilisateurBox = new JComboBox();
		ArrayList<Utilisateur> listeUtilisateur = new UtilisateurDAO().getList("Enseignant");
		for(int i=0; i<listeUtilisateur.size(); i++) {
			utilisateurBox.addItem(String.valueOf(listeUtilisateur.get(i).getIdUtilisateur()));
		}
		utilisateurBox.setSelectedItem(String.valueOf(enseignant.getIdUtilisateur()));
		
		confirmerBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idUtilisateur = Integer.parseInt((String) utilisateurBox.getSelectedItem());
				String nom = nomTextField.getText();
				String prenom = prenomTextField.getText();
				int numTel = Integer.parseInt(numTextField.getText());
				
				int retour = enseignantDAO.update(new Enseignant(idEnseignant, nom, prenom, idUtilisateur, numTel));
				if(retour!= 0) {
					JOptionPane.showMessageDialog(null, "Enseignant modifié dans la BDD");
					//idEnseignantLabel.setText(String.valueOf(enseignantDAO.selectCurrentSequenceValue()));
					frame.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour de l'enseignant dans la BDD");
				}
			}
		});
		utilisateurBox.setBounds(263, 242, 189, 29);
		frame.getContentPane().add(utilisateurBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 453, 680, -32);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(95, 158, 160));
		panel_2.setBounds(0, 427, 680, 36);
		frame.getContentPane().add(panel_2);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				frame.dispose();
			}
		});
		frame.setResizable(false);
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
