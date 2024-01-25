package com.yazdi.crawler;

import java.io.IOException;
import java.net.URL;
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
	private static void validateInputs(Integer maxDepth, URL url, String domain) {
	    if(maxDepth == null || maxDepth < 0) {
	    	throw new RuntimeException("Invalid max depth value: The value of max depth should be an integer and greater than 0!");
	    }
	    if(url == null) {
	    	throw new RuntimeException("Invalid URL format: the format of the URL is incorrect!");
	    }
	    if(!url.getHost().equals(domain) ){
	    	throw new RuntimeException("Domain mismatch: Crawler cannot process websites with different domains!");
	    }
	    
	}
	public Document getWebsiteHTMLDocument(String url) {
		return null;
	}
	@Override
	public void crawl(String domain, String strUrl, String strMaxDepth) {
		Integer depth = JsoupCrawler.convertStringToInteger(strMaxDepth);
		URL url = JsoupCrawler.convertStringToURL(strUrl);
		validateInputs(depth, url, domain);
		try {
			Document document = Jsoup.connect("https://pinzger.github.io/").get();
			
		} catch (IOException e) {
			
		}
		
	}

}
