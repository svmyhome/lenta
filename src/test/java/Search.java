import pages.MainPage;
import pages.SearchPage;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;

public class Search extends TestBase {
    MainPage mainPage = new MainPage();
    SearchPage search = new SearchPage();

    @Test
    void setSearchInput() {
        mainPage.openMainPage();
        mainPage.openSearch().fillItemSearchEnter("молоко");
        search.getTitle("молоко");
    }
}
