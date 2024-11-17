import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CatalogPanel;
import pages.MainPage;

import static java.lang.Thread.sleep;

@Feature("Каталог")
@Story("Поиск в каталоге")
@Owner("sarychev")
public class Catalog extends TestBase {
    MainPage mainPage = new MainPage();
    CatalogPanel catalogPanel = new CatalogPanel();

    @Test
    @DisplayName("Открыть гавную страницу каталога")
    @Severity(SeverityLevel.BLOCKER)
    void openCatalog() throws InterruptedException {
        SelenideLogger.addListener("allure", new AllureSelenide());

        mainPage.openMainPage();
        sleep(5000);

        mainPage.openCatalog();
        catalogPanel.clickMainCat();
    }
}
