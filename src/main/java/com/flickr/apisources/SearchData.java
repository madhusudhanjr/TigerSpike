package com.flickr.apisources;

import java.util.List;

/**
 * SearchData is a POJO Class used to convert the json response of GET Title
 * List API endpoint to Java Class
 * 
 * @author @author mjr (madhusudhan.jr@gmail.com)
 */
public class SearchData {

	public String title;
	public String link;
	public String description;
	public String modified;
	public String generator;
	public List<Item> items;

	class Media {
		public String m;
	}

	public class Item {
		public String title;
		public String link;
		public Media media;
		public String date_taken;
		public String description;
		public String published;
		public String author;
		public String author_id;
		public String tags;
	}

}