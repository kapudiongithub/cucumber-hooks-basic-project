package steps;

public class LoginSteps {

/*
    WebDriver driver = Hooks.getDriver();

    @Given("I open the Sauce Demo login page")
    public void iOpenTheSauceDemoLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("I enter valid credentials")
    public void iEnterValidCredentials() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
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

    @When("I add the product {string} to the cart")
    public void iAddTheProductToTheCart(String productName) {
        // Locate the product container by its name
        String productXpath = "//div[@class='inventory_item_name' and text()='" + productName + "']/ancestor::div[@class='inventory_item']//button";
        driver.findElement(By.xpath(productXpath)).click();

        /*
        //div[@class='inventory_item_name' and text()='PRODUCT_NAME']: Finds the product by its name.
        /ancestor::div[@class='inventory_item']: Traverses up the DOM tree to the main product container.
        //button: Locates the "Add to Cart" button within the container

        Dynamic Selection in Action
        If you run the updated test and provide the name of the product (e.g., "Sauce Labs Backpack"), the code will:
        Search for the product by name.
        Locate its corresponding "Add to Cart" button.
        Click the button to add the product to the cart.
        This approach allows you to select any product dynamically based on the name provided in the test scenario. Let me know if you'd like further enhancements!
         *
    }

    @Then("the cart should show {int} item")
    public void theCartShouldShowItem(int itemCount) {
        // Step to Verify the Cart Count
        String cartCount = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals("The cart count is incorrect", String.valueOf(itemCount), cartCount);
    }

    */

}
