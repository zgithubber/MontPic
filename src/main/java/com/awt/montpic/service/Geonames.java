package com.awt.montpic.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geonames {
	
	@JsonProperty("totalResultsCount")
	private String totalResultsCount;
	
	
	@JsonProperty("geonames")
	private List<HashMap<String, String>> geonames = new ArrayList<HashMap<String,String>>(); 

	public String getTotalResultsCount() {
		return totalResultsCount;
	}

	public void setTotalResultsCount(String totalResultsCount) {
		this.totalResultsCount = totalResultsCount;
	}

	public List<HashMap<String, String>> getGeonames() {
		return geonames;
	}

	public void setGeonames(List<HashMap<String, String>> geonames) {
		this.geonames = geonames;
	}


}
