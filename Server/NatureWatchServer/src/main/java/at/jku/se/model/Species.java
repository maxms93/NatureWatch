package at.jku.se.model;

import com.owlike.genson.annotation.JsonProperty;

public class Species {
	
	private int id;
	private String species;
	private String category;
	private String latinName;
	private String normalName;
	private String description;
	private long validFrom;
	private long validTo;
	private byte[] image1;
	private byte[] image2;
	private byte[] image3;
	private byte[] image4;
	private byte[] image5;
	private String image1Name;
	private String image2Name;
	private String image3Name;
	private String image4Name;
	private String image5Name;

	public Species(@JsonProperty("id") int id,
			@JsonProperty("species") String species,
			@JsonProperty("category") String category,
			@JsonProperty("latinName") String latinName,
			@JsonProperty("normalName") String normalName,
			@JsonProperty("description") String description,
			@JsonProperty("validFrom") long validFrom, 
			@JsonProperty("validTo") long validTo,
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
	
	public Species(int id, String species, String category, String latinName,
			String normalName, String description, long validFrom, long validTo,
			byte[] image1, byte[] image2, byte[] image3, byte[] image4,
			byte[] image5, String image1Name, String image2Name,
			String image3Name, String image4Name, String image5Name) {
		super();
		this.id = id;
		this.species = species;
		this.category = category;
		this.latinName = latinName;
		this.normalName = normalName;
		this.description = description;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.image4 = image4;
		this.image5 = image5;
		this.image1Name = image1Name;
		this.image2Name = image2Name;
		this.image3Name = image3Name;
		this.image4Name = image4Name;
		this.image5Name = image5Name;
	}


	public Species() {
		super();
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

	public long getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(long validFrom) {
		this.validFrom = validFrom;
	}

	public long getValidTo() {
		return validTo;
	}

	public void setValidTo(long validTo) {
		this.validTo = validTo;
	}

	public String getImage1Name() {
		return image1Name;
	}

	public void setImage1Name(String image1Name) {
		this.image1Name = image1Name;
	}

	public String getImage2Name() {
		return image2Name;
	}

	public void setImage2Name(String image2Name) {
		this.image2Name = image2Name;
	}

	public String getImage3Name() {
		return image3Name;
	}

	public void setImage3Name(String image3Name) {
		this.image3Name = image3Name;
	}

	public String getImage4Name() {
		return image4Name;
	}

	public void setImage4Name(String image4Name) {
		this.image4Name = image4Name;
	}

	public String getImage5Name() {
		return image5Name;
	}

	public void setImage5Name(String image5Name) {
		this.image5Name = image5Name;
	}
	
	

}
