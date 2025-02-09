package tests.ui;

import io.qameta.allure.*;
import models.pages.CatalogPanel;
import models.pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static helpers.Constants.CatalogItems.*;

@Feature("Каталог")
@Story("Пользователь может работать с каталогом товаров")
@Owner("sarychev")
@Tag("catalog")
@Tag("ui")
@DisplayName("Работа с каталогом")
public class CatalogTests extends TestBase {

    MainPage mainPage = new MainPage();
    CatalogPanel catalogPanel =new CatalogPanel();

    @Test
    @DisplayName("Переход в каталог 1-го уровня " + CATALOG_NEW_PRODUCTS)
    @Severity(SeverityLevel.BLOCKER)
    void openCatalogTest(){
        mainPage.openMainPage()
                .closeToolTip();
        mainPage.openCatalog();
        catalogPanel.openFirstLevelCatalog(CATALOG_NEW_PRODUCTS)
                .shouldFirstLevelCatalog(CATALOG_NEW_PRODUCTS);
    }


    @Test
    @DisplayName("Переход в каталог 2-го уровня " + CATALOG_DRINKS)
    @Severity(SeverityLevel.NORMAL)
    void openCatalogSecondLevelTest(){
        mainPage.openMainPage()
                .closeToolTip();
        mainPage.openCatalog();
        catalogPanel.hoverOnCatalog(CATALOG_NEW_PRODUCTS)
                .openSecondLevelCatalog(CATALOG_SKU_DRINKS)
                .shouldSecondLevelCatalog(CATALOG_DRINKS);
    }
}
