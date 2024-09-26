package pages;

import core.BasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ProductDetail extends BasePage {
    public ProductDetail(AndroidDriver driver) {
        super(driver);
    }
    @FindBy(how = How.XPATH, using = "//*[contains(@resource-id,'vn.tiki.app.tikiandroid.productDetail2:id/recyclerView')]//*[contains(@resource-id,'vn.tiki.app.tikiandroid:id/tvTitle')]")
    private List<WebElement> labelInfoList;
    @FindBy(how = How.XPATH, using = "//*[contains(@resource-id,'vn.tiki.app.tikiandroid.productDetail2:id/container')]")
    private List<WebElement> titleDetailInfoList;

    public void scrollToLabelInfo(String labelName) {
        boolean found = false;

        while (!found) {
            for (WebElement item : labelInfoList) {
                if (item.getText().equals(labelName)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                scrollPageDown(1000, 500);
            }
        }
    }

    public boolean verifyLabelDetailInfoDisplay(String labelName) {
        WebElement itemTextView = null;
        for (WebElement item : titleDetailInfoList) {
            itemTextView = item.findElement(By.xpath(".//android.widget.TextView"));
            String text = itemTextView.getText();
            if (text.equals(labelName)) {
                return true;
            }
        }
        return false;
    }
}
