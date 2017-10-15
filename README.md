# TigerSpike QA Automation Task

Goal:

Create a testing framework using any automation testing tool eg: Appium in java to automate the following scenario for the Flickr Browser app provided:
Scenario :
Check if the list for the searched text is displayed correctly on the screen by validating the list item titles (image check not needed) with that of what the api endpoint ( https://api.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=1&tags ={searched_text} ) gets back.


# Tech Stack details:

Created couple of tests for the Repayments Calculator and Borrowing Calculator UI component in the manner it is intended.

1. Project Type: Maven
2. Tool: 
    Appium Java Client (v 5.0.4), Appium Server (v 1.6.5 and 1.7.1) and Apache HTTP Client (v 4.5.3 for API Testing)
3. Program Language: Java (v 1.8 SDK)
4. Testing Framework: Cucumber BDD with JUnit Test Runner
5. Test Framework Design: Page Object Model (POM) with Page Factory Pattern
6. Logging: Log4j
7. Reproting: Default Cucumber HTML Reporting
8. Test App: Flick Browser iOS Native App
9. Device Tested: iOS Simulators (iPhone 5s, iPhone 6 Plus, iPhone 7 Plus)
10. Platform: macOS Sierra (v 10.12.6)

# Steps to run the Tetsts:

Firstly need to update the config.properties file located under "src/test/resources/" with the below details
1) App = Local Path of the App 
2) StartAppiumServerProgrammatically = No (we need to start the Appium Server Manually)
   StartAppiumServerProgrammatically = Yes (Appium and Node.js should be installed under "/usr/local/bin/" dir)
   
Run the the JUit Test Runner Class located at "src/test/java/com/flickr/tests/FlickrTest.java" as JUnit Test, test execution report will be generated under "target/cucumber_test_report" folder of the project.
Added Default Test Execution Report: Default_Cucumber_TestExecution_Report.html
