package models.screens.android;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;

public class CatalogScreen {
    public static final SelenideElement mainSearch = $(id("mainSearch")),
            placeholder = $(id("1. SearchPlaceholder")),
            goods = $(id("TitleCenter")),
            goodsName = $(accessibilityId("Молоко пастеризованное СЕВЕРНОЕ МОЛОКО Вологодское 2,5%, без змж, 930г")),
            goodsArticle = $(id("ProductArticleNumber"));
    public static final ElementsCollection goodsCount = $$(id("ItemBlock"));

    @Step("Кликнуть на поиск товара")
    public CatalogScreen clickMainSearch() {
        mainSearch.click();
        return this;
    }

    @Step("Кликнуть на строку поиска")
    public CatalogScreen clickSearchInput() {
        placeholder.click();
        return this;
    }

    @Step("Ввести товар в строку поиска")
    public CatalogScreen inputGoods() {
        placeholder.sendKeys("молоко");
        return this;
    }

    @Step("Выбрать товар")
    public CatalogScreen selectGoods(String value) {
        goods.click();
        return this;
    }

    @Step("Выбрать найденный товар")
    public CatalogScreen selectFindGoods(String value) {
        goodsName.click();
        return this;
    }

    @Step("Товары наужены")
    public CatalogScreen shouldGoodsFind(int count) {
        goodsCount.shouldHave(sizeGreaterThanOrEqual(count));
        return this;
    }

    @Step("Товар открыт")
    public CatalogScreen shouldGoodsChoose(String value) {
        goodsArticle.shouldHave(text("Арт: 656620"));
        return this;
    }


}
