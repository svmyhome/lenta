package tests.api;

import helpers.ApiHelper;
import io.qameta.allure.Feature;
import io.qameta.allure.Layer;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import models.api.sku.CatalogSearchRequest;
import models.api.sku.SkuResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static models.api.ApiConstants.CATALOG_SEARCH;
import static models.api.ApiConstants.NAME_VODKA_ARKHANGELSKAYA;
import static models.api.ApiConstants.SKUS_NAME;
import static models.api.ApiConstants.SKU_BREAD;
import static models.api.ApiConstants.SKU_IDS;
import static models.api.ApiConstants.SKU_VODKA_ARKHANGELSKAYA;
import static specifications.ApiSpecifications.requestSpecification;
import static specifications.ApiSpecifications.statusCode200ResponseSpecification;

@Layer("api")
@Slf4j
@Feature("Товары")
@Story("Неавторизованный пользователь может получить информацию о товаре")
@Owner("Sarychev")
@Tag("api")
@DisplayName("Информация о товарах")
public class SkuTests extends TestBase {
    ApiHelper api = new ApiHelper();

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
