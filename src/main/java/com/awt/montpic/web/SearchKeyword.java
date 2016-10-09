package com.awt.montpic.web;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SearchKeyword {
	//@IsMountain(message = "{IsMountain.searchKeyword.keyword}") //custom validator
	
	@Pattern(regexp="^[\\p{L}\\s\\'\\(\\)\\-]+$", message = "{Pattern.searchKeyword.keyword}")
	@Size(min=2, max=100, message = "{Size.searchKeyword.keyword}")
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword.toUpperCase();
	}
}
