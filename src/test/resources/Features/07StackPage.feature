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
Feature: STACK OPERATIONS FEATURE
  I want to use this template for my feature file

Background: Stack Operations
    Given User is on the homepage
    When User logs in with valid credentials
    And Click the GetStarted then redirected to Stack Page 

    Then Click on the Operations in Stack
    And User redirected to Stack Operations Page and clicks the TryHere

  @tag1
  Scenario: Stack Operations - Valid working Features
#    Given User is on the homepage
#    When User logs in with valid credentials
#    And Click the GetStarted then redirected to Stack Page 
    
#    Then Click on the Operations in Stack
#    And User redirected to Stack Operations Page and clicks the TryHere
    Then User redirected to the Stack Editor Page and works on code snippet
    When Clicked on the Stack Op RunButton and verify the output #Try a PrintHello statement here.
    
    Then Click on the Implementations in Stack
    And User redirected to Implementations Page and clicks the TryHere
    Then User redirected to the Stack Editor Page and works on code snippet
    When Clicked on the Stack Impl RunButton and verify the output #Try a PrintHello statement here.
    
    Then Click on the Applications in Stack
    And User redirected to Applications Page and clicks the TryHere
    Then User redirected to the Stack Editor Page and works on code snippet
    When Clicked on the Stack App RunButton and verify the output #Try a PrintHello statement here.
    
    Then Click on the PracticeQuestion
    And User redirected to PractivePage and do nothing
    And go back to homePage
    And close the browser
    
Scenario: Stack Operations - Invalid code testing
    Then User redirected to the Stack Editor Page and works on invalid code snippet
    When Clicked on the Stack Op RunButton and verify the alert #Try an Invalid Print statement here.
    And go back to homePage
    And close the browser