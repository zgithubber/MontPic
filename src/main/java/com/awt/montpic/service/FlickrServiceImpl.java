package com.awt.montpic.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.awt.montpic.domain.Image;
import com.awt.montpic.domain.Mountain;
import com.awt.montpic.persistence.MountainRepository;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photos.SearchParameters;

@Service
@Transactional
public class FlickrServiceImpl implements DowonloadImagesService {

	@Autowired
	private MountainRepository mountainRepository;
	
	@Override
	public List<Image> getImages(Mountain mountain) {
		
		String apiKey = "bf374c162781d7623d824e7f4088c194";
		String sharedSecret = "a52121d4c598895d";
		
		Flickr f = new Flickr(apiKey, sharedSecret, new REST());
		
		PhotosInterface photoInterface = f.getPhotosInterface();
		
		SearchParameters searchParams = new SearchParameters();
		
		searchParams.setText(mountain.getName());
		searchParams.setHasGeo(true);
		Set<String> extras = new HashSet<String>();
		extras.add("geo");
		searchParams.setExtras(extras);
		
		List<Photo> photos = new PhotoList<Photo>();
		
		try {
			photos = photoInterface.search(searchParams, 33, 1);
		} catch (FlickrException  e) {
			e.printStackTrace();
		}
		
		List<Image> images = new ArrayList<Image>();
		
		if (photos.isEmpty()) {
			
			System.out.println("No photos for :"+mountain.getName());
			
		} else {
			
			for (Photo photo : photos) {
				
				Image image = new Image();
				
				String url = "https://farm"+photo.getFarm()+".staticflickr.com/"+photo.getServer()+"/"+photo.getId()+"_"+photo.getSecret()+".jpg";
				
				image.setUrl(url);				
				image.setId(photo.getId());				
				image.setMountain(mountain);
				
				if (photo.getGeoData() != null) {
					
					image.setLatitude(photo.getGeoData().getLatitude());
					image.setLongitude(photo.getGeoData().getLongitude());
					
				} else {
					
					image.setLatitude(image.getMountain().getLatitude());
					image.setLongitude(image.getMountain().getLongitude());

					System.out.println("No geodata for"+image.getMountain().getName()+ image.getUrl());
				} 
				
				images.add(image);
			}
		}
		
		return images;
	}

}
