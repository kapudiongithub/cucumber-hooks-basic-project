@dataTables
Feature: Shopping cart and checkout functionality

  Scenario: Add items to cart, verify them, and checkout
    Given I open the Sauce Demo login page
    When I enter credentials "standard_user" and "secret_sauce"
    And I click on the login button
    Then I should be redirected to the products page
    When I add the following products to the cart:
      | Product Name          |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    Then the cart should contain the following products:
      | Product Name          |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    When I proceed to checkout with the following details:
      | First Name  | John  |
      | Last Name   | Doe   |
      | Postal Code | 12345 |
    Then the total price should match the sum of the product prices
