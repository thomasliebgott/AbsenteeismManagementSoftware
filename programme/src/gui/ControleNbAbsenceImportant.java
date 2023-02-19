package gui;
import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.AbsenceDAO;

import dao.EtudiantDAO;
import dao.SeanceDAO;
import model.Absence;
import model.Etudiant;
import model.Seance;

import java.sql.Date;
import java.time.LocalDate;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class ControleNbAbsenceImportant extends JFrame{
	
	String colonne[] = {"supprimer", "date","heure"};
	private JFrame frame;
	SeanceDAO seanceDAO = new SeanceDAO();
	
  //MAIN METHOD

  public static void main(String[] args)
  {

       EventQueue.invokeLater(new Runnable()
       {
           public void run()
           {
               //INITIALIZE JFRAME FORM
               Appel form = new Appel();
               form.setVisible(true);;
           }
       });

  }
  
  //CONSTRUCTOR
  public ControleNbAbsenceImportant(){
	  getContentPane().setBackground(new Color(25, 25, 112));
	  initialized();
  	}

	public void initialized(){
	frame = new JFrame();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(200,200,1024,651);
    setTitle("ProgrammingWizards Channel");
    getContentPane().setLayout(null);

    //ADD SCROLLPANE
    JScrollPane scroll = new JScrollPane();
    scroll.setBounds(165,166,676,311);
    getContentPane().add(scroll);

    //THE TABLE
    final JTable table=new JTable();
    scroll.setViewportView(table);
	frame.setResizable(false);
	
    //THE MODEL OF OUR TABLE
    DefaultTableModel model=new DefaultTableModel()
    {
      public Class<?> getColumnClass(int column)
      {
        switch(column)
        {
        case 0:
          return Boolean.class;
        case 1:
          return String.class;
        case 2:
          return String.class;
          default:
            return String.class;
        }
      }
    };

    //ASSIGN THE MODEL TO TABLE
    table.setModel(model);
    model.addColumn("supprimer");
    model.addColumn("date");
    model.addColumn("heure");
    
    //THE ROW
    LocalDate localDate = LocalDate.now();
    LocalDate dateDebut = localDate;
    LocalDate dateFin = localDate;
    ArrayList<Seance> listeSeance = seanceDAO.getList();
    String colonne[] = {"supprimer", "date","heure"};
	String donnees[][] = getListeSeanceEtHeure(listeSeance, colonne);
	
    
    for(int i=0; i < donnees.length ;i++){
    	
      model.addRow(new Object[0]);
      model.setValueAt(false, i, 0);
    //  System.out.println("test");
      model.setValueAt(donnees[i][0], i, 1);
    //  System.out.println("test2");
      model.setValueAt(donnees[i][1], i, 2);
    //  System.out.println("test3");
    }
    
    ArrayList<String> listeDate = new ArrayList<String>();
    ArrayList<String> listeHoraire = new ArrayList<String>();
    
    //OBTAIN SELECTED ROW
    JButton btn = new JButton("Valider");
    btn.setBackground(new Color(95, 158, 160));
    btn.setForeground(Color.WHITE);
    btn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {

        //GET SELECTED ROW
        for(int i=0;i<table.getRowCount();i++)
        {
          Boolean checked = Boolean.valueOf(table.getValueAt(i, 0).toString());
          String date = table.getValueAt(i, 1).toString();
          String heure = table.getValueAt(i, 2).toString();

          //DISPLAY
          if(checked)
          {
        	  
           // JOptionPane.showMessageDialog(null, nom);
            
          }
        }
      }
      
    });

    //ADD BUTTON TO FORM
    btn.setBounds(834,502,130,30);
    getContentPane().add(btn);
    
    JLabel LabelJour = new JLabel("Jour :  ");
    LabelJour.setFont(new Font("Dialog", Font.BOLD, 16));
    LabelJour.setForeground(new Color(255, 255, 255));
    LabelJour.setBounds(31, 125, 67, 16);
    getContentPane().add(LabelJour);
    
    JLabel lblMois = new JLabel("Mois :");
    lblMois.setForeground(new Color(255, 255, 255));
    lblMois.setFont(new Font("Dialog", Font.BOLD, 16));
    lblMois.setBounds(138, 127, 56, 16);
    getContentPane().add(lblMois);
    
    JLabel lblAnne = new JLabel("Ann\u00E9e :");
    lblAnne.setFont(new Font("Dialog", Font.BOLD, 16));
    lblAnne.setForeground(new Color(255, 255, 255));
    lblAnne.setBounds(289, 127, 73, 16);
    getContentPane().add(lblAnne);
    
    JLabel Jlabel12 = new JLabel("Heure de D\u00E9but :");
    Jlabel12.setFont(new Font("Dialog", Font.BOLD, 16));
    Jlabel12.setForeground(new Color(255, 255, 255));
    Jlabel12.setBounds(439, 127, 182, 16);
    getContentPane().add(Jlabel12);
    
    JLabel lblHeureFin = new JLabel("Heure De Fin:");
    lblHeureFin.setFont(new Font("Dialog", Font.BOLD, 16));
    lblHeureFin.setForeground(new Color(255, 255, 255));
    lblHeureFin.setBounds(694, 127, 122, 16);
    getContentPane().add(lblHeureFin);
    
    JLabel labelJour = new JLabel(" jour");
    labelJour.setForeground(new Color(255, 255, 255));
    labelJour.setBounds(82, 127, 56, 16);
    getContentPane().add(labelJour);
    
    JLabel labelMois = new JLabel("mois");
    labelMois.setForeground(new Color(255, 255, 255));
    labelMois.setBounds(189, 127, 56, 16);
    getContentPane().add(labelMois);
    
    JLabel labelAnnée = new JLabel(" ann\u00E9e");
    labelAnnée.setForeground(new Color(255, 255, 255));
    labelAnnée.setBounds(358, 127, 56, 16);
    getContentPane().add(labelAnnée);
    
    JLabel labelDebut = new JLabel("heure");
    labelDebut.setForeground(new Color(255, 255, 255));
    labelDebut.setBounds(592, 127, 56, 16);
    getContentPane().add(labelDebut);
    
    JLabel labelFin = new JLabel(" ");
    labelFin.setBounds(819, 104, 56, 16);
    getContentPane().add(labelFin);
    
    JPanel panel_1 = new JPanel();
    panel_1.setLayout(null);
    panel_1.setBackground(new Color(95, 158, 160));
    panel_1.setBounds(0, -11, 1006, 116);
    getContentPane().add(panel_1);
    
    JLabel lblAppel = new JLabel("APPEL");
    lblAppel.setHorizontalAlignment(SwingConstants.CENTER);
    lblAppel.setForeground(Color.WHITE);
    lblAppel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblAppel.setBackground(Color.WHITE);
    lblAppel.setBounds(-54, 46, 237, 25);
    panel_1.add(lblAppel);
    
    JLabel lblEsigelec = new JLabel(" ESIGELEC ");
    lblEsigelec.setIcon(new ImageIcon("./src/images/esigelec-irseem-squarelogo-1455621802282.png"));
    lblEsigelec.setForeground(Color.RED);
    lblEsigelec.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 38));
    lblEsigelec.setBounds(800, 30, 178, 47);
    panel_1.add(lblEsigelec);
    
    JLabel JlabelFin = new JLabel("heure");
    JlabelFin.setForeground(new Color(255, 255, 255));
    JlabelFin.setBounds(804, 127, 56, 16);
    getContentPane().add(JlabelFin);
    
    JPanel panel = new JPanel();
    panel.setBackground(new Color(95, 158, 160));
    panel.setBounds(0, 556, 1006, 48);
    getContentPane().add(panel);
	

    JButton btnNewButton = new JButton("retour");
    btnNewButton.setBackground(new Color(95, 158, 160));
    btnNewButton.setForeground(new Color(255, 255, 255));
    btnNewButton.setBounds(669, 503, 122, 28);
    getContentPane().add(btnNewButton);
    
    JPanel panel_2 = new JPanel();
    panel_2.setBackground(new Color(119, 136, 153));
    panel_2.setBounds(0, 166, 166, 311);
    getContentPane().add(panel_2);
    
    JPanel panel_2_1 = new JPanel();
    panel_2_1.setBackground(new Color(119, 136, 153));
    panel_2_1.setBounds(840, 166, 166, 311);
    getContentPane().add(panel_2_1);
    
	frame.setResizable(false);

  }
  
	public String[][] getListeSeanceEtHeure(ArrayList<Seance> listeSeance, String[] colonne){
		String data[][] = new String[listeSeance.size()][colonne.length];
		for(int i=0; i<listeSeance.size(); i++) {
			data[i][0] = String.valueOf(listeSeance.get(i).getDateSeance());
			data[i][1] = String.valueOf(listeSeance.get(i).getIdHoraire());
		}
		return data;
	}
	
	public JFrame getFrame(){
		return frame;
}
}