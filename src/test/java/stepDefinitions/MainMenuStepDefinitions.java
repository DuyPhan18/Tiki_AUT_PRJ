package stepDefinitions;

import core.BaseTest;
import core.Constants;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.HomePage;

public class MainMenuStepDefinitions extends BaseTest {
    private HomePage homePage;
    public MainMenuStepDefinitions(){
        homePage = new HomePage(getDriver());
    }
    @Then("User shoud see icons of {string} at menu bar")
    public void user_shoud_see_icons_of_at_menu_bar(String menuItemName) {
        Assert.assertTrue(homePage.verifyMenuIconDisplayed(menuItemName), menuItemName + "icon" + Constants.IS_NOT_DISPLAY);
    }
}
