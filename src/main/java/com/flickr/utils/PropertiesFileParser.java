package com.flickr.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * PropertiesFileParser Class is used to parse the Properties File
 * 
 * @author mjr (madhusudhan.jr@gmail.com)
 */
public class PropertiesFileParser {

	/**
	 * This method gets the value for the property keyword whic are stored in
	 * the config.properties file
	 * 
	 * @param property
	 * @return String
	 */
	public static String getProperty(String property) {

		Properties prop = new Properties();
		InputStream input = null;
		String value = "";
		try {

			input = new FileInputStream("src/test/resources/config.properties");

			// load a properties file
			prop.load(input);
			value = prop.getProperty(property);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return value;

	}
}