package at.jku.se.model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;

public class Sighting {

	private int id;
	private int speciesId;
	private ArrayList<Image> images;
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

	public Sighting(int id, int speciesId, String description,
			double longitude, double latitude, int seaLevel, String state,
			String country, String city, String user, Date dateTime,
			boolean enabled) {
		super();
		this.id = id;
		this.speciesId = speciesId;
		this.images = new ArrayList<Image>();
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSpeciesId() {
		return speciesId;
	}

	public void setSpeciesId(int speciesId) {
		this.speciesId = speciesId;
	}

	public ArrayList<Image> getImages() {
		return images;
	}

	public void addImages(Image image) {
		this.images.add(image);
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

}
