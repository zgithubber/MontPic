package com.awt.montpic.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Mountain {


	@Id
	private String name;
	
	private float latitude;
	
	private float longitude;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "mountain")
	List<Image> images;

	public Mountain() {
	}

	public Mountain(String name, float latitude, float longitude) {
		super();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

}
