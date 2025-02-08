package models.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SearchPage {
    private final static SelenideElement searchTitle = $(".head h1");

    @Step("Переход на страницу с результатами поиска")
    public SearchPage getTitle(String value) {
        searchTitle.shouldHave(text(value));
        return this;
    }
}
