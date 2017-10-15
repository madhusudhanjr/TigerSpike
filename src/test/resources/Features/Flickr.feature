#Author: madhusudhan.jr@gmail.com
Feature: TigerSpike QA Automation Task on Flickr Browser iOS App
  As an Flickr Browser App Tester
  I should get the list of images displayed for the search Keyword
  So that I can verfiy the list of images by titles with that from the API endpoint

  Scenario Outline: Search for a keyword and verify the listed item titles
    Given A Flickr Browser app
    And I launch the app on "<Platform>" and "<Version>" and "<Device>"
    When I search for text "<SearchKeyword>"
    Then I check if the list for the searched text is displayed correctly on the screen
    And validate the list of item titles with that of the api endpoint

    Examples: TestData
      | Platform | Version | Device        | SearchKeyword |
      | iOS      |    10.3 | iPhone 7 Plus | Kingsman      |
