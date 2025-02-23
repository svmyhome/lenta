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
import tests.TestBase;
import web.pages.CatalogPanel;
import web.pages.MainPage;

@Layer("web")
@Feature("Каталог")
@Story("Пользователь может взаимодействовать с каталогом товаров")
@Owner("Ivanov")
@Tag("catalog")
@Tag("ui")
@DisplayName("Взаимодействие с каталогом")
public class InteractionWithProductCatalogTests extends TestBase {

    public static final String CATALOG_NEW_PRODUCTS = "Новинки";
    public static final String CATALOG_DRINKS = "Напитки";
    public static final String CATALOG_SKU_DRINKS = "napitki-19314";
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
