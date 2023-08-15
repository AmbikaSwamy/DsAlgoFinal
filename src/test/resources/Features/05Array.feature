@Array
Feature: Testing the functionality of Array Module

  Scenario: User is testing Array Module
    Given User is on the homepage
    When User logs in with valid credentials
    When User clicks Get Started button under Array Column
    
    When User clicks on Arrays in Python link
    When User clicks on Try Here button on Arrays in Python page
    When User inputs a python code on tryEditor page of Array
    And User clicks on Run button
    And User navigates back to Array Page
    
    When User clicks on Arrays Using List link
    When User clicks on Try Here button on Arrays Using List page
    When User inputs a python code on tryEditor page of Array
    And User clicks on Run button
    And User navigates back to Array Page
    
    When User clicks on Basic Operations in Lists link
    When User clicks on Try Here button on Basic Operations in Lists page
    When User inputs a python code on tryEditor page of Array
    And User clicks on Run button
    And User navigates back to Array Page
    
    When User clicks on Applications of Array link
    When User clicks on Try Here button on Applications of Array page
    When User inputs a python code on tryEditor page of Array
    And User clicks on Run button
    And User navigates back
    
    When User clicks on Practice Question link    
    When the user clicks on Search the array link
    When User inputs a python code on tryEditor page for Practice Question1
    And User clicks on Run button for Practice Question1 
    When User clicks on Submit button for Practice Question1
    Then User receives a message Submitted successfully
    And User navigates back to Array Practice page
    
    When the user clicks on Max Consecutive Ones link

    When User inputs a python code on tryEditor page for Practice Question2
    And User clicks on Run button for Practice Question2
    When User clicks on Submit button for Practice Question2
    Then User receives a message Submitted successfully
    And User navigates back to Array Practice page
    
    When the user clicks on Find Numbers with Even Number of Digits link
    When User inputs a python code on tryEditor page for Practice Question3
    And User clicks on Run button for Practice Question3
    When User clicks on Submit button for Practice Question3
    Then User receives a message Submitted successfully
    And User navigates back to Array Practice page
    
    When the user clicks on Squares of a Sorted Array link
    When User inputs a python code on tryEditor page for Practice Question4
    And User clicks on Run button for Practice Question4
    When User clicks on Submit button for Practice Question4
    Then User receives a message Submitted the code successfully
    
    And User navigates back to Home page