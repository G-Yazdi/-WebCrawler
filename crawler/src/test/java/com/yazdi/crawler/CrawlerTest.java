package com.yazdi.crawler;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


/**
 * Unit test for Crawler.
 */
public class CrawlerTest 
{
	private static Crawler crawler;
	private static Document document;
	
	@BeforeAll 
	public static void init() {
		crawler = new JsoupCrawler();
		String html = "<html><head><title>Sample Page</title></head><body>" +
		            "<h1>Heading 1</h1>" +
					"<a href='https://pinzger.github.io/'>Link 1</a>"+
		            "<a href='https://yazdi.github.io/'>Link 2</a>"+
		            "<h2>Heading 2</h2>" +
		            "<h3>Heading 3</h3>" +
		            "</body></html>";
		document = Jsoup.parse(html);
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
    	Set<String> visitedLinks = Collections.singleton("https://pinzger.github.io/"); 
		assertTrue(((JsoupCrawler) crawler).getNewUrlsToVisit(document, visitedLinks).size() == 1);
    }
    @Test
    public void getHeadingsStringTest() {
    	String expected = "#Heading 1\n"+"##Heading 2\n"+"###Heading 3\n";
    	String actual = ((JsoupCrawler) crawler).getHeadingsString(document);
    	assertEquals(expected, actual);
    }
    
}
