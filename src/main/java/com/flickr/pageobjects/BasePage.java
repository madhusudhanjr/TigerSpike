/*
 * Copyright 2014-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.flickr.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * A base for all the pages within the suite
 * 
 * @author mjr (madhusudhan.jr@gmail.com)
 */
public abstract class BasePage {

	/**
	 * The driver
	 */
	protected final AppiumDriver driver;

	/**
	 * A base constructor that sets the page's driver
	 *
	 * The page structure is being used within this test in order to separate
	 * the page actions from the tests.
	 *
	 * Please use the AppiumFieldDecorator class within the page factory. This
	 * way annotations like @iOSFindBy within the page objects.
	 *
	 * @param driver
	 *            the appium driver created in the beforesuite method.
	 */
	protected BasePage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), this);
	}

}