package at.jku.se.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnector {

	private Connection connection;

	public DatabaseConnector() {

		String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
		String MYSQL_URL = null;
		Properties prop = new Properties();
		InputStream input = null;

		try {

			String filename = "database.properties";
			input = DatabaseConnector.class.getClassLoader()
					.getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return;
			}

			prop.load(input);

			System.out.println(prop.getProperty("database"));
			System.out.println(prop.getProperty("dbuser"));
			System.out.println(prop.getProperty("dbpassword"));
			
			MYSQL_URL = "jdbc:mysql://"+prop.getProperty("database")+"?user="+prop.getProperty("dbuser")+"&password="+prop.getProperty("dbpassword");

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

		try {

			Class.forName(MYSQL_DRIVER);
			connection = DriverManager.getConnection(MYSQL_URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {

		return this.connection;
	}

}
