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

public class ModifierGroupe {

	private JFrame frame;
	private JTextField textNumeroGroupe;
	private JTextField textCapiciteMax;
	private int idGroupe = 1;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifierGroupe window = new ModifierGroupe();
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
	public ModifierGroupe() {
		initialize();
	}
	
	public ModifierGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 584, 526);
		GroupeDAO groupeDAO = new GroupeDAO();
		Groupe groupe = groupeDAO.get(idGroupe);
		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textNumeroGroupe = new JTextField();
		textNumeroGroupe.setBounds(251, 176, 116, 22);
		textNumeroGroupe.setText(String.valueOf(groupe.getNumeroGroupe()));
		panel.add(textNumeroGroupe);
		textNumeroGroupe.setColumns(10);
		
		textCapiciteMax = new JTextField();
		textCapiciteMax.setBounds(251, 214, 116, 22);
		textCapiciteMax.setText(String.valueOf(groupe.getCapaciteMax()));
		panel.add(textCapiciteMax);
		textCapiciteMax.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(250, 280, 117, 19);
		panel.add(comboBox);
		comboBox.addItem("Classique");
		comboBox.addItem("Apprentissage");
		
		JButton btnValid = new JButton("Valid\u00E9");
		btnValid.setForeground(new Color(255, 255, 255));
		btnValid.setBackground(new Color(95, 158, 160));
		btnValid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int retour = 0;
				retour = groupeDAO.update(new Groupe(Integer.parseInt(textNumeroGroupe.getText()),Integer.parseInt(textCapiciteMax.getText()),comboBox.getSelectedItem().toString()));
				if(retour!=0) {
					JOptionPane.showMessageDialog(null, "Groupe modifié dans la BDD avec succès");
					frame.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Erreur lors de  la modification du groupe dans la BDD");
				}
			}
		});
		
		btnValid.setBounds(295, 363, 111, 34);
		panel.add(btnValid);
		
		JButton btnNewButton_1 = new JButton("Annuler");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(95, 158, 160));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(418, 363, 110, 34);
		panel.add(btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(95, 158, 160));
		panel_3.setBounds(0, 0, 892, 82);
		panel.add(panel_3);
		
		Label label = new Label("MODIFIER GROUPE ");
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
		lblEsigelec.setBounds(380, 20, 181, 47);
		panel_3.add(lblEsigelec);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(95, 158, 160));
		panel_1.setBounds(0, 426, 637, 66);
		panel.add(panel_1);
		
		JLabel lblNumeroGroupe_1 = new JLabel("numero Groupe ");
		lblNumeroGroupe_1.setForeground(Color.WHITE);
		lblNumeroGroupe_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNumeroGroupe_1.setBounds(82, 177, 126, 16);
		panel.add(lblNumeroGroupe_1);
		
		JLabel lblCapacitMax = new JLabel("capacit\u00E9 Maximun");
		lblCapacitMax.setForeground(Color.WHITE);
		lblCapacitMax.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCapacitMax.setBounds(82, 206, 154, 34);
		panel.add(lblCapacitMax);
		
		JLabel lblFiliere = new JLabel("La Filiere");
		lblFiliere.setForeground(Color.WHITE);
		lblFiliere.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFiliere.setBounds(82, 280, 104, 16);
		panel.add(lblFiliere);
		frame.setResizable(false);
		
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
