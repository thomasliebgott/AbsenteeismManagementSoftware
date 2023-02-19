package model;
/**
 * Classe Personne  
 * @author Dara Liebgott 
 * @version 1.4 
 *
 */
public class Personne {
	
	/**
	 * nom de la personne 
	 */
	protected String nom;
	/**
	 * prenom de la personne 
	 */
	protected String prenom;
	/**
	 * id utilisateur de la personne 
	 */
	protected int idUtilisateur;
	
	public Personne() {
		
	}
	/**
	 * Constructor 
	 * @param nom
	 * @param prenom
	 * @param idUtilisateur
	 */
	public Personne(String nom, String prenom, int idUtilisateur) {
		this.nom = nom;
		this.prenom = prenom;
		this.idUtilisateur = idUtilisateur;
	}
	/**
	 * getteur nom
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * setteur nom
	 * @param nouveauNom
	 */
	public void setNom(String nouveauNom) {
		nom = nouveauNom;
	}
	/**
	 * getteur du prenom 
	 * @return
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * setteur du prenom
	 * @param nouveauPrenom
	 */
	public void setPrenom(String nouveauPrenom) {
		prenom = nouveauPrenom;
	}
	/**
	 * id de l'utilisateur 
	 * @return
	 */
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	/**
	 * setteur de l'utilisateur 
	 * @param nouvelId
	 */
	public void setIdUtilisateur(int nouvelId) {
		idUtilisateur = nouvelId;
	}
	/**
	 * affichage des infos 
	 */
	public void afficher() {
		System.out.println("Nom : "+nom +"\nPrenom : "+prenom);
		System.out.println("Identifiant utilisateur : " + idUtilisateur);
	}
}
