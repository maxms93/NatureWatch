package at.jku.se.model;

import java.io.Serializable;
import com.owlike.genson.annotation.JsonProperty;

public class Species implements Serializable {

	private int id;
	private String species;
	private String category;
	private String latinName;
	private String normalName;
	private String description;
	private byte[] image1;
	private byte[] image2;
	private byte[] image3;
	private byte[] image4;
	private byte[] image5;
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
			@JsonProperty("image1") byte[] image1,
			@JsonProperty("image2") byte[] image2,
			@JsonProperty("image3") byte[] image3,
			@JsonProperty("image4") byte[] image4,
			@JsonProperty("image5") byte[] image5) {
		super();
		this.id = id;
		this.species = species;
		this.category = category;
		this.latinName = latinName;
		this.normalName = normalName;
		this.description = description;
		this.image1 = image1;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.image4 = image4;
		this.image5 = image5;
		
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

	public byte[] getImage4() {
		return image4;
	}

	public void setImage4(byte[] image4) {
		this.image4 = image4;
	}

	public byte[] getImage5() {
		return image5;
	}

	public void setImage5(byte[] image5) {
		this.image5 = image5;
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
