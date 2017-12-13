package at.jku.se.database;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseConnector {
	
	private Connection connection;

	public DatabaseConnector() {

		String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
		String MYSQL_URL = "jdbc:mysql://localhost:3306/naturewatch?user=admin&password=admin";

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
