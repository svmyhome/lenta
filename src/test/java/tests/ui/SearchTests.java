package tests.ui;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import models.pages.MainPage;
import models.pages.SearchPage;
import tests.TestBase;

import static models.pages.SearchPage.MILK;


@Feature("Поиск")
@Story("Пользователь может найти товар")
@Owner("sarychev")
@Tags({@Tag("search"), @Tag("smoke")})
@Tag("ui")
@DisplayName("Поиск товара")
public class SearchTests extends TestBase {

    MainPage mainPage = new MainPage();
    SearchPage searchPage= new SearchPage();

    @Test
    @DisplayName("Успешный поиск товара через строку поиска")
    @Severity(SeverityLevel.CRITICAL)
    void setSearchInput(){
        mainPage.openMainPage()
                .closeToolTip();
        mainPage.openSearch()
                .fillItemSearchEnter(MILK);
        searchPage.getTitle(MILK);
    }
}
