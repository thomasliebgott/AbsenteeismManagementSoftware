package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import dao.UtilisateurDAO;
import model.Utilisateur;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class ModifierUtilisateur {

	private JFrame frame;
	private JTextField textField;
	private int idUtilisateur = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifierUtilisateur window = new ModifierUtilisateur();
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
	public ModifierUtilisateur() {
		initialize();
	}

	public ModifierUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(95, 158, 160));
		frame.setBounds(100, 100, 581, 408);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		UtilisateurDAO userDAO = new UtilisateurDAO();
		Utilisateur user = userDAO.get(idUtilisateur);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		panel.setBounds(0, 75, 575, 269);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(237, 155, 191, 22);
		panel.add(comboBox);
		comboBox.addItem("Enseignant");
		comboBox.addItem("Etudiant");
		comboBox.addItem("Gestionnaire");
		comboBox.setSelectedItem(user.getProfil());
		
		textField = new JTextField();
		textField.setBounds(237, 106, 191, 20);
		textField.setText(user.getMdp());
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Identifiant");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(59, 49, 115, 24);
		panel.add(lblNewLabel_1);
		
		
		JLabel id = new JLabel(String.valueOf(idUtilisateur));
		id.setForeground(new Color(255, 255, 255));
		id.setFont(new Font("Tahoma", Font.BOLD, 16));
		id.setBounds(237, 50, 191, 24);
		panel.add(id);
		
		JButton btnNewButton = new JButton("Modifier");
		btnNewButton.setBackground(new Color(95, 158, 160));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String mdp = textField.getText();
				String profil = (String)comboBox.getSelectedItem();
				int rVal = userDAO.update(new Utilisateur(idUtilisateur, mdp, profil));
				if(rVal != 0) {
					JOptionPane.showMessageDialog(null, "Utilisateur modifié avec succès dans la BDD");
					//id.setText(String.valueOf(new UtilisateurDAO().selectCurrentSequenceValue()));
					textField.setText("");
					frame.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Erreur lors de la modification de l'utilisateur");
				}
				//System.out.println(profil + " " + mdp);
			}
		});
		btnNewButton.setBounds(261, 209, 99, 35);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mot de Passe");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(59, 102, 156, 24);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Profil");
		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(59, 155, 115, 24);
		panel.add(lblNewLabel_1_1_1);
		
		JButton btnTerminer = new JButton("Terminer");
		btnTerminer.setForeground(new Color(255, 255, 255));
		btnTerminer.setBackground(new Color(95, 158, 160));
		btnTerminer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		btnTerminer.setBounds(397, 209, 99, 35);
		panel.add(btnTerminer);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(95, 158, 160));
		panel_1.setBounds(0, 0, 575, 116);
		frame.getContentPane().add(panel_1);
		
		JLabel lblModifierDutilisateur = new JLabel("MODIFIER D'UTILISATEUR");
		lblModifierDutilisateur.setHorizontalAlignment(SwingConstants.CENTER);
		lblModifierDutilisateur.setForeground(Color.WHITE);
		lblModifierDutilisateur.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblModifierDutilisateur.setBackground(Color.WHITE);
		lblModifierDutilisateur.setBounds(12, 30, 237, 25);
		panel_1.add(lblModifierDutilisateur);
		frame.setResizable(false);
		
		JLabel lblEsigelec = new JLabel("\r\n");
		lblEsigelec.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblEsigelec.setForeground(Color.RED);
		lblEsigelec.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 38));
		lblEsigelec.setBounds(392, 14, 171, 47);
		panel_1.add(lblEsigelec);
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
