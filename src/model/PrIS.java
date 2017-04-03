package model;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Stop alle models samen in deze implementatie.
 * Wordt gebruikt door de controllers om de data op te halen van het hele systeem.
 * @author tjvan
 * @version 0.1
 * 
 */
public class PrIS {
	private PrISService dataService;
	private ArrayList<Les> lessen = new ArrayList<Les>();
	public PrIS(){
		
		// verander dit gedeelte om de data uit een andere bron te halen.
		this.dataService = new PrISMockService();
		this.lessen = this.loadLessenByPerson();
	}
	/**
	 * Haal een student op
	 * @param studentNmr, het student nummer van de Student die je wilt ophalen.
	 * @return Student klas waar het studentnmr van is, return false als de Student niet bestaat.
	 */
	public Student getStudent(int studentNmr) {
		return null;
	}
	
	/**
	 * 
	 * @param studentNmr, het studentnummer van de Student waar je de presentie van wilt bekijken.
	 * @param lesNmr, de les waarvan je de presentie wilt bekijken.
	 * @param aanwezig True = student is aanwezig , false = student is afwezig.
	 */
	public void setPresentie(int studentNmr, int lesNmr, boolean aanwezig){
		
	}
	
	/**
	 * Haal via rooster alle klassen op.
	 * @param klasNaam
	 * @return
	 */
	public ArrayList<Klas> getKlassen() {
		ArrayList<Klas> klassen = new ArrayList<Klas>();
		for (Les les : this.getLessen()){
			if(!klassen.contains(les.getKlas()))
				klassen.add(les.getKlas());
		}
		return klassen;
	}
	
	/**
	 * 
	 * @param docentEmail
	 * @return
	 */
	public ArrayList<Les> loadLessenByPerson() {
	/*	ArrayList<Les> docentLessen = new ArrayList<Les>();
		for(Les les : this.dataService.loadLessen()) {
			if(docent.test(les.getDocent())){
				docentLessen.add(les);
			}
		}
		return this.dataService.loadLessen();*/
		
		return this.dataService.loadLessen();
	}

	/**
	 * 
	 * @param docentEmail
	 * @return
	 */
	public ArrayList<Student>loadSLBMeldingen(String docentEmail){
		return null;
		
	}
	
	public String getSystemRole(Person person){
		if(person instanceof Docent){
			return "docent";
		} else {
			return "student";
		}
		
	}
	/**
	 * Haal ingelogde gebruiker op (als deze er is).
	 * @return false als er geen ingelogde gebruiker is, anders persoon object.
	 */
	
	public Person getLoggedInPerson(){
		return new Docent("test", "test", "test", "test");
	}
	public ArrayList<Les> getLessen() {
		return lessen;
	}
}


