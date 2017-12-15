package at.jku.se.model;

import java.awt.Image;
import java.util.ArrayList;

public class Species {
	
	private int id;
	private String species;
	private String category;
	private String latinName;
	private String normalName;
	private String description;
	private ArrayList<Image> images;
	private int validFrom;
	private int validTo;
	
	
	public Species(int id, String species, String category, String latinName,
			String normalName, String description, int validFrom, int validTo) {
		super();
		this.id = id;
		this.species = species;
		this.category = category;
		this.latinName = latinName;
		this.normalName = normalName;
		this.description = description;
		this.images = new ArrayList<Image>();
		this.validFrom = validFrom;
		this.validTo = validTo;
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
	public ArrayList<Image> getImages() {
		return images;
	}
	public void setImages(ArrayList<Image> images) {
		this.images = images;
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
