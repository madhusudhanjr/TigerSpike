package com.flickr.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * FlickrTest Class is a JUnit Cucumber TestRunner
 *  
 * @author mjr (madhusudhan.jr@gmail.com)
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber_test_report" }, features = {
		"classpath:Features/Flickr.feature" }, glue = { "com.flickr.stepdefs" })
public class FlickrTest {

}
