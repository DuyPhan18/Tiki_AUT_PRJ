package pages;

import core.BasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HomePage extends BasePage {
    public HomePage(AndroidDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//*[contains(@resource-id,'vn.tiki.app.tikiandroid:id/navHome')]")
    private WebElement homeBtn;
    @FindBy(how = How.XPATH, using = "//*[contains(@resource-id,'vn.tiki.app.tikiandroid:id/navCategories')]")
    private WebElement categoryBtn;
    @FindBy(how = How.XPATH, using = "//*[contains(@resource-id,'vn.tiki.app.tikiandroid:id/navChatAssistant')]")
    private WebElement chatAssitantBtn;
    @FindBy(how = How.XPATH, using = "//*[contains(@resource-id,'vn.tiki.app.tikiandroid:id/navChat')]")
    private WebElement chatBtn;
    @FindBy(how = How.XPATH, using = "//*[contains(@resource-id,'vn.tiki.app.tikiandroid:id/navProfile')]")
    private WebElement accountBtn;
    @FindBy(how = How.XPATH, using = "//*[contains(@resource-id,'vn.tiki.app.tikiandroid.category2:id/ervLeft')]")
    private WebElement menuLeft;
    @FindBy(how = How.XPATH, using = "//*[contains(@resource-id, 'vn.tiki.app.tikiandroid.category2:id/ervRight')]")
    private WebElement menuRirght;
    @FindBy(how = How.XPATH, using = "//*[contains(@resource-id,'vn.tiki.app.tikiandroid.category2:id/ervRight')]//*[contains(@resource-id,'vn.tiki.app.tikiandroid.category2:id/tvName')]")
    private List<WebElement> subItemRightMenuList;
    @FindBy(how = How.XPATH, using = "//*[contains(@resource-id,'vn.tiki.app.tikiandroid.category2:id/ervLeft')]//*[contains(@resource-id,'vn.tiki.app.tikiandroid.category2:id/tvContent')]")
    private List<WebElement> itemLeftMenuList;
    @FindBy(how = How.XPATH, using = "//*[contains(@resource-id,'vn.tiki.app.tikiandroid.category2:id/tvTitle')]")
    private List<WebElement> titleRightMenuList;

    public boolean isHomeDisplay(){
        return this.isElementVisibility(homeBtn);
    }
    public void tapOnCategoryBtn(){
        this.tapByElement(categoryBtn);
    }

    public void tapToMenuLeftItem(String itemName){
        boolean found = false;
        while (!found){
            for (WebElement item : itemLeftMenuList){
                if (item.getText().contains(itemName)){
                    this.tapByElement(item);
                    found = true;
                    break;
                }
            }
            if (!found) { // Chỉ cuộn nếu chưa tìm thấy phần tử
                swipeMenu(menuLeft, -300);
            }
        }

    }

    public void scrollToPannelItem(String panelName){
        isElementVisibility(menuRirght);
        boolean found = false;
        while (!found) {
            for (WebElement item : titleRightMenuList) {
                if (item.getText().equals(panelName)) {
                    found = true;
                    break;
                }
            }
            if (!found) { // Chỉ cuộn nếu chưa tìm thấy phần tử
                swipeMenu(menuRirght, -300);
            }
        }
    }
    public int verifyNumberItemDisplay(String panelName){
        int totalItems = 0;
        boolean foundNewItems = false;
        Set<String> itemList = new HashSet<>();
        if (!titleRightMenuList.isEmpty()) {
            while (!foundNewItems) {
                // XPath để tìm phần tử title với văn bản cụ thể và phần tử sibling ervContent
                String subPanelPath = "//*[contains(@resource-id,'vn.tiki.app.tikiandroid.category2:id/tvTitle') and @text='" + panelName + "']/following-sibling::*[@resource-id='vn.tiki.app.tikiandroid.category2:id/ervContent']";
                WebElement subPanel = driver.findElement(By.xpath(subPanelPath));
                List<WebElement> subPanelItemList = subPanel.findElements(By.xpath(".//*[contains(@resource-id, 'vn.tiki.app.tikiandroid.category2:id/tvName')]"));
                System.out.println(subPanelItemList.size());
                boolean newItemsFound = true;
                // Nếu không tìm thấy phần tử mới, dừng vòng lặp
                if (!newItemsFound) {
                    foundNewItems = false;
                }

                // Cuộn để tiếp tục tìm phần tử
                swipeMenu(menuRirght, 350);

            }
        }else{
            List<WebElement> nonTitleItemsList = driver.findElements(By.xpath("//*[contains(@class, 'android.view.ViewGroup')]//*[contains(@resource-id, 'vn.tiki.app.tikiandroid.category2:id/ervContent')]//*[contains(@resource-id, 'vn.tiki.app.tikiandroid.category2:id/tvName')]"));
            for (WebElement item : nonTitleItemsList) {
                String itemText = item.getText();
                itemList.add(itemText);
            }
            System.out.println(itemList);
            // Cuộn để tiếp tục tìm phần tử
            swipeMenu(menuRirght, 350);
        }

        return totalItems;
    }


    public void tapToSubItemRightMenu(String subItemName){
        boolean found = false;
        while(!found){
            for (WebElement item : subItemRightMenuList){
                if(item.getText().equals(subItemName)){
                    this.tapByElement(item);
                    found = true;
                    break;
                }
            }
            if (!found){
                swipeMenu(menuRirght, 200);
            }
        }

    }



    //menuicon
    public boolean verifyMenuIconDisplayed(String menuItemName){
        String iconXpath = "//*[contains(@resource-id,'vn.tiki.app.tikiandroid:id/icon')]";
        WebElement iconBtn = null;

        switch (menuItemName) {
            case "Trang chủ":
                iconBtn = homeBtn.findElement(By.xpath(iconXpath));
                break;
            case "Danh mục":
                iconBtn = categoryBtn.findElement(By.xpath(iconXpath));
                break;
            case "Trợ lý":
                iconBtn = chatAssitantBtn.findElement(By.xpath(iconXpath));
                break;
            case "Tin mới":
                iconBtn = chatBtn.findElement(By.xpath(iconXpath));
                break;
            case "Tài khoản":
                iconBtn = accountBtn.findElement(By.xpath(iconXpath));
                break;
            default:
                return false; // or throw an exception
        }

        return this.isElementVisibility(iconBtn);
    }

}
