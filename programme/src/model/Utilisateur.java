package model;
/**
 * Classe Utilisateur 
 * @author Dara Liebgott 
 * @version 1.4 
 *
 */
public class Utilisateur {
	
	private int idUtilisateur; 
	
	private String mdp; 
	
	private String profil; 
	
	/**
	 * constructor 
	 * @param idUtilisateur
	 * @param mdp
	 * @param profil
	 */
	
	public Utilisateur(int idUtilisateur, String mdp, String profil) {
		this.idUtilisateur = idUtilisateur;
		this.mdp = mdp;
		this.profil = profil;
	}
	/**
	 * Identifiant de l'utilisateur
	 * @return
	 */
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	/**
	 * Identifiant de l'utilisateur
	 * @param nouvelId Identifiant de l'utilisateur
	 */
	public void setIdUtilisateur(int nouvelId) {
		idUtilisateur = nouvelId;
	}
	/**
	 * Mot de passe de l'utilisateur
	 * @return
	 */
	public String getMdp() {
		return mdp;
	}
	/**
	 * Mot de passe de l'utilisateur
	 * @param nouveauMdp Mot de passe de l'utilisateur
	 */
	public void setMdp(String nouveauMdp) {
		mdp = nouveauMdp;
	}
	/**
	 * profil : Etudiant, Enseignant ou Gestionnaire
	 * @return
	 */
	public String getProfil() {
		return profil;
	}
	/**
	 * profil : Etudiant, Enseignant ou Gestionnaire
	 * @param nouveauProfil profil : Etudiant, Enseignant ou Gestionnaire
	 */
	public void setProfil(String nouveauProfil) {
		if(nouveauProfil.equals("Etudiant") || nouveauProfil.equals("Enseignant") || nouveauProfil.equals("Gestionnaire"))
			profil = nouveauProfil;
	}
	/**
	 * affiche l'id le mdp et le profil de l'utilisateur 
	 */
	public void afficher() {
		System.out.println("Identifiant : " + idUtilisateur + "\nMot de passe : " + mdp + "\nProfil : " + profil);
	}
}
