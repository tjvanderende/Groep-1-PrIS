package model;

public class PersonRole {
	private String name;

	public PersonRole(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public boolean equals(Object anderObject){
		boolean isGelijk = false;
		if(anderObject instanceof PersonRole){
			PersonRole anderPersonRole = (PersonRole) anderObject;
			if(anderPersonRole.name.equals(this.name)){
				isGelijk = true;
			}
		}
		return isGelijk;
	}

}
