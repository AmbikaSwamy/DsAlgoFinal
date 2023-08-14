@Queue
Feature: Testing the functionality of Queue Module

  Scenario: QUEUE OPERATIONS
    Given User is on the homepage
    When User logs in with valid credentials
    When User clicks Get Started button under Queue Column
 #   Then User is directed to Queue page

    When User clicks on Queue in Python link
    When User clicks on Try Here button on Queue in Python page
    When User inputs a python code on tryEditor page of Queue
    And User clicks on Run button
    And User navigates back to Queue Page
    
#    Then User is directed to implementation-lists page
    When User clicks on Implementation using collections deque link
    When User clicks on Try Here button on Implementation using collections deque page
    When User inputs a python code on tryEditor page of Queue
    And User clicks on Run button
    And User navigates back to Queue Page
    
#    Then User is directed to implementation-collections page
    When User clicks on Implementation using array link
    When User clicks on Try Here button on Implementation using array page
    When User inputs a python code on tryEditor page of Queue
    And User clicks on Run button
    And User navigates back to Queue Page
    
#    Then User is directed to Implementation-array page
    When User clicks on Queue Operations link
    When User clicks on Try Here button on Queue Operations page
    When User inputs a python code on tryEditor page of Queue
    And User clicks on Run button
#    And User navigates back to Queue Page

    Then User is directed to QueueOp page
#    Then User is redirected to tryEditor page of DS-Algo portal
		Then Click on the Queue PracticeQuestion
    And User redirected to PractivePage and do nothing
    
    And User navigates back to Home page
