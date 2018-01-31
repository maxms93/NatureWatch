package at.jku.se.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import at.jku.se.model.User;

public class UserFacade {

	public static User getUser(Connection connection, String email) {

		User u = null;

		try {
			PreparedStatement statement = connection
					.prepareStatement("SELECT * from user where email = ?");
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				String username = result.getString("USERNAME");
				String password = result.getString("PASSWORD");
				String remail = result.getString("EMAIL");
				String firstname = result.getString("FIRSTNAME");
				String lastname = result.getString("LASTNAME");
				String zip = result.getString("ZIP");
				String city = result.getString("CITY");
				boolean enabled = result.getString("ENABLED").equals("Y");

				u = new User(username, password, remail, firstname, lastname,
						zip, city, enabled);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	public static void insertUser(Connection connection, User newUser) {
		
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO user(USERNAME,PASSWORD,EMAIL,FIRSTNAME,LASTNAME,ZIP,CITY,ENABLED) "
							+ "	VALUES(?,?,?,?,?,?,?,?)");
			statement.setString(1, newUser.getUsername());
			statement.setString(2, newUser.getPassword());
			statement.setString(3, newUser.getEmail());
			statement.setString(4, newUser.getFirstname());
			statement.setString(5, newUser.getLastname());
			statement.setString(6, newUser.getZip());
			statement.setString(7, newUser.getCity());
			statement.setString(8, "N");
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void enableUser(Connection connection, String username) {

		try {
			PreparedStatement statement = connection
					.prepareStatement("UPDATE user SET ENABLED = ? "
							+ " WHERE USERNAME = ?");
			statement.setString(1, "Y");
			statement.setString(2, username);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static void updateUser(Connection connection, User updateUser) {

		try {
			PreparedStatement statement = connection
					.prepareStatement("UPDATE user SET PASSWORD = ?, "
							+ " EMAIL = ?, "
							+ " FIRSTNAME = ?, "
							+ " LASTNAME = ?, "
							+ " ZIP = ?, "
							+ " CITY = ? "
							+ " WHERE USERNAME = ?");
			statement.setString(1, updateUser.getPassword());
			statement.setString(2, updateUser.getEmail());
			statement.setString(3, updateUser.getFirstname());
			statement.setString(4, updateUser.getLastname());
			statement.setString(5, updateUser.getZip());
			statement.setString(6, updateUser.getCity());
			statement.setString(7, updateUser.getUsername());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
