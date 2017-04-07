package controller;

import model.*;
import server.Conversation;
import server.Handler;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

/**
 * Created by tjvan on 6-4-2017.
 */
public class MeldingenController implements Handler{
    private PrIS infoSysteem;
    public MeldingenController(PrIS infoSysteem) {
        this.infoSysteem = infoSysteem;
    }


    @Override
    public void handle(Conversation conversation) {
        if(conversation.getRequestedURI().startsWith("/meldingen")){
            this.toonMeldingen(conversation);
        }
    }

    private void toonMeldingen(Conversation conversation) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        if(infoSysteem.isLoggedIn()){
            Person person = infoSysteem.getLoggedInPerson();
            if(infoSysteem.getSystemRole(person).equals("docent")){
                Docent docent = (Docent) person;
                if(person.isRole("decaan") || person.isRole("slber")){
                    for(Les les: infoSysteem.getLessen()){
                        for(Student student : les.getKlas().getStudenten()){
                            builder.add("nummer", student.getStudentNummer());
                            builder.add("studentPercentage", student.calculatePercentage());
                           // builder.add("status", student.getStudentStatus());
                            //builder.add("statusToelichting", student.getStudentStatusToelichting());

                            if(student.getSlbEmail().equals(docent.getEmail()) && student.getStudentStatus().equals("bij-slber")){
                                arrayBuilder.add(builder);
                            } else if (student.getDecaanEmail().equals(docent.getEmail())) {
                                arrayBuilder.add(builder);
                            }
                            conversation.sendJSONMessage(arrayBuilder.build().toString());

                        }
                    }
                } else {
                    conversation.sendJSONMessage(new Error("Je moet een rol als SLB'er of Decaan hebben.", 500).make());
                }

            } else {
                conversation.sendJSONMessage(new Error("Je moet een rol als docent.", 500).make());

            }
        }else {
            conversation.sendJSONMessage(new Error("Je moet ingelogd zijn.", 500).make());
        }

    }
}
