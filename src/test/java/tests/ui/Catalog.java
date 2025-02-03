package tests.ui;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import models.pages.CatalogPanel;
import models.pages.MainPage;
import tests.TestBase;

import static java.lang.Thread.sleep;

@Feature("Каталог")
@Story("Поиск в каталоге")
@Owner("sarychev")
@Tag("CATALOG")
@Tag("ui")
public class Catalog extends TestBase {

    MainPage mainPage = new MainPage();;
    CatalogPanel catalogPanel =new CatalogPanel();;

    @Test
    @DisplayName("Открыть гавную страницу каталога")
    @Severity(SeverityLevel.BLOCKER)
    void openCatalog() throws InterruptedException {

        mainPage.openMainPage();
        sleep(5000);

        mainPage.openCatalog();
        catalogPanel.clickMainCat("Новинки");
        catalogPanel.shouldCatalog("Новинки");
    }
}
