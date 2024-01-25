package com.yazdi.crawler;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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
			assertTrue(expected.hasSameValue(actual));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    @Test
    public void getNewUrlsToVisitTest() {
    	String html = "<html><body></body><a href='https://pinzger.github.io/'>Link 1</a><a href='https://yazdi.github.io/'>Link 2</a></html>";
    	Document doc = Jsoup.parse(html);
    	Set<String> visitedLinks = Collections.singleton("https://pinzger.github.io/"); 
		assertTrue(((JsoupCrawler) crawler).getNewUrlsToVisit(doc, visitedLinks).size() == 1);
    }
    
}
