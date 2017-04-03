package controller;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class Error {
    private String message;
    private int status;
    public Error(String message, int status){
        this.message = message;
        this.status = status;
    }

    public String make(){
        JsonObjectBuilder error = Json.createObjectBuilder();
        error.add("message", this.message);
        error.add("status", this.status);

        return error.build().toString();
    }

}
