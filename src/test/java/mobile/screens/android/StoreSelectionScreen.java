package mobile.screens.android;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class StoreSelectionScreen {
    public static final SelenideElement enterAddress = $(AppiumBy.id("SearchText")),
            selectStore = $(id("TitleCenter")),
            storeAddress = $(id("AvailableAddressText")),
            viewGoods = $(id("BottomButton"));

    @Step("Кликнуть на поле Выведите адрес")
    public StoreSelectionScreen clickEnterAddress() {
        Selenide.sleep(5000);
        enterAddress.shouldBe(enabled, Duration.ofSeconds(10));
        enterAddress.click();
        return this;
    }

    @Step("Выбрать магазин")
    public StoreSelectionScreen selectStore() {
        selectStore.click();
        return this;
    }

    @Step("Магазин выбран {value}")
    public StoreSelectionScreen shouldStoreSelection(String value) {
        storeAddress.shouldHave(text(value));
        return this;
    }

    @Step("Кликнуть Посмотреть товары")
    public StoreSelectionScreen clickViewGoods() {
        viewGoods.click();
        return this;
    }

}
