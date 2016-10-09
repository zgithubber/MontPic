package com.awt.montpic.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Image {
	
	@Id 
	private String id;
	
	private float latitude;
	
	private float longitude;
	
	private String url;
	
	@ManyToOne(cascade=CascadeType.ALL)
	Mountain mountain;
	
	public Mountain getMountain() {
		return mountain;
	}
	
	public void setMountain(Mountain mountain) {
		this.mountain = mountain;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String string) {
		this.id = string;
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
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
}
