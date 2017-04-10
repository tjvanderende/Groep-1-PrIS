package controller;

import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import model.Klas;
import model.Les;
import model.PrIS;
import model.Student;
import server.Conversation;
import server.Handler;

public class DocentController implements Handler {
	private PrIS informationSystem;
	
	public DocentController(PrIS infoSysteem){
		this.informationSystem = infoSysteem;
	}
	
	@Override
	public void handle(Conversation conversation) {
		// TODO Auto-generated method stub
		if (conversation.getRequestedURI().startsWith("/docent")) {
			this.getKlassen(conversation);
		}
	}
	/**
	 * Geef een array terug met klassen
	 * [
	 * {
	 * 	"klasCode": "test",
	 * 	"klasNaam": "S1E",
	 * 	"studenten": [
	 * 		{
	 * 			"precentiePercentage": 30.0,
	 * 			"nummer": 3823883,
	 * 			"voornaam": "Tjibbe",
	 * 			"achternaam": "van der Ende",
	 * 			"email": "tjibbe.vanderende@student.hu.nl"
	 * 		}
	 * 	]
	 * }
	 * ]
	 * @param conversation
	 */
	public void getKlassen(Conversation conversation){
		ArrayList<Klas> klassen = this.informationSystem.getKlassen();
		JsonArrayBuilder klasArrayBuilder = Json.createArrayBuilder();						// Uiteindelijk gaat er een array...
		JsonArrayBuilder studentArrayBuilder = Json.createArrayBuilder();
		if(this.informationSystem.getSystemRole(this.informationSystem.getLoggedInPerson()) == "docent") {
			for (Klas klas : klassen) {
				for (Student student : klas.getStudenten()) {
					JsonObjectBuilder jsonObjectStudent = Json.createObjectBuilder(); // maak het JsonObject voor een klas
					jsonObjectStudent.add("nummer", student.getStudentNummer())
							.add("voornaam", student.getVoornaam())
							.add("studentPrecentie", student.calculatePercentage())
							.add("achternaam", student.getAchternaam())
							.add("studentStatus", informationSystem.getStudentByNummer(student.getStudentNummer()).getStudentStatus())
							.add("email", student.getEmail());

					studentArrayBuilder.add(jsonObjectStudent);

				}
				JsonObjectBuilder jsonObjectKlas = Json.createObjectBuilder(); // maak het JsonObject voor een klas
				jsonObjectKlas.add("klasCode", klas.getKlasCode())
						.add("klasNaam", klas.getKlasNaam())
						.add("studenten", studentArrayBuilder);
				klasArrayBuilder.add(jsonObjectKlas);
			}

			String lJsonOutStr = klasArrayBuilder.build().toString();                                                // maak er een string van
			conversation.sendJSONMessage(lJsonOutStr);
		} else {
			conversation.sendJSONMessage(new Error("Je moet docent zijn om deze pagina te kunnen bekijken", 500).make());
		}
	}
}
