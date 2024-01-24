package com.yazdi.crawler;

public interface Crawler {

	void crawl(String domain, String url, int maxDepth);
}
