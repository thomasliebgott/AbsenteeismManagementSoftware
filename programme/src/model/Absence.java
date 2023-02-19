package model;

/**
 * Classe Absence 
 * @author Dara Liebgott 
 * @version 1.4 
 *
 */
public class Absence {
	/**
	 * id de l'absence 
	 */
	private int idAbsence;
	/**
	 * id de l'etudiant 
	 */
	private int idEtudiant;
	/**
	 * id de la seance 
	 */
	private int idSeance;
	/**
	 * id du justificatif 
	 */
	private int idJustificatif;
	/**
	 * justife ou non 
	 */
	private String justifieOuNon;
	/**
	 * determination si on est dans le quota 
	 */
	private String horsQuota;
	
	/**
	 * Constructor 
	 * @param idAbsence
	 * @param idEtudiant
	 * @param idSeance
	 * @param idJustificatif
	 * @param justifieOuNon
	 * @param horsQuota
	 */
	public Absence(int idAbsence, int idEtudiant, int idSeance, int idJustificatif, String justifieOuNon,
			String horsQuota) {
		this.idAbsence = idAbsence;
		this.idEtudiant = idEtudiant;
		this.idSeance = idSeance;
		this.idJustificatif = idJustificatif;
		this.justifieOuNon = justifieOuNon;
		this.horsQuota = horsQuota;
	}

	/**
	 * @return the idAbsence
	 */
	public int getIdAbsence() {
		return idAbsence;
	}

	/**
	 * @param idAbsence the idAbsence to set
	 */
	public void setIdAbsence(int idAbsence) {
		this.idAbsence = idAbsence;
	}

	/**
	 * @return the idEtudiant
	 */
	public int getIdEtudiant() {
		return idEtudiant;
	}

	/**
	 * @param idEtudiant the idEtudiant to set
	 */
	public void setIdEtudiant(int idEtudiant) {
		this.idEtudiant = idEtudiant;
	}

	/**
	 * @return the idSeance
	 */
	public int getIdSeance() {
		return idSeance;
	}

	/**
	 * @param idSeance the idSeance to set
	 */
	public void setIdSeance(int idSeance) {
		this.idSeance = idSeance;
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
	 * @return the justifieOuNon
	 */
	public String getJustifieOuNon() {
		return justifieOuNon;
	}

	/**
	 * @param justifieOuNon the justifieOuNon to set
	 */
	public void setJustifieOuNon(String justifieOuNon) {
		this.justifieOuNon = justifieOuNon;
	}

	/**
	 * @return the horsQuota
	 */
	public String getHorsQuota() {
		return horsQuota;
	}

	/**
	 * @param horsQuota the horsQuota to set
	 */
	public void setHorsQuota(String horsQuota) {
		this.horsQuota = horsQuota;
	}
	
	
	
}
