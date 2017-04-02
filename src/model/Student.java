package model;

import java.util.ArrayList;

public class Student extends Person {
	private String klasNaam;
	private boolean verwachtAfwezig;
	private String decaanEmail;
	private int studentNummer;
	private String voornaam;
	private String achternaam;
	private String slbEmail;
	private ArrayList<StudentPresentie> studentPresentie;
	public Student(
			int studentNummer, 
			String uname, 
			String pword,
			String voornaam, 
			String achternaam, 
			String klasNaam, 
			String dEmail, 
			String slbEmail){
		super(uname, pword, voornaam);
		this.setKlasNaam(klasNaam);
		this.decaanEmail = dEmail;
		this.slbEmail = slbEmail;
		
		this.voornaam = voornaam;
		this.achternaam = achternaam;
	}
	
	/**
	 * ---------------------
	 * Getter en setters 
	 * ---------------------
	 */
	public String getDecaanEmail() {
		return decaanEmail;
	}

	public void setDecaanEmail(String decaanEmail) {
		this.decaanEmail = decaanEmail;
	}

	public String getSlbEmail() {
		return slbEmail;
	}

	public void setSlbEmail(String slbEmail) {
		this.slbEmail = slbEmail;
	}

	public boolean isVerwachtAfwezig() {
		return verwachtAfwezig;
	}

	public void setVerwachtAfwezig(boolean verwachtAfwezig) {
		this.verwachtAfwezig = verwachtAfwezig;
	}

	public String getKlasNaam() {
		return klasNaam;
	}

	public void setKlasNaam(String klasNaam) {
		this.klasNaam = klasNaam;
	}


	public String getEmail(){
		return (this.voornaam + "." + this.achternaam).toLowerCase() + "@student.hu.nl";
	}
	

	public int getStudentNummer() {
		return studentNummer;
	}

	public void setStudentNummer(int studentNummer) {
		this.studentNummer = studentNummer;
	}
	
	public double calculatePercentage(){
		return 30.0;
	}
	
}
