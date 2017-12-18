package at.jku.se.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.owlike.genson.annotation.JsonProperty;

public class Species implements Serializable {

	private int id;
	private String species;
	private String category;
	private String latinName;
	private String normalName;
	private String description;
	private ArrayList<byte[]> images;
	private int validFrom;
	private int validTo;

	public Species(@JsonProperty("id") int id,
			@JsonProperty("species") String species,
			@JsonProperty("category") String category,
			@JsonProperty("latinName") String latinName,
			@JsonProperty("normalName") String normalName,
			@JsonProperty("description") String description,
			@JsonProperty("validFrom") int validFrom, 
			@JsonProperty("validTo") int validTo,
			@JsonProperty("images") ArrayList<byte[]> images) {
		super();
		this.id = id;
		this.species = species;
		this.category = category;
		this.latinName = latinName;
		this.normalName = normalName;
		this.description = description;
		this.images = images;
		this.validFrom = validFrom;
		this.validTo = validTo;
	}

	public Species(int id, String species, String category, String latinName,
			String normalName, String description, int validFrom, int validTo) {
		super();
		this.id = id;
		this.species = species;
		this.category = category;
		this.latinName = latinName;
		this.normalName = normalName;
		this.description = description;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.images = new ArrayList<byte[]>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLatinName() {
		return latinName;
	}

	public void setLatinName(String latinName) {
		this.latinName = latinName;
	}

	public String getNormalName() {
		return normalName;
	}

	public void setNormalName(String normalName) {
		this.normalName = normalName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<byte[]> getImages() {
		return images;
	}

	public void addImage(byte[] image) {
		this.images.add(image);
	}

	public int getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(int validFrom) {
		this.validFrom = validFrom;
	}

	public int getValidTo() {
		return validTo;
	}

	public void setValidTo(int validTo) {
		this.validTo = validTo;
	}

}
