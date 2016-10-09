package com.awt.montpic.service;

import com.awt.montpic.domain.Mountain;

public interface LookUpService {
	
	public Mountain geoCodeMountain(String mountainName);
	
}
