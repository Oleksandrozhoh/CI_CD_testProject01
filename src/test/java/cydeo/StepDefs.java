package cydeo;

import static org.junit.Assert.fail;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;


public class StepDefs {

    @Given("^I am on the home page$")
    public void i_am_on_the_home_page() throws Throwable {
        Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().get("http://etsy.com");

    }

    @When("^I search for \"([^\"]*)\"$")
    public void i_search_for(String search) throws Throwable {
        WebElement inputBox = Driver.getDriver().findElement(By.cssSelector("[id*='search-query']"));

        // Checking the location of an input box by using Point java class reference and getting the location with .getLocation() Selenium method
        Point elementLocation = inputBox.getLocation();
        // can print the coordinates of x and y with Point class .getX() and .getY() methods
        System.out.println("X = "+elementLocation.getX());
        System.out.println("Y = "+elementLocation.getY());

        inputBox.sendKeys(search + Keys.ENTER);


    }

    @Then("^I should see the results$")
    public void i_should_see_the_results() throws Throwable {
        Thread.sleep(2000);
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("search"));
    }

    @Then("^I should see more results$")
    public void i_should_see_more_results() throws Throwable {
        Thread.sleep(2000);
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("search"));
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        Driver.closeDriver();
    }

}
