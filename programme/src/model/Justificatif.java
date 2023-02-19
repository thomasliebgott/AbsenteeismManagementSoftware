package model;
/**
 * Classe Justificatif  
 * @author Dara Liebgott 
 * @version 1.4 
 *
 */
public class Justificatif {
	/**
	 * id du justificatif 
	 */
	private int idJustificatif;
	/**
	 * motif du justificatif 
	 */
	private String motif;
	/**
	 * chemin pour toruver fichier du justificatif 
	 */
	private String chemin;
	
	/**contructor 
	 * @param idJustificatif
	 * @param motif
	 * @param chemin
	 */
	public Justificatif(int idJustificatif, String motif, String chemin) {
		this.idJustificatif = idJustificatif;
		this.motif = motif;
		this.chemin = chemin;
	}
	
	/**
	 * @return the idJustificatif
	 */
	public int getIdJustificatif() {
		return idJustificatif;
	}
	/**
	 * @param idJustificatif the idJustificatif to set
	 */
	public void setIdJustificatif(int idJustificatif) {
		this.idJustificatif = idJustificatif;
	}
	/**
	 * @return the motif
	 */
	public String getMotif() {
		return motif;
	}
	/**
	 * @param motif the motif to set
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}
	/**
	 * @return the chemin
	 */
	public String getChemin() {
		return chemin;
	}
	/**
	 * @param chemin the chemin to set
	 */
	public void setChemin(String chemin) {
		this.chemin = chemin;
	}
	
	
}
