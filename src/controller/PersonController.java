package controller;

import model.Person;
import model.PersonRole;
import model.PrIS;
import model.Student;
import server.Conversation;
import server.Handler;

import javax.json.*;

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
        } else if (conversation.getRequestedURI().startsWith("/person/me")) {
            this.getLoggedInPerson(conversation);
        }
    }

    private void getLoggedInPerson(Conversation conversation){
        JsonObjectBuilder personObject = Json.createObjectBuilder();
        JsonObjectBuilder personRoleObject = Json.createObjectBuilder();
        JsonArrayBuilder extraRoles = Json.createArrayBuilder();
        if(informatieSysteem.isLoggedIn()) {
            Person person = informatieSysteem.getLoggedInPerson();
            personObject.add("rol", informatieSysteem.getSystemRole(person));
            if(informatieSysteem.getSystemRole(person) == "docent"){
                for (PersonRole role: person.getRoles()) {
                    personRoleObject.add("naam", role.getName());
                    extraRoles.add(personRoleObject);
                    personObject.add("subroles", extraRoles);
                }

            }
            conversation.sendJSONMessage(personObject.build().toString());
        } else {
            conversation.sendJSONMessage(new Error("Je moet ingelogd zijn", 500).make());
        }

    }
    private void getStudentByNummer(Conversation conversation){
        int nummer = Integer.parseInt(conversation.getParameter("studentNummer"));
        Student studentByNummer = informatieSysteem.getStudentByNummer(nummer);
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        jsonBuilder.add("email", studentByNummer.getEmail())
                   .add("nummer", studentByNummer.getStudentNummer())
                   .add("voornaam", studentByNummer.getVoornaam()).add("username", studentByNummer.getUsername())
                    .add("presentie", studentByNummer.calculatePercentage())
                    .add("statusToelichting", studentByNummer.getStudentStatusToelichting())
                    .add("status", studentByNummer.getStudentStatus());

        conversation.sendJSONMessage(jsonBuilder.build().toString());
    }
}
