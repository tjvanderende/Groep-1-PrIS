package controller;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import model.*;
import server.Conversation;
import server.Handler;

public class RoosterController implements Handler {
	private PrIS informatieSysteem;
	private String kleur = "blue";
	private boolean isBezig = false;
	public RoosterController(PrIS infoSysteem) {
		informatieSysteem = infoSysteem;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(Conversation conversation) {
		// TODO Auto-generated method stub
        try{
            if (conversation.getRequestedURI().startsWith("/rooster/les/edit")){
                this.editLes(conversation);
            }
            else if (conversation.getRequestedURI().startsWith("/rooster/les")){
                this.ophalenLesPresentie(conversation);
            }
            else if (conversation.getRequestedURI().startsWith("/rooster")) {
                this.ophalenLessen(conversation);
            }
        } catch(NullPointerException exception){
            conversation.sendJSONMessage(new Error("Nullpointer exception!", 500).make());
        }


		
	}

    /**
     * Pas een presentie aan.
     * Kan zowel van student (eigen presentie) als docent (presentie van student) zijn.
     * Logica voor de afhandeling zit in Student.
     * @param conversation
     */
    private void editLes(Conversation conversation) throws NullPointerException{
        JsonObject jsonObject = (JsonObject) conversation.getRequestBodyAsJSON();
        String lesUuid = jsonObject.getString("uuid");
        boolean afwezigheid = jsonObject.getBoolean("afwezigheid");
        int studentNummer = jsonObject.getInt("student");

        if(lesUuid != null){
            Les les = informatieSysteem.getLesByNummer(lesUuid);
            Student student= les.getKlas().getStudentByNummer(studentNummer);
            Person person = informatieSysteem.getLoggedInPerson();
				if(informatieSysteem.getSystemRole(person).equals("student")){
					Student studentCheck = (Student) person;
					if(studentCheck.getStudentNummer() == student.getStudentNummer()){
						informatieSysteem.setPresentie(student, lesUuid, afwezigheid);
						conversation.sendJSONMessage(new Error("Succesvol opgeslagen!", 200).make());

					} else {
						conversation.sendJSONMessage(new Error("Deze student presentie is niet van jouw!", 200).make());

					}
				}else {
					informatieSysteem.setAfwezigheid(student, lesUuid,afwezigheid);
					conversation.sendJSONMessage(new Error("Succesvol opgeslagen voor docent!", 200).make());

				}
                /**
                 * if (currentUserRole == "student" && currentUser.nummer == studentNummer) student.setVerwachtAfwezig(afwezigheid);
                 * else student.presentie
                 */

        }else {
            conversation.sendJSONMessage(new Error("Geef een les UUID mee", 500).make());
        }

    }

    /**
     * Haal de presentie lijsten van de studenten van een klas op.
     * @param conversation
     */
	private void ophalenLesPresentie(Conversation conversation) throws NullPointerException{
		String uuid = conversation.getParameter("uuid");
    	Les les = informatieSysteem.getLesByNummer(uuid);

		JsonArrayBuilder presentieArray = Json.createArrayBuilder();						// Uiteindelijk gaat er een array...

		if(les != null){
			ArrayList<Student> studenten = les.getKlas().getStudenten(); // haal studenten op.
			for (Student student : studenten){
				JsonObjectBuilder jsonObjectStudent = Json.createObjectBuilder();
				JsonObjectBuilder jsonObjectPresentie = Json.createObjectBuilder();
				StudentPresentie presentie = student.getPresentieByLes(les.getLesNummer());
				jsonObjectPresentie.add("aanwezig", presentie.getIsAanwezig())
				   				   .add("verwachtAfwezig", presentie.getIsPresent());
				jsonObjectStudent.add("nummer", student.getStudentNummer())
						         .add("email", student.getEmail())
								 .add("voornaam", student.getVoornaam())
								 .add("achternaam", student.getAchternaam())
								 .add("presentie", jsonObjectPresentie);
				presentieArray.add(jsonObjectStudent);
			}
			
		} else {

			/**
			 * Toon fout melding.
			 */
			conversation.sendJSONMessage(new Error("Niet alle parameters die nodig zijn, zijn meegegeven", 500).make());
		}
		String lJsonOutStr = presentieArray.build().toString();
		conversation.sendJSONMessage(lJsonOutStr);
	}
	private void ophalenLessen(Conversation conversation) throws NullPointerException{
		ArrayList<Les> lessen = this.informatieSysteem.getLessenByPerson();
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();						// Uiteindelijk gaat er een array...

		for(Les les : lessen){
			JsonObjectBuilder jsonObjectLes = Json.createObjectBuilder(); // maak het JsonObject voor een student
			JsonObjectBuilder jsonObjectDocent = Json.createObjectBuilder();
			JsonObjectBuilder jsonObjectKlas = Json.createObjectBuilder();
			
			jsonObjectKlas.add("klascode", les.getKlas().getKlasCode())
						   .add("klasnaam", les.getKlas().getKlasNaam());
			
			jsonObjectDocent.add("email", les.getDocent().getEmail())
							.add("voornaam", les.getDocent().getVoornaam());
			
			jsonObjectLes.add("start",les.getDatum()+ "T"+les.getStartTijd()) // "2017-03-30T11:30";
						 .add("end", les.getDatum()+ "T"+ les.getEindTijd())
						 .add("uuid", les.getLesNummer())
						 .add("title", les.getCursusCode())
						 .add("docent", jsonObjectDocent)
						 .add("klas", jsonObjectKlas);
			if (LocalDate.now().toString().compareTo(les.getDatum()) == 0 && LocalTime.now().toString().substring(0,5).compareTo(les.getStartTijd()) > 0 && LocalTime.now().toString().substring(0,5).compareTo(les.getEindTijd()) < 0){
				isBezig = true;
				kleur = "green";
			}
			jsonObjectLes.add("color" , kleur)
				     .add("isBezig", isBezig);
	
			jsonArrayBuilder.add(jsonObjectLes);													//voeg het JsonObject aan het array toe				     

		}
		String lJsonOutStr = jsonArrayBuilder.build().toString();												// maak er een string van
		conversation.sendJSONMessage(lJsonOutStr);	

	}
	
}
