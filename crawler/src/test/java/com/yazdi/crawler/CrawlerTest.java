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
	}
    @Test
    public void invalidMaxDepthExceptionTest()
    {
    	Throwable exception = assertThrows(RuntimeException.class, () -> crawler.crawl("", "", -1));
		assertEquals("Invalid max depth value: The value of max depth should be an integer and greater than 0", exception.getMessage());
    }
}
