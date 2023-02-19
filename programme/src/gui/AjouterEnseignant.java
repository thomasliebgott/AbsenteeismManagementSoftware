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
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class AjouterEnseignant {

	private JFrame frame;
	private JTextField nomTextField;
	private JTextField prenomTextField;
	private JTextField numTextField;
	private EnseignantDAO enseignantDAO = new EnseignantDAO();

	/**
	 * lauch the application 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterEnseignant window = new AjouterEnseignant();
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
	public AjouterEnseignant() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 686, 498);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(SystemColor.textHighlight);
		panel.setBackground(UIManager.getColor("Menu.selectionBackground"));
		panel.setBounds(0, 0, 680, 82);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		Label label = new Label("AJOUTER UN ENSEIGNANT");
		label.setBackground(new Color(95, 158, 160));
		label.setForeground(SystemColor.text);
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Dialog", Font.BOLD, 20));
		label.setBounds(10, 10, 338, 60);
		panel.add(label);
		
		JLabel lblEsigelec = new JLabel(" ESIGELEC ");
		lblEsigelec.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblEsigelec.setForeground(Color.RED);
		lblEsigelec.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 38));
		lblEsigelec.setBounds(484, 10, 184, 47);
		panel.add(lblEsigelec);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(95, 158, 160));
		panel_3.setBounds(0, 0, 680, 82);
		panel.add(panel_3);
		
		Label nomLabel = new Label("Nom");
		nomLabel.setForeground(Color.WHITE);
		nomLabel.setBackground(new Color(25, 25, 112));
		nomLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		nomLabel.setBounds(64, 103, 147, 27);
		frame.getContentPane().add(nomLabel);
		
		Label prenomLabel = new Label("Pr\u00E9nom");
		prenomLabel.setForeground(Color.WHITE);
		prenomLabel.setBackground(new Color(25, 25, 112));
		prenomLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		prenomLabel.setBounds(64, 146, 147, 27);
		frame.getContentPane().add(prenomLabel);
		
		Label numLabel = new Label("T\u00E9l\u00E9phone");
		numLabel.setForeground(Color.WHITE);
		numLabel.setBackground(new Color(25, 25, 112));
		numLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		numLabel.setBounds(64, 195, 147, 27);
		frame.getContentPane().add(numLabel);
		
		Label labelEnseignant = new Label("Identifiant Enseignant");
		labelEnseignant.setForeground(Color.WHITE);
		labelEnseignant.setBackground(new Color(25, 25, 112));
		labelEnseignant.setFont(new Font("Dialog", Font.BOLD, 16));
		labelEnseignant.setBounds(64, 298, 173, 34);
		frame.getContentPane().add(labelEnseignant);
		
		Label labelUtilisateur = new Label("Identifiant Utilisateur");
		labelUtilisateur.setForeground(Color.WHITE);
		labelUtilisateur.setBackground(new Color(25, 25, 112));
		labelUtilisateur.setFont(new Font("Dialog", Font.BOLD, 16));
		labelUtilisateur.setBounds(64, 242, 173, 34);
		frame.getContentPane().add(labelUtilisateur);
		
		nomTextField = new JTextField();
		nomTextField.setBounds(263, 103, 189, 27);
		frame.getContentPane().add(nomTextField);
		nomTextField.setColumns(10);
		
		prenomTextField = new JTextField();
		prenomTextField.setColumns(10);
		prenomTextField.setBounds(263, 146, 189, 27);
		frame.getContentPane().add(prenomTextField);
		
		numTextField = new JTextField();
		numTextField.setColumns(10);
		numTextField.setBounds(263, 195, 189, 27);
		frame.getContentPane().add(numTextField);
		
		JComboBox utilisateurBox = new JComboBox();
		ArrayList<Utilisateur> listeUtilisateur = new UtilisateurDAO().getList("Enseignant");
		for(int i=0; i<listeUtilisateur.size(); i++) {
			utilisateurBox.addItem(String.valueOf(listeUtilisateur.get(i).getIdUtilisateur()));
		}
		
		utilisateurBox.setBounds(263, 242, 189, 29);
		frame.getContentPane().add(utilisateurBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(25, 25, 112));
		panel_1.setBounds(0, 69, 680, 394);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		Button confirmerBtn = new Button("Confirmer");
		confirmerBtn.setForeground(Color.WHITE);
		confirmerBtn.setBackground(new Color(95, 158, 160));
		confirmerBtn.setFont(new Font("Dialog", Font.PLAIN, 12));
		confirmerBtn.setBounds(328, 297, 137, 36);
		panel_1.add(confirmerBtn);
		
		Button annulerBtn = new Button("Annuler");
		annulerBtn.setForeground(Color.WHITE);
		annulerBtn.setBackground(new Color(95, 158, 160));
		annulerBtn.setBounds(494, 297, 137, 36);
		panel_1.add(annulerBtn);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(95, 158, 160));
		panel_2.setBounds(0, 352, 680, 58);
		panel_1.add(panel_2);
		
		Label idEnseignantLabel = new Label(String.valueOf(enseignantDAO.selectCurrentSequenceValue()));
		idEnseignantLabel.setForeground(new Color(255, 255, 255));
		idEnseignantLabel.setBounds(283, 231, 93, 27);
		panel_1.add(idEnseignantLabel);
		idEnseignantLabel.setBackground(new Color(25, 25, 112));
		idEnseignantLabel.setFont(new Font("Edwardian Script ITC", Font.BOLD, 20));
		annulerBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				//frame = new GestionEtudiant().getFrame();
				//frame.setVisible(true);
			}
		});
		confirmerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		confirmerBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idUtilisateur = Integer.parseInt((String) utilisateurBox.getSelectedItem());
				String nom = nomTextField.getText();
				String prenom = prenomTextField.getText();
				int numTel = Integer.parseInt(numTextField.getText());
				
				int retour = enseignantDAO.add(new Enseignant(0, nom, prenom, idUtilisateur, numTel));
				if(retour!= 0) {
					JOptionPane.showMessageDialog(null, "Enseignant enregistré dans la BDD");
					idEnseignantLabel.setText(String.valueOf(enseignantDAO.selectCurrentSequenceValue()));
				}else {
					JOptionPane.showMessageDialog(null, "Erreur lors de l'insertion de l'enseignant dans la BDD");
				}
				prenomTextField.setText(null);
				nomTextField.setText(null);
				numTextField.setText(null);
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
	
	public JFrame getFrame() {
		return frame;
	}
}
