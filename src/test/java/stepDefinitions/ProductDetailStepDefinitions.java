package stepDefinitions;

import core.BaseTest;
import core.Constants;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.ProductDetail;
import pages.ProductPage;


public class ProductDetailStepDefinitions extends BaseTest {

    private HomePage homePage;
    private ProductPage productPage;
    private ProductDetail productDetail;

    public ProductDetailStepDefinitions(){
        homePage = new HomePage(getDriver());
        productPage = new ProductPage(getDriver());
        productDetail = new ProductDetail(getDriver());
    }
    @When("User click to the {string}")
    public void user_click_to_the(String subItemName) {
        homePage.tapToSubItemRightMenu(subItemName);
    }
    @When("User click to first product")
    public void user_click_to_first_product() {
        productPage.tapToFirstProduct(0);
    }
    @When("User scroll to the {string}")
    public void user_scroll_to_the(String labelName) {
        productDetail.scrollToLabelInfo(labelName);
    }
    @Then("User should see {string}")
    public void user_should_see(String labelName) {
        Assert.assertTrue(productDetail.verifyLabelDetailInfoDisplay(labelName), labelName + Constants.IS_NOT_DISPLAY);
    }



}
