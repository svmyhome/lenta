package tests.ui;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import qameta.allure.Layer;
import qameta.allure.Microservice;
import tests.TestBase;
import web.pages.MainPage;
import web.pages.SearchPage;

@Layer("web")
@Microservice("checkout")
@Feature("Корзина")
@Story("Неавторизованный пользователь может добавить товар в корзину")
@Owner("Ivanov")
@Tag("ui")
@DisplayName("Добавление товара в корзину")
public class CheckoutTests extends TestBase {

    private static final String KEFIR = "кефир";
    final MainPage mainPage = new MainPage();
    final SearchPage searchPage = new SearchPage();

    @Test
    @DisplayName("Успешное добавление товара в корзину через строку поиска")
    @Severity(SeverityLevel.CRITICAL)
    void setSearchInput() {
        mainPage.openMainPage()
                .closeStoreSelectionTooltip();
        mainPage.openSearch()
                .fillItemSearchEnter(KEFIR);
        Selenide.switchTo().frame("fl-738251");

        searchPage.clickCloseDiscountPopup();
        Selenide.switchTo().defaultContent();
        searchPage.addItemToCart(KEFIR).shouldItemFoundViaSearchBar1(KEFIR, "1");
        System.out.println();
    }
}
