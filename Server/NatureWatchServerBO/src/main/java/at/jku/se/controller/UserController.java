package at.jku.se.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.jku.se.session.UserFacade;
import at.jku.se.database.DatabaseConnector;
import at.jku.se.database.MailHandler;
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
			System.out
					.println("User " + user.getUsername() + " doesn't exist!");
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
	public Response create(User newUser) {

		DatabaseConnector db = new DatabaseConnector();
		User u = UserFacade.getUser(db.getConnection(), newUser.getUsername());

		if (u != null) {
			System.out.println("User " + u.getUsername() + " already exists!");
			return Response.status(201)
					.entity("User " + u.getUsername() + " already exists!")
					.build();
		}

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
					+ "/NatureWatchServer/user/enable/" + newUser.getUsername();
			MailHandler.sendMail(newUser.getEmail(), "Bestätigungsmail", link);

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

		return Response.status(200).entity("User created!").build();
	}

	@GET
	@Path("/enable/{usernmame}")
	public Response enable(@PathParam("usernmame") String username) {

		DatabaseConnector db = new DatabaseConnector();
		
		User u = UserFacade.getUser(db.getConnection(), username);

		if (u == null) {
			System.out.println("User " + username + " doesn't exist!");
			return Response.status(202)
					.entity("User " + username + " doesn't exist!").build();
		}

		UserFacade.enableUser(db.getConnection(), username);

		return Response.status(200).entity("User enabled!").build();
	}

	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(User updateUser) {

		DatabaseConnector db = new DatabaseConnector();
		
		User u = UserFacade.getUser(db.getConnection(), updateUser.getUsername());

		if (u == null) {
			System.out.println("User " + updateUser.getUsername() + " doesn't exist!");
			return Response.status(202)
					.entity("User " + updateUser.getUsername() + " doesn't exist!").build();
		}
		
		UserFacade.updateUser(db.getConnection(), updateUser);

		return Response.status(200).entity("User updated!").build();
	}

}
