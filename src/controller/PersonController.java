package controller;

import model.PrIS;
import model.Student;
import server.Conversation;
import server.Handler;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

/**
 * Created by tjvan on 4-4-2017.
 */
public class PersonController implements Handler {
    PrIS informatieSysteem;
    public PersonController (PrIS informatieSysteem){
        this.informatieSysteem = informatieSysteem;
    }
    @Override
    public void handle(Conversation conversation) {
        if(conversation.getRequestedURI().startsWith("/student")){
            this.getStudentByNummer(conversation);
        }
    }

    private void getLoggedInPerson(Conversation conversation){

    }
    private void getStudentByNummer(Conversation conversation){
        int nummer = Integer.parseInt(conversation.getParameter("studentNummer"));
        Student studentByNummer = informatieSysteem.getStudentByNummer(nummer);
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        jsonBuilder.add("email", studentByNummer.getEmail())
                   .add("nummer", studentByNummer.getStudentNummer())
                   .add("voornaam", studentByNummer.getVoornaam()).add("username", studentByNummer.getUsername());

        conversation.sendJSONMessage(jsonBuilder.build().toString());
    }
}
