package model;

import java.util.ArrayList;

public abstract class Person {
	private String username;
	private String voornaam;
	private String achternaam;
	private String password;
	protected ArrayList<PersonRole> roles = new ArrayList<>();
	public Person(String uname, String pword, String voornaam){
		this.setUsername(uname);
		this.password = pword;
		this.voornaam = voornaam;
	}
	
	
	
	protected void login(){
		
	}
	
	protected void logout(){
		
	}
	
	public boolean isRole(){
		return true;
	}
	
	public String toString(){
		return this.username + this.password;
	}



	abstract String getEmail();


	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getVoornaam() {
		return voornaam;
	}



	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

}
