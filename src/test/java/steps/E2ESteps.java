package steps;

import hooks.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class E2ESteps {

    WebDriver driver = Hooks.getDriver();

    @Given("I open the Sauce Demo login page")
    public void iOpenTheSauceDemoLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("I enter credentials {string} and {string}")
    public void iEnterCredentials(String username, String password) {
        // Locate username and password fields and enter the values
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.clear();
        usernameField.sendKeys(username);

        passwordField.clear();
        passwordField.sendKeys(password);

        System.out.println("Entered Username: " + username);
        System.out.println("Entered Password: " + password);
    }

    @And("I click on the login button")
    public void iClickOnTheLoginButton() {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("I should be redirected to the products page")
    public void iShouldBeRedirectedToTheProductsPage() {
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("Failed to redirect to the products page", expectedUrl, actualUrl);
    }

//    @When("I add the following products to the cart:")
//    public void iAddTheFollowingProductsToTheCart(List<String> productNames) {
//        for (String productName : productNames) {
//            // Locate the product container by its name and add it to the cart
//            String productXpath = "//div[@class='inventory_item_name' and text()='" + productName + "']/ancestor::div[@class='inventory_item']//button";
//            driver.findElement(By.xpath(productXpath)).click();
//        }
//    }

    @When("I add the following products to the cart:")
    public void iAddTheFollowingProductsToTheCart(DataTable dataTable) {
        // Extract the product names from the DataTable
        List<String> productNames = dataTable.asMaps(String.class, String.class)
                .stream()
                .map(row -> row.get("Product Name"))
                .collect(Collectors.toList());

        for (String productName : productNames) {
            System.out.println("Adding product: " + productName);

            // Updated and dynamic XPath
            String productXpath = "//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button[contains(text(), 'Add to cart')]";

            // Wait for the element to be present and click
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(productXpath)));
            WebElement addToCartButton = driver.findElement(By.xpath(productXpath));
            addToCartButton.click();
        }
    }

    @Then("the cart should show {int} items")
    public void theCartShouldShowItems(int itemCount) {
        String cartCount = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals("The cart count is incorrect", String.valueOf(itemCount), cartCount);
    }

    @Then("the cart should contain the following products:")
    public void theCartShouldContainTheFollowingProducts(DataTable dataTable) {
        // Extract the expected product names from the DataTable
        List<String> expectedProducts = dataTable.asMaps(String.class, String.class)
                .stream()
                .map(row -> row.get("Product Name"))
                .collect(Collectors.toList());

        // Navigate to the cart page
        driver.findElement(By.className("shopping_cart_link")).click();

        // Extract the names of products in the cart
        List<WebElement> cartItems = driver.findElements(By.className("inventory_item_name"));
        List<String> actualProducts = cartItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        // Verify that the cart contains all the expected products
        Assert.assertTrue("The cart does not contain all expected products", actualProducts.containsAll(expectedProducts));

        // Optionally, ensure the count matches
        Assert.assertEquals("Mismatch in the number of items in the cart", expectedProducts.size(), actualProducts.size());
    }

    @When("I proceed to checkout with the following details:")
    public void iProceedToCheckoutWithTheFollowingDetails(DataTable dataTable) {
        // Convert DataTable to Map for easier access
        Map<String, String> userDetails = dataTable.asMap(String.class, String.class);

        // Click the "Checkout" button on the cart page
        driver.findElement(By.id("checkout")).click();

        // Fill out the checkout information form
        driver.findElement(By.id("first-name")).sendKeys(userDetails.get("First Name"));
        driver.findElement(By.id("last-name")).sendKeys(userDetails.get("Last Name"));
        driver.findElement(By.id("postal-code")).sendKeys(userDetails.get("Postal Code"));

        // Click the "Continue" button to proceed to the summary page
        driver.findElement(By.id("continue")).click();
    }

    @Then("the total price should match the sum of the product prices")
    public void theTotalPriceShouldMatchTheSumOfTheProductPrices() {
        // Get the prices of individual items on the summary page
        List<WebElement> itemPrices = driver.findElements(By.className("inventory_item_price"));
        double totalItemPrice = itemPrices.stream()
                .mapToDouble(price -> Double.parseDouble(price.getText().replace("$", "")))
                .sum();

        // Get the tax value from the summary page
        WebElement taxElement = driver.findElement(By.className("summary_tax_label"));
        double tax = Double.parseDouble(taxElement.getText().replace("Tax: $", ""));

        // Calculate the expected total price
        double expectedTotalPrice = totalItemPrice + tax;

        // Get the total price displayed on the summary page
        WebElement totalPriceElement = driver.findElement(By.className("summary_total_label"));
        double displayedTotalPrice = Double.parseDouble(totalPriceElement.getText().replace("Total: $", ""));

        System.out.println("Subtotal " + totalItemPrice);
        System.out.println("Tax: " + tax);
        System.out.println("Calculated Total: " + expectedTotalPrice);
        System.out.println("Displayed Total: " + displayedTotalPrice);

        // Verify that the displayed total matches the expected total
        Assert.assertEquals("Total price does not match the sum of item prices and tax", expectedTotalPrice, displayedTotalPrice, 0.01);
    }
}
