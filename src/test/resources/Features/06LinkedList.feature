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
Feature: Test the features of a Linked List algorithm
  I want to use this template for my feature file

  @tag1
  Scenario: LINKED LIST CONCEPTS
    Given User is on the homepage
    When User logs in with valid credentials
    And Click the GetStarted then redirected to LL Page 
    
    Then Click on the Intro Link
    And User redirected to LLIntroPage and clicks the TryHere
    Then User redirected to the Editor Page and works on code snippet
    When Clicked on the RunButton and verify the output #Try a PrintHello statement here.
    
    Then Click on Creating LL
    And User redirected to CreateLLPage and clicks the TryHere
    Then User redirected to the CreateLL Editor Page and works on code snippet
    When Clicked on the CreateLL RunButton and verify the output #Try a code snippet here.
    
    Then Click on Types of LL
    And User redirected to LLTypesPage and clicks the TryHere
    Then User redirected to the TypesofLL Editor Page and works on code snippet
    When Clicked on the TypesofLL RunButton and verify the output #Try a code snippet for single and double LL here.
		
		Then Click on Implement LL in python
		And User redirected to ImplementLLPage and clicks the TryHere
    Then User redirected to the ImplementLL Editor Page and works on code snippet
    When Clicked on the ImplementLL RunButton and verify the output #Try a code snippet here.
    
    Then Click on Traversal
    And User redirected to TraversePage and clicks the TryHere
    Then User redirected to the Traversal Editor Page and works on code snippet
    When Clicked on the Traversal RunButton and verify the output #Try a code snippet here. available in the same web page
    
    Then Click on Insertion
    And User redirected to InsertInLLPage and clicks the TryHere
    Then User redirected to the Insertion Editor Page and works on code snippet
    When Clicked on the Insertion RunButton and verify the output #Try a code snippet here.
    
    Then Click on Deletion
    And User redirected to DeleteFromLLPage and clicks the TryHere
    Then User redirected to the Deletion Editor Page and works on code snippet
    When Clicked on the Deletion RunButton and verify the output #Try a code snippet here.    
    
    Then Click on the PracticeQn
    And User redirected to PractivePage and do nothing
    And go back to homePage
    And close the browser