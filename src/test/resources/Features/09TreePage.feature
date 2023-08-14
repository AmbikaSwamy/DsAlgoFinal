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
Feature: TREE OPERATIONS FEATURE
  I want to use this template for my feature file

  @tag1
  Scenario: Tree Operations
    Given User is on the homepage
    When User logs in with valid credentials
    And Click the GetStarted then redirected to Tree Page 
    
    Then Click on the Overview of Trees
    And User redirected to Tree Overview Page and clicks the TryHere
    Then User redirected to the Tree Editor Page and works on code snippet
    When Clicked on the Overview of Trees RunButton and verify the output #Try a PrintHello statement here.
    
    Then Click on the Terminologies of Trees
    And User redirected to Tree Terminologies and clicks the TryHere
    Then User redirected to the Tree Editor Page and works on code snippet
    When Clicked on the Terminologies of Trees RunButton and verify the output #Try a PrintHello statement here.
    
    Then Click on the Types of Trees
    And User redirected to Tree Types Page and clicks the TryHere
    Then User redirected to the Tree Editor Page and works on code snippet
    When Clicked on the Types of Trees RunButton and verify the output #Try a PrintHello statement here.
    
    Then Click on the Tree Traversals
    And User redirected to Tree Traversals Page and clicks the TryHere
    Then User redirected to the Tree Editor Page and works on code snippet
    When Clicked on the Tree Traversals RunButton and verify the output #Try a PrintHello statement here.
    
    Then Click on the Trees Traversal Illustration
    And User redirected to Tree Traversal Illustration Page and clicks the TryHere
    Then User redirected to the Tree Editor Page and works on code snippet
    When Clicked on the Trees Traversal Illustration RunButton and verify the output #Try a PrintHello statement here.
    
    Then Click on the Binary Trees
    And User redirected to Binary Tree Page and clicks the TryHere
    Then User redirected to the Tree Editor Page and works on code snippet
    When Clicked on the Binary Trees RunButton and verify the output #Try a PrintHello statement here.
    
    Then Click on the Types of Binary Trees
    And User redirected to Binary Tree Types Page and clicks the TryHere
    Then User redirected to the Tree Editor Page and works on code snippet
    When Clicked on the Types of Binary Trees RunButton and verify the output #Try a PrintHello statement here.
    
    Then Click on the Implementation of Trees in Python
    And User redirected to Tree Python Impl Page and clicks the TryHere
    Then User redirected to the Tree Editor Page and works on code snippet
    When Clicked on the Implementation of Trees in Python RunButton and verify the output #Try a PrintHello statement here.
    
    Then Click on the Binary Trees Traversal
    And User redirected to Binary Tree Traversal Page and clicks the TryHere
    Then User redirected to the Tree Editor Page and works on code snippet
    When Clicked on the Binary Trees Traversal RunButton and verify the output #Try a PrintHello statement here.
    
    Then Click on the Impl of Binary Trees
    And User redirected to Impl of Binary Trees Page and clicks the TryHere
    Then User redirected to the Tree Editor Page and works on code snippet
    When Clicked on the Impl of Binary Trees RunButton and verify the output #Try a PrintHello statement here.
    
    Then Click on the Appl of Binary Trees
    And User redirected to  Appl of Binary Trees Page and clicks the TryHere
    Then User redirected to the Tree Editor Page and works on code snippet
    When Clicked on the Appl of Binary Trees RunButton and verify the output #Try a PrintHello statement here.
    
    Then Click on the Binary Search Trees
    And User redirected to Binary Search Tree Page and clicks the TryHere
    Then User redirected to the Tree Editor Page and works on code snippet
    When Clicked on the Binary Search Trees RunButton and verify the output #Try a PrintHello statement here.
    
    Then Click on the Impl of BinSrch Tree
    And User redirected to Impl of BinSrch Tree Page and clicks the TryHere
    Then User redirected to the Tree Editor Page and works on code snippet
    When Clicked on the Impl of BinSrch Tree RunButton and verify the output #Try a PrintHello statement here.
    
    Then Click on the Tree PracticeQuestion
    And User redirected to PractivePage and do nothing
    And go back to homePage
    
    And close the browser