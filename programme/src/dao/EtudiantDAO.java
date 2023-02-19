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


public class EtudiantDAO extends ConnectionLogicielDAO{
	public EtudiantDAO() {
		super();
	}
	
	public int add(Etudiant etudiant) {
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
			
			ps = con.prepareStatement("INSERT INTO etudiant (idEtudiant, id_utilisateur, adresseMail, nomEtudiant, prenomEtudiant, numeroGrp, filiere)  VALUES (etudiant_auto_incr.nextval, ?, ?, ?, ?, ?, ?)");
			
			ps.setInt(1, etudiant.getIdUtilisateur());			
			ps.setString(2, etudiant.getAdresseMail());
			ps.setString(3, etudiant.getNom());
			ps.setString(4, etudiant.getPrenom());
			ps.setInt(5,etudiant.getIdGroupe());
			ps.setString(6, etudiant.getFiliere());
			
			
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
	
	public int selectCurrentSequenceValue() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int returnValue = -1;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("SELECT MAX(idEtudiant)+1 FROM etudiant");
			//ps.setInt(1, id);

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
	
	public int update(Etudiant etudiant) {
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
			ps = con.prepareStatement("UPDATE etudiant set "
					+ "adresseMail = ? ,"
					+ "nomEtudiant = ? ,"
					+ "prenomEtudiant = ? ,"
					+ "numeroGrp = ? ,"
					+ "filiere = ? ,"
					+ "id_utilisateur = ? "
					+ "WHERE idEtudiant = ?");
			ps.setString(1, etudiant.getAdresseMail());
			ps.setString(2, etudiant.getNom());
			ps.setString(3, etudiant.getPrenom());
			ps.setInt(4, etudiant.getIdGroupe());
			ps.setString(5, etudiant.getFiliere());
			ps.setInt(6, etudiant.getIdUtilisateur());
			ps.setInt(7, etudiant.getIdEtudiant());

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
	
	public int delete(int idEtudiant) {
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
			ps = con.prepareStatement("DELETE FROM etudiant WHERE idEtudiant = ?");
			ps.setInt(1,idEtudiant);

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
	
	public Etudiant get(int idEtudiant) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Etudiant returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM etudiant WHERE idEtudiant = ?");
			ps.setInt(1, idEtudiant);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Etudiant(rs.getInt("idEtudiant"),
	                     rs.getString("nomEtudiant"),
	                     rs.getString("prenomEtudiant"),
	                     rs.getInt("id_utilisateur"),
	                     rs.getString("adresseMail"),
	                     rs.getInt("numeroGrp"),
	                     rs.getString("filiere"));
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
	
    public static String getNumeroGroupe(int idUtilisateur) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String returnValue = null;
        
        // connexion a la base de donnees
        
        try {

 

            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT numeroGrp FROM etudiant WHERE id_Utilisateur = ?");
            ps.setInt(1, idUtilisateur);

 

            // on execute la requete
            // rs contient un pointeur situe juste avant la premiere ligne retournee
            rs = ps.executeQuery();
            // passe a la premiere (et unique) ligne retournee
            
            rs = ps.executeQuery();
            if(rs.next()){
                returnValue = rs.getString("numeroGrp");
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
    
    /**
     * get id de l'etudiant a partir de son nom
     * @param nomEtudiant
     * @return id de l'etudiant 
     */
    public static int getIdEtudiant(String nomEtudiant) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int returnValue = 0;
        
        // connexion a la base de donnees
        
        try {

            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT idEtudiant FROM etudiant WHERE nomEtudiant = ?");
            ps.setString(1, nomEtudiant);

 

            // on execute la requete
            // rs contient un pointeur situe juste avant la premiere ligne retournee
            rs = ps.executeQuery();
            // passe a la premiere (et unique) ligne retournee
            
            rs = ps.executeQuery();
            if(rs.next()){
                returnValue = rs.getInt("idEtudiant");
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
    
	/**
	 * Permet de recuperer tous les utilisateurs stockes dans la table fournisseur
	 * 
	 * @return une ArrayList de utilisateur
	 */
	public ArrayList<Etudiant> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Etudiant> returnValue = new ArrayList<Etudiant>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM etudiant ORDER BY idEtudiant");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Etudiant(rs.getInt("idEtudiant"),
	                     rs.getString("nomEtudiant"),
	                     rs.getString("prenomEtudiant"),
	                     rs.getInt("id_utilisateur"),
	                     rs.getString("adresseMail"),
	                     rs.getInt("numeroGrp"),
	                     rs.getString("filiere")));
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
	
	public ArrayList<Etudiant> getList(int numeroGroupe) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Etudiant> returnValue = new ArrayList<Etudiant>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM etudiant WHERE numeroGrp = ? ORDER BY idEtudiant");
			ps.setInt(1, numeroGroupe);

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Etudiant(rs.getInt("idEtudiant"),
	                     				rs.getString("nomEtudiant"),
	                     				rs.getString("prenomEtudiant"),
	                     				rs.getInt("id_utilisateur"),
	                     				rs.getString("adresseMail"),
	                     				rs.getInt("numeroGrp"),
	                     				rs.getString("filiere")));
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
	
	public ArrayList<Etudiant> getNomPrenomList(int numeroGroupe) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Etudiant> returnValue = new ArrayList<Etudiant>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT nomEtudiant,prenomEtudiant FROM etudiant WHERE numeroGrp = ? ORDER BY nomEtudiant");
			ps.setInt(1, numeroGroupe);

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Etudiant(rs.getInt("idEtudiant"),
	                     				rs.getString("nomEtudiant"),
	                     				rs.getString("prenomEtudiant"),
	                     				rs.getInt("id_utilisateur"),
	                     				rs.getString("adresseMail"),
	                     				rs.getInt("numeroGrp"),
	                     				rs.getString("filiere")));
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
	public ArrayList<Etudiant> getList(String criteria, String sensi) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String critere;
		String sens;
		ArrayList<Etudiant> returnValue = new ArrayList<Etudiant>();
		
		if(criteria.equals("Id")) {
			critere = "idEtudiant";
		}else if(criteria.equals("Groupe")) {
			critere = "numeroGrp";
		}else if(criteria.equals("Filiere")) {
			critere = "filiere";
		}else if(criteria.equals("Nom")){
			critere = "nomEtudiant";
		}else if(criteria.equals("Prenom")) {
			critere = "prenomEtudiant";
		}else if(criteria.equals("Email")) {
			critere = "adresseMail";
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
			ps = con.prepareStatement("SELECT * FROM etudiant ORDER BY  "+ critere + " " + sens );
			//ps.setString(1, critere);
			//ps.setString(2, sens);
			

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Etudiant(rs.getInt("idEtudiant"),
	                     rs.getString("nomEtudiant"),
	                     rs.getString("prenomEtudiant"),
	                     rs.getInt("id_utilisateur"),
	                     rs.getString("adresseMail"),
	                     rs.getInt("numeroGrp"),
	                     rs.getString("filiere")));
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
	
	public ArrayList<Etudiant> recherchez(String criteria,  String aChercher) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String critere;
		String comparaison = " LIKE ";
		ArrayList<Etudiant> returnValue = new ArrayList<Etudiant>();
		
		if(criteria.equals("Groupe") || criteria.equals("Id") || criteria.equals("Utilisateur")) {
			comparaison = " = ";
		}
		
		if(criteria.equals("Id")) {
			critere = "idEtudiant";
		}else if(criteria.equals("Groupe")) {
			critere = "numeroGrp";
		}else if(criteria.equals("Filiere")) {
			critere = "filiere";
		}else if(criteria.equals("Nom")){
			critere = "nomEtudiant";
		}else if(criteria.equals("Prenom")) {
			critere = "prenomEtudiant";
		}else if(criteria.equals("Email")) {
			critere = "adresseMail";
		}else {
			critere = "id_utilisateur";
		}
		
		
		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			if(comparaison.equals(" = ")) {
				ps = con.prepareStatement("SELECT * FROM etudiant WHERE " + critere + comparaison + " ? ");
				ps.setInt(1, Integer.parseInt(aChercher));
			}else {
				ps = con.prepareStatement("SELECT * FROM etudiant WHERE " + critere + comparaison + "'%" + aChercher + "%'");
			}

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Etudiant(rs.getInt("idEtudiant"),
	                     rs.getString("nomEtudiant"),
	                     rs.getString("prenomEtudiant"),
	                     rs.getInt("id_utilisateur"),
	                     rs.getString("adresseMail"),
	                     rs.getInt("numeroGrp"),
	                     rs.getString("filiere")));
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
