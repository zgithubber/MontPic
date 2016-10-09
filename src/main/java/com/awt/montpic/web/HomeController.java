package com.awt.montpic.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.awt.montpic.domain.Image;
import com.awt.montpic.domain.Mountain;
import com.awt.montpic.service.DowonloadImagesService;
import com.awt.montpic.service.LookUpService;
import com.awt.montpic.service.MontPicService;

@Controller
public class HomeController {
	
	@Autowired
	private DowonloadImagesService downloadImagesService;
	
	@Autowired
	private MontPicService montPicService;
	
	@Autowired
	private LookUpService lookUpService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(Model model){
		
		List<Mountain> mountains = montPicService.getAllMountains();
		
		model.addAttribute("mountains", mountains);
		model.addAttribute(new SearchKeyword());
		
		return "searchView";
	}
	
	@RequestMapping(value="/map", method=RequestMethod.POST)
	public String showImagesToMap(@Valid SearchKeyword searchKeyword, BindingResult bindingResult, Model model) throws UnsupportedEncodingException {
			
		if (bindingResult.hasErrors()){
			
			List<Mountain> mountains = montPicService.getAllMountains();			
			model.addAttribute("mountains", mountains);
			
			return "searchView";
		}

		List<Image> images = new ArrayList<Image>();
		String mountainNameEncoded = URLEncoder.encode(searchKeyword.getKeyword(), "UTF-8");
		
		model.addAttribute("mountainNameEncoded", mountainNameEncoded);
		model.addAttribute("keyword",searchKeyword.getKeyword());
		
		//check if the mountain is in the db 
		if (montPicService.mountainNotInDB(searchKeyword.getKeyword())) {
			
			System.out.println("Mountain " + searchKeyword.getKeyword() + " not in the DB!");
			
			//check if its a mountain and get the geodata
			Mountain mountain = lookUpService.geoCodeMountain(searchKeyword.getKeyword());
			
			if(mountain.getName() == null){
				System.out.println("We could't geocode "+searchKeyword.getKeyword()+ " as a mountain");
				
				return "errorGeocode";
			}
			else {
				
				images = downloadImagesService.getImages(mountain);
				
				if (images.isEmpty()) {
					
					System.out.println("Flickr didn't return any images for mountain: "+mountain.getName());
					
					return "errorDownload";
				}
				
				model.addAttribute("images", images);
				
				montPicService.saveImages(images);
			}			
		} else {
			
			images = montPicService.getImages(searchKeyword.getKeyword());	
			
			model.addAttribute("images", images);			
		}
		
		return "mapView";
	}
	
	@RequestMapping(value="/gallery", method=RequestMethod.GET)
	public String goToGalleryView(@RequestParam("mountainNameEncoded") String mountainNameEncoded, Model model)throws UnsupportedEncodingException{
		
		String mountainNameDecoded = URLDecoder.decode(mountainNameEncoded, "UTF-8");
		List<Image> images = montPicService.getImages(mountainNameDecoded);
		
		model.addAttribute("mountainNameDecoded", mountainNameDecoded);			
		model.addAttribute("images", images);
		
		return "galleryView";
	}
	
	@RequestMapping(value="/map", method=RequestMethod.GET)
	public String goToMapView(@RequestParam("mountainNameEncoded") String mountainNameEncoded, Model model)throws UnsupportedEncodingException{
		
		String mountainNameDecoded = URLDecoder.decode(mountainNameEncoded, "UTF-8");
		List<Image> images = montPicService.getImages(mountainNameDecoded);
		
		model.addAttribute("keyword",mountainNameDecoded);
		model.addAttribute("mountainNameEncoded", mountainNameEncoded);			
		model.addAttribute("images", images);
		
		return "mapView";
	}
}
