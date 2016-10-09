package com.awt.montpic.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.awt.montpic.domain.Mountain;

@Service
@Transactional
public class GeonamesLookUpServiceImpl implements LookUpService {

	@Override
	public Mountain geoCodeMountain(String keyword) {
		
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("format", "json");
		vars.put("username", "z_geo_names");
		vars.put("keyword", keyword);
		String url = "http://api.geonames.org/search?q={keyword}&featureClass=T&featureCodeMT&maxRows=1&type={format}&username={username}";
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		Geonames geonamesResult = restTemplate.getForObject(url, Geonames.class, vars);
		
		String toponymName="";
		String name="";		
		float latitude=0;
		float longitude=0;
		Mountain mountain = new Mountain();
		
		if(!geonamesResult.getGeonames().isEmpty()){
			for (int a =0; a<geonamesResult.getGeonames().size();a++){

				latitude = Float.parseFloat(geonamesResult.getGeonames().get(a).get("lat")); 
				longitude = Float.parseFloat(geonamesResult.getGeonames().get(a).get("lng"));						
				toponymName = geonamesResult.getGeonames().get(a).get("toponymName");
				name = geonamesResult.getGeonames().get(a).get("name");
			}
			if(toponymName.toUpperCase().contains(keyword) || name.toUpperCase().contains(keyword))
			{
				
				mountain.setLatitude(latitude);
				mountain.setLongitude(longitude);
				mountain.setName(keyword);
			} 
		}
		else {
			
			System.out.println("Sorry we can't geocode the mountain name your inserted, it propably doesn't exist in the geonames archives or you entered a non valid mountain name!");
			//return ERROR;
		}	
		
		return mountain;
	}

}
