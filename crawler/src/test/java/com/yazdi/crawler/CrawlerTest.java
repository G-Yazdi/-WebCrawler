package com.yazdi.crawler;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


/**
 * Unit test for Crawler.
 */
public class CrawlerTest 
{
	private static Crawler crawler;
	
	@BeforeAll 
	public static void init() {
		crawler = new JsoupCrawler();
	}
    @Test
    public void invalidMaxDepthExceptionTest()
    {
    	Throwable negativeValueException = assertThrows(RuntimeException.class, () -> crawler.crawl("", "", "-1"));
    	Throwable nonIntegerValueException = assertThrows(RuntimeException.class, () -> crawler.crawl("", "", "0.1"));
		
		assertAll(
	            () -> assertEquals("Invalid max depth value: The value of max depth should be an integer and greater than 0!", negativeValueException.getMessage()),
	            () -> assertEquals("Invalid max depth value: The value of max depth should be an integer and greater than 0!", nonIntegerValueException.getMessage())); 
    }
    @Test
    public void invalidURLExceptionTest()
    {
    	Throwable exception = assertThrows(RuntimeException.class, () -> crawler.crawl("", "hgjll777", "4"));
    	assertEquals("Invalid URL format: the format of the URL is incorrect!", exception.getMessage()); 
    }
    @Test
    public void invalidDomainExceptionTest()
    {
    	Throwable exception = assertThrows(RuntimeException.class, () -> crawler.crawl("hhhh", "https://www.tutorialspoint.com", "4"));
    	assertEquals("Domain mismatch: Crawler cannot process websites with different domains!", exception.getMessage()); 
    }
    @Test
    public void getWebsiteHTMLDocumentTest() {
    	Document actual = ((JsoupCrawler) crawler).getWebsiteHTMLDocument("https://pinzger.github.io/");
    	try {
			Document expected = Jsoup.connect("https://pinzger.github.io/").get();
			assertEquals(expected, actual);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	 
    	
    }
    
}
