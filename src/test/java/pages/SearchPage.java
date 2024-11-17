package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.getSelectedText;

public class SearchPage {
    private final static SelenideElement searchTitle = $(".head h1");


    public SearchPage getTitle(String value) {
        searchTitle.shouldHave(text(value));
        return this;
    }
}
