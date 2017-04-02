package model;

public abstract class Person {
	private String username;
	private String voornaam;
	private String achternaam;
	private String password;
	protected PersonRole roles;
	public Person(String uname, String pword, String voornaam){
		this.setUsername(uname);
		this.password = pword;
		this.voornaam = voornaam;
	}
	
	
	
	protected void login(){
		
	}
	
	protected void logout(){
		
	}
	
	public void isRole(){
		
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



	public String getAchternaam() {
		return achternaam;
	}



	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}
}
