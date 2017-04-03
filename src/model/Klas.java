package model;

import java.util.ArrayList;
import java.util.Collections;

public class Klas {
	private String klasCode;
	private String klasNaam;
	private ArrayList<Student> studenten = new ArrayList<Student>();
	public Klas(String klasCode, String klasNaam){
		this.setKlasCode(klasCode);
		this.setKlasNaam(klasNaam);
	}

	public void addStudent(Student s) {
		this.studenten.add(s);
	}
	public ArrayList<Student> getStudenten(){
		return studenten;
	}
	public String getKlasNaam() {
		return klasNaam;
	}

	public void setKlasNaam(String klasNaam) {
		this.klasNaam = klasNaam;
	}

	public String getKlasCode() {
		return klasCode;
	}

	public void setKlasCode(String klasCode) {
		this.klasCode = klasCode;
	}
	
	public String toString(){
		return this.klasCode + " " + this.klasNaam;
	}
	
	public boolean equals(Object andereObject){
		boolean isGelijk = false;
		if(andereObject instanceof Klas){
			Klas andereKlas = (Klas) andereObject;
			isGelijk =  this.klasCode.equals(andereKlas.klasCode)
			  && this.klasNaam.equals(andereKlas.klasNaam);
		}
		return isGelijk;
		
	}
}
