package at.jku.se.database;

import at.jku.se.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseTest {

	public static void main(String[] args) {

		System.out.println("Start Test");

		String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
		String MYSQL_URL = "jdbc:mysql://localhost:3306/naturewatch?user=admin&password=admin";

		Connection connection;
		Statement statement;
		ResultSet resultSet;
		PreparedStatement preparedStatement;
		
		try {

			Class.forName(MYSQL_DRIVER);
			connection = DriverManager.getConnection(MYSQL_URL);
			
			statement = connection.createStatement();

			resultSet = statement.executeQuery("select * from USER;");
			while(resultSet.next()){
				String a = resultSet.getString("USERNAME");
				System.out.println(a);
			}
			
			preparedStatement = connection
					.prepareStatement("insert into USER values (?,?,?,?)");
			preparedStatement.setString(1, "test");
			preparedStatement.setString(2, "test");
			preparedStatement.setString(3, "test");
			preparedStatement.setString(4, "test");
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
