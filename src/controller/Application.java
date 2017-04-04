package controller;

import java.io.File;

import model.PrIS;
import server.JSONFileServer;
 
public class Application {

	/**
	 * Deze klasse is het startpunt voor de applicatie. Hierin maak je een server 
	 * op een bepaalde poort (bijv. 8888). Daarna is er een PrIS-object gemaakt. Dit
	 * object fungeert als toegangspunt van het domeinmodel. Hiervandaan kan alle
	 * informatie bereikt worden.
	 * 
	 * Om het domeinmodel en de Polymer-GUI aan elkaar te koppelen zijn diverse controllers
	 * gemaakt. Er zijn meerdere controllers om het overzichtelijk te houden. Je mag zoveel
	 * controller-klassen maken als je nodig denkt te hebben. Elke controller krijgt een
	 * koppeling naar het PrIS-object om benodigde informatie op te halen.
	 * 
	 * Je moet wel elke URL die vanaf Polymer aangeroepen kan worden registreren! Dat is
	 * hieronder gedaan voor een drietal URLs. Je geeft daarbij ook aan welke controller
	 * de URL moet afhandelen.
	 * 
	 * Als je alle URLs hebt geregistreerd kun je de server starten en de applicatie in de
	 * browser opvragen! Zie ook de controller-klassen voor een voorbeeld!
	 * 
	 */
	public static void main(String[] args) {
		JSONFileServer server = new JSONFileServer(new File("app"), 8888);
		
		PrIS infoSysteem = new PrIS();
		
		RoosterController roosterController = new RoosterController(infoSysteem);
		DocentController docentController = new DocentController(infoSysteem);
		LoginController loginController = new LoginController(infoSysteem);
		PersonController personController = new PersonController(infoSysteem);

		server.registerHandler("/rooster", roosterController);
		server.registerHandler("/rooster/les", roosterController);
		server.registerHandler("/rooster/les/edit", roosterController);

		/**
		 * Haal klas presenties op.
		 */
		server.registerHandler("/docent", docentController);

		/**
		 * Loguit / login routes.
		 */
		server.registerHandler("/login", loginController);
		server.registerHandler("/logout", loginController);


		/**
		 * Person routes
		 */
		server.registerHandler("/student", personController);
		//server.registerHandler("/login", loginController);
		server.start();
	}
}