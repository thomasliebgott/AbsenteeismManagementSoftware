package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import dao.EtudiantDAO;
import dao.GroupeDAO;
import model.Etudiant;
import model.Groupe;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.awt.Label;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class AjouterGroupe {

	private JFrame frame;
	private JTextField textNumeroGroupe;
	private JTextField textCapiciteMax;
	
	/**
	 * Launch the application.
	 * @param args 
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterGroupe window = new AjouterGroupe();
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
	public AjouterGroupe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 591, 509);
		frame.setResizable(false);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblGestionGroupe = new JLabel("");
		lblGestionGroupe.setBounds(36, 86, 146, 16);
		panel.add(lblGestionGroupe);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(95, 158, 160));
		panel_3.setBounds(0, 0, 892, 82);
		panel.add(panel_3);
		
		Label label = new Label("AJOUTER GROUPE ");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Dialog", Font.BOLD, 20));
		label.setBackground(new Color(95, 158, 160));
		label.setAlignment(Label.CENTER);
		label.setBounds(22, 20, 247, 33);
		panel_3.add(label);
		
		JLabel lblEsigelec = new JLabel(" ESIGELEC ");
		lblEsigelec.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblEsigelec.setForeground(Color.RED);
		lblEsigelec.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 38));
		lblEsigelec.setBounds(389, 20, 179, 47);
		panel_3.add(lblEsigelec);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(95, 158, 160));
		panel_5.setBounds(0, 433, 680, 46);
		panel.add(panel_5);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(25, 25, 112));
		panel_1.setBounds(0, 75, 676, 360);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblFiliere = new JLabel("La Filiere");
		lblFiliere.setForeground(new Color(255, 255, 255));
		lblFiliere.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFiliere.setBounds(73, 220, 104, 16);
		panel_1.add(lblFiliere);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(215, 221, 117, 19);
		panel_1.add(comboBox);
		comboBox.addItem("Classique");
		comboBox.addItem("Apprentissage");
		
		JLabel lblCapacitMax = new JLabel("capacit\u00E9 Maximun");
		lblCapacitMax.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCapacitMax.setForeground(new Color(255, 255, 255));
		lblCapacitMax.setBounds(49, 132, 154, 34);
		panel_1.add(lblCapacitMax);
		
		textCapiciteMax = new JTextField();
		textCapiciteMax.setBounds(216, 140, 116, 22);
		panel_1.add(textCapiciteMax);
		textCapiciteMax.setColumns(10);
		
		JLabel lblNumeroGroupe = new JLabel("numero Groupe ");
		lblNumeroGroupe.setForeground(new Color(255, 255, 255));
		lblNumeroGroupe.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNumeroGroupe.setBounds(49, 89, 126, 16);
		panel_1.add(lblNumeroGroupe);
		
		textNumeroGroupe = new JTextField();
		textNumeroGroupe.setBounds(216, 88, 116, 22);
		panel_1.add(textNumeroGroupe);
		textNumeroGroupe.setColumns(10);
		
		JButton btnValid = new JButton("Valid\u00E9");
		btnValid.setBackground(new Color(95, 158, 160));
		btnValid.setForeground(new Color(255, 255, 255));
		btnValid.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnValid.setBounds(261, 291, 116, 34);
		panel_1.add(btnValid);
		
		JButton btnNewButton_1 = new JButton("Retour");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(95, 158, 160));
		btnNewButton_1.setBounds(406, 291, 116, 34);
		panel_1.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnValid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int retour = 0;
				retour = new GroupeDAO().add(new Groupe(Integer.parseInt(textNumeroGroupe.getText()),Integer.parseInt(textCapiciteMax.getText()),comboBox.getSelectedItem().toString()));
				if(retour!=0) {
					JOptionPane.showMessageDialog(null, "Groupe enregistré dans la BDD avec succès");
					textNumeroGroupe.setText(null);
					textCapiciteMax.setText(null);
				}else {
					JOptionPane.showMessageDialog(null, "Erreur lors de  l'enregistrement du groupe dans la BDD");
				}
			}
		});
		
		
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				frame.dispose();
			}
		});

	}
	
	public JFrame getFrame() {
		return frame;
	}
}
