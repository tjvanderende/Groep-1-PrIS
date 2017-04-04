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
        /**
         * Haal ingelogde persoon op;
         * else helaas geen rooster = geen data door de hele applicatie.
         */

		this.lessen = this.loadLessenByPerson(new Docent("Test", "test123", "Jos", "jos.vanreenen@hu.nl"));
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
	 * Haal via rooster alle klassen op.
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
	
	public Les getLesByNummer(String nummer){
		Les lesByNummer = null;
		for(Les les : this.lessen){
			if(les.getLesNummer().equals(nummer)){
				lesByNummer = les;
			}
		}
		return lesByNummer;
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList<Les> loadLessenByPerson(Person person) {
		ArrayList<Les> personLessen = new ArrayList<>();
		for(Les les : this.dataService.loadLessen()) {
            /**
             * Als het een docent is , gebruik de mail om de lessen op te halen.
             */
		    if(getSystemRole(person) == "docent"){
                if(person.getEmail().equals(les.getDocent().getEmail())){
                    personLessen.add(les);
                }
            } else if (getSystemRole(person) == "student"){
		        Student student = (Student) person;
		        if(person == les.getKlas().getStudentByNummer(student.getStudentNummer())){
                    personLessen.add(les);
                }
            }

		}
		return personLessen;
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

	/**
	 * Zet de presentie van een student.
	 * Het wordt in dit stuk afgehandeld aangezien het dan gemakkelijk gekoppeld kan worden aan de PrISService.
	 *
	 */
	 public void setAfwezigheid(Student student, String lesUuid, boolean afwezig ){
	 	StudentPresentie presentie = student.getPresentieByLes(lesUuid);
	 	presentie.setIsAfwezig(afwezig);
	 	this.dataService.saveStudentPresentie(presentie); // "sla de student op", dit is alleen om te illustreren hoe dit wordt aangeroepen.
	 }
}


