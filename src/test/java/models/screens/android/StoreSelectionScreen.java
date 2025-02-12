package models.screens.android;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class StoreSelectionScreen {
    public static final SelenideElement searchText = $(AppiumBy.id("SearchText")),
            selectStore = $(id("TitleCenter")),
            storeAddress = $(id("AvailableAddressText")),
            viewGoods = $(id("BottomButton"));

    @Step("Кликнуть на кнопке While using the App")
    public StoreSelectionScreen clickPermissionAllowed() {
        searchText.click();
        return this;
    }

    @Step("Выбрать магазин")
    public StoreSelectionScreen selectStore() {
        selectStore.click();
        return this;
    }

    @Step("Посмотреть товары")
    public StoreSelectionScreen viewGoods() {
        viewGoods.click();
        return this;
    }

    @Step("Магазин выбран")
    public StoreSelectionScreen assertStoreSelection(String value) {
        storeAddress.shouldHave(text("Санкт-Петербург, Заневский пр., 71"));
        ;
        return this;
    }


}
