package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class AnticiperAbsence {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnticiperAbsence window = new AnticiperAbsence();
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
	public AnticiperAbsence() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 655, 488);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Retour");
		btnNewButton_1.setBackground(new Color(95, 158, 160));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuEtudiant menuEtudiant = new MenuEtudiant();
				MenuEtudiant.main(null);
			}
		});
		btnNewButton_1.setBounds(392, 343, 97, 25);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(95, 158, 160));
		panel_1.setBounds(0, 0, 1006, 116);
		panel.add(panel_1);
		
		JLabel lblAnticiperUneAbsence = new JLabel("ANTICIPER UNE ABSENCE");
		lblAnticiperUneAbsence.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnticiperUneAbsence.setForeground(Color.WHITE);
		lblAnticiperUneAbsence.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAnticiperUneAbsence.setBackground(Color.WHITE);
		lblAnticiperUneAbsence.setBounds(34, 46, 237, 25);
		panel_1.add(lblAnticiperUneAbsence);
		
		JLabel lblEsigelec = new JLabel(" ESIGELEC ");
		lblEsigelec.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblEsigelec.setForeground(Color.RED);
		lblEsigelec.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 38));
		lblEsigelec.setBounds(448, 30, 171, 47);
		panel_1.add(lblEsigelec);
		
		JButton btnNewButton_2 = new JButton("Valider");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(new Color(95, 158, 160));
		btnNewButton_2.setBounds(514, 343, 97, 25);
		panel.add(btnNewButton_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(95, 158, 160));
		panel_2.setBounds(0, 393, 764, 57);
		panel.add(panel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(269, 226, 165, 25);
		panel.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Date Absence :");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(39, 156, 123, 21);
		panel.add(lblNewLabel);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(210, 155, 51, 22);
		panel.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(285, 155, 51, 22);
		panel.add(comboBox_2);
		
		JLabel lblJour = new JLabel("jour");
		lblJour.setForeground(new Color(255, 255, 255));
		lblJour.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblJour.setBounds(210, 126, 56, 16);
		panel.add(lblJour);
		
		JLabel lblMois = new JLabel("mois");
		lblMois.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMois.setForeground(new Color(255, 255, 255));
		lblMois.setBounds(285, 129, 56, 16);
		panel.add(lblMois);
		
		JLabel lblAnne = new JLabel("ann\u00E9e");
		lblAnne.setForeground(new Color(255, 255, 255));
		lblAnne.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAnne.setBounds(368, 129, 56, 16);
		panel.add(lblAnne);
		
		JLabel lblSlectionnerSceance = new JLabel("S\u00E9lectionner Sceance  : ");
		lblSlectionnerSceance.setForeground(Color.WHITE);
		lblSlectionnerSceance.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSlectionnerSceance.setBounds(39, 218, 201, 37);
		panel.add(lblSlectionnerSceance);
		
		JLabel lblAjouterJustificatif = new JLabel("Ajouter Justificatif :");
		lblAjouterJustificatif.setForeground(Color.WHITE);
		lblAjouterJustificatif.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAjouterJustificatif.setBounds(39, 294, 167, 16);
		panel.add(lblAjouterJustificatif);
		
		JButton btnNewButton = new JButton("download");
		btnNewButton.setBackground(new Color(95, 158, 160));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(269, 292, 97, 25);
		panel.add(btnNewButton);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(368, 155, 63, 19);
		panel.add(comboBox_3);
		frame.setResizable(false);
	}
	public JFrame getFrame() {
		return frame;
	}
}
