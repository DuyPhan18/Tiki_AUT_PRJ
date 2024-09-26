package pages;

import core.BasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ProductPage extends BasePage {
    public ProductPage(AndroidDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//*[contains(@resource-id,'vn.tiki.app.tikiandroid.productList2:id/recyclerView')]//*[contains(@resource-id,'vn.tiki.app.tikiandroid:id/vCardView')]")
    private List<WebElement> productCardList;

    public void tapToFirstProduct(int index) {
        scrollPageDown(1000, 300);
        WebElement product = productCardList.get(index);
        this.tapByElement(product);
    }
}
