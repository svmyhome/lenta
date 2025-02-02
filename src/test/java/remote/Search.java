package remote;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import models.pages.MainPage;
import models.pages.SearchPage;

import static java.lang.Thread.sleep;

@Tag("ui")
@Tags({@Tag("SEARCH"), @Tag("SMOKE")})
@Feature("Поиск")
@Story("Поиск через меню поиска")
@Owner("sarychev")
public class Search extends TestBaseRemote {

    MainPage mainPage = new MainPage();;
    SearchPage searchPage= new SearchPage();

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
