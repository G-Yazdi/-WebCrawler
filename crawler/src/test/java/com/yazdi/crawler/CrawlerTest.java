package com.yazdi.crawler;

import static org.junit.jupiter.api.Assertions.*;

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
    	Throwable exception = assertThrows(RuntimeException.class, () -> crawler.crawl("", "hgjll777", ""));
    	assertEquals("Invalid URL!", exception.getMessage()); 
    }
}
