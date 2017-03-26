package controller;

import java.io.File;

import controller.memory.*;
import server.Handler;
import server.JSONFileServer;

public class Application {
	public static void main(String[] args) {
		JSONFileServer server = new JSONFileServer(new File("webapp/app"), 8888);
		
		Handler personController = new PersonController();
		server.registerHandler("/me", personController);

		server.start();
	}
}
