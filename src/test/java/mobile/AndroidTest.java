package mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static helpers.LocalHelper.getAppPath;
import static helpers.LocalHelper.getLocalUrl;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.xpath;

@Tag("android")
public class AndroidTest extends TestBaseLocal{
    @Test
    public void WikipediaTest() throws MalformedURLException, InterruptedException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setCapability("appium:app", "bs://sample.app");
        options.setCapability("appium:deviceName", "Samsung Galaxy S22 Ultra");
        options.setCapability("appium:platformVersion", "12.0");
        options.setCapability("project", "First Java Project");
        options.setCapability("build", "browserstack-build-1");
        options.setCapability("name", "first_test");


        AndroidDriver driver = new AndroidDriver(
                new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", "voldemarsar_Rc82ns", "jRLbERPZYfVKaieMRXyX")), options);

        WebElement searchElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Search Wikipedia")));
        searchElement.click();
        WebElement insertTextElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(
                        id("org.wikipedia.alpha:id/search_src_text")));
        insertTextElement.sendKeys("Appium");
        Thread.sleep(5000);
        List<WebElement> allProductsName = driver.findElements(AppiumBy.className(
                "android.widget.TextView"));
        assert (allProductsName.size() > 0);

        driver.quit();

    }

    @Test
    public void WikilocalTest() throws MalformedURLException, InterruptedException {

        UiAutomator2Options options = new UiAutomator2Options();
//        options.setDeviceName(androidConfig.getDeviceName());
        options.setPlatformVersion("14.0");
        options.setAutomationName(ANDROID_UIAUTOMATOR2);
        options.setPlatformName(ANDROID);
        options.setUdid("2FK0224429007348");
        options.setApp(getAppPath());
        options.setAppPackage("com.icemobile.lenta.stage.qa");
        options.setAppActivity("ru.app.main.MainActivity");

//        options.setCapability("appium:app", "bs://sample.app");
//        options.setCapability("appium:deviceName", "Samsung Galaxy S22 Ultra");
//        options.setCapability("appium:platformVersion", "12.0");
//        options.setCapability("project", "First Java Project");
//        options.setCapability("build", "browserstack-build-1");
//        options.setCapability("name", "first_test");


        AndroidDriver driver = new AndroidDriver(getLocalUrl(), options);

        $(id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
        System.out.println();
//        WebElement searchElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
//                ExpectedConditions.elementToBeClickable(
//                        AppiumBy.accessibilityId("Search Wikipedia")));
//        searchElement.click();
//        WebElement insertTextElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
//                ExpectedConditions.elementToBeClickable(
//                        id("org.wikipedia.alpha:id/search_src_text")));
//        insertTextElement.sendKeys("Appium");
//        Thread.sleep(5000);
//        List<WebElement> allProductsName = driver.findElements(AppiumBy.className(
//                "android.widget.TextView"));
//        assert (allProductsName.size() > 0);

        driver.quit();

    }


    @Test
    public void LentaTest() throws InterruptedException {
        $(id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
        Thread.sleep(5000);
        $(id("SearchText")).click();
        $(id("TitleCenter")).click();
        $(id("AvailableAddressText")).shouldHave(text("Санкт-Петербург, Заневский пр., 71"));
    }


    @Test
    void successfulSearchTest() {
        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(partialText("The Free Encyclopedia"));
        $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();

        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("New ways to explore"));
        $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();

        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Reading lists with sync"));
        $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();

        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Data & Privacy"));
        $(id("org.wikipedia.alpha:id/fragment_onboarding_done_button")).click();

        $(id("org.wikipedia.alpha:id/view_announcement_text")).shouldHave(partialText("Customize your Explore feed"));


        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Ubuntu");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

}
