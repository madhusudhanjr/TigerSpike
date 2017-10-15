package com.flickr.stepdefs;

import com.flickr.utils.CommonUtil;

import cucumber.api.java.en.When;

/**
 * Actions Class contains the Step Definitions for the Cucumber Keyword "When"
 * 
 * @author mjr (madhusudhan.jr@gmail.com)
 */
public class Actions {

	@When("^I search for text \"([^\"]*)\"$")
	public void i_search_for_text(String searchKeyword) {

		CommonUtil.perfromSearchAction(searchKeyword);
	}

}