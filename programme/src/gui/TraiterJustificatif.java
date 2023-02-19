package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.AbsenceDAO;
import dao.EtudiantDAO;
import dao.EtudiantDAO;
import model.Etudiant;
import model.Etudiant;
import model.Absence;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class TraiterJustificatif extends DeposerJustificatif{

	private JFrame frmTraiterJustificatif;
	private JTable table;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TraiterJustificatif window = new TraiterJustificatif();
					window.frmTraiterJustificatif.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TraiterJustificatif() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTraiterJustificatif = new JFrame();
		frmTraiterJustificatif.setResizable(false);
		frmTraiterJustificatif.setTitle("Traiter un Justificatif ");
		frmTraiterJustificatif.setBounds(100, 100, 547, 495);
		frmTraiterJustificatif.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		frmTraiterJustificatif.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		table = new JTable();
		table.setBounds(387, 282, -294, -128);
		panel.add(table);
		
		/**
		 * va chercher le fichier a aller download 
		 */
		
		btnNewButton = new JButton("download");
		btnNewButton.setBackground(new Color(95, 158, 160));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try { 
					Runtime.getRuntime().exec("explorer.exe");
					
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, "check file details");
				}
				
			}
		});
		btnNewButton.setBounds(229, 193, 97, 25);
		panel.add(btnNewButton);
		
		JLabel lblFichier = new JLabel("fichier justificatif :");
		lblFichier.setForeground(new Color(255, 255, 255));
		lblFichier.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFichier.setBounds(48, 197, 170, 16);
		panel.add(lblFichier);
		
		JButton btnNewButton_1 = new JButton("valider");
		btnNewButton_1.setBackground(new Color(95, 158, 160));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
			}
		});
		btnNewButton_1.setBounds(269, 352, 97, 25);
		panel.add(btnNewButton_1);
		
		JButton btnRetour = new JButton("retour ");
		btnRetour.setForeground(new Color(255, 255, 255));
		btnRetour.setBackground(new Color(95, 158, 160));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmTraiterJustificatif.dispose();
				frmTraiterJustificatif = new MenuGestionnaire().getFrame();
				frmTraiterJustificatif.setVisible(true);
			}
		});
		btnRetour.setBounds(387, 352, 97, 25);
		panel.add(btnRetour);
		
		JCheckBox chckbxJustifi = new JCheckBox("     Justifi\u00E9 ");
		chckbxJustifi.setFont(new Font("Dialog", Font.BOLD, 13));
		chckbxJustifi.setForeground(new Color(255, 255, 255));
		chckbxJustifi.setBackground(new Color(25, 25, 112));
		chckbxJustifi.setBounds(230, 234, 113, 25);
		panel.add(chckbxJustifi);
		
		JLabel lblNombreAbsence = new JLabel("Nombre Absence :");
		lblNombreAbsence.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNombreAbsence.setForeground(new Color(255, 255, 255));
		lblNombreAbsence.setBounds(48, 282, 159, 16);
		panel.add(lblNombreAbsence);
		
		JLabel nbAbsence = new JLabel("afficher nb Absence");
		nbAbsence.setBounds(230, 282, 146, 16);
		panel.add(nbAbsence);
		
		JLabel lblSeance = new JLabel("Seance :");
		lblSeance.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSeance.setForeground(new Color(255, 255, 255));
		lblSeance.setBounds(48, 238, 102, 16);
		panel.add(lblSeance);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(95, 158, 160));
		panel_1.setBounds(0, 0, 1006, 116);
		panel.add(panel_1);
		
		JLabel lblTraiterJustificatif = new JLabel("TRAITER JUSTIFICATIF");
		lblTraiterJustificatif.setHorizontalAlignment(SwingConstants.CENTER);
		lblTraiterJustificatif.setForeground(Color.WHITE);
		lblTraiterJustificatif.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTraiterJustificatif.setBackground(Color.WHITE);
		lblTraiterJustificatif.setBounds(34, 46, 237, 25);
		panel_1.add(lblTraiterJustificatif);
		
		JLabel lblEsigelec = new JLabel(" ESIGELEC ");
		lblEsigelec.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblEsigelec.setForeground(Color.RED);
		lblEsigelec.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 38));
		lblEsigelec.setBounds(339, 30, 179, 47);
		panel_1.add(lblEsigelec);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(95, 158, 160));
		panel_2.setBounds(0, 404, 569, 44);
		panel.add(panel_2);
		
		
		
	}
}
