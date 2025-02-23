package tests.api;

import common.helpers.ApiHelper;
import io.qameta.allure.Feature;
import io.qameta.allure.Layer;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import api.models.stores.delivery.DeliveryRequest;
import api.models.stores.delivery.response.DeliveryModeResponse;
import api.models.stores.store.StoreResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static api.models.ApiConstants.DELIVERY_MODE_GET;
import static api.models.ApiConstants.STORES;
import static api.models.ApiConstants.STORE_ADDRESS_LENINA;
import static api.models.ApiConstants.STORE_CITY;
import static api.models.ApiConstants.STORE_CODE;
import static api.models.ApiConstants.STORE_COUNT;
import static api.specifications.ApiSpecifications.requestSpecification;
import static api.specifications.ApiSpecifications.statusCode200ResponseSpecification;

@Layer("rest")
@Slf4j
@Feature("Магазины")
@Story("Неавторизованный пользователь может получить информацию о магазинах")
@Owner("Sarychev")
@Tag("api")
@DisplayName("Информация о магазинах")
public class StoreTests extends TestBase {
    final ApiHelper api = new ApiHelper();

    @Test
    @DisplayName("Успешное получение данных о магазине номер " + STORE_CODE)
    @Severity(SeverityLevel.BLOCKER)
    public void getStoreTest() {
        StoreResponse storeResponse = step("Успешный запрос данных магазина " + STORE_CODE, () ->
                given(requestSpecification)
                        .get(STORES + STORE_CODE)
                        .then()
                        .spec(statusCode200ResponseSpecification)
                        .extract().as(StoreResponse.class));

        step("Код, адрес и город совпадают", () ->
        {
            api.assertValues(storeResponse.id(), STORE_CODE)
                    .assertValues(storeResponse.address(), STORE_ADDRESS_LENINA)
                    .assertValues(storeResponse.cityName(), (STORE_CITY));
        });

    }

    @Test
    @DisplayName("Успешное получение данных о всех магазинах")
    @Severity(SeverityLevel.CRITICAL)
    public void getListStoresTest() {
        Response response = step("Успешный запрос данных о всех магазинах", () ->
                given(requestSpecification)
                        .get(STORES)
                        .then()
                        .spec(statusCode200ResponseSpecification)
                        .extract().response());

        step("Количество магазинов " + STORE_COUNT, () -> {
            api.assertListSize(response, "id", STORE_COUNT);
        });
    }

    @Test
    @DisplayName("Успешное получение информации о доставке")
    @Severity(SeverityLevel.TRIVIAL)
    public void getDeliveryModeTest() {
        DeliveryRequest deliveryRequest = new DeliveryRequest("deliveryModeGet", null, "2.0", "deliveryModeGet_1daa977f820b6");

        DeliveryModeResponse response = step("Успешный запрос типом Доставка", () ->
                given(requestSpecification)
                        .when().body(deliveryRequest)
                        .post(DELIVERY_MODE_GET)
                        .then()
                        .spec(statusCode200ResponseSpecification)
                        .extract().as(DeliveryModeResponse.class));

        step("Версия и токен получены", () -> {
            api.assertValues(response.jsonrpc(), "2.0")
                    .assertValues(response.result().sessionToken(), "3849FACA09F05B077ADF56894288E40A1");
        });
    }

}
