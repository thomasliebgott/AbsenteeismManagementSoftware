package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

import dao.QuotaDAO;
import model.Quota;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class AjouterQuota {

	private JFrame frame;
	private JTextField textNomQuota;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterQuota window = new AjouterQuota();
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
	
	public AjouterQuota() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 578, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblSeuil = new JLabel("seuil d'absence en heure :");
		lblSeuil.setForeground(new Color(255, 255, 255));
		lblSeuil.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSeuil.setBounds(44, 234, 213, 30);
		panel.add(lblSeuil);
		
		JSpinner spinner = new JSpinner();
		spinner.setForeground(SystemColor.activeCaptionBorder);
		spinner.setBounds(306, 240, 30, 22);
		panel.add(spinner);
		
		JButton btnValider = new JButton("valider");
		btnValider.setForeground(new Color(255, 255, 255));
		btnValider.setBackground(new Color(95, 158, 160));
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomQuota = textNomQuota.getText();
				int seuil = Integer.parseInt(spinner.getValue().toString());
				int retour = new QuotaDAO().add(new Quota(0,seuil, nomQuota));
				if(retour!= 0) {
					JOptionPane.showMessageDialog(null, "Cours enregistré dans la BDD");
				}else {
					JOptionPane.showMessageDialog(null, "Erreur lors de l'insertion de l'étudiant dans la BDD");
				}
			textNomQuota.setText(null);
			}
		});
		btnValider.setBounds(440, 291, 97, 25);
		panel.add(btnValider);
		
		JLabel lblNomQuota = new JLabel("Penalite : ");
		lblNomQuota.setForeground(new Color(255, 255, 255));
		lblNomQuota.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNomQuota.setBounds(44, 169, 139, 40);
		panel.add(lblNomQuota);
		
		textNomQuota = new JTextField();
		textNomQuota.setBounds(267, 180, 116, 22);
		panel.add(textNomQuota);
		textNomQuota.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(95, 158, 160));
		panel_1.setBounds(0, 0, 1006, 116);
		panel.add(panel_1);
		
		JLabel lblAjouterQuota = new JLabel("AJOUTER QUOTA");
		lblAjouterQuota.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjouterQuota.setForeground(Color.WHITE);
		lblAjouterQuota.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAjouterQuota.setBackground(Color.WHITE);
		lblAjouterQuota.setBounds(-11, 46, 237, 25);
		panel_1.add(lblAjouterQuota);
		
		JLabel lblEsigelec = new JLabel(" ESIGELEC ");
		lblEsigelec.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblEsigelec.setForeground(Color.RED);
		lblEsigelec.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 38));
		lblEsigelec.setBounds(377, 30, 181, 47);
		panel_1.add(lblEsigelec);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(95, 158, 160));
		panel_3.setBounds(0, 341, 755, 81);
		panel.add(panel_3);
		
		JButton btnNewButton = new JButton("Retour");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frame = new MenuGestionnaire().getFrame();
				frame.setVisible(true);
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(95, 158, 160));
		btnNewButton.setBounds(333, 291, 97, 25);
		panel.add(btnNewButton);
		frame.setResizable(false);
		
		
	}
	public JFrame getFrame() {
		return frame;
	}
}
