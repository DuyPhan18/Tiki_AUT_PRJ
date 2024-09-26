package stepDefinitions;

import core.BaseTest;
import core.Constants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;

public class StepDefinitions extends BaseTest {
    private HomePage homePage;

    public StepDefinitions(){
        homePage = new HomePage(getDriver());
    }

    @Given("User open Tiki application")
    public void user_open_tiki_application() {
        Assert.assertTrue(homePage.isHomeDisplay(), "HomePage" + Constants.IS_NOT_DISPLAY);
    }
    @Given("User click to Danh mục")
    public void user_click_to_danh_mục() {
        homePage.tapOnCategoryBtn();
    }
    @When("User scroll to {string} in left menu")
    public void user_scroll_to_in_left_menu(String itemName) {
        homePage.tapToMenuLeftItem(itemName);
    }
    @When("User scroll to panel {string}")
    public void user_scroll_to_panel(String panelName) {
        homePage.scrollToPannelItem(panelName);
    }
    @Then("User should see {int} items in panel {string}")
    public void user_should_see_items_in_panel(Integer total, String panelName) {
        Assert.assertEquals(homePage.verifyNumberItemDisplay(panelName), total, "Not match");
    }
}
