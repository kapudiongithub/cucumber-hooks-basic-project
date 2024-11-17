Feature: Login functionality

  Scenario: Successful login with valid credentials
    Given I open the Sauce Demo login page
    When I enter valid credentials
    And I click on the login button
    Then I should be redirected to the products page
