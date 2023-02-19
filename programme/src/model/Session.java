package model;
/**
 * Classe Session 
 * @author Dara Liebgott 
 * @version 1.4 
 *
 */
import java.sql.Date;

public class Session {
	private int numSession;
	private Date dateDebutSession;
	private Date dateFinSession;
	
	public Session(int numSession, Date dateDebutSession, Date dateFinSession) {
		this.numSession = numSession;
		this.dateDebutSession = dateDebutSession;
		this.dateFinSession = dateFinSession;
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
	 * @return the dateDebutSession
	 */
	public Date getDateDebutSession() {
		return dateDebutSession;
	}

	/**
	 * @param dateDebutSession the dateDebutSession to set
	 */
	public void setDateDebutSession(Date dateDebutSession) {
		this.dateDebutSession = dateDebutSession;
	}

	/**
	 * @return the dateFinSession
	 */
	public Date getDateFinSession() {
		return dateFinSession;
	}

	/**
	 * @param dateFinSession the dateFinSession to set
	 */
	public void setDateFinSession(Date dateFinSession) {
		this.dateFinSession = dateFinSession;
	}

	
	
}
