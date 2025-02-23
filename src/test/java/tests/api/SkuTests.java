package tests.api;

import common.helpers.ApiHelper;
import io.qameta.allure.Feature;
import qameta.allure.Layer;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import api.models.sku.CatalogSearchRequest;
import api.models.sku.SkuResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static api.specifications.ApiSpecifications.requestSpecification;
import static api.specifications.ApiSpecifications.statusCode200ResponseSpecification;

@Layer("rest")
@Slf4j
@Feature("Товары")
@Story("Неавторизованный пользователь может получить информацию о товаре")
@Owner("Sarychev")
@Tag("api")
@DisplayName("Информация о товарах")
public class SkuTests extends TestBase {
    public static final String SKU_VODKA_ARKHANGELSKAYA = "354331";
    public static final String NAME_VODKA_ARKHANGELSKAYA = "Водка АРХАНГЕЛЬСКАЯ Северная выдержка 40%, 0.5л";
    public static final String SKU_BREAD = "хлеб";
    public static final String SKU_IDS = "0177";
    public static final String SKU_MILK = "молоко";
    public static final String SKU_MILK_ART = "Арт: 435450";
    public static final String SKU_MILK_LONG_NAME = "Молоко пастеризованное СЕВЕРНОЕ МОЛОКО Вологодское 3,2%, без змж, 1000г";
    public static final String SKUS_NAME = "/api/v1/skus/%s/name";
    public static final String CATALOG_SEARCH = "/api/v1/stores/0012/catalog/search/?value=";
    final ApiHelper api = new ApiHelper();

    @Test
    @DisplayName("Успешное получение данных товара по SKU: " + SKU_VODKA_ARKHANGELSKAYA)
    @Severity(SeverityLevel.BLOCKER)
    public void getSkuTest() {
        SkuResponse skuResponse = step("Успешный запрос данных товара по SKU: " + SKU_VODKA_ARKHANGELSKAYA, () ->
                given(requestSpecification)
                        .get(String.format(SKUS_NAME, SKU_VODKA_ARKHANGELSKAYA))
                        .then()
                        .spec(statusCode200ResponseSpecification)
                        .extract().as(SkuResponse.class));

        step("SKU и название товара совпадают", () ->
        {
            api.assertValues(skuResponse.code(), SKU_VODKA_ARKHANGELSKAYA)
                    .assertValues(skuResponse.name(), NAME_VODKA_ARKHANGELSKAYA);
        });
    }

    @Test
    @DisplayName("Успешный поиск товара в каталоге " + SKU_BREAD)
    @Severity(SeverityLevel.TRIVIAL)
    public void searchSkuFromCatalogTest() {
        CatalogSearchRequest catalogSearchRequest = new CatalogSearchRequest(SKU_IDS);
        Response response = step("Успешный запрос данных о товаре", () ->
                given(requestSpecification)
                        .when().body(catalogSearchRequest)
                        .get(CATALOG_SEARCH + SKU_BREAD)
                        .then()
                        .spec(statusCode200ResponseSpecification)
                        .extract().response());

        step("Товар найден " + SKU_BREAD, () -> {
            api.assertListSize(response, "skus.title", 1);
        });
    }

}
