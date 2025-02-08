package tests.ui;

import io.qameta.allure.*;
import models.pages.CatalogPanel;
import models.pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static helpers.Constants.CatalogItems.CATALOG_NEW_PRODUCTS;

@Feature("Каталог")
@Story("Работа с каталогом")
@Owner("sarychev")
@Tag("CATALOG")
@Tag("ui")
@DisplayName("Работа с каталогом")
public class CatalogTests extends TestBase {

    MainPage mainPage = new MainPage();;
    CatalogPanel catalogPanel =new CatalogPanel();;

    @Test
    @DisplayName("Переход в пункт каталога " + CATALOG_NEW_PRODUCTS)
    @Severity(SeverityLevel.BLOCKER)
    void openCatalog(){
        mainPage.openMainPage()
                .closeToolTip();
        mainPage.openCatalog();
        catalogPanel.clickMainCat(CATALOG_NEW_PRODUCTS)
                .shouldCatalog(CATALOG_NEW_PRODUCTS);
    }
}
