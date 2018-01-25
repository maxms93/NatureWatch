package at.jku.se.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.owlike.genson.annotation.JsonProperty;

public class Sighting {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private int speciesId;
	private byte[] image1;
	private byte[] image2;
	private byte[] image3;
	private String description;
	private double longitude;
	private double latitude;
	private int seaLevel;
	private String state;
	private String country;
	private String city;
	private String user;
	private Date dateTime;
	private boolean enabled;

	public Sighting(@JsonProperty("id") Long id,
			@JsonProperty("speciesId") int speciesId,
			@JsonProperty("description") String description,
			@JsonProperty("longitude") double longitude,
			@JsonProperty("latitude") double latitude,
			@JsonProperty("seaLevel") int seaLevel,
			@JsonProperty("state") String state,
			@JsonProperty("country") String country,
			@JsonProperty("city") String city,
			@JsonProperty("user") String user,
			@JsonProperty("dateTime") Date dateTime,
			@JsonProperty("enabled") boolean enabled,
			@JsonProperty("image1") byte[] image1,
			@JsonProperty("image2") byte[] image2,
			@JsonProperty("image3") byte[] image3) {
		super();
		this.id = id;
		this.speciesId = speciesId;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.description = description;
		this.longitude = longitude;
		this.latitude = latitude;
		this.seaLevel = seaLevel;
		this.state = state;
		this.country = country;
		this.city = city;
		this.user = user;
		this.dateTime = dateTime;
		this.enabled = enabled;
	}
	
	public Sighting(
			@JsonProperty("speciesId") int speciesId,
			@JsonProperty("description") String description,
			@JsonProperty("longitude") double longitude,
			@JsonProperty("latitude") double latitude,
			@JsonProperty("seaLevel") int seaLevel,
			@JsonProperty("state") String state,
			@JsonProperty("country") String country,
			@JsonProperty("city") String city,
			@JsonProperty("user") String user,
			@JsonProperty("dateTime") Date dateTime,
			@JsonProperty("enabled") boolean enabled,
			@JsonProperty("image1") byte[] image1,
			@JsonProperty("image2") byte[] image2,
			@JsonProperty("image3") byte[] image3) {
		super();
		this.speciesId = speciesId;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.description = description;
		this.longitude = longitude;
		this.latitude = latitude;
		this.seaLevel = seaLevel;
		this.state = state;
		this.country = country;
		this.city = city;
		this.user = user;
		this.dateTime = dateTime;
		this.enabled = enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSpeciesId() {
		return speciesId;
	}

	public void setSpeciesId(int speciesId) {
		this.speciesId = speciesId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public int getSeaLevel() {
		return seaLevel;
	}

	public void setSeaLevel(int seaLevel) {
		this.seaLevel = seaLevel;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public byte[] getImage1() {
		return image1;
	}
	public void setImage1(byte[] image1) {
		this.image1 = image1;
	}
	
	public byte[] getImage2() {
		return image2;
	}
	public void setImage2(byte[] image2) {
		this.image2 = image2;
	}
	
	public byte[] getImage3() {
		return image3;
	}
	
	public void setImage3(byte[] image3) {
		this.image3 = image3;
	}

}
