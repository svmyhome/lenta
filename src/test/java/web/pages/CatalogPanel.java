package web.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CatalogPanel {

    public static final ElementsCollection mainCat = $$("[automation-id=mainCat]");
    public static final SelenideElement firstLevelCatalogTitle = $("div h1");
    public static final SelenideElement secondLevelCatalogTitle = $("div h1");

    @Step("Открыть главную страницу каталога {value}")
    public CatalogPanel openFirstLevelCatalog(String value) {
        mainCat.filterBy(text(value)).first().click();
        return this;
    }

    @Step("Каталог 1-го уровня {value} открыт")
    public CatalogPanel shouldFirstLevelCatalog(String value) {
        firstLevelCatalogTitle.shouldHave(text(value));
        return this;
    }

    @Step("Отображается состав каталога 1-го уровня")
    public CatalogPanel shouldFirstLevelCatalogContentDisplayedOnHover(String value) {
        mainCat.filterBy(text(value)).first().hover();
        return this;
    }

    @Step("Открыть страницу каталога 2-го уровня {value}")
    public CatalogPanel openSecondLevelCatalog(String value) {
        String catalog = String.format("[href=\"https://lenta.com/catalog/%s/\"]", value);
        $(catalog).click();
        return this;
    }

    @Step("Каталог 2-го уровня {value} открыт")
    public CatalogPanel shouldSecondLevelCatalog(String value) {
        secondLevelCatalogTitle.shouldHave(text(value));
        return this;
    }

}
