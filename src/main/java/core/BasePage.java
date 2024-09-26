package core;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.SupportsRotation;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

public class BasePage {
    protected AndroidDriver driver;
    protected WebDriverWait wait;
    protected WebDriverWait waitWithSpecificTimeout;


    public BasePage(AndroidDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(Constants.TIMEOUT));
        waitWithSpecificTimeout = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        PageFactory.initElements(this.driver, this);
    }

    public void scrollPageDown(int yPress, int yMoveTo) {
        new TouchAction<>(driver)
                .press(PointOption.point(300, yPress)) // Tọa độ bắt đầu cuộn (giữa màn hình, gần dưới)
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))) // Thời gian chờ giữa các thao tác
                .moveTo(PointOption.point(300, yMoveTo)) // Tọa độ kết thúc cuộn (giữa màn hình, gần trên)
                .release()
                .perform();
    }
    public void swipeMenu(WebElement menu, int yOffset) {
        // Lấy tọa độ của phần tử menu
        Point menuLocation = menu.getLocation();
        int menuStartY = menuLocation.getY() + (menu.getSize().getHeight() / 2); // Bắt đầu cuộn từ giữa menu
        int menuEndY = menuStartY + yOffset; // Kết thúc cuộn dựa trên yOffset
        int menuX = menuLocation.getX() + (menu.getSize().getWidth() / 2); // Điểm giữa theo trục X của menu

        // Cuộn trong phạm vi của menu
        new TouchAction<>(driver)
                .press(PointOption.point(menuX, menuStartY)) // Tọa độ bắt đầu cuộn
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))) // Thời gian chờ giữa các thao tác
                .moveTo(PointOption.point(menuX, menuEndY)) // Tọa độ kết thúc cuộn
                .release()
                .perform();
    }

    public boolean isElementVisibility(WebElement element){
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }

    public void enterText(WebElement element, String text){
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }
    public void clickElement(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
    //Phương thức chạm vào element
    public void tapByElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence tap = new Sequence(finger, 1) .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), element.getLocation().getX(), element.getLocation().getY())) .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); driver.perform(Collections.singletonList(tap));
    }
    // Phương thức để chạm vào tọa độ cụ thể
    public void tapByCoordinates(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence tap = new Sequence(finger, 1) .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y)) .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); driver.perform(Collections.singletonList(tap));
    }
    // Phương thức để nhấn giữ phần tử
    public void longPressElement(WebElement element, Duration duration) {
        wait.until(ExpectedConditions.visibilityOf(element));
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence longPress = new Sequence(finger, 1) .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), element.getLocation().getX(), element.getLocation().getY())) .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) .addAction(new Pause(finger, duration)) .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); driver.perform(Collections.singletonList(longPress));
    }
    // Phương thức để nhấn giữ toạ độ
    public void longPressCoordinates(int x, int y, Duration duration) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence longPress = new Sequence(finger, 1) .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y)) .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) .addAction(new Pause(finger, duration)) .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); driver.perform(Collections.singletonList(longPress));
    }
    // Phương thức để thực hiện thao tác kéo thả
    public void swipe(int startx, int starty, int endx, int endy, Duration duration) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(finger, 1) .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty)) .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) .addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), endx, endy)) .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); driver.perform(Collections.singletonList(swipe));
    }
    // Phương thức để thực hiện thao tác kéo thả lên trên
    public void swipeMobileUp(Duration duration) {
        Dimension size = driver.manage().window().getSize(); int startx = size.width / 2; int starty = (int) (size.height * 0.8); int endy = (int) (size.height * 0.2);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipeUp = new Sequence(finger, 1) .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty)) .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) .addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), startx, endy)) .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); driver.perform(Collections.singletonList(swipeUp));
    }
    // Phương thức để thực hiện thao tác kéo thả xuống dưới
    public void swipeMobileDown(Duration duration) {
        Dimension size = driver.manage().window().getSize(); int startx = size.width / 2; int starty = (int) (size.height * 0.2); int endy = (int) (size.height * 0.8);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipeDown = new Sequence(finger, 1) .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty)) .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) .addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), startx, endy)) .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); driver.perform(Collections.singletonList(swipeDown));
    }
    // Phương thức để thực hiện thao tác kéo thả từ phải sang trái
    public void rightLeftSwipe(Duration duration) {
        Dimension size = driver.manage().window().getSize(); int startX = (int) (size.width * 0.8); int endX = (int) (size.width * 0.2); int startY = size.height / 2;
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipeRightToLeft = new Sequence(finger, 1) .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY)) .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) .addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), endX, startY)) .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); driver.perform(Collections.singletonList(swipeRightToLeft));
    }
    // Phương thức để thực hiện thao tác kéo thả từ trái sang phải
    public void leftRightSwipe(Duration duration) {
        Dimension size = driver.manage().window().getSize(); int startX = (int) (size.width * 0.2); int endX = (int) (size.width * 0.8); int startY = size.height / 2;
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipeLeftToRight = new Sequence(finger, 1) .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY)) .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) .addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), endX, startY)) .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); driver.perform(Collections.singletonList(swipeLeftToRight));
    } public void rotateScreenToLandscape() {
        // Kiểm tra hướng màn hình hiện tại và xoay nếu cần thiết
        if (((SupportsRotation) driver).getOrientation() != ScreenOrientation.LANDSCAPE) {
            try {
                ((SupportsRotation) driver).rotate(ScreenOrientation.LANDSCAPE); System.out.println("Screen rotated to LANDSCAPE successfully.");
            } catch (Exception e) {
                System.err.println("Failed to rotate screen on the first attempt. Retrying...");
                try { Thread.sleep(2000); // Chờ 2 giây và thử lại
                    ((SupportsRotation) driver).rotate(ScreenOrientation.LANDSCAPE); System.out.println("Screen rotated to LANDSCAPE on retry.");
                } catch (InterruptedException ie) { ie.printStackTrace();
                } catch (Exception retryException) { retryException.printStackTrace(); System.err.println("Second attempt to rotate screen also failed."); } }
        } else {
            System.out.println("Screen is already in LANDSCAPE mode.");
        }
    }
}
