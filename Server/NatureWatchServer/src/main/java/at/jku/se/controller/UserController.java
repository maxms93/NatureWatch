package at.jku.se.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.jku.se.session.UserFacade;
import at.jku.se.database.DatabaseConnector;
import at.jku.se.database.MailHandler;
import at.jku.se.model.User;

@Path("/user")
public class UserController {

	@PUT
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public User login(@DefaultValue("null") @QueryParam("email") String email,
					  @DefaultValue("null") @QueryParam("password") String password) {

		DatabaseConnector db = new DatabaseConnector();

		User u = UserFacade.getUser(db.getConnection(), email);

		db.close();
		
		if (u == null) {
			System.out
					.println("User " + email + " doesn't exist!");
			return new User("", "", "", "", "", "", "", false);
		}

		if (!u.getPassword().equals(email)) {
			System.out.println("Password does't match!");
			return u;
		}

		if (!u.isEnabled()) {
			System.out.println("User " + email + " is not enabled!");
			return u;
		}

		return u;
	}

	@POST
	@Path("/create")
	public Response create(@DefaultValue("null") @QueryParam("email") String email,
			  			   @DefaultValue("null") @QueryParam("password") String password,
			  			   @DefaultValue("null") @QueryParam("name") String name) {

		DatabaseConnector db = new DatabaseConnector();
		User u = UserFacade.getUser(db.getConnection(), email);

		if (u != null) {
			db.close();
			System.out.println("User " + u.getUsername() + " already exists!");
			return Response.status(201)
					.entity("User " + u.getUsername() + " already exists!")
					.build();
		}
		
		User newUser = new User(name, password, email, null, null, null, null, false);

		UserFacade.insertUser(db.getConnection(), newUser);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			String filename = "server.properties";
			input = UserController.class.getClassLoader().getResourceAsStream(
					filename);

			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
			}

			prop.load(input);

			System.out.println(prop.getProperty("server"));
			System.out.println(prop.getProperty("port"));

			String link = "http://" + prop.getProperty("server") + ":"
					+ prop.getProperty("port")
					+ "/NatureWatchServer/user/enable/" + email;
			MailHandler.sendMail(email, "Bestätigungsmail", link);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		
		db.close();

		return Response.status(200).entity("User created!").build();
	}

	@GET
	@Path("/enable/{email}")
	public Response enable(@PathParam("email") String email) {

		DatabaseConnector db = new DatabaseConnector();
		
		User u = UserFacade.getUser(db.getConnection(), email);

		if (u == null) {
			db.close();
			System.out.println("User " + email + " doesn't exist!");
			return Response.status(202)
					.entity("User " + email + " doesn't exist!").build();
		}

		UserFacade.enableUser(db.getConnection(), email);

		db.close();
		
		return Response.status(200).entity("User enabled!").build();
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(User updateUser) {

		DatabaseConnector db = new DatabaseConnector();
		
		User u = UserFacade.getUser(db.getConnection(), updateUser.getUsername());

		if (u == null) {
			db.close();
			System.out.println("User " + updateUser.getUsername() + " doesn't exist!");
			return Response.status(202)
					.entity("User " + updateUser.getUsername() + " doesn't exist!").build();
		}
		
		UserFacade.updateUser(db.getConnection(), updateUser);

		db.close();
		
		return Response.status(200).entity("User updated!").build();
	}
	
	
	@PUT
	@Path("/reset/{email}")
	public Response reset(@PathParam("email") String email) {

		DatabaseConnector db = new DatabaseConnector();
		
		User resetUser = UserFacade.getUser(db.getConnection(), email);

		if (resetUser == null) {
			db.close();
			System.out.println("User " + email + " doesn't exist!");
			return Response.status(202)
					.entity("User " + email + " doesn't exist!").build();
		}
		
		Double newPassword = Math.random() * 1000000;
		
		resetUser.setPassword(newPassword.toString());
		
		UserFacade.updateUser(db.getConnection(), resetUser);
		
		String mail = "Ihr Passwort wurde auf " + newPassword + "gesetzt!";

		MailHandler.sendMail(resetUser.getEmail(), "Passwort zurückgesetzt!", mail);
		
		db.close();

		return Response.status(200).entity("User Password reset!").build();
	}

}
