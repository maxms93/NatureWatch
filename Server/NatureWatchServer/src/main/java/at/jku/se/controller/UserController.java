package at.jku.se.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.jku.se.session.UserFacade;
import at.jku.se.database.DatabaseConnector;
import at.jku.se.database.MailHandler;
import at.jku.se.model.User;
import at.jku.se.model.adapter.JsonAdapter;

@Path("/user")
public class UserController {

	@PUT
	@Path("/login")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response login(String value) {
		
		User user = JsonAdapter.read(value, new User());

		DatabaseConnector db = new DatabaseConnector();

		User u = UserFacade.getUser(db.getConnection(), user.getEmail());

		db.close();
		
		if (u == null) {
			System.out
					.println("User " + user.getEmail() + " doesn't exist!");
			return Response.status(201).entity("User doesn't exist!").build();
		}

		if (!u.getPassword().equals(user.getPassword())) {
			System.out.println("Password does't match!");
			return Response.status(202).entity("Password doesnt match!").build();
		}

		if (!u.isEnabled()) {
			System.out.println("User " + user.getEmail() + " is not enabled!");
			return Response.status(203).entity("User not enabled!").build();
		}

		return Response.status(200).entity("User login!").build();
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response create(String value) {

		User user = JsonAdapter.read(value, new User());
		DatabaseConnector db = new DatabaseConnector();
		User u = UserFacade.getUser(db.getConnection(), user.getEmail());

		if (u != null) {
			db.close();
			System.out.println("User " + u.getEmail() + " already exists!");
			return Response.status(201)
					.entity("User " + u.getEmail() + " already exists!")
					.build();
		}
		
		user.setEnabled(false);
		user.setAdminflag(false);
		
		UserFacade.insertUser(db.getConnection(), user);

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
					+ "/NatureWatchServer/rest/user/enable/" + user.getEmail();
			if (!MailHandler.sendMail(user.getEmail(), "Bestatigungsmail", link)){
				UserFacade.enableUser(db.getConnection(), user.getEmail());
			}

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
	@Consumes(MediaType.TEXT_PLAIN)
	public Response update(String updateUserStr) {

		DatabaseConnector db = new DatabaseConnector();
		
		User updateUser = JsonAdapter.read(updateUserStr, new User());
		
		User u = UserFacade.getUser(db.getConnection(), updateUser.getEmail());

		if (u == null) {
			db.close();
			System.out.println("User " + updateUser.getEmail() + " doesn't exist!");
			return Response.status(202)
					.entity("User " + updateUser.getEmail() + " doesn't exist!").build();
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

		MailHandler.sendMail(resetUser.getEmail(), "Passwort zur�ckgesetzt!", mail);
		
		db.close();

		return Response.status(200).entity("User Password reset!").build();
	}

}
