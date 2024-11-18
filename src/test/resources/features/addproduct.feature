
Feature: Add product functionality

  @multipleProducts
  Scenario: Successfully add multiple products to the cart
    Given I open the Sauce Demo login page
    When I enter valid credentials
    And I click on the login button
    Then I should be redirected to the products page
    When I add the following products to the cart:
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    Then the cart should show 2 items

    @cartItems
  Scenario: Successfully verify items in the cart
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


