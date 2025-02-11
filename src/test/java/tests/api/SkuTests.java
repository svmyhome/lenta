package tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import models.api.catalogsearch.CatalogSearchRequest;
import models.api.sku.SkuResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static models.api.ApiConstants.NAME_VODKA_ARKHANGELSKAYA;
import static models.api.ApiConstants.SKU_BREAD;
import static models.api.ApiConstants.SKU_IDS;
import static models.api.ApiConstants.SKU_VODKA_ARKHANGELSKAYA;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.StoresSpecs.requestSpec;
import static specs.StoresSpecs.successResponse;


@Slf4j
@Feature("Товары")
@Story("Пользователь может получить информацию о товаре")
@Owner("Sarychev")
@Tag("api")
@DisplayName("Информация о товарах")
public class SkuTests extends TestBase {

    @Test
    @DisplayName("Успешное получение данных товара по SKU: " + SKU_VODKA_ARKHANGELSKAYA)
    @Severity(SeverityLevel.BLOCKER)
    public void getSkuTest() {
        SkuResponse skuResponse = step("Успешный запрос данных товара по SKU: " + SKU_VODKA_ARKHANGELSKAYA, () -> given(requestSpec)
                .get(String.format("/api/v1/skus/%s/name", SKU_VODKA_ARKHANGELSKAYA))
                .then()
                .spec(successResponse)
                .extract().as(SkuResponse.class));

        step("SKU и название товара совпадают", () ->
        {
            assertThat(skuResponse.code()).isEqualTo(SKU_VODKA_ARKHANGELSKAYA);  // TODO в page перенсти
            assertThat(skuResponse.name()).isEqualTo(NAME_VODKA_ARKHANGELSKAYA);
        });
    }

    @Test
    @DisplayName("Успешный поиск товара " + SKU_BREAD)
    @Severity(SeverityLevel.TRIVIAL)
    public void catalogSearchTest() {
        CatalogSearchRequest catalogSearchRequest = new CatalogSearchRequest(SKU_IDS);
        Response response = step("Успешный запрос данных о всех магазинах", () -> given(requestSpec)
                .when().body(catalogSearchRequest)
                .get("/api/v1/stores/0012/catalog/search/?value=" + SKU_BREAD)
                .then()
                .spec(successResponse)
                .extract().response());

        step("Товар найден " + SKU_BREAD, () -> {
            assertThat(response.jsonPath().getList("skus.title").size()).isGreaterThanOrEqualTo(1);
        });
    }

}
