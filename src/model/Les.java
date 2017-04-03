package model;

public class Les {
	private String datum, startTijd, eindTijd,lokaal, cursusCode;
	private Docent docent;
	private Klas klas;
	private int lesNummer;

	/**
	 * ------------------
	 * Getter en setters.
	 * ------------------
	 */
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getStartTijd() {
		return startTijd;
	}
	public void setStartTijd(String startTijd) {
		this.startTijd = startTijd;
	}
	public String getEindTijd() {
		return eindTijd;
	}
	public void setEindTijd(String eindTijd) {
		this.eindTijd = eindTijd;
	}
	public String getLokaal() {
		return lokaal;
	}
	public void setLokaal(String lokaal) {
		this.lokaal = lokaal;
	}
	public String getCursusCode() {
		return cursusCode;
	}
	public void setCursusCode(String cursusCode) {
		this.cursusCode = cursusCode;
	}
	public int getLesNummer() {
		return lesNummer;
	}
	public void setLesNummer(int lesNummer) {
		this.lesNummer = lesNummer;
	}
	
	/**
	 * 
	 */
	public Les(String dtm, String start, String eind, String cursusCode, Klas klas, Docent docent){
		this.datum = dtm;
		this.startTijd = start;
		this.eindTijd = eind;
		this.cursusCode = cursusCode;
		this.setKlas(klas);
		this.setDocent(docent);
	}
	public Docent getDocent() {
		return docent;
	}
	public void setDocent(Docent docent) {
		this.docent = docent;
	}
	public Klas getKlas() {
		return klas;
	}
	public void setKlas(Klas klas) {
		this.klas = klas;
	}
	
	
	public String toString(){
		return this.datum + " " + this.startTijd + " " + this.eindTijd + " klas: " + this.klas.toString()
		+ " docent: " + this.docent.toString();
	}
	
	
}
