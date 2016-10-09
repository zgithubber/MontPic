package com.awt.montpic.service;

import java.util.List;

import com.awt.montpic.domain.Image;
import com.awt.montpic.domain.Mountain;

public interface MontPicService {

	public List<Image> getImages(String inputName);
	
	public void saveImages(List<Image> images);

	public List<Mountain> getAllMountains();
	
	public Boolean mountainNotInDB(String inputName);
}
