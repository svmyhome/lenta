package tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Layer;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import models.pages.MainPage;
import models.pages.SearchPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static models.pages.SearchPage.MILK;

@Layer("web")
@Feature("Поиск")
@Story("Пользователь может найти товар")
@Owner("Ivanov")
@Tag("ui")
@DisplayName("Поиск товара")
public class SearchTests extends TestBase {

    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();

    @Test
    @DisplayName("Успешный поиск товара через строку поиска")
    @Severity(SeverityLevel.CRITICAL)
    void setSearchInput() {
        mainPage.openMainPage()
                .closeToolTip();
        mainPage.openSearch()
                .fillItemSearchEnter(MILK);
        searchPage.getTitle(MILK);
    }
}
