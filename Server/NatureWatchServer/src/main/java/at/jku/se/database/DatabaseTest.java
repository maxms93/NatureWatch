package at.jku.se.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseTest {

	public static void main(String[] args) {

		System.out.println("Start Test");
		
		DatabaseConnector db = new DatabaseConnector();

		Connection connection;
		Statement statement;
		ResultSet resultSet;
		PreparedStatement preparedStatement;
		
		try {

			connection = db.getConnection();
			
			statement = connection.createStatement();

			resultSet = statement.executeQuery("select * from USER;");
			while(resultSet.next()){
				String a = resultSet.getString("USERNAME");
				System.out.println(a);
			}
			
			preparedStatement = connection
					.prepareStatement("insert into USER values (?,?,?,?)");
			preparedStatement.setString(1, "max");
			preparedStatement.setString(2, "test");
			preparedStatement.setString(3, "test");
			preparedStatement.setString(4, "test");
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
