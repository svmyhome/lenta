package drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackDriver implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {

        UiAutomator2Options options = new UiAutomator2Options();
//        options.setCapability("appium:app", "bs://sample.app");
        options.setCapability("disableIdLocatorAutocompletion", true);
        options.setCapability("appium:app", "bs://fc4ab49ddb80f18bfe221982ea79c0f7a8d3aaf1");
        options.setCapability("appium:deviceName", "Google Pixel 6 Pro");
        options.setCapability("appium:platformVersion", "12.0");
        options.setCapability("project", "First Java Project");
        options.setCapability("build", "browserstack-build-1");
        options.setCapability("name", "first_test");
        options.setCapability("interactiveDebugging", true);
        options.setCapability("browserstack.debug", true);
        options.setCapability("geoLocation","RU");
        options.setCapability("location", "59.937647, 30.436059");


        try {
            return new AndroidDriver(
                    new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", "voldemarsar_Rc82ns", "jRLbERPZYfVKaieMRXyX")), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
