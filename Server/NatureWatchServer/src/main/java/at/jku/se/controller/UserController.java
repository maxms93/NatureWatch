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
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postStudentRecord(User student) {

		String result = "Record entered: " + student;

		return Response.status(201).entity(result).build();

	}

	@GET
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User login(User user) {

		// check if user für username exists

		// check if user ist enabled

		// check password wenn ja

		return user;
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
