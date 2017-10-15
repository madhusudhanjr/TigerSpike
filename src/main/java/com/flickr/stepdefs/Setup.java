package com.flickr.stepdefs;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.flickr.pageobjects.SearchPage;
import com.flickr.utils.CommonUtil;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

/**
 * Setup Class contains the Step Definitions for the Cucumber Keyword "Given"
 * 
 * @author mjr (madhusudhan.jr@gmail.com)
 */
public class Setup {
	
	/**
	 * Class variable which holds the reference to the Logger Object
	 */
	static Logger logger = Logger.getLogger(Setup.class);

	@Before
	public static void setup() {

		PropertyConfigurator.configure("src/main/resources/Logger/log4j.properties");
		logger.info("\n******Test Feature Execution Satrted*******");
	}

	@After
	public static void teardown() {
		CommonUtil.quitDriver();
		logger.info("\n******Test Feature Execution Ends*******");
	}
	
	@Given("^A Flickr Browser app$")
	public void a_Flickr_Browser_app()  {
		
		logger.info("\n******Test Scenario Execution Satrted*******");
	}

	@Given("^I launch the app on \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_launch_the_app_on_and_and(String platform, String version, String deviceName) {
	
		CommonUtil.setUpDriver(platform, version, deviceName);
		CommonUtil.m_homePage = new SearchPage(CommonUtil.m_driver);
		
	}

}