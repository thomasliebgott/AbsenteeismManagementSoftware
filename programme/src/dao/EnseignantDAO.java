package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import model.Etudiant;
import model.Utilisateur;
import model.Enseignant;


public class EnseignantDAO extends ConnectionLogicielDAO{
	public EnseignantDAO() {
		super();
	}
	
	public int add(Enseignant professeur) {
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
			
			ps = con.prepareStatement("INSERT INTO enseignant (idEnseignant, id_utilisateur, nomEnseignant, prenomEnseignant, numTel)  VALUES (enseignant_auto_incr.nextval, ?, ?, ?, ?)");
			
			ps.setInt(1, professeur.getIdUtilisateur());			
			ps.setString(2, professeur.getNom());
			ps.setString(3, professeur.getPrenom());
			ps.setInt(4, professeur.getNumTel());
			
			
	// Execution de la requete
			returnValue = ps.executeUpdate();
			
		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant d'enseignant existe dï¿½jï¿½. Ajout impossible !");
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
	
	public int selectCurrentSequenceValue() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int returnValue = -1;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("SELECT MAX(idEnseignant)+1 FROM enseignant");
			

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
	
	public int update(Enseignant enseignant) {
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
			ps = con.prepareStatement("UPDATE enseignant set id_utilisateur = ?, nomEnseignant = ?, prenomEnseignant = ?, numTel = ? WHERE idEnseignant = ?");
			ps.setInt(1, enseignant.getIdUtilisateur());
			ps.setString(2, enseignant.getNom());
			ps.setString(3, enseignant.getPrenom());
			ps.setInt(4, enseignant.getNumTel());
			ps.setInt(5, enseignant.getIdEnseignant());
			
			

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

	/**
	 * Permet de supprimer utilisateur dans la table utilisateur.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param utilisateur l'utilisateur a supprimer
	 * @return retourne le nombre de lignes supprimees dans la table
	 */
	
	public int delete(int idEnseignant) {
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
			ps = con.prepareStatement("DELETE FROM enseignant WHERE idEnseignant = ?");
			ps.setInt(1, idEnseignant);

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-02292"))
				System.out.println("Cet enseignant ne peut etre supprimï¿½ rï¿½ï¿½ssayer plus tard.");
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


	/**
	 * Permet de recuperer un utilisateur a partir de sa reference
	 * 
	 * @param reference la reference de l'utilisateur a recuperer
	 * @return le utilisateur trouve;
	 * 			null si aucun utilisateur ne correspond a cette reference
	 */
	public Enseignant get(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Enseignant returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM enseignant WHERE idEnseignant = ?");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Enseignant(rs.getInt("idEnseignant"),
									       rs.getString("nomEnseignant"),
									       rs.getString("prenomEnseignant"),
									       rs.getInt("id_utilisateur"),
									       rs.getInt("numTel"));
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
	
	public ArrayList<Enseignant> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Enseignant> returnValue = new ArrayList<Enseignant>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM enseignant ORDER BY idEnseignant");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Enseignant(rs.getInt("idEnseignant"),
					       rs.getString("nomEnseignant"),
					       rs.getString("prenomEnseignant"),
					       rs.getInt("id_utilisateur"),
					       rs.getInt("numTel")));
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
	
	public ArrayList<Enseignant> getList(String criteria, String sensi) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String critere;
		String sens;
		ArrayList<Enseignant> returnValue = new ArrayList<Enseignant>();
		
		if(criteria.equals("Id")) {
			critere = "idEnseignant";
		}else if(criteria.equals("Nom")){
			critere = "nomEnseignant";
		}else if(criteria.equals("Prenom")) {
			critere = "prenomEnseignant";
		}else if(criteria.equals("Téléphone")) {
			critere = "numTel";
		}else {
			critere = "id_utilisateur";
		}

		if(sensi.equals("Croissant")) {
			sens = "ASC";
		}else {
			sens = "DESC";
		}
		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM enseignant ORDER BY  "+ critere + " " + sens );
			
			

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Enseignant(rs.getInt("idEnseignant"),
					       rs.getString("nomEnseignant"),
					       rs.getString("prenomEnseignant"),
					       rs.getInt("id_utilisateur"),
					       rs.getInt("numTel")));
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
	
	public ArrayList<Enseignant> recherchez(String criteria,  String aChercher) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String critere;
		String comparaison = " LIKE ";
		ArrayList<Enseignant> returnValue = new ArrayList<Enseignant>();
		
		if(criteria.equals("Id") || criteria.equals("Utilisateur")) {
			comparaison = " = ";
		}
		
		if(criteria.equals("Id")) {
			critere = "idEnseignant";
		}else if(criteria.equals("Nom")){
			critere = "nomEnseignant";
		}else if(criteria.equals("Prenom")) {
			critere = "prenomEnseignant";
		}else if(criteria.equals("Téléphone")) {
			critere = "numTel";
		}else {
			critere = "id_utilisateur";
		}
		
		
		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			if(comparaison.equals(" = ")) {
				ps = con.prepareStatement("SELECT * FROM enseignant WHERE " + critere + comparaison + " ? ");
				ps.setInt(1, Integer.parseInt(aChercher));
			}else {
				ps = con.prepareStatement("SELECT * FROM enseignant WHERE " + critere + comparaison + "'%" + aChercher + "%'");
			}

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Enseignant(rs.getInt("idEnseignant"),
					       rs.getString("nomEnseignant"),
					       rs.getString("prenomEnseignant"),
					       rs.getInt("id_utilisateur"),
					       rs.getInt("numTel")));
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
	 public static String getIdEnseignant(int idUtilisateur) {
	        Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        String returnValue = null;
	        
	        // connexion a la base de donnees
	        
	        try {
	            con = DriverManager.getConnection(URL, LOGIN, PASS);
	            ps = con.prepareStatement("SELECT idEnseignant FROM enseignant WHERE id_Utilisateur = ?");
	            ps.setInt(1, idUtilisateur);

	 

	            // on execute la requete
	            // rs contient un pointeur situe juste avant la premiere ligne retournee
	            rs = ps.executeQuery();
	            // passe a la premiere (et unique) ligne retournee
	            
	            rs = ps.executeQuery();
	            if(rs.next()){
	                returnValue = rs.getString("idEnseignant");
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
