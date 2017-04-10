package model;

import java.util.ArrayList;

public class Student extends Person {
	private String klasNaam;
	private boolean verwachtAfwezig;
	private String decaanEmail;
	private int studentNummer;
	private String voornaam;
	private String achternaam;
	private String password;
	private String tussenvoegsel;
	private String slbEmail;
	private String studentStatus;
	private String studentStatusToelichting;
	private ArrayList<StudentPresentie> studentPresenties = new ArrayList<StudentPresentie>();
	public Student(
			int studentNummer, 
			String uname, 
			String pword,
			String voornaam,
			String tussenvoegsel,
			String achternaam, 
			String klasNaam, 
			String dEmail, 
			String slbEmail){
		super(uname, pword, voornaam);
		this.setKlasNaam(klasNaam);
		this.studentNummer = studentNummer;
		this.tussenvoegsel = tussenvoegsel;
		this.decaanEmail = dEmail;
		this.slbEmail = slbEmail;
		this.password = pword;
		this.voornaam = voornaam;
		this.achternaam = achternaam;

		/**
		 * Testing:
		 * Wijs jos.reenene toe aan alle studenten.
		 * Wijs een status van "bij-slber" toe aan een student
		 */
		this.slbEmail = "jos.vanreenen@hu.nl";
		this.voegStudentStatusToe("bij-slber", "ziekte");
	}
	public void voegStudentStatusToe(String status, String toelichting){
		this.studentStatus = status;
		this.studentStatusToelichting = toelichting;
	}
	public String getStudentStatusToelichting(){
		return this.studentStatusToelichting;
	}
	public String getStudentStatus(){
		return this.studentStatus;
	}
	/**
	 * Haal student presentie op met lesnummer;
	 */
	public StudentPresentie getPresentieByLes(String lesNummer){
		StudentPresentie presentie = null;
		if(this.studentPresenties.size() > 0){
			for (StudentPresentie studentPresentie : this.studentPresenties){
				if(studentPresentie.getLesNummer().equals(lesNummer)){
					presentie = studentPresentie;
				} else {
					presentie = new StudentPresentie(lesNummer, this);
					this.addPresentie(presentie);
				}
			}
		} else {
			presentie = new StudentPresentie(lesNummer, this);
			this.addPresentie(presentie);
		}

		return presentie;
		
	}
	public void setStudentStatus(String status) {
		this.studentStatus = status;
	}
	public void setStudentStatusToelichting(String toelichting){
		this.studentStatusToelichting = toelichting;
	}

	/**
	 * Voeg nieuwe presentie toe
	 */
	public void addPresentie(StudentPresentie presentie){
		this.studentPresenties.add(presentie);
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

	public String getKlasNaam() {
		return klasNaam;
	}

	public void setKlasNaam(String klasNaam) {
		this.klasNaam = klasNaam;
	}

	@Override
	public String getEmail(){
		return (this.voornaam + "." + this.achternaam).toLowerCase() + "@student.hu.nl";
	}

	@Override
	protected String getPassword() {
		return this.password;
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

	public String getAchternaam() {
		return this.tussenvoegsel + " "+achternaam;
	}

	public String getTussenvoegsel() {
		return tussenvoegsel;
	}

	public void setTussenvoegsel(String tussenvoegsel) {
		this.tussenvoegsel = tussenvoegsel;
	}
}
