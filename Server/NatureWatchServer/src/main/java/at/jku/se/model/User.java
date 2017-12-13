package at.jku.se.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mysql.jdbc.PreparedStatement;


public class User {
	
	
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
	

	/*Der Benutzer kann sich registrieren, indem er folgende Daten angibt:
		- E-Mail
		- Passwort (muss doppelt eingegeben werden)
		- Username
	
	Optional kann der Benutzer noch folgende Felder ausfüllen:
		- Vorname
		- Nachname
		- Postleitzahl
		- Ort*/
	
}
