package at.jku.se.model;

import com.owlike.genson.annotation.JsonProperty;

public class User {

	private String username;
	private String password;
	private String email;
	private String firstname;
	private String lastname;
	private String zip;
	private String city;
	private boolean enabled;

	public User(@JsonProperty("username") String username,
			@JsonProperty("password") String password,
			@JsonProperty("email") String email,
			@JsonProperty("firstname") String firstname,
			@JsonProperty("lastname") String lastname,
			@JsonProperty("zip") String zip,
			@JsonProperty("city") String city,
			@JsonProperty("enabled") boolean enabled) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.zip = zip;
		this.city = city;
		this.enabled = enabled;
	}

	public User() {
		super();
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
