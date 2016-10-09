package com.awt.montpic.persistence;

import java.util.List;

import com.awt.montpic.domain.Image;
import com.awt.montpic.domain.Mountain;

public interface MountainRepository {
	
	public void saveImages(Image image);
	
	public List<Image> getImages(String mountainName);
	
	public List<Mountain> getAllMountains();

	public Boolean mountainNotInDB(String inputName);
	
}
