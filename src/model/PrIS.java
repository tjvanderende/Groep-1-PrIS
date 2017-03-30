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
	
	public PrIS(){
		// verander dit gedeelte om de data uit een andere bron te halen.
		this.dataService = new PrISMockService();
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
	 * 
	 * @param klasNaam
	 * @return
	 */
	public ArrayList<Les> getLessenByKlas(String klasNaam) {
		return null;
	}
	
	/**
	 * 
	 * @param docentEmail
	 * @return
	 */
	public ArrayList<Les> getLessenByPerson() {
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
	
}


