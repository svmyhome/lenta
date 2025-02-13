package tests.mobile;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import models.screens.android.CatalogScreen;
import models.screens.android.DeviceLocationScreen;
import models.screens.android.StoreSelectionScreen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;


@Feature("Магазины")
@Story("Пользователь может получить информацию о магазине и товарах в нем")
@Owner("sarychev")
@Tag("android")
@DisplayName("Информация о магазинах и товарах")
public class AndroidTest extends TestBase {
    DeviceLocationScreen deviceLocationScreen = new DeviceLocationScreen();
    StoreSelectionScreen storeSelectionScreen = new StoreSelectionScreen();
    CatalogScreen catalogScreen = new CatalogScreen();

    @Test
    @DisplayName("Успешный выбор магазин через строку поиска")
    @Severity(SeverityLevel.BLOCKER)
    public void successfulSelectStoreFromSearchInputTest() throws InterruptedException {
        deviceLocationScreen.clickPermissionAllowed();
        Thread.sleep(5000);

        storeSelectionScreen.clickFindStore()
                .selectStore()
                .assertStoreSelection("Санкт-Петербург, Заневский пр., 71");
    }

    @Test
    @DisplayName("Успешный поиск товара через строку поиска")
    @Severity(SeverityLevel.BLOCKER)
    public void successfulSelectSkuFromSearchInputTest() throws InterruptedException {
        deviceLocationScreen.clickPermissionAllowed();
        Thread.sleep(5000);

        storeSelectionScreen.clickFindStore()
                .selectStore()
                .assertStoreSelection("Санкт-Петербург, Заневский пр., 71")
                .viewGoods();

        catalogScreen.clickMainSearch()
                .clickSearchInput()
                .inputGoods()
                .selectGoods("")
                .shouldGoodsFind(1);
    }

    @Test
    @DisplayName("Успешный выбор товара через строку поиска")
    @Severity(SeverityLevel.BLOCKER)
    public void successfulSkuSelectionFromSearchInputTest() throws InterruptedException {
        deviceLocationScreen.clickPermissionAllowed();
        Thread.sleep(5000);

        storeSelectionScreen.clickFindStore()
                .selectStore()
                .assertStoreSelection("Санкт-Петербург, Заневский пр., 71")
                .viewGoods();

        catalogScreen.clickMainSearch()
                .clickSearchInput()
                .inputGoods()
                .selectGoods("")
                .selectFindGoods("")
                .shouldGoodsChoose("");
    }
}
