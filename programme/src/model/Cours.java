package model;

import java.util.ArrayList;

/**
 * Classe cours
 * @author thomas Liebgott et Morgan Dara
 * @version 1.4
 * */

public class Cours {
	
	/**
	 * id du cours 
	 */
	private int idCours;
	/**
	 * id de l'enseignant 
	 */
	private int idEnseignant;
	/**
	 * nom du cours 
	 */
	private String nomCours;
	/**
	 * masse Horaire de l'amphi 
	 */
	private int masseHoraireAmphi;
	/**
	 * masseHoraire de TD du cours 
	 */
	private int masseHoraireTD;
	/**
	 * massse horaire en tp du cours 
	 */
	private int masseHoraireTP;
	/**
	 * masse horaire des exam 
	 */
	private int masseHoraireExam;

	/**
	 * Constructor
	 * @param idCours
	 * @param idEnseignant
	 * @param nomCours
	 * @param masseHoraireAmphi
	 * @param masseHoraireTD
	 * @param masseHoraireTP
	 * @param masseHoraireExam
	 */
	public Cours(int idCours, int idEnseignant, String nomCours, int masseHoraireAmphi, int masseHoraireTD,
			int masseHoraireTP, int masseHoraireExam) {
		this.idCours = idCours;
		this.idEnseignant = idEnseignant;
		this.nomCours = nomCours;
		this.masseHoraireAmphi = masseHoraireAmphi;
		this.masseHoraireTD = masseHoraireTD;
		this.masseHoraireTP = masseHoraireTP;
		this.masseHoraireExam = masseHoraireExam;
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
	 * @return the nomCours
	 */
	public String getNomCours() {
		return nomCours;
	}

	/**
	 * @param nomCours the nomCours to set
	 */
	public void setNomCours(String nomCours) {
		this.nomCours = nomCours;
	}

	/**
	 * @return the masseHoraireAmphi
	 */
	public int getMasseHoraireAmphi() {
		return masseHoraireAmphi;
	}

	/**
	 * @param masseHoraireAmphi the masseHoraireAmphi to set
	 */
	public void setMasseHoraireAmphi(int masseHoraireAmphi) {
		this.masseHoraireAmphi = masseHoraireAmphi;
	}

	/**
	 * @return the masseHoraireTD
	 */
	public int getMasseHoraireTD() {
		return masseHoraireTD;
	}

	/**
	 * @param masseHoraireTD the masseHoraireTD to set
	 */
	public void setMasseHoraireTD(int masseHoraireTD) {
		this.masseHoraireTD = masseHoraireTD;
	}

	/**
	 * @return the masseHoraireTP
	 */
	public int getMasseHoraireTP() {
		return masseHoraireTP;
	}

	/**
	 * @param masseHoraireTP the masseHoraireTP to set
	 */
	public void setMasseHoraireTP(int masseHoraireTP) {
		this.masseHoraireTP = masseHoraireTP;
	}

	/**
	 * @return the masseHoraireExam
	 */
	public int getMasseHoraireExam() {
		return masseHoraireExam;
	}

	/**
	 * @param masseHoraireExam the masseHoraireExam to set
	 */
	public void setMasseHoraireExam(int masseHoraireExam) {
		this.masseHoraireExam = masseHoraireExam;
	}
	
	
	
}
