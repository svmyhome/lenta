package web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    public static final SelenideElement catalog = $(".header__top .catalog-button");
    public static final SelenideElement searchInput = $("#header-search-input");
    public static final SelenideElement closeToolTip = $("[aria-label=\"Закрыть\"]");

    @Step("Открыть главную страницу")
    public MainPage openMainPage() {
        open("/");
        return this;
    }

    @Step("Открыть каталог")
    public MainPage openCatalog() {
        catalog.click();
        return this;
    }

    @Step("Активировать поле поиска")
    public MainPage openSearch() {
        searchInput.click();
        return this;
    }

    @Step("Закрыть тултип с предложением выбора магазина")
    public MainPage closeStoreSelectionTooltip() {
        closeToolTip.click();
        return this;
    }

    @Step("Заполнить поле поиска значением {value} и нажать Enter")
    public MainPage fillItemSearchEnter(String value) {
        searchInput.setValue(value).pressEnter();
        return this;
    }
}
