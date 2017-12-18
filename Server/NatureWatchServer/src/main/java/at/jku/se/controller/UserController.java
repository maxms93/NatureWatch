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
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User login(User user) {

		DatabaseConnector db = new DatabaseConnector();

		User u = UserFacade.getUser(db.getConnection(), user.getUsername());

		if (u == null) {
			System.out.println("User " + user.getUsername() + " doesn't exist!");
			return new User("", "", "", "", "", "", "", false);
		}

		if (!u.getPassword().equals(user.getPassword())) {
			System.out.println("Password does't match!");
			return u;
		}

		if (!u.isEnabled()) {
			System.out.println("User " + u.getUsername() + " is not enabled!");
			return u;
		}

		return u;
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(User newUsser) {

		DatabaseConnector db = new DatabaseConnector();
		String output = "User created!";
		User u = UserFacade.getUser(db.getConnection(), newUsser.getUsername());

		if (u != null) {
			output = "User " + u.getUsername() + " already exists!";
			System.out.println(output);
			return Response.status(200).entity(output).build();
		}
		
		// insert user
		
		// mail schicken


		return Response.status(200).entity(output).build();
	}

	/*
	 * Functions:
	 * 
	 * Registrieren (inserten in die datenbank als unable, senden der
	 * bestätigungsmail mit link auf einen restservice)
	 * 
	 * empfangen der Bestätigungsmail und freischalten des Users
	 * 
	 * Ändern der User Daten
	 */

}
