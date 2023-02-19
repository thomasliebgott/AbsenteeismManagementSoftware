package model;

/**
 * Classe Horaire  
 * @author Dara Liebgott 
 * @version 1.4 
 *
 */
public class Horaire {
	/**
	 * id de l'horaire 
	 */
	private int idHoraire;
	/**
	 * horaire de debut de seance 
	 */
	private int heureDebut;
	/**
	 * horraire de fin de la seance 
	 */
	private int heureFin;
	/**
	 * @param idHoraire
	 * @param heureDebut
	 * @param heureFin
	 */
	public Horaire(int idHoraire, int heureDebut, int heureFin) {
		this.idHoraire = idHoraire;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}
	/**
	 * @return the idHoraire
	 */
	public int getIdHoraire() {
		return idHoraire;
	}
	/**
	 * @param idHoraire the idHoraire to set
	 */
	public void setIdHoraire(int idHoraire) {
		this.idHoraire = idHoraire;
	}
	/**
	 * @return the heureDebut
	 */
	public int getHeureDebut() {
		return heureDebut;
	}
	/**
	 * @param heureDebut the heureDebut to set
	 */
	public void setHeureDebut(int heureDebut) {
		this.heureDebut = heureDebut;
	}
	/**
	 * @return the heureFin
	 */
	public int getHeureFin() {
		return heureFin;
	}
	/**
	 * @param heureFin the heureFin to set
	 */
	public void setHeureFin(int heureFin) {
		this.heureFin = heureFin;
	}
	
	
}
