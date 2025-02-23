package tests.ui;

import io.qameta.allure.Feature;
import qameta.allure.Layer;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import web.pages.MainPage;
import web.pages.SearchPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static web.pages.SearchPage.MILK;

@Layer("web")
@Feature("Поиск")
@Story("Пользователь может найти товар")
@Owner("Ivanov")
@Tag("ui")
@DisplayName("Поиск товара")
public class SearchTests extends TestBase {

    final MainPage mainPage = new MainPage();
    final SearchPage searchPage = new SearchPage();

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
