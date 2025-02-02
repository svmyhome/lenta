package models.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CatalogPanel {
    public static final ElementsCollection mainCat = $$("[automation-id=mainCat]");
    public static final SelenideElement catalogTitle = $("div h1");

    @Step("Открыть главную страницу каталога {value}")
    public CatalogPanel clickMainCat(String value) {
        mainCat.filterBy(text(value)).first().click();
        return this;
    }

    @Step("Товар {value} найден")
    public CatalogPanel shouldCatalog(String value) {
        catalogTitle.shouldHave(text(value));
        return this;
    }
}
