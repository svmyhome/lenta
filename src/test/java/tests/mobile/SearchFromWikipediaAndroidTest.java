package tests.mobile;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

@Tag("android")
public class SearchFromWikipediaAndroidTest extends TestBaseRemote {

    @Test
    public void WikipediaTest() {
        String searchValue = "Appium";
        step("Find " + searchValue, () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(searchValue);
        });
        step("Verify value", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(sizeGreaterThan(0));
        });

    }


    @Test
    public void FindSeleniumTest() {
        String searchValue = "Ubuntu";
        String expectedResult = "Linux distribution developed by Canonical";
        step("Find " + searchValue, () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(searchValue);
        });
        step("Verify value", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(sizeGreaterThan(0));
            $(id("org.wikipedia.alpha:id/page_list_item_description")).shouldHave(text(expectedResult)).click();
        });
    }

    @Tag("android")
    @Test
    public void LentaTest() throws InterruptedException {
        $(id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
        Thread.sleep(5000);
        $(id("SearchText")).click();
        $(id("TitleCenter")).click();
        $(id("AvailableAddressText")).shouldHave(text("Санкт-Петербург, Заневский пр., 71"));
    }


}
