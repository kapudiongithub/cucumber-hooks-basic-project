@checkout
Feature: Checkout functionality

  Scenario: Successfully checkout items and verify the total price
    Given I open the Sauce Demo login page
    When I enter valid credentials
    And I click on the login button
    Then I should be redirected to the products page
    When I add the following products to the cart:
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    Then the cart should contain the following products:
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    When I proceed to checkout with the following details:
      | First Name  | Kamo   |
      | Last Name   | Lebelo |
      | Postal Code | 12345  |
    Then the total price should match the sum of the product prices
