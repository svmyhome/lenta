package tests.ui;

import io.qameta.allure.Feature;
import qameta.allure.Layer;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import web.pages.CatalogPanel;
import web.pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static web.pages.CatalogPanel.CATALOG_DRINKS;
import static web.pages.CatalogPanel.CATALOG_NEW_PRODUCTS;
import static web.pages.CatalogPanel.CATALOG_SKU_DRINKS;

@Layer("web")
@Feature("Каталог")
@Story("Пользователь может работать с каталогом товаров")
@Owner("Ivanov")
@Tag("catalog")
@Tag("ui")
@DisplayName("Работа с каталогом")
public class CatalogTests extends TestBase {

    final MainPage mainPage = new MainPage();
    final CatalogPanel catalogPanel = new CatalogPanel();

    @Test
    @DisplayName("Успешный переход в каталог 1-го уровня " + CATALOG_NEW_PRODUCTS)
    @Severity(SeverityLevel.BLOCKER)
    void openCatalogTest() {
        mainPage.openMainPage()
                .closeToolTip();
        mainPage.openCatalog();
        catalogPanel.openFirstLevelCatalog(CATALOG_NEW_PRODUCTS)
                .shouldFirstLevelCatalog(CATALOG_NEW_PRODUCTS);
    }

    @Test
    @DisplayName("Успешный переход в каталог 2-го уровня " + CATALOG_DRINKS)
    @Severity(SeverityLevel.NORMAL)
    void openCatalogSecondLevelTest() {
        mainPage.openMainPage()
                .closeToolTip();
        mainPage.openCatalog();
        catalogPanel.hoverOnCatalog(CATALOG_NEW_PRODUCTS)
                .openSecondLevelCatalog(CATALOG_SKU_DRINKS)
                .shouldSecondLevelCatalog(CATALOG_DRINKS);
    }
}
