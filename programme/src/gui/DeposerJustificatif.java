package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class DeposerJustificatif {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeposerJustificatif window = new DeposerJustificatif();
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
	public DeposerJustificatif() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 702, 496);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblDateAbsence = new JLabel("Date de debut de l'absence ");
		lblDateAbsence.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDateAbsence.setForeground(new Color(255, 255, 255));
		lblDateAbsence.setBounds(35, 203, 219, 16);
		panel.add(lblDateAbsence);
		
		JButton btnParcourir = new JButton("parcourir");
		btnParcourir.setForeground(new Color(255, 255, 255));
		btnParcourir.setBackground(new Color(95, 158, 160));
		btnParcourir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try { 
						Runtime.getRuntime().exec("explorer.exe");
						
					}
					catch(Exception e1){
						JOptionPane.showMessageDialog(null, "check file details");
					}
				
			}
		});
		
		btnParcourir.setBounds(249, 304, 97, 25);
		panel.add(btnParcourir);
		
		JLabel lblFichier = new JLabel("Fichier :");
		lblFichier.setForeground(new Color(255, 255, 255));
		lblFichier.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFichier.setBounds(164, 306, 85, 16);
		panel.add(lblFichier);
		
		JLabel lblJpgOuPdf = new JLabel("jpg ou pdf");
		lblJpgOuPdf.setForeground(new Color(255, 255, 255));
		lblJpgOuPdf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblJpgOuPdf.setBounds(358, 308, 85, 16);
		panel.add(lblJpgOuPdf);
		
		JButton btnValider = new JButton("Valider ");
		btnValider.setForeground(new Color(255, 255, 255));
		btnValider.setBackground(new Color(95, 158, 160));
		btnValider.setBounds(395, 376, 97, 25);
		panel.add(btnValider);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setForeground(new Color(255, 255, 255));
		btnRetour.setBackground(new Color(95, 158, 160));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frame = new MenuEtudiant().getFrame();
				frame.setVisible(true);
			}
		});
		btnRetour.setBounds(535, 376, 97, 25);
		panel.add(btnRetour);
		
		JLabel lblDateFinAbsence = new JLabel("Date de fin de l'absence");
		lblDateFinAbsence.setForeground(new Color(255, 255, 255));
		lblDateFinAbsence.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDateFinAbsence.setBounds(35, 248, 237, 16);
		panel.add(lblDateFinAbsence);
		
		JComboBox comboBox_jD = new JComboBox();
		comboBox_jD.setEditable(true);
		comboBox_jD.setBounds(271, 202, 49, 22);
		panel.add(comboBox_jD);
		
		comboBox_jD.addItem("1");
		comboBox_jD.addItem("2");
		comboBox_jD.addItem("3");
		comboBox_jD.addItem("4");
		comboBox_jD.addItem("5");
		comboBox_jD.addItem("6");
		comboBox_jD.addItem("7");
		comboBox_jD.addItem("8");
		comboBox_jD.addItem("9");
		comboBox_jD.addItem("10");
		comboBox_jD.addItem("11");
		comboBox_jD.addItem("12");
		comboBox_jD.addItem("13");
		comboBox_jD.addItem("14");
		comboBox_jD.addItem("15");
		comboBox_jD.addItem("16");
		comboBox_jD.addItem("17");
		comboBox_jD.addItem("18");
		comboBox_jD.addItem("19");
		comboBox_jD.addItem("20");
		comboBox_jD.addItem("21");
		comboBox_jD.addItem("22");
		comboBox_jD.addItem("23");
		comboBox_jD.addItem("24");
		comboBox_jD.addItem("25");
		comboBox_jD.addItem("26");
		comboBox_jD.addItem("27");
		comboBox_jD.addItem("28");
		comboBox_jD.addItem("29");
		comboBox_jD.addItem("30");
		comboBox_jD.addItem("31");
		comboBox_jD.setSelectedItem("jour");
		
		JComboBox comboBox_mD = new JComboBox();
		comboBox_mD.setEditable(true);
		comboBox_mD.setBounds(332, 202, 62, 22);
		panel.add(comboBox_mD);
		comboBox_mD.addItem("1");
		comboBox_mD.addItem("2");
		comboBox_mD.addItem("3");
		comboBox_mD.addItem("4");
		comboBox_mD.addItem("5");
		comboBox_mD.addItem("6");
		comboBox_mD.addItem("7");
		comboBox_mD.addItem("8");
		comboBox_mD.addItem("9");
		comboBox_mD.addItem("10");
		comboBox_mD.addItem("11");
		comboBox_mD.addItem("12");
		comboBox_mD.setSelectedItem("mois");
		
		JComboBox comboBox_jF = new JComboBox();
		comboBox_jF.setEditable(true);
		comboBox_jF.setBounds(271, 247, 49, 22);
		panel.add(comboBox_jF);
		comboBox_jF.addItem("1");
		comboBox_jF.addItem("2");
		comboBox_jF.addItem("3");
		comboBox_jF.addItem("4");
		comboBox_jF.addItem("5");
		comboBox_jF.addItem("6");
		comboBox_jF.addItem("7");
		comboBox_jF.addItem("8");
		comboBox_jF.addItem("9");
		comboBox_jF.addItem("10");
		comboBox_jF.addItem("11");
		comboBox_jF.addItem("12");
		comboBox_jF.addItem("13");
		comboBox_jF.addItem("14");
		comboBox_jF.addItem("15");
		comboBox_jF.addItem("16");
		comboBox_jF.addItem("17");
		comboBox_jF.addItem("18");
		comboBox_jF.addItem("19");
		comboBox_jF.addItem("20");
		comboBox_jF.addItem("21");
		comboBox_jF.addItem("22");
		comboBox_jF.addItem("23");
		comboBox_jF.addItem("24");
		comboBox_jF.addItem("25");
		comboBox_jF.addItem("26");
		comboBox_jF.addItem("27");
		comboBox_jF.addItem("28");
		comboBox_jF.addItem("29");
		comboBox_jF.addItem("30");
		comboBox_jF.addItem("31");
		comboBox_jF.setSelectedItem("jour");
		
		JComboBox comboBox_mF = new JComboBox();
		comboBox_mF.setEditable(true);
		comboBox_mF.setBounds(332, 247, 62, 22);
		panel.add(comboBox_mF);
		comboBox_mF.addItem("1");
		comboBox_mF.addItem("2");
		comboBox_mF.addItem("3");
		comboBox_mF.addItem("4");
		comboBox_mF.addItem("5");
		comboBox_mF.addItem("6");
		comboBox_mF.addItem("7");
		comboBox_mF.addItem("8");
		comboBox_mF.addItem("9");
		comboBox_mF.addItem("10");
		comboBox_mF.addItem("11");
		comboBox_mF.addItem("12");
		comboBox_mF.setSelectedItem("mois");
		
		JComboBox comboBox_aF = new JComboBox();
		comboBox_aF.setEditable(true);
		comboBox_aF.setBounds(431, 247, 75, 22);
		panel.add(comboBox_aF);
		comboBox_aF.addItem("2019");
		comboBox_aF.addItem("2020");
		comboBox_aF.setSelectedItem("année");
		
		JComboBox comboBox_aD = new JComboBox();
		comboBox_aD.setEditable(true);
		comboBox_aD.setBounds(431, 202, 75, 22);
		panel.add(comboBox_aD);
		comboBox_aD.addItem("2019");
		comboBox_aD.addItem("2020");
		comboBox_aD.setSelectedItem("année");
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(95, 158, 160));
		panel_1.setBounds(0, 0, 1006, 116);
		panel.add(panel_1);
		
		JLabel lblDeposerJustificatif = new JLabel("DEPOSER JUSTIFICATIF");
		lblDeposerJustificatif.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeposerJustificatif.setForeground(Color.WHITE);
		lblDeposerJustificatif.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDeposerJustificatif.setBackground(Color.WHITE);
		lblDeposerJustificatif.setBounds(22, 46, 237, 25);
		panel_1.add(lblDeposerJustificatif);
		
		JLabel lblEsigelec = new JLabel(" ");
		lblEsigelec.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblEsigelec.setForeground(Color.RED);
		lblEsigelec.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 38));
		lblEsigelec.setBounds(488, 30, 181, 47);
		panel_1.add(lblEsigelec);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(95, 158, 160));
		panel_2.setBounds(-14, 426, 975, 40);
		panel.add(panel_2);
		frame.setResizable(false);

	}
	public JFrame getFrame() {
		return frame;
	}
}
