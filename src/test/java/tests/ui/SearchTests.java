package tests.ui;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import models.pages.MainPage;
import models.pages.SearchPage;
import tests.TestBase;

import static java.lang.Thread.sleep;

@Feature("Поиск")
@Story("Поиск через меню поиска")
@Owner("sarychev")
@Tags({@Tag("SEARCH"), @Tag("SMOKE")})
@Tag("ui")
public class SearchTests extends TestBase {

    MainPage mainPage = new MainPage();;
    SearchPage searchPage= new SearchPage();

    @Test
    @DisplayName("Поиск товара через строку поиска находит товар")
    @Severity(SeverityLevel.BLOCKER)
    void setSearchInput(){
        mainPage.openMainPage()
                .closeToolTip();
        mainPage.openSearch()
                .fillItemSearchEnter("молоко");
        searchPage.getTitle("молоко");
    }
}
