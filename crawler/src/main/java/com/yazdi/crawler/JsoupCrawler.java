package com.yazdi.crawler;

import java.net.URL;

public class JsoupCrawler implements Crawler {
	
	private Integer convertStringToInteger(String strNum) {
	    try {
	    	return Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	    	return null;
	    }
	    
	}
	private boolean validateURL(String url) {
		   try {
		      new URL(url).toURI();
		      return true;
		   }catch (Exception e) {
		      return false;
		   }
		}
	private void validateInputs(Integer maxDepth, String url) {
	    if(maxDepth == null || maxDepth < 0) {
	    	throw new RuntimeException("Invalid max depth value: The value of max depth should be an integer and greater than 0!");
	    }
	    if(!validateURL(url)) {
	    	throw new RuntimeException("Invalid URL!");
	    }
	    
	}

	@Override
	public void crawl(String domain, String url, String maxDepth) {
		Integer depth = this.convertStringToInteger(maxDepth);
		validateInputs(depth, url);
		
	}

}
