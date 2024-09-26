package stepDefinitions;

import core.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;

public class CategoryStepDefinitions extends BaseTest {
    private HomePage homePage;
    public CategoryStepDefinitions(){
        homePage = new HomePage(getDriver());
    }

    @Then("User should see {int} sub categories in panel {string}")
    public void user_should_see_sub_categories_in_panel(Integer int1, String panelName) {
        Assert.assertEquals(homePage.verifyNumberItemDisplay(panelName), int1,"Not match");
    }

}
