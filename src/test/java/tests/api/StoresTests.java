package tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import models.api.deliveryModeGet.DeliveryRequest;
import models.api.deliveryModeGet.response.Delivery;
import models.api.requestCatalogItems.CatalogItemsResponse;
import models.api.requestCatalogItems.Filters;
import models.api.requestCatalogItems.Sort;
import models.api.sku.SkuResponse;
import models.api.stores.StoreResponse;
import models.api.stores.StoresResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.List;
import java.util.Map;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static models.api.ApiConstants.NAME_VODKA_ARKHANGELSKAYA;
import static models.api.ApiConstants.SKU_VODKA_ARKHANGELSKAYA;
import static models.api.ApiConstants.STORE_ADDRESS;
import static models.api.ApiConstants.STORE_CITY;
import static models.api.ApiConstants.STORE_CODE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static specs.StoresSpecs.requestSpec;
import static specs.StoresSpecs.successResponse;


@Slf4j
@Feature("Магазины")
@Story("Пользователь может получить информацию о магазинах")
@Owner("Sarychev")
@Tag("api")
@DisplayName("Информация о магазинах")
public class StoresTests extends TestBase {

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
            assertThat(skuResponse.code()).isEqualTo(SKU_VODKA_ARKHANGELSKAYA);
            assertThat(skuResponse.name()).isEqualTo(NAME_VODKA_ARKHANGELSKAYA);
        });
    }


    @Test
    @DisplayName("Успешное получение данных о магазине номер " + STORE_CODE)
    @Severity(SeverityLevel.BLOCKER)
    public void getStoreTest() {
        StoreResponse storeResponse = step("Успешный запрос данных магазина " + STORE_CODE, () -> given(requestSpec)
                .get("/api/v1/stores/" + STORE_CODE)
                .then()
                .spec(successResponse)
                .extract().as(StoreResponse.class));

        step("Код, адрес и город совпадают", () ->
        {
            assertThat(storeResponse.id()).isEqualTo(STORE_CODE);
            assertThat(storeResponse.address()).isEqualTo(STORE_ADDRESS);
            assertThat(storeResponse.cityName()).isEqualTo(STORE_CITY);
        });

    }


    @Test
    @DisplayName("Информация о магазинах получена")
    @Severity(SeverityLevel.BLOCKER)
    public void getListStoresTest() {
        StoresResponse response = step("", () -> given().log().all()
                .get("/api/v1/stores")
                .then()
                .log().all()
                .statusCode(200).extract().as(StoresResponse.class));
    }


    @Test
    public void catalogItemsTest() {
        Map<String, String> headers = Map.of(
                "Content-Type", "application/json",
                "DeviceID", "ff86f8dd-7154-f591-199c-19fecb138aac",
                "SessionToken", "3849FACA09F05B077ADF56894288E40A1",
                "X-Domain", "moscow",
                "X-Organization-ID", "", // Пустое значение
                "X-Platform", "omniweb",
                "X-Retail-Brand", "lo"
        );


        Sort sort = new Sort("popular", "desc");
        Filters filters = new Filters(List.of(), List.of(), List.of());
        CatalogItemsResponse searchRequest = new CatalogItemsResponse(null, "665629", 3, 0, sort, filters);

        // {"categoryId":null,"query":"665629","limit":40,"offset":0,"sort":{"type":"popular","order":"desc"},"filters":{"range":[],"checkbox":[],"multicheckbox":[]}}
        // {"categoryId":null,"query":"хлеб","limit":3,"offset":0,"sort":{"type":"popular","order":"desc"},"filters":{"range":[],"checkbox":[],"multicheckbox":[]}}

        given().log().all()
                .headers(headers)
                .when().body(searchRequest)
                .post("https://lenta.com/api-gateway/v1/catalog/items")
                .then()
                .log().all()
                .statusCode(200);

    }


    @Test
    public void deliveryModeGet() {
        // {"method":"deliveryModeGet","params":{},"jsonrpc":"2.0","id":"deliveryModeGet_1daa977f820b6"}

        DeliveryRequest deliveryRequest = new DeliveryRequest("deliveryModeGet", null, "2.0", "deliveryModeGet_1daa977f820b6");

        Delivery response = step("Make request", () -> given(requestSpec)
                .when().body(deliveryRequest)
                .post("/jrpc/deliveryModeGet")
                .then()
                .spec(successResponse).extract().as(Delivery.class));

        step("Verify", () -> {
            assertThat(response.jsonrpc()).isEqualTo("2.0");
            assertThat(response.result().sessionToken()).isEqualTo("3849FACA09F05B077ADF56894288E40A1");
        });

    }


    @Test
    public void catalogSearchTest() {
        given().log().all().header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36")
                .when().body("{    \"StoreIds\": [\"0177\"]\n" +
                        "}")
                .get("https://lenta.com/api/v1/stores/0012/catalog/search/?value=хлеб")
                .then()
                .log().all()
                .statusCode(200)
                .body("code", is("354331"));
    }

    @Test
    public void registrationTest() {
        given().log().all().header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36")
                .when().body(" {  \"phoneNumber\": \"7953")
                .post("https://lenta.com/api/v1/Registration/RequestUserStatus")
                .then()
                .log().all()
                .statusCode(200)
                .body("code", is("354331"));
    }

    @Test
    public void loginotpTest() {
        given().log().all().header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36")
                .when().body(" {  \"phoneNumber\": \"7953500675\"}")
                .post("https://lenta.com/api/v1/authentication/loginotp")
                .then()
                .log().all()
                .statusCode(200)
                .body("code", is("354331"));
    }


}
