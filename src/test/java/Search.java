import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import pages.MainPage;
import pages.SearchPage;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;

@Feature("Поиск")
@Story("Поиск через меню поиска")
@Owner("sarychev")
@Tags({@Tag("SEARCH"), @Tag("SMOKE")})
public class Search extends TestBase {

    @Test
    @DisplayName("Поиск товара через строку поиска находит товар")
    @Severity(SeverityLevel.BLOCKER)
    void setSearchInput() throws InterruptedException {
        mainPage.openMainPage();
        sleep(5000);
        mainPage.openSearch().fillItemSearchEnter("молоко");
        searchPage.getTitle("молоко");
        System.out.println();
    }
}
