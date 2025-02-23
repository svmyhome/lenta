package web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    public static final SelenideElement catalog = $(".header__top .catalog-button");
    public static final SelenideElement searchInput = $("#header-search-input");
    public static final SelenideElement closeToolTip = $("[aria-label=\"Закрыть\"]");

    @Step("Открыта главная страница")
    public MainPage openMainPage() {
        open("/");
        return this;
    }

    @Step("Открыт каталог")
    public MainPage openCatalog() {
        catalog.click();
        return this;
    }

    @Step("Открыт поиск")
    public MainPage openSearch() {
        searchInput.click();
        return this;
    }

    @Step("Закрыт выбор магазина")
    public MainPage closeToolTip() {
        closeToolTip.click();
        return this;
    }

    @Step("Поле поиска заполнено значением {value}")
    public MainPage fillItemSearchEnter(String value) {
        searchInput.setValue(value).pressEnter();
        return this;
    }
}
