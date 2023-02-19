package model;
/**
 * Classe quota 
 * @author Dara Liebgott 
 * @version 1.4 
 *
 */
public class Quota {
	/**
	 * id du quota 
	 */
	private int idQuota;
	/**
	 * seuil d'absence 
	 */
	private int seuil;
	/**
	 * penalite 
	 */
	private String penalite;
	/**
	 * @param idQuota
	 * @param idSanction
	 */
	public Quota(int idQuota, int seuil, String penalite) {
		this.idQuota = idQuota;
		this.seuil = seuil;
		this.penalite = penalite;
	}
	/**
	 * @return the idQuota
	 */
	public int getIdQuota() {
		return idQuota;
	}
	/**
	 * @param idQuota the idQuota to set
	 */
	public void setIdQuota(int idQuota) {
		this.idQuota = idQuota;
	}
	
	/**
	 * @return the seuil
	 */
	public int getSeuil() {
		return seuil;
	}
	/**
	 * @param seuil the seuil to set
	 */
	public void setSeuil(int seuil) {
		this.seuil = seuil;
	}
	
	
	
	/**
	 * @return the penalite
	 */
	public String getPenalite() {
		return penalite;
	}
	/**
	 * @param penalite the penalite to set
	 */
	public void setPenalite(String penalite) {
		this.penalite = penalite;
	}
	
	@Override
	public String toString() {
		return "Quota [idQuota=" + idQuota + ", seuil=" + seuil + ", penalite=" + penalite + "]";
	}
	
	
	
}
