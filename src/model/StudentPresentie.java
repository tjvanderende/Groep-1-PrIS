package model;

public class StudentPresentie {
	private boolean isPresent;
	private boolean isAanwezig;
	private String lesNummer; 
	public StudentPresentie(String lesNmr, Student student){
		this.setLesNummer(lesNmr);
		this.isAanwezig = true;
	
	}
	
	public boolean getIsPresent() {
		return isPresent;
	}
	public void setIsPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}
	public boolean getIsAanwezig() {
		return isAanwezig;
	}
	public void setIsAanwezig(boolean isAanwezig) {
	}

	public String getLesNummer() {
		return lesNummer;
	}

	public void setLesNummer(String lesNummer) {
		this.lesNummer = lesNummer;
	}

}
