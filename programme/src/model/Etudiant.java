package model;

/**
 * Classe Etudiant  
 * @author Dara Liebgott 
 * @version 1.4 
 *
 */
public class Etudiant extends Personne{
	
	/**
	 * filliere de l'etudiant 
	 */
	private String filiere;
	/**
	 * adresse mail de l'étudiant 
	 */
	private String adresseMail; 
	/**
	 * id de l'etudiant 
	 */
	private int idEtudiant;
	/**
	 * id de groupe de l'etudiant 
	 */
	private int idGroupe;
	
	
	/**
	 * Constructeur de Etudiant prenant 7 paramètres
	 * @param idEtudiant
	 * @param nom
	 * @param prenom
	 * @param utilisateur
	 * @param adresseMail
	 * @param groupe
	 * @param filiere
	 */
	
	public Etudiant(int idEtudiant, String nom, String prenom, int idUtilisateur, String adresseMail, int idGroupe, String filiere) {
		super(nom, prenom, idUtilisateur);
		this.idEtudiant = idEtudiant;
		this.adresseMail = adresseMail;
		this.idGroupe = idGroupe;
		this.filiere = filiere;
	}
	
	
	/**
	 * filiere etudiant 
	 * @return filiere
	 */
	public String getFiliere() {
		return filiere;
	}
	/**
	 * filiere etudiant 
	 * @param filiere filiere etudiant 
	 */
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	
	/**
	 * adresse mail de l'etudiant 
	 * @return adresseMail
	 */
	public String getAdresseMail() {
		return adresseMail;
	}
	/**
	 * adresse mail de l'etudiant 
	 * @param adresseMail adresse mail de l'etudiant 
	 */
	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}
	
	
	/**
	 * Getter de l'identifiant de l'étudiant dans la base de données 
	 */	
	public int getIdEtudiant() {
		return idEtudiant;
	}
	
	/**
	 * Setter de idEtudiant
	 * @param nouvelId
	 */
	public void setIdEtudiant(int nouvelId) {
		idEtudiant = nouvelId;
	}
	
	
	public int getIdGroupe() {
		return idGroupe;
	}
	
	public void setIdGroup(int nouvelId) {
		idGroupe = nouvelId;
	}
	
	/*
	 * Affiche les informations de l'utilisateur
	 */
	public void displayEtudiant() {
		super.afficher();
		System.out.println("Filiere : "+ filiere + "\n" + "Adresse Mail : " + adresseMail);
	}
}
