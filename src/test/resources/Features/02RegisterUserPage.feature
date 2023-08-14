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
@Register
Feature: Register a user
	User trying to register in the website
  
    @RegisterUserDataDriven
  Scenario Outline:  Data driven concept of Registering with a valid username and password
  
  	Given the constants are initialized
 		Given user launches the browser
    Then user opens the URL "https://dsportalapp.herokuapp.com/register"
    When user enters new "<username>" and "<password1>" and "<password2>"
    And click on register
    When Page source contains "New Account Created. You are logged in as "
    Then click on sign out
    And close the browser
    
    Examples:
    | username		| password1			| password2					|
    |							|								|										|
    |							| password			| password					|
    | username		|								|										|
    | username		|	password			| 									|
    | username		|								| password					|
    | username		| password			| pass							|
    | user1234		| password			| pass							|
    | username		|	username			| 									|
    | 123456789		| 						  | 									|
    | user				|								| password					|
    | user12			|								| password					|
    | user.123		|								| password					|
    | user12()	  |								| password					|
#   | userDeAm		| DeAmPass			| DeAmPass					| -> User Created on 06/17/2023