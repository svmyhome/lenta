package tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import models.api.deliveryModeGet.DeliveryRequest;
import models.api.deliveryModeGet.response.Delivery;
import models.api.stores.StoreResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static models.api.ApiConstants.STORE_ADDRESS;
import static models.api.ApiConstants.STORE_CITY;
import static models.api.ApiConstants.STORE_CODE;
import static models.api.ApiConstants.STORE_COUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.StoresSpecs.requestSpec;
import static specs.StoresSpecs.successResponse;


@Slf4j
@Feature("Магазины")
@Story("Пользователь может получить информацию о магазинах")
@Owner("Sarychev")
@Tag("api")
@DisplayName("Информация о магазинах")
public class StoreTests extends TestBase {

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
    @DisplayName("Успешное получение данных о всех магазинах")
    @Severity(SeverityLevel.CRITICAL)
    public void getListStoresTest() {
        Response response = step("Успешный запрос данных о всех магазинах", () -> given(requestSpec)
                .get("/api/v1/stores")
                .then()
                .spec(successResponse)
                .extract().response());

        step("Количество магазинов 662", () -> {
            assertThat(response.jsonPath().getList("id").size()).isEqualTo(STORE_COUNT);
        });
    }

    @Test
    @DisplayName("Успешное получение информации о доставке")
    @Severity(SeverityLevel.TRIVIAL)
    public void deliveryModeGet() {
        DeliveryRequest deliveryRequest = new DeliveryRequest("deliveryModeGet", null, "2.0", "deliveryModeGet_1daa977f820b6");

        Delivery response = step("Успешный запрос типом Доставка", () -> given(requestSpec)
                .when().body(deliveryRequest)
                .post("/jrpc/deliveryModeGet")
                .then()
                .spec(successResponse)
                .extract().as(Delivery.class));

        step("Версия и токен получены", () -> {
            assertThat(response.jsonrpc()).isEqualTo("2.0");
            assertThat(response.result().sessionToken()).isEqualTo("3849FACA09F05B077ADF56894288E40A1");
        });
    }

}
