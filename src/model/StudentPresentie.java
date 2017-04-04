package model;

public class StudentPresentie {
	private boolean isPresent;
	private boolean isAfwezig;
	private String lesNummer; 
	public StudentPresentie(String lesNmr, Student student){
		this.setLesNummer(lesNmr);
	
	}
	
	public boolean getIsPresent() {
		return isPresent;
	}
	public void setIsPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}
	public boolean getIsAfwezig() {
		return isAfwezig;
	}
	public void setIsAfwezig(boolean isAfwezig) {
		this.isAfwezig = isAfwezig;
	}

	public String getLesNummer() {
		return lesNummer;
	}

	public void setLesNummer(String lesNummer) {
		this.lesNummer = lesNummer;
	}
	
}
