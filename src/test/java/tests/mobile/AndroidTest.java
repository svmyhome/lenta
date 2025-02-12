package tests.mobile;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import models.screens.android.DeviceLocationScreen;
import models.screens.android.StoreSelectionScreen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;


@Feature("Магазины")
@Story("Пользователь может получить информацию о магазине и товарах в нем")
@Owner("sarychev")
@Tag("android")
@DisplayName("Информация о магазинах и товарах")
public class AndroidTest extends TestBase {
    DeviceLocationScreen deviceLocationScreen = new DeviceLocationScreen();
    StoreSelectionScreen storeSelectionScreen = new StoreSelectionScreen();

    @Test
    @DisplayName("Успешный выбор магазин через строку поиска")
    @Severity(SeverityLevel.BLOCKER)
    public void successfulSelectStoreFromSearchInputTest() throws InterruptedException {
//        $(id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
        deviceLocationScreen.clickPermissionAllowed();
        Thread.sleep(5000);
        storeSelectionScreen.clickPermissionAllowed()
                .selectStore()
                .assertStoreSelection("Санкт-Петербург, Заневский пр., 71");
//        $(id("SearchText")).click();
//        $(id("TitleCenter")).click();
//        $(id("AvailableAddressText")).shouldHave(text("Санкт-Петербург, Заневский пр., 71"));
    }

    @Test
    @DisplayName("Успешный поиск товара через строку поиска")
    @Severity(SeverityLevel.BLOCKER)
    public void successfulSelectSkuFromSearchInputTest() throws InterruptedException {
//        $(id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
//        Thread.sleep(5000);
//        $(id("SearchText")).click();
//        $(id("TitleCenter")).click();
//        $(id("AvailableAddressText")).shouldHave(text("Санкт-Петербург, Заневский пр., 71"));
        deviceLocationScreen.clickPermissionAllowed();
        Thread.sleep(5000);
        storeSelectionScreen.clickPermissionAllowed()
                .selectStore()
                .assertStoreSelection("Санкт-Петербург, Заневский пр., 71");
        $(id("BottomButton")).click();
        $(id("mainSearch")).click();
        $(id("1. SearchPlaceholder")).click();
        $(id("1. SearchPlaceholder")).sendKeys("молоко");
        $(id("TitleCenter")).click();
        $$(id("ItemBlock")).shouldHave(sizeGreaterThanOrEqual(1));
    }

    @Test
    @DisplayName("Успешный выбор товара через строку поиска")
    @Severity(SeverityLevel.BLOCKER)
    public void successfulSkuSelectionFromSearchInputTest() throws InterruptedException {
//        $(id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
//        Thread.sleep(5000);
//        $(id("SearchText")).click();
//        $(id("TitleCenter")).click();
//        $(id("AvailableAddressText")).shouldHave(text("Санкт-Петербург, Заневский пр., 71"));
        deviceLocationScreen.clickPermissionAllowed();
        Thread.sleep(5000);
        storeSelectionScreen.clickPermissionAllowed()
                .selectStore()
                .assertStoreSelection("Санкт-Петербург, Заневский пр., 71");
        $(id("BottomButton")).click();
        $(id("mainSearch")).click();
        $(id("1. SearchPlaceholder")).click();
        $(id("1. SearchPlaceholder")).sendKeys("молоко");
        $(id("TitleCenter")).click();
        $$(id("ItemBlock")).shouldHave(sizeGreaterThanOrEqual(1));
        $(accessibilityId("Молоко пастеризованное СЕВЕРНОЕ МОЛОКО Вологодское 2,5%, без змж, 930г")).click();
        $(id("ProductArticleNumber")).shouldHave(text("Арт: 656620"));
        System.out.println();
    }
}
