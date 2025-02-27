package tests.mobile;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import mobile.screens.android.DeviceLocationScreen;
import mobile.screens.android.ProductSelectionScreen;
import mobile.screens.android.StoreSelectionScreen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import qameta.allure.Layer;


@Layer("android")
@Feature("Магазины")
@Story("Пользователь может получить информацию о магазине и товарах в нем")
@Owner("Petrov")
@Tag("android")
@DisplayName("Информация о магазинах и товарах")
public class StoreAndProductInfoAndroidTest extends TestBaseMobile {
    private static final String SKU_MILK = "молоко";
    private static final String SKU_MILK_ART = "Арт: 435450";
    public static final String SKU_MILK_LONG_NAME = "Молоко пастеризованное СЕВЕРНОЕ МОЛОКО Вологодское 3,2%, без змж, 1000г";
    private static final String STORE_ADDRESS_SPB = "Санкт-Петербург, Заневский пр., 71";
    final DeviceLocationScreen deviceLocationScreen = new DeviceLocationScreen();
    final StoreSelectionScreen storeSelectionScreen = new StoreSelectionScreen();
    final ProductSelectionScreen catalogScreen = new ProductSelectionScreen();

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
