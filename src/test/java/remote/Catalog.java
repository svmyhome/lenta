package remote;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.CatalogPanel;
import pages.MainPage;

import static java.lang.Thread.sleep;

@Tag("ui")
@Tag("CATALOG")
@Feature("Каталог")
@Story("Поиск в каталоге")
@Owner("sarychev")
public class Catalog extends TestBaseRemote {

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
