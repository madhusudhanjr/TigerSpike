package com.flickr.stepdefs;

import java.util.List;

import org.apache.log4j.Logger;

import com.flickr.utils.CommonUtil;

import cucumber.api.java.en.Then;

/**
 * Verifications Class contains the Step Definitions for the Cucumber Keyword "Then"
 * 
 * @author mjr (madhusudhan.jr@gmail.com)
 */
public class Verifications {

	/**
	 * Class variable which holds the reference to the Logger Object
	 */
	static Logger logger = Logger.getLogger(Verifications.class);
	
	private List<String> uiTitlesList = null;
	private List<String> apiTitlesList = null;

	@Then("^I check if the list for the searched text is displayed correctly on the screen$")
	public void i_check_if_the_list_for_the_searched_text_is_displayed_correctly_on_the_screen() {

		uiTitlesList = CommonUtil.getUITitleList();
	}

	@Then("^validate the list of item titles with that of the api endpoint$")
	public void validate_the_list_item_titles_with_that_of_the_api_endpoint() {

		apiTitlesList = CommonUtil.getTitleListByAPI();
		CommonUtil.verifyTitlesList(uiTitlesList, apiTitlesList);

		logger.info("\n******Test Scenario Execution Ends*******");
	}

}