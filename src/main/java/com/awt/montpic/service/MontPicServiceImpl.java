package com.awt.montpic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.awt.montpic.domain.Image;
import com.awt.montpic.domain.Mountain;
import com.awt.montpic.persistence.MountainRepository;

@Service
@Transactional
public class MontPicServiceImpl implements MontPicService {

	@Autowired
	private MountainRepository mountainRepository;
	
	@Override
	public List<Image> getImages(String inputName) {
		
		List<Image> images = mountainRepository.getImages(inputName);
		
		return images;
	}

	@Override
	public void saveImages(List<Image> images) {
		
		for (Image image : images) {
			
			mountainRepository.saveImages(image);
			
		}
		
	}

	@Override
	public List<Mountain> getAllMountains() {
		
		List<Mountain> mountains= mountainRepository.getAllMountains();	
		
		return mountains;
	}

	@Override
	public Boolean mountainNotInDB(String inputName) {
		
		return mountainRepository.mountainNotInDB(inputName);
	}

}
