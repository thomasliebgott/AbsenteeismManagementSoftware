package model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FonctionsDates {
	public FonctionsDates() {
		super();
	}
	
	public static Date CalendarToDate(Calendar calendar) {
		
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		
		return Date.valueOf(formater.format(calendar.getTimeInMillis()));
	}

	public static Date getDateFinSession(Date debutSession, int nombreSemaine) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(debutSession);
		calendar.add(Calendar.WEEK_OF_YEAR, nombreSemaine);
		 
		return CalendarToDate(calendar);
	}
	
	public static ArrayList<Date> getListeDatesSession(Date debutSession, int nombreSemaine){
		ArrayList<Date> listeDate = new ArrayList<Date>();
		Date finSession = getDateFinSession(debutSession, nombreSemaine);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(debutSession);
		
		while (calendar.getTime().before(finSession)) {
		    if(getJourSemaine(CalendarToDate(calendar)).equals("Samedi") || getJourSemaine(CalendarToDate(calendar)).equals("Dimanche"));
		    	else listeDate.add(CalendarToDate(calendar));
		    
		    calendar.add(Calendar.DATE, 1);
		}
		
		return listeDate;
	}
	
	public static ArrayList<Date> getListeDatesSession(Date debutSession, Date finSession){
		ArrayList<Date> listeDate = new ArrayList<Date>();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(debutSession);
		
		while (calendar.getTime().before(finSession)) {
		    if(getJourSemaine(CalendarToDate(calendar)).equals("Samedi") || getJourSemaine(CalendarToDate(calendar)).equals("Dimanche"));
		    	else listeDate.add(CalendarToDate(calendar));
		    
		    calendar.add(Calendar.DATE, 1);
		}
		
		return listeDate;
	}
	
	public static String getJourSemaine(Date jour) {
		Calendar c = Calendar.getInstance();
		c.setTime(jour);
		String jourSemaine;
		
		switch(c.get(Calendar.DAY_OF_WEEK)) {
		
		case 1 : jourSemaine = "Dimanche";
				 break;
		case 2 : jourSemaine = "Lundi";
				 break;
		case 3 : jourSemaine = "Mardi";
				 break;
		case 4 : jourSemaine = "Mercredi";
				 break;
		case 5 : jourSemaine = "Jeudi";
				 break;
		case 6 : jourSemaine = "Vendredi";
				 break;
		case 7 : jourSemaine = "Samedi";
				  break;
		default : jourSemaine = null;
		}
		//System.out.println(jourSemaine);
		
		return jourSemaine;
	}
	
	public Date dateJavaToDateSql(java.util.Date date) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		return Date.valueOf(formater.format(date));
	}
	
	public static int jourToInt(String jour) {
		int j = 0;
		if(jour.equals("Lundi")) j = 1;
		else if(jour.equals("Mardi")) j = 2;
		else if(jour.equals("Mercredi")) j = 3;
		else if(jour.equals("Jeudi")) j = 4;
		else if(jour.equals("Vendredi")) j = 5;
		else if(jour.equals("Samedi")) j = 6;
		else j = 7;
		return j;
	}
	
	public static int getNombreSemaines(Date debut, Date fin) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(debut);
		int nombreSemaines = 1;
		int semaineActuelle = calendar.get(Calendar.WEEK_OF_YEAR);
		while (calendar.getTime().before(fin)) {
		    calendar.add(Calendar.DATE, 1);
		    if(semaineActuelle!=calendar.get(Calendar.WEEK_OF_YEAR)) {
		    	nombreSemaines++;
		    	semaineActuelle = calendar.get(Calendar.WEEK_OF_YEAR);
		    }
		}
		//System.out.println(nombreSemaines);
		return nombreSemaines;
	}
	
	public static int getNumeroSemaines(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
}
