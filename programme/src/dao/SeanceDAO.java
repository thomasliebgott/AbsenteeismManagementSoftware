package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.FonctionsDates;
import model.Seance;

public class SeanceDAO extends ConnectionLogicielDAO {

	public SeanceDAO() {
		super();
	}
	
	public int add(Seance seance) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;
		
		// connexion a la base de donnees
		try {
			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			// preparation de l'instruction SQL, chaque ? represente une valeur
			// a communiquer dans l'insertion.
			// les getters permettent de recuperer les valeurs des attributs souhaites
			
			ps = con.prepareStatement("INSERT INTO seance (idSeance, typeSeance, numSession, numeroGrp , idEnseignant, idCours, idHoraire, dateSeance, idSemaine)  VALUES (seance_auto_incr.nextval, ?, ?, ?, ?, ?, ? , ?, ?)");
			
			//ps.setInt(1, seance.getIdSeance());
			ps.setString(1, seance.getTypeSeance());
			ps.setInt(2, seance.getNumSession());
			ps.setInt(3, seance.getNumeroGrp());
			ps.setInt(4, seance.getIdEnseignant());
			ps.setInt(5, seance.getIdCours());
			ps.setInt(6, seance.getIdHoraire());
			ps.setDate(7, seance.getDateSeance());
			ps.setInt(8, seance.getIdSemaine());
			
			
			// Execution de la requete
			returnValue = ps.executeUpdate();
			
			
			
		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant de fournisseur existe d�j�. Ajout impossible !");
			else
				e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	public int update(Seance seance) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, chaque ? represente une valeur
			// a communiquer dans la modification.
			// les getters permettent de recuperer les valeurs des attributs souhaites
			ps = con.prepareStatement("UPDATE seance SET typeSeance = ? ,"
					+ "numSession = ? ,"
					+ "numeroGrp = ?,"
					+ "idEnseignant = ? ,"
					+ "idCours = ? ,"
					+ "idHoraire = ? ,"
					+ "dateSeance = ? ,"
					+ "idSemaine = ? "
					+ "WHERE idSeance = ? ");
			
			
			ps.setString(1, seance.getTypeSeance());
			ps.setInt(2, seance.getNumSession());
			ps.setInt(3, seance.getNumeroGrp());
			ps.setInt(4, seance.getIdEnseignant());
			ps.setInt(5, seance.getIdCours());
			ps.setInt(6, seance.getIdHoraire());
			ps.setDate(7, seance.getDateSeance());
			ps.setInt(8, seance.getIdSemaine());
			ps.setInt(9, seance.getIdSeance());
			

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	public int delete(int idSeance) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, le ? represente la valeur de l'ID
			// a communiquer dans la suppression.
			// le getter permet de recuperer la valeur de l'ID de l'utilisateur
			ps = con.prepareStatement("DELETE FROM seance WHERE idSeance = ?");
			ps.setInt(1,idSeance);

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-02292"))
				System.out.println("Cet etudiant ne peut etre supprim� r��ssayer plus tard.");
			else
				e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	public Seance get(int idSeance) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Seance returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM seance WHERE idSeance = ?");
			ps.setInt(1, idSeance);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Seance(rs.getInt("idSeance"), 
						rs.getString("typeSeance"), 
						rs.getInt("numSession"), 
						rs.getInt("numeroGrp"), 
						rs.getInt("idEnseignant"), 
						rs.getInt("idCours"), 
						rs.getInt("idHoraire"),
						rs.getDate("dateSeance"),
						rs.getInt("idSemaine")
						);
				returnValue.setJour(FonctionsDates.getJourSemaine(returnValue.getDateSeance()));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du ResultSet, du PreparedStatement et de la Connexion
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	public ArrayList<Seance> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Seance> returnValue = new ArrayList<Seance>();
		int i = 0;

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM seance ORDER BY idSeance");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Seance(rs.getInt("idSeance"), 
						rs.getString("typeSeance"), 
						rs.getInt("numSession"), 
						rs.getInt("numeroGrp"), 
						rs.getInt("idEnseignant"), 
						rs.getInt("idCours"), 
						rs.getInt("idHoraire"),
						rs.getDate("dateSeance"),
						rs.getInt("idSemaine")
						));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	public ArrayList<Seance> getListSeanceEnseignant(int idEnseignant, int idSemaine) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Seance> returnValue = new ArrayList<Seance>();
		int i = 0;

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM seance WHERE idEnseignant = ? AND idSemaine = ? ORDER BY idSeance");
			ps.setInt(1, idEnseignant);
			ps.setInt(2, idSemaine);
			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Seance(rs.getInt("idSeance"), 
						rs.getString("typeSeance"), 
						rs.getInt("numSession"), 
						rs.getInt("numeroGrp"), 
						rs.getInt("idEnseignant"), 
						rs.getInt("idCours"), 
						rs.getInt("idHoraire"),
						rs.getDate("dateSeance"),
						rs.getInt("idSemaine")
						));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	public ArrayList<Seance> getListSeanceEtudiant(int numeroGrp, int idSemaine) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Seance> returnValue = new ArrayList<Seance>();
		int i = 0;

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM seance WHERE numeroGrp = ? AND idSemaine = ? ORDER BY idSeance");
			ps.setInt(1, numeroGrp);
			ps.setInt(2, idSemaine);
			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Seance(rs.getInt("idSeance"), 
						rs.getString("typeSeance"), 
						rs.getInt("numSession"), 
						rs.getInt("numeroGrp"), 
						rs.getInt("idEnseignant"), 
						rs.getInt("idCours"), 
						rs.getInt("idHoraire"),
						rs.getDate("dateSeance"),
						rs.getInt("idSemaine")
						));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	public int getNombreSemaine() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT MAX(idSemaine) FROM seance");

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = (int)rs.getLong(1);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du ResultSet, du PreparedStatement et de la Connexion
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
}
