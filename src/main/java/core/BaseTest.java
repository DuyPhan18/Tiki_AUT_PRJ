package core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    private static AndroidDriver driver;
    private static UiAutomator2Options options;

    public static void setUpDriver() throws MalformedURLException {
        options = new UiAutomator2Options()
                .setDeviceName("vivo V2206")
                .setPlatformName("Android")
                .setPlatformVersion("14")
                .setAppPackage("vn.tiki.app.tikiandroid")
                .setAppActivity("vn.tiki.android.shopping.homeV3.HomeActivity")
                .setAutomationName("UiAutomator2");
        options.setCapability("autoGrantPermissions", true);

        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723/wd/hub"), options);
    }

    public static void tearDown() {
        // Đóng driver sau khi tất cả các test kết thúc
        if (driver != null) {
            driver.quit();
        }
    }


    public AndroidDriver  getDriver(){
        return driver;
    }
}
