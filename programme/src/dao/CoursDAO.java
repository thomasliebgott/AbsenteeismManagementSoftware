package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Absence;
import model.Cours;
import model.Etudiant;

public class CoursDAO extends ConnectionLogicielDAO {
	
	public CoursDAO() {
		super();
	}
	
	public int add(Cours cours) {
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
			
			ps = con.prepareStatement("INSERT INTO cours (idCours, nomCours, masseHoraireAmphi, masseHoraireTD , masseHoraireTP, masseHoraireExam, idEnseignant)  VALUES (cours_auto_incr.nextval, ?, ?, ?, ?, ?, ?)");
			
			ps.setString(1, cours.getNomCours());
			ps.setInt(2, cours.getMasseHoraireAmphi());
			ps.setInt(3, cours.getMasseHoraireTD());
			ps.setInt(4, cours.getMasseHoraireTP());
			ps.setInt(5, cours.getMasseHoraireExam());
			ps.setInt(6, cours.getIdEnseignant());
			
			
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
	
	public int update(Cours cours) {
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
			ps = con.prepareStatement("UPDATE cours SET idEnseignant = ? ,"
					+ "nomCours = ? ,"
					+ "masseHoraireAmphi = ?,"
					+ "masseHoraireTD = ? ,"
					+ "masseHoraireTP = ? ,"
					+ "masseHoraireExam = ? ,"
					+ "idEnseignant = ?"
					+ "WHERE idCours = ? ");
			ps.setInt(1, cours.getIdEnseignant());
			ps.setString(2, cours.getNomCours());
			ps.setInt(3, cours.getMasseHoraireAmphi());
			ps.setInt(4, cours.getMasseHoraireTD());
			ps.setInt(4, cours.getMasseHoraireTD());
			ps.setInt(5, cours.getMasseHoraireExam());
			ps.setInt(6, cours.getIdEnseignant());
			ps.setInt(7, cours.getIdCours());

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
	
	public int delete(int idCours) {
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
			ps = con.prepareStatement("DELETE FROM cours WHERE idCours = ?");
			ps.setInt(1,idCours);

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
	
	public Cours get(int idCours) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cours returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM cours WHERE idCours = ?");
			ps.setInt(1, idCours);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Cours(rs.getInt("idCours"), 
						rs.getInt("idEnseignant"), 
						rs.getString("nomCours"), 
						rs.getInt("masseHoraireAmphi"), 
						rs.getInt("masseHoraireTD"), 
						rs.getInt("masseHoraireTP"), 
						rs.getInt("masseHoraireExam")
						);
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
	
	public static ArrayList<Cours> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Cours> returnValue = new ArrayList<Cours>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM cours ORDER BY idCours");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Cours(rs.getInt("idCours"), 
						rs.getInt("idEnseignant"), 
						rs.getString("nomCours"), 
						rs.getInt("masseHoraireAmphi"), 
						rs.getInt("masseHoraireTD"), 
						rs.getInt("masseHoraireTP"), 
						rs.getInt("masseHoraireExam")
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
	
	public static ArrayList<Cours> getList(int idEnseignant) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Cours> returnValue = new ArrayList<Cours>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM cours WHERE idEnseignant = ? ORDER BY idCours");
			ps.setInt(1, idEnseignant);

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Cours(rs.getInt("idCours"), 
						rs.getInt("idEnseignant"), 
						rs.getString("nomCours"), 
						rs.getInt("masseHoraireAmphi"), 
						rs.getInt("masseHoraireTD"), 
						rs.getInt("masseHoraireTP"), 
						rs.getInt("masseHoraireExam")
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
	
}
