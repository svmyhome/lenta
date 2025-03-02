package tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import qameta.allure.Layer;
import web.pages.MainPage;
import web.pages.SearchPage;

@Layer("web")
@Feature("Поиск")
@Story("Пользователь может найти товар")
@Owner("Ivanov")
@Tag("ui")
@DisplayName("Поиск товара")
public class ProductSearchTests extends TestBaseWeb {

    private static final String MILK = "молоко";
    final MainPage mainPage = new MainPage();
    final SearchPage searchPage = new SearchPage();

    @Test
    @DisplayName("Успешный поиск товара через строку поиска")
    @Severity(SeverityLevel.CRITICAL)
    void setSearchInput() {
        mainPage.openMainPage()
                .closeStoreSelectionTooltip();
        mainPage.openSearch()
                .fillItemSearchEnter(MILK);
        searchPage.shouldItemFoundViaSearchBar(MILK);
    }
}
