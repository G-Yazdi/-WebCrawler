package com.yazdi.crawler;

import java.io.IOException;
import java.net.URL;
import java.util.Set;

import org.jsoup.*;
import org.jsoup.nodes.*;

public class JsoupCrawler implements Crawler {
	
	private static Integer convertStringToInteger(String strNum) {
	    try {
	    	return Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	    	return null;
	    }
	    
	}
	private static URL convertStringToURL(String url) {
		try {
		    return new URL(url);
		}catch (Exception e) {
			return null;
		}
	    
	}
	private static void validateInputs(Integer maxDepth, String strUrl, String domain) {
	    if(maxDepth == null || maxDepth < 0) {
	    	throw new RuntimeException("Invalid max depth value: The value of max depth should be an integer and greater than 0!");
	    }
	    URL url = JsoupCrawler.convertStringToURL(strUrl);
	    if(url == null) {
	    	throw new RuntimeException("Invalid URL format: the format of the URL is incorrect!");
	    }
	    if(!url.getHost().equals(domain) ){
	    	throw new RuntimeException("Domain mismatch: Crawler cannot process websites with different domains!");
	    }
	    
	}
	protected Document getWebsiteHTMLDocument(String url) {
		try {
			return Jsoup.connect(url).get();
			
		} catch (IOException e) {
			return null;
		}
	}
	protected Set<String> getNewLinks(Document doc, Set<String> visitedLinks){
		return null;
	}
	@Override
	public void crawl(String domain, String strUrl, String strMaxDepth) {
		Integer depth = JsoupCrawler.convertStringToInteger(strMaxDepth);
		validateInputs(depth, strUrl, domain);
		
		
			
		}

}
