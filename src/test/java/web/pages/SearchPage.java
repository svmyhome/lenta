package web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SearchPage {
    public static final String MILK = "молоко";


    private final static SelenideElement searchTitle = $(".head h1");

    @Step("Товар {value} найден через поиск")
    public SearchPage getTitle(String value) {
        searchTitle.shouldHave(text(value));
        return this;
    }
}
