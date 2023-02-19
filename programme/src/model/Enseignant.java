package model;
/**
 * Classe Enseignant  
 * @author Dara Liebgott 
 * @version 1.4 
 *
 */
public class Enseignant extends Personne{
    
	/**
	 * numero de tel du prof
	 */
	private int numTel;
	/**
	 * id de l'enseignant 
	 */
	private int idEnseignant;
	
	
	/**
	 * Constructor
	 * @param nomEnseignant
	 * @param prenomEnseignant
	 * @param idUtilisateur
	 * @param numTel
	 */
    
    public Enseignant(int idEnseignant, String nomEnseignant, String prenomEnseignant, int idUtilisateur, int numTel) {
    	super(nomEnseignant, prenomEnseignant, idUtilisateur);
		this.numTel = numTel;
		this.idEnseignant = idEnseignant;
	}
    
    
	/**
	 * donne le numero de telephone d'un enseignant
	 * @return numTel
	 */
	public int getNumTel() {
		return numTel;
	}
	/**
	 * donne le numero de telephone d'un enseignant
	 * @param numTel donne le numero de telephone d'un enseignant
	 */
	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}

	/**
	 * Getter de idEnseignant
	 * @return idEnseignant
	 */
	public int getIdEnseignant() {
		return idEnseignant;
	}
	
	/**
	 * Setter de idEnseignant
	 * @param nouvelId
	 */
	public void setIdEnseignant(int nouvelId) {
		idEnseignant = nouvelId;
	}
	
	
	/**
	 * Affiche les informations de l'enseignant
	 */
	public void displayEnseignant() {
		super.afficher();
		System.out.println("Numéro de téléphone : " + numTel);
	}

}
