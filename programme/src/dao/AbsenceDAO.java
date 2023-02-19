package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Absence;
import model.Enseignant;

public class AbsenceDAO extends ConnectionLogicielDAO {
	public AbsenceDAO() {
		super();
	}
	
	public int add(Absence absence) {
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
			
			ps = con.prepareStatement("INSERT INTO absence (idAbsence, idEtudiant, idSeance, idJustificatif, justifieOuNon, horsQuota)  VALUES (absence_auto_incr.nextval, ?, ?, ?, ?, ?)");
			
			ps.setInt(1, absence.getIdEtudiant());
			ps.setInt(2, absence.getIdSeance());
			ps.setInt(3, absence.getIdJustificatif());
			ps.setString(4, absence.getJustifieOuNon());
			ps.setString(5, absence.getHorsQuota());
			
	// Execution de la requete
			
			returnValue = ps.executeUpdate();
			
		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant d'absence existe d�j�. Ajout impossible !");
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
	
	public int update(Absence absence) {
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
			
			ps = con.prepareStatement("UPDATE absence set idJustificatif = ?, justifieOuNon = ? WHERE idAbsence = ?");
			ps.setInt(1, absence.getIdJustificatif());
			ps.setString(2, absence.getJustifieOuNon());
			ps.setInt(3, absence.getIdAbsence());

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
	
	public int delete(int idAbsence) {
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
			ps = con.prepareStatement("DELETE FROM absence WHERE idAbsence = ?");
			ps.setInt(1,idAbsence);

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-02292"))
				System.out.println("Cette absence ne peut etre supprim� r��ssayer plus tard.");
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
	

	public Absence get(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Absence returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM absence WHERE idAbsence = ?");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Absence(rs.getInt("idAbsence"),
									       rs.getInt("idEtudiant"),
									       rs.getInt("idSeance"),
									       rs.getInt("idJustificatif"),
									       rs.getString("justifieOuNon"), 
									       rs.getString("horsQuota"));
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
	
	public ArrayList<Absence> getList(int idEtudiant) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Absence> returnValue = new ArrayList<Absence>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM absence WHERE idEtudiant = ? ORDER BY idAbsence");
			ps.setInt(1, idEtudiant);
			
			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Absence(rs.getInt("idAbsence"),
					       rs.getInt("idEtudiant"),
					       rs.getInt("idSeance"),
					       rs.getInt("idJustificatif"),
					       rs.getString("justifieOuNon"), 
					       rs.getString("horsQuota")));
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
