package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.CoursDAO;
import dao.EnseignantDAO;
import model.Cours;
import model.Enseignant;
import model.Etudiant;
import model.Groupe;
import model.Sanction;
import dao.EtudiantDAO;
import dao.GroupeDAO;
import dao.SanctionDAO;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.ImageIcon;

public class ModifierSanction {

	private JFrame frame;
	private JLabel nomPrenomLabel;
	private int idSanction;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifierSanction window = new ModifierSanction();
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
	public ModifierSanction() {
		initialize();
	}
	
	public ModifierSanction(int idSanction) {
		this.idSanction = idSanction;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(25, 25, 112));
		frame.setBounds(100, 100, 547, 443);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTypeSanction = new JLabel("Type de Sanction :");
		lblTypeSanction.setForeground(new Color(255, 255, 255));
		lblTypeSanction.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTypeSanction.setBounds(37, 160, 171, 25);
		frame.getContentPane().add(lblTypeSanction);
		
		JComboBox groupeBox = new JComboBox();
		JComboBox etudiantBox = new JComboBox();
		etudiantBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					int idEtudiant = (int)etudiantBox.getSelectedItem();
					Etudiant etudiant = new EtudiantDAO().get(idEtudiant);
					nomPrenomLabel.setText(etudiant.getNom() + " " + etudiant.getPrenom());
			}catch(Exception ex) {
				
			}
			}
		});
		
		JLabel label = new JLabel("");
		label.setBounds(49, 237, 56, 16);
		frame.getContentPane().add(label);
		int idEtudiant = new SanctionDAO().get(idSanction).getIdEtudiant();
		Etudiant etudiant = new EtudiantDAO().get(idEtudiant);
		groupeBox.addItem(etudiant.getIdGroupe());
		groupeBox.setSelectedItem(etudiant.getIdGroupe());
		etudiantBox.addItem(idEtudiant);
		etudiantBox.setSelectedItem(idEtudiant);
		//selectionnerEtudiantDuGroupe(groupeBox, etudiantBox);
		JComboBox comboBoxSanction = new JComboBox();
		comboBoxSanction.setBounds(220, 159, 168, 22);
		comboBoxSanction.addItem("reliquat");
		comboBoxSanction.addItem("note 0");
		comboBoxSanction.addItem("rattrapage justifié");
		comboBoxSanction.addItem("courrier dépassement");
		frame.getContentPane().add(comboBoxSanction);
		
		
		JButton btnValider = new JButton("valider");
		btnValider.setBackground(new Color(95, 158, 160));
		btnValider.setForeground(new Color(255, 255, 255));
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String typeSanction = (String)comboBoxSanction.getSelectedItem();
				int idEtudiant = (int)etudiantBox.getSelectedItem();
				int	retour = new SanctionDAO().update(new Sanction(idSanction,typeSanction,idEtudiant));
				if(retour!= 0) {
					JOptionPane.showMessageDialog(null, "Sanction modifiée dans la BDD");
				}else {
					JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour de la sanction dans la BDD");
				}
			}
		});
		
		btnValider.setBounds(373, 313, 97, 25);
		frame.getContentPane().add(btnValider);
		
		nomPrenomLabel = new JLabel(etudiant.getNom() + " " + etudiant.getPrenom());
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(95, 158, 160));
		panel_1.setBounds(0, 0, 1006, 116);
		frame.getContentPane().add(panel_1);
		
		JLabel lblTraiterJustificatif = new JLabel("AJOUTER SANCTION");
		lblTraiterJustificatif.setHorizontalAlignment(SwingConstants.CENTER);
		lblTraiterJustificatif.setForeground(Color.WHITE);
		lblTraiterJustificatif.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTraiterJustificatif.setBackground(Color.WHITE);
		lblTraiterJustificatif.setBounds(12, 46, 237, 25);
		panel_1.add(lblTraiterJustificatif);
		
		JLabel lblEsigelec = new JLabel(" ESIGELEC ");
		lblEsigelec.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblEsigelec.setForeground(Color.RED);
		lblEsigelec.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 38));
		lblEsigelec.setBounds(331, 30, 178, 47);
		panel_1.add(lblEsigelec);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(95, 158, 160));
		panel.setForeground(SystemColor.textHighlight);
		panel.setBounds(0, 359, 568, 37);
		frame.getContentPane().add(panel);
		
		
		groupeBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//selectionnerEtudiantDuGroupe(groupeBox, etudiantBox);
				
			}
		});
		groupeBox.setBounds(220, 219, 88, 22);
		frame.getContentPane().add(groupeBox);
		
		JLabel lblGroupe = new JLabel("Groupe");
		lblGroupe.setForeground(Color.WHITE);
		lblGroupe.setFont(new Font("Dialog", Font.BOLD, 16));
		lblGroupe.setBounds(37, 212, 171, 30);
		frame.getContentPane().add(lblGroupe);
		
		JLabel lblEtudiant = new JLabel("Etudiant");
		lblEtudiant.setForeground(Color.WHITE);
		lblEtudiant.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEtudiant.setBounds(37, 262, 171, 30);
		frame.getContentPane().add(lblEtudiant);
		
		etudiantBox.setBounds(220, 269, 88, 22);
		frame.getContentPane().add(etudiantBox);
		
		
		nomPrenomLabel.setForeground(Color.WHITE);
		nomPrenomLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		nomPrenomLabel.setBounds(322, 262, 171, 30);
		frame.getContentPane().add(nomPrenomLabel);
		
		JButton retourBtn = new JButton("Retour");
		retourBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		retourBtn.setForeground(Color.WHITE);
		retourBtn.setBackground(new Color(95, 158, 160));
		retourBtn.setBounds(247, 314, 97, 25);
		frame.getContentPane().add(retourBtn);
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
	
	public void slectionnerEtudiantDuGroupe(JComboBox groupeBox, JComboBox etudiantBox) {
		int numeroGrp = (int)groupeBox.getSelectedItem();
		ArrayList<Etudiant> listeEtudiant = new EtudiantDAO().getList(numeroGrp);
		etudiantBox.removeAllItems();
		for(int i=0; i<listeEtudiant.size(); i++) {
			etudiantBox.addItem(listeEtudiant.get(i).getIdEtudiant());
		}
		
	}
}
