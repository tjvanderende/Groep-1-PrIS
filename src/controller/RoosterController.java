package controller;

import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import model.Les;
import model.PrIS;
import model.Student;
import model.StudentPresentie;
import server.Conversation;
import server.Handler;

public class RoosterController implements Handler {
	private PrIS informatieSysteem;
	public RoosterController(PrIS infoSysteem) {
		informatieSysteem = infoSysteem;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(Conversation conversation) {
		// TODO Auto-generated method stub
		if (conversation.getRequestedURI().startsWith("/rooster/les")){
			this.ophalenLesPresentie(conversation);
		}
		else if (conversation.getRequestedURI().startsWith("/rooster")) {
			this.ophalenLessen(conversation);
		}
		
	}
	private void ophalenLesPresentie(Conversation conversation){
//		Les les = informatieSysteem.getLesByNummer(lesNummer);
		Les les = informatieSysteem.getLessen().get(0);
		JsonArrayBuilder presentieArray = Json.createArrayBuilder();						// Uiteindelijk gaat er een array...

		if(les != null){
			ArrayList<Student> studenten = les.getKlas().getStudenten(); // haal studenten op.
			for (Student student : studenten){
				JsonObjectBuilder jsonObjectStudent = Json.createObjectBuilder();
				JsonObjectBuilder jsonObjectPresentie = Json.createObjectBuilder();
				StudentPresentie presentie = student.getPresentieByLes(les.getLesNummer());
				jsonObjectPresentie.add("aanwezig", presentie.getIsAfwezig())
				   				   .add("verwachtAfwezig", presentie.getIsPresent());
				jsonObjectStudent.add("nummer", student.getStudentNummer())
						         .add("naam", student.getVoornaam())
								 .add("voornaam", student.getEmail())
								 .add("presentie", jsonObjectPresentie);
				presentieArray.add(jsonObjectStudent);
			}
			
		} else {
			/**
			 * Toon fout melding.
			 */
			String lJsonOutStr = "test";
			conversation.sendJSONMessage(lJsonOutStr);
		}
		String lJsonOutStr = presentieArray.build().toString();
		conversation.sendJSONMessage(lJsonOutStr);
	}
	private void ophalenLessen(Conversation conversation){
		ArrayList<Les> lessen = this.informatieSysteem.getLessen();
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
						 .add("cursus", les.getCursusCode())
						 .add("docent", jsonObjectDocent)
						 .add("klas", jsonObjectKlas);
			
			jsonArrayBuilder.add(jsonObjectLes);													//voeg het JsonObject aan het array toe				     

		}
		String lJsonOutStr = jsonArrayBuilder.build().toString();												// maak er een string van
		conversation.sendJSONMessage(lJsonOutStr);	

	}
	
}
