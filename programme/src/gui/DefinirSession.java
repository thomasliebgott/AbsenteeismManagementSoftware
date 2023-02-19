package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import dao.SessionDAO;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import model.FonctionsDates;
import model.Session;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.ImageIcon;

public class DefinirSession {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DefinirSession window = new DefinirSession();
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
	public DefinirSession() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		SessionDAO sessionDAO = new SessionDAO();
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(25, 25, 112));
		frame.setBounds(100, 100, 577, 364);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JDateChooser dateDebut = new JDateChooser();
		dateDebut.setBounds(319, 186, 98, 21);
		frame.getContentPane().add(dateDebut);
		
		JDateChooser dateFin = new JDateChooser();
		dateFin.setBounds(110, 186, 118, 23);
		frame.getContentPane().add(dateFin);
		
		JLabel lblNewLabel = new JLabel("D\u00E9finir Session");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 19));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 13, 146, 48);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Date de fin");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(309, 153, 108, 34);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Date de d\u00E9but");
		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(110, 153, 108, 34);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		textField = new JTextField();
		textField.setBounds(286, 114, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.setBackground(new Color(95, 158, 160));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FonctionsDates foncDates = new FonctionsDates();
				Date debut = foncDates.dateJavaToDateSql(dateDebut.getDate());
				Date fin = foncDates.dateJavaToDateSql(dateFin.getDate());
				int retour = sessionDAO.add(new Session(1, debut, fin));
				if(retour != 0 ){
					JOptionPane.showMessageDialog(null, "Session deja créee");
				}
				
			}
		});
		btnNewButton.setBounds(136, 258, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Num\u00E9ro de la session");
		lblNewLabel_1_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1_1.setBounds(78, 106, 182, 34);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(95, 158, 160));
		panel.setBounds(0, 0, 574, 79);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel(" ");
		lblNewLabel_1.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
		lblNewLabel_1.setBounds(378, 13, 172, 47);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(95, 158, 160));
		panel_1.setBounds(0, 310, 574, 23);
		frame.getContentPane().add(panel_1);
		
		JButton btnFermer = new JButton("Fermer");
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frame = new MenuGestionnaire().getFrame();
				frame.setVisible(true);
			}
		});
		btnFermer.setForeground(Color.WHITE);
		btnFermer.setBackground(new Color(95, 158, 160));
		btnFermer.setBounds(286, 258, 89, 23);
		frame.getContentPane().add(btnFermer);
		frame.setResizable(false);
		
	}
	public JFrame getFrame() {
		return frame;
	}
}
