package controller.memory;

import controller.IPerson;
import server.Conversation;
import server.Handler;

/**
 * Implementeer algemene functies voor de modellen:
 * - Person
 * 		- Student
 * 		- Decaan
 * 		- Docent
*  Gegevens worden uit het geheugen gehaald op dit moment.
 * @author tjvan
 *
 */
public class PersonController implements IPerson, Handler {

	@Override
	public void getLoggedInUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handle(Conversation conversation) {
		if (conversation.getRequestedURI().startsWith("/me")) {
		} 
		else if (conversation.getRequestedURI().startsWith("/student")){
			
		}
	}

}
