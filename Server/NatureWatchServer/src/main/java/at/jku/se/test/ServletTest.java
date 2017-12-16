package at.jku.se.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.jku.se.model.User;

public class ServletTest {

	public static void main(String[] args) {

		User u = new User("test", "test", null, null, null, null, null, true);

		Client client = ClientBuilder.newClient();
		String r = client
				.target("http://localhost:9356/NatureWatchServer/user")
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.TEXT_PLAIN)
				.post(Entity.json(u), String.class);

	}

}
