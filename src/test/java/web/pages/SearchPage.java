package web.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.switchTo;

public class SearchPage {

    private final static SelenideElement searchTitle = $(".head h1"),
            noNotificationButton = $("[data-fl-track='click-button-no']"),
            goodsCartQuantity = $(".p-scrollpanel-content [automation-id='goods-card-quantity']");
    private final static ElementsCollection cart = $$(".search-list [label='В корзину']");

    @Step("Закрыть окно о скидках")
    public SearchPage clickCloseDiscountPopup() {
        noNotificationButton.click();
        return this;
    }

    @Step("Товар {value} найден через поиск")
    public SearchPage shouldItemFoundViaSearchBar(String value) {
        searchTitle.shouldHave(text(value));
        return this;
    }

    @Step("Добавить товар в корзину через кнопку")
    public SearchPage addItemToCart(String value) {
        cart.first().click();
        return this;
    }

    @Step("Товар {value} в количестве {quantity} добавлен в корзину")
    public SearchPage shouldItemFoundViaSearchBar1(String value, String quantity) {
        goodsCartQuantity.shouldHave(text(quantity));
        return this;
    }

    @Step("Переключится на новый фрейм")
    public SearchPage switchToNewFrame(String value) {
        switchTo().frame(value);
        return this;
    }

    @Step("Переключится на основной фрейм")
    public SearchPage switchToMainFrame() {
        switchTo().defaultContent();
        return this;
    }




}
