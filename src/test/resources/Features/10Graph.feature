Feature: Testing Graph - Page

  Scenario: GRAPH OPERATIONS
    Given User is on the homepage
    When User logs in with valid credentials
    When User clicks the Getstarted button of Graph
    
    Then User is directed to Graph Page    
    When User clicks on the Graph link
    
    Then User is directed to GraphIntro Page    
    When User clicks on Try here button    
    Then User is directed to tryEditor Page
    And User clicks on Run button    
    And Navigates back to GraphIntro page
    
    When User clicks on Graph Representations link
    When User clicks on Try here button
    Then User is directed to tryEditor Page
    And User clicks on Run button    
    And Navigates back to Graph Representations page
    
    When User clicks on GraphPractice Questions link
    Then User is directed to GraphPractice Questions page
    And go back to homePage
    And close the browser
