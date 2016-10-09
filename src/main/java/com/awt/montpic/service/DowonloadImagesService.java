package com.awt.montpic.service;

import java.util.List;

import com.awt.montpic.domain.Image;
import com.awt.montpic.domain.Mountain;

public interface DowonloadImagesService {
	
	public List<Image> getImages(Mountain mountain);
	
}
