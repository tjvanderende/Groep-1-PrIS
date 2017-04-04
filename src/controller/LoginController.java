package controller;

import model.Person;
import model.PrIS;
import model.Student;
import server.Conversation;
import server.Handler;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Created by tjvan on 4-4-2017.
 */
public class LoginController implements Handler {
    private PrIS informatieSysteem;
    public LoginController(PrIS informatieSysteem) {
        this.informatieSysteem = informatieSysteem;
    }
    @Override
    public void handle(Conversation conversation) {
        if(conversation.getRequestedURI().startsWith("/login")){
            this.login(conversation);
        } else if (conversation.getRequestedURI().startsWith("/logout")){
            this.logout(conversation);
        }
    }

    private void login(Conversation conversation) {
        JsonObject jsonObject = (JsonObject) conversation.getRequestBodyAsJSON();
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        if(informatieSysteem.login(username, password)){
            JsonObjectBuilder userObject = Json.createObjectBuilder();
            Person person = informatieSysteem.getLoggedInPerson();
            if(informatieSysteem.getSystemRole(person).equals("student")){
                Student student = (Student) person;
                userObject.add("nummer", student.getStudentNummer());
            }
            userObject.add("role", informatieSysteem.getSystemRole(person));
            conversation.sendJSONMessage(userObject.build().toString());
        } else {
            conversation.sendJSONMessage(new Error("De ingevulde gebruikersnaam of wachtwoord is foutief.", 500).make());
        }


    }
    private void logout(Conversation conversation){
        if(informatieSysteem.isLoggedIn()){
            informatieSysteem.logout();
            conversation.sendJSONMessage(new Error("Je bent succesvol uitgelogd!", 200).make());
        } else {
            conversation.sendJSONMessage(new Error("Je moet ingelogd zijn om in te loggen!", 500).make());
        }
    }
}
