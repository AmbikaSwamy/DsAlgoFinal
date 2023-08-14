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
@LogIn
Feature: Sign Into the website with valid credentials
  User trying to log into the website

  @LogIntoWebsite
  Scenario: Sign into website
  
  	Given Launchh the browser
    Then Openn the website
    And Readd the XL file values
    Then Checkk for valid credentials
    Then Loginn if valid