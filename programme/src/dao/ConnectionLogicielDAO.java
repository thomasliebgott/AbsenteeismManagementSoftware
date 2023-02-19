package dao;

/**
 * Classe d'acces a la base de donnees
 * 
 * @author ESIGELEC - TIC Department
 * @version 2.0
 * */
public class ConnectionLogicielDAO {
	/**
	 * Parametres de connexion a la base de donnees oracle
	 * URL, LOGIN et PASS sont des constantes
	 */
	final static String URL   = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String LOGIN = "gestionAbsence2";   // remplacer les ********. Exemple BDD1
	final static String PASS  = "system";   // remplacer les ********. Exemple BDD1
	
	/**
	 * Constructor
	 * 
	 */
	public ConnectionLogicielDAO() {
		// chargement du pilote de bases de donnees
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}
	}
}