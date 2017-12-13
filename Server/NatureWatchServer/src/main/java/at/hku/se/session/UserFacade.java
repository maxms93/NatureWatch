package at.hku.se.session;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import at.jku.se.model.User;

public class UserFacade {

 public static User getUser(Connection connection, String name) {
		
		User u = null;
		
		try {
		
		PreparedStatement preparedStatement = connection.prepareStatement("insert into USER values (?,?,?,?)");
		preparedStatement.setString(1, "max");
		preparedStatement.setString(2, "test");
		preparedStatement.setString(3, "test");
		preparedStatement.setString(4, "test");
		preparedStatement.executeUpdate();
		ResultSet resultSet = statement.executeQuery("select * from USER where username = ?;");
		
		
			
			while(resultSet.next()){
				String username = resultSet.getString("USERNAME");
				String password = resultSet.getString("PASSWORD");
				u = new User(username, password);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
}
