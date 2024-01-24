package com.yazdi.crawler;

public class JsoupCrawler implements Crawler {
	
	private Integer convertStringToInteger(String strNum) {
	    try {
	    	return Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	    	return null;
	    }
	    
	}
	private void validateInputs(Integer maxDepth) {
	    if(maxDepth == null || maxDepth < 0) {
	    	throw new RuntimeException("Invalid max depth value: The value of max depth should be an integer and greater than 0!");
	    }
	    
	}

	@Override
	public void crawl(String domain, String url, String maxDepth) {
		Integer depth = this.convertStringToInteger(maxDepth);
		validateInputs(depth);
		
	}

}
