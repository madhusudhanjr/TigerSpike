package com.flickr.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.flickr.apisources.SearchData;
import com.flickr.pageobjects.SearchPage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

/**
 * CommonUtil class contains the reusable functions which can be used across the
 * project
 * 
 * @author mjr (madhusudhan.jr@gmail.com)
 */
public class CommonUtil {

	/**
	 * Class variable which holds the reference to the Logger Object
	 */
	static Logger logger = Logger.getLogger(CommonUtil.class);

	/**
	 * Class variable which holds the reference to the WebDriver Object
	 */
	public static AppiumDriver<MobileElement> m_driver;
	private static AppiumDriverLocalService service;
	public static SearchPage m_homePage = null;
	private static String m_searchKey = "";

	/**
	 * Method to start Appium server programmatically
	 * 
	 * @throws Exception
	 */
	private static void startAppiumServer() {

		service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File("/usr/local/bin/node"))
						.withAppiumJS(new File("/usr/local/bin/appium")).withIPAddress("127.0.0.1").usingPort(4723));

		service.start();

	}

	/**
	 * This method used to setup the Appium Driver
	 * 
	 * @throws MalformedURLException
	 */
	public static void setUpDriver(String platform, String version, String deviceName) {

		logger.info("Setup Appium Driver with Platform:: " + platform + ", Version:: " + version + ", DeviceName:: "
				+ deviceName);
		logger.info("App Location:: " + getProperty("App"));

		if (getProperty("StartAppiumServerProgrammatically").equals("Yes")) {

			logger.info("\n******Starting Appium Server Programmatically*******\n");
			startAppiumServer();

		}

		DesiredCapabilities capabilities = new DesiredCapabilities();

		/**
		 * Below Capabilities to be added for executing tests locally
		 **/
		capabilities.setCapability("automationName", "XCUITest");
		capabilities.setCapability("platformName", platform);
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("platformVersion", version);
		capabilities.setCapability("app", getProperty("App"));
		capabilities.setCapability("newCommandTimeout", 600);
		capabilities.setCapability("launchTimeout", 90000);
		capabilities.setCapability("autoAcceptAlerts", true);

		URL appiumURL;
		try {

			if (null != service && service.getUrl() != null)
				appiumURL = new URL(service.getUrl().toString());
			else
				appiumURL = new URL("http://127.0.0.1:4723/wd/hub");

			m_driver = new IOSDriver<MobileElement>(appiumURL, capabilities);
			m_driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method is used to perform search action
	 * 
	 * @param searchKeyword
	 */
	public static void perfromSearchAction(String searchKeyword) {

		logger.info("Perform Search Action with text:: " + searchKeyword);
		m_searchKey = searchKeyword;

		m_homePage.enterSearchText(searchKeyword);
		m_driver.getKeyboard().pressKey(Keys.RETURN);

	}

	public static List<String> getUITitleList() {

		return m_homePage.getTitlesList();

	}

	public static List<String> getTitleListByAPI() {

		return getTitlesByAPIEndPoint(m_searchKey);

	}

	/**
	 * This method compare two lists and asserts the result
	 * 
	 * @param uiTitleDetails
	 * @param serviceTitleDetails
	 */
	public static void verifyTitlesList(List<String> uiTitleDetails, List<String> serviceTitleDetails) {

		logger.info("Verify and Assert Titles List");

		Assert.assertTrue("UI and API Title List size mismatch!!", uiTitleDetails.size() == serviceTitleDetails.size());
		logger.info("UI and API Title List size matched!!");

		List<String> l1 = new ArrayList<String>();
		l1.addAll(uiTitleDetails);

		List<String> l2 = new ArrayList<String>();
		l2.addAll(serviceTitleDetails);

		l2.removeAll(uiTitleDetails);
		l1.removeAll(serviceTitleDetails);

		Assert.assertTrue("API GET Title list contains more Titles compared to displyed in the UI :: " + l2,
				l2.isEmpty());
		Assert.assertTrue("UI contains more Titles compared to API GET UI Titles list:: " + l1, l1.isEmpty());

		logger.info("Item Title contents verified successfully!!");
	}

	/**
	 * This method is used to get the Title List using API End Point by HTTP
	 * Client
	 * 
	 * @param searchText
	 * @return
	 */
	public static List<String> getTitlesByAPIEndPoint(String searchText) {

		logger.info("GET Title List using API End Point");
		String url = getProperty("APIEndPoint") + searchText;

		List<String> titles = new ArrayList<String>();
		try {

			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);

			// add request header
			request.addHeader("content-type", ContentType.APPLICATION_JSON.toString());
			HttpResponse response = client.execute(request);
			ResponseHandler<String> handler = new BasicResponseHandler();

			String body = handler.handleResponse(response);
			int code = response.getStatusLine().getStatusCode();
			Assert.assertTrue(code == 200);

			JSONObject jsonObject = new JSONObject(body);
			SearchData accountDetails = new Gson().fromJson(jsonObject.toString(), new TypeToken<SearchData>() {
			}.getType());

			for (SearchData.Item item : accountDetails.items) {

				titles.add(item.title);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Titles List Size by API:: " + titles.size());

		return titles;
	}

	/**
	 * This method is used to quit the Driver
	 */
	public static void quitDriver() {

		logger.info("Quit Driver");
		if (null != m_driver)
			m_driver.quit();

		if (getProperty("StartAppiumServerProgrammatically").equals("Yes")) {
			if (null != service)
				service.stop();
		}
	}

	public static String getProperty(String property) {

		return PropertiesFileParser.getProperty(property);
	}
}
