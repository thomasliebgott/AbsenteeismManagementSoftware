package dao;
import java.sql.*;
import java.util.ArrayList;
import model.Utilisateur;


public class UtilisateurDAO extends ConnectionLogicielDAO {
	/**
	 * Constructor
	 * 
	 */
	public UtilisateurDAO() {
		super();
	}

	/**
	 * Permet d'ajouter l'utilisateur dans la table utilisateur.
	 * Le mode est auto-commit par defaut : chaque insertion est validee
	 * 
	 * @param supplier l'utilisateur a ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(Utilisateur utilisateur) {
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
			ps = con.prepareStatement("INSERT INTO utilisateur (id_utilisateur,mdp, profil) VALUES (utilisateur_auto_incr.nextval, ?, ?)");
			//ps.setInt(1, utilisateur.getIdUtilisateur());
			ps.setString(1, utilisateur.getMdp());
			ps.setString(2, utilisateur.getProfil());
			
			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant d'utilisateur existe d�j�. Ajout impossible !");
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
	 * Permet de modifier l'utilisateur dans la table utilisateur.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param supplier l'utilisateur a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	
	public int update(Utilisateur utilisateur) {
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
			ps = con.prepareStatement("UPDATE utilisateur set mdp = ?, profil = ? WHERE id_utilisateur = ?");
			ps.setString(1, utilisateur.getMdp());
			ps.setString(2, utilisateur.getProfil());
			ps.setInt(3, utilisateur.getIdUtilisateur());

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
	
	public int delete(Utilisateur utilisateur) {
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
			ps = con.prepareStatement("DELETE FROM utilisateur WHERE id_utilisateur = ?");
			ps.setInt(1, utilisateur.getIdUtilisateur());

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-02292"))
				System.out.println("Cet utilisateur ne peut etre supprim� r��ssayer plus tard.");
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
	public Utilisateur get(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Utilisateur returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM utilisateur WHERE id_utilisateur = ?");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Utilisateur(rs.getInt("id_utilisateur"),
									       rs.getString("mdp"),
									       rs.getString("profil"));
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
	
	public int selectCurrentSequenceValue() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int returnValue = -1;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("SELECT MAX(id_utilisateur)+1 FROM utilisateur");
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

	/**
	 * Permet de recuperer un utilisateur a partir de sa reference et de son mot de passe
	 * 
	 * @param reference la reference de l'utilisateur a recuperer
	 * @param mot de passe de l'utilisateur
	 * @return l'utilisateur trouve;
	 * 			null si aucun utilisateur ne correspond a cette reference
	 */
	public Utilisateur get(int id, String mdp) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Utilisateur returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM utilisateur WHERE id_utilisateur = ? AND mdp = ?");
			ps.setInt(1, id);
			ps.setString(2, mdp);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Utilisateur(rs.getInt("id_utilisateur"),
									       rs.getString("mdp"),
									       rs.getString("profil"));
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
	public ArrayList<Utilisateur> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Utilisateur> returnValue = new ArrayList<Utilisateur>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM utilisateur ORDER BY id_utilisateur");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Utilisateur(rs.getInt("id_utilisateur"),
						                     rs.getString("mdp"),
						                     rs.getString("profil")));
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

	public ArrayList<Utilisateur> getList(String profil) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Utilisateur> returnValue = new ArrayList<Utilisateur>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM utilisateur WHERE profil LIKE ? ORDER BY id_utilisateur");
			ps.setString(1, profil);

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Utilisateur(rs.getInt("id_utilisateur"),
						                     rs.getString("mdp"),
						                     rs.getString("profil")));
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
	
	// main permettant de tester la classe (pour des test unitaires)
	public static void main(String[] args) throws SQLException {
		int returnValue;
		UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
		// test du constructeur
		Utilisateur u1 = new Utilisateur(4, "TEST", "Etudiant");
		Utilisateur u2 = new Utilisateur(5, "TESTER", "Enseignant");
		Utilisateur u3 = new Utilisateur(6, "TESTEUR", "Gestionnaire");
		
		// test de la methode add
		returnValue = utilisateurDAO.add(u1);
		System.out.println(returnValue + " utilisateur ajoute");
		returnValue = utilisateurDAO.add(u2);
		System.out.println(returnValue + " utilisateur ajoute");
		returnValue = utilisateurDAO.add(u3);
		System.out.println(returnValue + " utilisateur ajoute");
		System.out.println();
		
		// test de la methode get
		Utilisateur sg = utilisateurDAO.get(1);
		Utilisateur ug = utilisateurDAO.get(2);
		Utilisateur dg = utilisateurDAO.get(3);
		
		// affichage de l'utilisateur recup�r�
		sg.afficher();
		System.out.println();
		ug.afficher();
		dg.afficher();
		
		// test de la methode getList
		ArrayList<Utilisateur> list = utilisateurDAO.getList();
		for (Utilisateur u : list) {
			// appel explicite de la methode toString de la classe Object (a privilegier)
			u.afficher();
		}
		System.out.println();
		/*
		//test de la methode delete
		returnValue = 0;
		if (list.size() > 0) {
			returnValue = utilisateurDAO.delete(list.get(0));
		}
		System.out.println(returnValue + " utilisateur supprime");
		
		//test de la methode delete
		returnValue = 0;
		if (list.size() > 0) {
			returnValue = utilisateurDAO.delete(list.get(1));
		}
		System.out.println(returnValue + " utilisateur supprime");
		
		ArrayList<Utilisateur> liste = utilisateurDAO.getList();
		for (Utilisateur u : liste) {
			// appel explicite de la methode toString de la classe Object (a privilegier)
			u.afficher();
		}
		*/
		System.out.println();
	}
}