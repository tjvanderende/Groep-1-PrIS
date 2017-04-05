package model;

public class Docent extends Person {
	
	private String email;
	private String password;
	public Docent(String uname, String pword, String voornaam, String email){
		super(uname, pword, voornaam);
		this.email = email;
		this.password = pword;
	}
	
	public String toString(){
		return super.toString();
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	protected String getPassword() {
		return this.password;
	}

	public void getExtraRole(){
	}
}
