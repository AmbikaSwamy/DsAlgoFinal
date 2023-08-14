#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Launch the heroku web page.

  @LaunchWebPage
  Scenario: User launches the web page
    # Change it to a more readable statement instead of "constants".
    Given the constants are initialized  
    When user launches the web page
    And clicks the get_started button
    Then go to the home page
	  Given user clicks the page title
	  Then user clicks the DS dropdown
	  Then user clicks the DS-IntroButton
	  Then user clicks the ArrayButton
	  Then user clicks the LinkedListButton
	  Then user clicks the StackButton
	  Then user clicks the QueueButton
		Then user clicks the TreeButton
	  Then user clicks the GraphButton
	  Then user clicks the RegisterLink
	  Then user clicks the SignInLink
		And close the browser  