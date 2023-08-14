Feature: Testing Data Structures - Introduction page

  Scenario: Testing Data Structures Introduction
    Given User is on the homepage
    When User logs in with valid credentials
    When User clicks the Getstarted button of Data Structures - Introduction 
    Then User is directed to Data Structures -Introduction Page
    When  User clicks on the Time Complexity link
    Then User is directed to Time Complexity Page
    When User clicks on DSTry here button
    Then User is directed to DStryEditor Page     
    And User clicks on DSRun button
    And Navigates back to Time Complexity page
    When User clicks on DSPractice Questions link
    Then User is directed to DSPractice Questions page
    And go back to homePage
    And close the browser
    