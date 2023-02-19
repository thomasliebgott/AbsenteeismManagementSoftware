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
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class AjouterUtilisateur {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterUtilisateur window = new AjouterUtilisateur();
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
	public AjouterUtilisateur() {
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		panel.setBounds(0, 100, 575, 227);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(237, 139, 191, 22);
		panel.add(comboBox);
		comboBox.addItem("Enseignant");
		comboBox.addItem("Etudiant");
		comboBox.addItem("Gestionnaire");
		
		textField = new JTextField();
		textField.setBounds(237, 106, 191, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Identifiant");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(59, 49, 115, 24);
		panel.add(lblNewLabel_1);
		
		
		JLabel id = new JLabel(String.valueOf(new UtilisateurDAO().selectCurrentSequenceValue()));
		id.setForeground(new Color(255, 255, 255));
		id.setFont(new Font("Tahoma", Font.PLAIN, 16));
		id.setBounds(237, 50, 191, 24);
		panel.add(id);
		
		JButton btnNewButton = new JButton("Cr\u00E9er");
		btnNewButton.setBackground(new Color(95, 158, 160));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String mdp = textField.getText();
				String profil = (String)comboBox.getSelectedItem();
				int rVal = new UtilisateurDAO().add(new Utilisateur(0, mdp, profil));
				if(rVal != 0) {
					JOptionPane.showMessageDialog(null, "Utilisateur : " + profil + " créé avec succès");
					id.setText(String.valueOf(new UtilisateurDAO().selectCurrentSequenceValue()));
					textField.setText("");
				}else {
					JOptionPane.showMessageDialog(null, "Erreur lors de la création de l'utilisateur");
				}
				//System.out.println(profil + " " + mdp);
			}
		});
		btnNewButton.setBounds(339, 190, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mot de Passe");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(59, 102, 156, 24);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Profil");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(59, 137, 115, 24);
		panel.add(lblNewLabel_1_1_1);
		
		JButton btnTerminer = new JButton("Retour");
		btnTerminer.setBackground(new Color(95, 158, 160));
		btnTerminer.setForeground(Color.WHITE);
		btnTerminer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		btnTerminer.setBounds(443, 190, 89, 23);
		panel.add(btnTerminer);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(UIManager.getColor("Menu.selectionBackground"));
		panel_2.setBounds(0, 0, 583, -99);
		panel.add(panel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(95, 158, 160));
		panel_1.setBounds(0, 0, 575, 116);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CREATION D'UTILISATEUR");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(32, 30, 237, 25);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblEsigelec = new JLabel(" ESIGELEC ");
		lblEsigelec.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblEsigelec.setBounds(381, 14, 182, 47);
		panel_1.add(lblEsigelec);
		lblEsigelec.setForeground(Color.RED);
		lblEsigelec.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 38));
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
