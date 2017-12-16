package at.jku.se.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.jku.se.session.UserFacade;
import at.jku.se.database.DatabaseConnector;
import at.jku.se.model.User;

@Path("/user")
public class UserController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String login(User user) {

		//check if user für username exists
		
		//check password wenn ja 

		return "test";
	}

	/*
	 * Functions:
	 * 
	 * Anmeleden Registrieren
	 */

}
