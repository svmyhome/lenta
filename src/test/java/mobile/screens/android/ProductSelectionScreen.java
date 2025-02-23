package mobile.screens.android;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static api.models.ApiConstants.SKU_MILK_LONG_NAME;

public class ProductSelectionScreen {
    public static final SelenideElement findProducts = $(id("mainSearch")),
            placeholder = $(id("1. SearchPlaceholder")),
            product = $(id("TitleCenter")),
            productName = $(accessibilityId(SKU_MILK_LONG_NAME)),
            productArticle = $(id("ProductArticleNumber"));
    public static final ElementsCollection goodsCount = $$(id("ItemBlock"));

    @Step("Кликнуть на поле Найти продукты")
    public ProductSelectionScreen clickFindProducts() {
        findProducts.click();
        return this;
    }

    @Step("Ввести товар в строку поиска")
    public ProductSelectionScreen enterProductInSearch(String product) {
        placeholder.sendKeys(product);
        return this;
    }

    @Step("Кликнуть на товар")
    public ProductSelectionScreen clickToProduct() {
        product.click();
        return this;
    }

    @Step("Выбрать найденный товар")
    public ProductSelectionScreen selectProductFound() {
        productName.click();
        return this;
    }

    @Step("Найдено товаров больше {count}")
    public ProductSelectionScreen shouldGoodsFind(int count) {
        goodsCount.shouldHave(sizeGreaterThanOrEqual(count));
        return this;
    }

    @Step("Товар {value} открыт")
    public ProductSelectionScreen shouldGoodsChoose(String value) {
        productArticle.shouldHave(text(value));
        return this;
    }


}
