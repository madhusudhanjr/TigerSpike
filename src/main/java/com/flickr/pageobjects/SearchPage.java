package com.flickr.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

/**
 * SearchPage Class contains info on MobileElements and its Operations
 * 
 * @author mjr (madhusudhan.jr@gmail.com)
 */
public class SearchPage extends BasePage {

	/**
	 * Class variable which holds the reference to the Logger Object
	 */
	static Logger logger = Logger.getLogger(SearchPage.class);

	@iOSFindBy(xpath = "//XCUIElementTypeSearchField")
	MobileElement searchTextBox;

	@iOSFindBy(xpath = "//XCUIElementTypeStaticText")
	List<MobileElement> titles;

	public SearchPage(AppiumDriver driver) {

		super(driver);
	}

	/**
	 * This method is used to enter the search text
	 * 
	 * @param searchText
	 */
	public void enterSearchText(String searchText) {

		logger.info("Enter Search Text :: " + searchText);
		searchTextBox.sendKeys(searchText);
	}

	/**
	 * This method is used to get the titles list
	 * 
	 * @return List<String>
	 */
	public List<String> getTitlesList() {

		logger.info("Get UI Titles List");

		List<String> titlesList = new ArrayList<String>();

		for (MobileElement el : titles) {
			titlesList.add(el.getText());
		}

		logger.info("Titles List Size by UI:: " + titlesList.size());
		
		return titlesList;
	}

}
