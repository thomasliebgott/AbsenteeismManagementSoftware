package model;
/**
 * Classe Seance  
 * @author Dara Liebgott 
 * @version 1.4 
 *
 */
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Seance {
	private int idSeance;
	private String typeSeance;
	private int numSession;
	private int numeroGrp;
	private int idEnseignant;
	private int idCours;
	private int idHoraire;
	private Date dateSeance;
	private int idSemaine;
	private String jour;
	/**
	 * @param idSeance
	 * @param typeSeance
	 * @param numSession
	 * @param numeroGrp
	 * @param idEnseignant
	 * @param idCours
	 * @param idHoraire
	 * @param dateSeance
	 * @param idSemaine
	 */
	public Seance(int idSeance, String typeSeance, int numSession, int numeroGrp, int idEnseignant, int idCours,
			int idHoraire, Date dateSeance, int idSemaine) {
		this.idSeance = idSeance;
		this.typeSeance = typeSeance;
		this.numSession = numSession;
		this.numeroGrp = numeroGrp;
		this.idEnseignant = idEnseignant;
		this.idCours = idCours;
		this.idHoraire = idHoraire;
		this.dateSeance = dateSeance;
		this.idSemaine = idSemaine;
	}
	
	public Seance(int idSeance, String typeSeance, int numSession, int numeroGrp, int idEnseignant, int idCours,
			int idHoraire, String jour, int idSemaine) {
		this.idSeance = idSeance;
		this.typeSeance = typeSeance;
		this.numSession = numSession;
		this.numeroGrp = numeroGrp;
		this.idEnseignant = idEnseignant;
		this.idCours = idCours;
		this.idHoraire = idHoraire;
		this.jour = jour;
		this.idSemaine = idSemaine;
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
	 * @return the typeSeance
	 */
	public String getTypeSeance() {
		return typeSeance;
	}
	/**
	 * @param typeSeance the typeSeance to set
	 */
	public void setTypeSeance(String typeSeance) {
		this.typeSeance = typeSeance;
	}
	/**
	 * @return the numSession
	 */
	public int getNumSession() {
		return numSession;
	}
	/**
	 * @param numSession the numSession to set
	 */
	public void setNumSession(int numSession) {
		this.numSession = numSession;
	}
	/**
	 * @return the numeroGrp
	 */
	public int getNumeroGrp() {
		return numeroGrp;
	}
	/**
	 * @param numeroGrp the numeroGrp to set
	 */
	public void setNumeroGrp(int numeroGrp) {
		this.numeroGrp = numeroGrp;
	}
	/**
	 * @return the idEnseignant
	 */
	public int getIdEnseignant() {
		return idEnseignant;
	}
	/**
	 * @param idEnseignant the idEnseignant to set
	 */
	public void setIdEnseignant(int idEnseignant) {
		this.idEnseignant = idEnseignant;
	}
	/**
	 * @return the idCours
	 */
	public int getIdCours() {
		return idCours;
	}
	/**
	 * @param idCours the idCours to set
	 */
	public void setIdCours(int idCours) {
		this.idCours = idCours;
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
	 * @return the dateSeance
	 */
	public Date getDateSeance() {
		return dateSeance;
	}
	/**
	 * @param dateSeance the dateSeance to set
	 */
	public void setDateSeance(Date dateSeance) {
		this.dateSeance = dateSeance;
	}
	/**
	 * @return the idSemaine
	 */
	public int getIdSemaine() {
		return idSemaine;
	}
	/**
	 * @param idSemaine the idSemaine to set
	 */
	public void setIdSemaine(int idSemaine) {
		this.idSemaine = idSemaine;
	}

	/**
	 * @return the jour
	 */
	public String getJour() {
		return jour;
	}

	/**
	 * @param jour the jour to set
	 */
	public void setJour(String jour) {
		this.jour = jour;
	}

	@Override
	public String toString() {
		return "Seance [idSeance=" + idSeance + ", typeSeance=" + typeSeance + ", numSession=" + numSession
				+ ", numeroGrp=" + numeroGrp + ", idEnseignant=" + idEnseignant + ", idCours=" + idCours
				+ ", idHoraire=" + idHoraire + ", dateSeance=" + dateSeance + ", idSemaine=" + idSemaine + ", jour="
				+ jour + "]";
	}
	
	public String[] myToString(String nomCours, String nomEnseignant) {
		return new String[] {nomCours, typeSeance, "Enseignant : " + nomEnseignant,"Date : " + dateSeance.toString()} ;
	}
	
	public String[] myToStringD(String nomCours, String nomEnseignant) {
		return new String[] {nomCours, typeSeance, "Enseignant : " + nomEnseignant} ;
	}
	
	public String myToStringT(String nomCours, String nomEnseignant) {
		return nomCours + "\n" + typeSeance + "\nEnseignant : " + nomEnseignant ;
	}
	
	public String myToStringT(String nomCours) {
		return nomCours + "  :  " + typeSeance ;
	}
	
	
}
