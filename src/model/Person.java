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
		roles.add(new PersonRole("decaan"));
		roles.add(new PersonRole("slb"));
	}
	

	protected void login(){
		
	}
	
	protected void logout(){
		
	}
	
	public boolean isRole(String roleName){
		boolean rolEqual = false;
		for (PersonRole presentie: roles) {
			if(presentie.getName().equals(roleName)){
				rolEqual = true;
			}
		}
		return rolEqual;
	}

	public String toString(){
		return this.username + this.password;
	}



	protected abstract String getEmail();
	protected abstract String getPassword();

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
