package model;

/**
 * Classe Groupe 
 * @author thomas Liebgott et Morgan Dara
 * @version 1.4
 **/
public class Groupe {
	/**
	 * numeor du groupe
	 */
	private int numeroGroupe;
	/**
	 * capacité max du groupe 
	 */
	private int capaciteMax;
	/**
	 * filiere du groupe 
	 */
	private String filiere;
	
	/**
	 * Constructor
	 * @param numero du groupe
	 * @param capacite max du groupe
	 * 
	 */
	public Groupe(int numeroGroupe, int capaciteMax, String filiere) {
		this.numeroGroupe = numeroGroupe;
		this.capaciteMax = capaciteMax;
		this.filiere = filiere;
		
	}
	
	
	/**
	 * getteur pour numero du groupe 
	 * @return filiere : getteur pour numero du groupe 
	 */
	public String getFiliere() {
		return filiere;
	}
	/**
	 * setter numero du groupe 
	 * @param filiere setter numero du groupe 
	 */
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}

	/**
	 * getteur pour numero du groupe 
	 * @return numeroGroupe : numero du groupe
	 */
	public int getNumeroGroupe() {
		return numeroGroupe;
	}
	/**
	 * setter numero du groupe 
	 * @param numeroGroupe : numero du groupe 
	 */
	
	public void setNumeroGroupe(int numeroGroupe) {
		this.numeroGroupe = numeroGroupe;
	}
	/**
	 * getter capacite max du groupe
	 * @return capaciteMax : id de l'etudiant 
	 */
	
	public int getCapaciteMax() {
		return capaciteMax;
	}
	/**
	 * setter capacite max du groupe
	 * @param capaciteMax : masse horaire de cours de td  
	 */
	
	public void setCapaciteMax(int capaciteMax) {
		this.capaciteMax = capaciteMax;
	}
	
}
