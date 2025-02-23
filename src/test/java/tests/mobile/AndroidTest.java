package tests.mobile;

import io.qameta.allure.Feature;
import io.qameta.allure.Layer;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import models.screens.android.DeviceLocationScreen;
import models.screens.android.ProductSelectionScreen;
import models.screens.android.StoreSelectionScreen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static models.api.ApiConstants.SKU_MILK;
import static models.api.ApiConstants.SKU_MILK_ART;
import static models.api.ApiConstants.STORE_ADDRESS_SPB;

@Layer("android")
@Feature("Магазины")
@Story("Пользователь может получить информацию о магазине и товарах в нем")
@Owner("Petrov")
@Tag("android")
@DisplayName("Информация о магазинах и товарах")
public class AndroidTest extends TestBase {
    DeviceLocationScreen deviceLocationScreen = new DeviceLocationScreen();
    StoreSelectionScreen storeSelectionScreen = new StoreSelectionScreen();
    ProductSelectionScreen catalogScreen = new ProductSelectionScreen();

    @Test
    @DisplayName("Успешный выбор магазин через строку поиска")
    @Severity(SeverityLevel.BLOCKER)
    public void successfulSelectStoreFromSearchInputTest() throws InterruptedException {
        deviceLocationScreen.clickPermissionAllowedButton();
        Thread.sleep(5000);

        storeSelectionScreen.clickEnterAddress()
                .selectStore()
                .shouldStoreSelection(STORE_ADDRESS_SPB);
    }

    @Test
    @DisplayName("Успешный поиск товара через строку поиска")
    @Severity(SeverityLevel.BLOCKER)
    public void successfulSelectSkuFromSearchInputTest() throws InterruptedException {
        deviceLocationScreen.clickPermissionAllowedButton();
        Thread.sleep(5000);

        storeSelectionScreen.clickEnterAddress()
                .selectStore()
                .shouldStoreSelection(STORE_ADDRESS_SPB)
                .clickViewGoods();

        catalogScreen.clickFindProducts()
                .enterProductInSearch("молоко")
                .clickToProduct()
                .shouldGoodsFind(1);
    }

    @Test
    @DisplayName("Успешный выбор товара через строку поиска")
    @Severity(SeverityLevel.BLOCKER)
    public void successfulSkuSelectionFromSearchInputTest() throws InterruptedException {
        deviceLocationScreen.clickPermissionAllowedButton();
        Thread.sleep(5000);

        storeSelectionScreen.clickEnterAddress()
                .selectStore()
                .shouldStoreSelection(STORE_ADDRESS_SPB)
                .clickViewGoods();

        catalogScreen.clickFindProducts()
                .enterProductInSearch(SKU_MILK)
                .clickToProduct()
                .selectProductFound()
                .shouldGoodsChoose(SKU_MILK_ART);
    }
}
