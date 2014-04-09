package com.example.randomnotes;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class RandomNotesParser {
	private String url;
	private int year;
	public RandomNotesParser(String url, int year) {
		this.url = url;
		this.year = year;
	}
	
	public String generatePost() throws IOException {
		String result = "generatePost";
//		Document doc = Jsoup.connect(url).get();
//		doc.body().wrap("<pre></pre>");
//		String text = doc.text();
//		  // Converting nbsp entities
//		text = text.replaceAll("\u00A0", " ");
//		System.out.print(text);
		return result;
	}
	
}
