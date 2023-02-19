package model;
/**
 * Classe Sanction 
 * @author Dara Liebgott 
 * @version 1.4 
 *
 */
public class Sanction {
	
	/**
	 * id de la sanction
	 */
	private int idSanction;
	/**
	 * type de sanction
	 */
	private String typeSanction;
	/**
	 * id de l'etudiant 
	 */
	private int idEtudiant;
	
	/**
	 * constructor 
	 * @param idSanction
	 * @param typeSanction
	 * @param idEtudiant
	 */
	public Sanction(int idSanction, String typeSanction, int idEtudiant) {
		this.idSanction = idSanction;
		this.typeSanction = typeSanction;
		this.idEtudiant = idEtudiant;
	}

	/**
	 * @return the idSanction
	 */
	public int getIdSanction() {
		return idSanction;
	}

	/**
	 * @param idSanction the idSanction to set
	 */
	public void setIdSanction(int idSanction) {
		this.idSanction = idSanction;
	}

	/**
	 * @return the typeSanction
	 */
	public String getTypeSanction() {
		return typeSanction;
	}

	/**
	 * @param typeSanction the typeSanction to set
	 */
	public void setTypeSanction(String typeSanction) {
		this.typeSanction = typeSanction;
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
}
