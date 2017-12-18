package at.jku.se.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import at.jku.se.model.User;

public class UserFacade {

 public static User getUser(Connection connection, String username) {
		
		User u = null;	
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * from user where username = ?");
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				String rusername = result.getString("USERNAME");
				String password = result.getString("PASSWORD");
				String email = result.getString("EMAIL");
				String firstname = result.getString("FIRSTNAME");
				String lastname = result.getString("LASTNAME");
				String zip = result.getString("ZIP");
				String city = result.getString("CITY");
				boolean enabled = result.getString("ENABLED").equals("Y");

				u = new User(rusername, password, email, firstname, lastname, zip, city, enabled);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
}
