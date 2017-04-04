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
	private Person loggedInPerson;
	public PrIS(){
		
		// verander dit gedeelte om de data uit een andere bron te halen.
		this.dataService = new PrISMockService();

        /**
         * Haal ingelogde persoon op;
         * else helaas geen rooster = geen data door de hele applicatie.
         */

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
		        if(student.getKlasNaam().equals(les.getKlas().getKlasNaam())){
                    personLessen.add(les);
                }
            }

		}
		return personLessen;
	}
	public ArrayList<Les> getLessen(){
		return this.lessen;
	}

	public Student getStudentByNummer(int nummer){
		Student studentByNummer = null;
		for(Les les : this.dataService.loadLessen()){
			ArrayList<Student> studenten = les.getKlas().getStudenten();
            for (Student student: studenten) {
                if(student.getStudentNummer() == nummer){
                    studentByNummer = student;
                }
            }
        }
		return studentByNummer;
	}
	/**
	 * 
	 * @param docentEmail
	 * @return
	 */
	public ArrayList<Student>loadSLBMeldingen(String docentEmail){
		return null;
		
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



	/**
	 * Login afhandelings helpers komen hier.
	 */
	/**
	 * Valideer de input van een gebruiker.
	 * Daarna wordt alle data ingeladen voor de gebruiker.
	 * @return True als succesvol ingelogd.
	 */
	public boolean login(String username, String password){
		this.loggedInPerson = this.validateLoginInfo(username, password);
		if(this.loggedInPerson != null){
			this.lessen = this.loadLessenByPerson(this.loggedInPerson); // laadt nu alle data in met de persoon.
			return true;
		} else {
			return false;
		}
	}
	public Person validateLoginInfo(String username, String password){
		for(Les les : this.dataService.loadLessen()) {
			if(les.getDocent().getUsername().equals(username) && les.getDocent().getPassword().equals(password)){
				return les.getDocent();
			}
			for (Student student : les.getKlas().getStudenten()) {
				if(student.getUsername().equals(username) && student.getPassword().equals(password)){
					return student;
				}
			}
		}
		return null;
	}



	/**
	 * Check of de gebruiker op dit moment is ingelogd.
	 * @return true als er een persoon object is opgeslagen.
	 */
	public boolean isLoggedIn() {
	 	return this.loggedInPerson != null;
	}

	public Person getLoggedInPerson(){
		return this.loggedInPerson;
	}

	/**
	 * Haal systeem rol op van de gebruiker (Let op een docent kan ook SLB'er en decaan als subrollen hebben).
	 * @param person
	 * @return
	 */
	public String getSystemRole(Person person){
		if(person instanceof Docent){
			return "docent";
		} else {
			return "student";
		}

	}

	public void logout() {
		this.loggedInPerson = null;
	}
}


